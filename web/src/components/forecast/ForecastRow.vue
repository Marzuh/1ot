<script setup lang="ts">
import { ArrowRightIcon, ArrowDownIcon } from '@heroicons/vue/24/outline';
import PlaceForecast from "../forecast/PlaceForecast.vue";
import { Forecast } from "../../types/Forecast";
import { computed, ComputedRef, ref, Ref } from "vue";

const props = defineProps<{
  forecast: Forecast;
}>();

const showDetails: Ref<boolean> = ref(false);

const isPlaceForecast: ComputedRef<boolean> = computed(() => {
  return props.forecast.placeForecasts.length > 0;
});

const toggleDetails = () => {
  showDetails.value = !showDetails.value;
};
</script>

<template>
  <tr @click="toggleDetails" class="hover:cursor-pointer">
    <td>
      <template v-if="showDetails">
        <ArrowDownIcon class="h-5 w-5" />
      </template>
      <template v-else>
        <ArrowRightIcon class="h-5 w-5" />
      </template>
    </td>
    <td>{{ forecast.date }}</td>
    <td>{{ forecast.timeOfDay }}</td>
    <td>{{ forecast.phenomenon }}</td>
    <td>{{ forecast.tempMin ?? '-' }}</td>
    <td>{{ forecast.tempMax ?? '-' }}</td>
    <td>{{ forecast.textDescription }}</td>
    <td>{{ forecast.peipsi }}</td>
  </tr>
  <tr v-if="showDetails">
    <td v-if="isPlaceForecast" colspan="8">
      <PlaceForecast
          v-for="placeForecast in props.forecast.placeForecasts"
          :key="placeForecast.pfId"
          :place-forecast="placeForecast"
      />
    </td>
    <td v-else colspan="8">
      <div class="text-center">No place forecast.</div>
    </td>
  </tr>
</template>