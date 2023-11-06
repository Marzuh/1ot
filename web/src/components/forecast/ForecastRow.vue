<script setup lang="ts">
import PlaceForecast from "../forecast/PlaceForecast.vue"
import {Forecast} from "../../types/Forecast.ts";
import {computed, ref} from "vue";

const props = defineProps<{
  forecast: Forecast;
}>();

const showDetails = ref(false);
const isPlaceForecast = computed(() => {
      return props.forecast.placeForecasts.length > 0;
    }
);

const toggleDetails = () => {
  showDetails.value = !showDetails.value;
};

</script>

<template>
  <tr @click="toggleDetails" class="hover:cursor-pointer">
    <td>{{ forecast.date }}</td>
    <td>{{ forecast.timeOfDay }}</td>
    <td>{{ forecast.phenomenon }}</td>
    <td>{{ forecast.tempMin ?? '-'}}</td>
    <td>{{ forecast.tempMax ?? '-'}}</td>
    <td>{{ forecast.textDescription }}</td>
    <td>{{ forecast.peipsi }}</td>
  </tr>
  <tr v-if="showDetails">
    <td v-if="isPlaceForecast" colspan="7">
      <PlaceForecast
          v-for="placeForecast in props.forecast.placeForecasts"
          :key="placeForecast.pfId"
          :place-forecast="placeForecast"
      />
    </td>
    <td v-else colspan="7">
      <div class="text-center">No place forecast.</div>
    </td>
  </tr>
</template>
