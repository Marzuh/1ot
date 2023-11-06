<script setup lang="ts">
import {Forecast} from "../../types/Forecast.ts";
import ForecastRow from "./ForecastRow.vue";
import {computed} from "vue";

const props = defineProps<{
  forecasts: Forecast[];
  loaded: boolean;
}>();

const isForecasts = computed(() => {
      return props.forecasts.length > 0;
    }
);
</script>

<template>
  <div class="overflow-x-auto">
    <table class="min-w-full divide-y divide-green-300">
      <thead class="bg-green-200">
      <tr>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Date
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Time Of Day
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Phenomenon
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Temp Min
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Temp Max
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Description
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-green-800 uppercase tracking-wider">
          Peipsi
        </th>
      </tr>
      </thead>
      <tbody class="bg-white divide-y-2 divide-green-300">
      <ForecastRow
          v-for="forecast in forecasts"
          :key="forecast.id"
          :forecast="forecast"
      />
      <tr v-if="loaded && !isForecasts">
        <td colspan="7" class="text-center">
          No forecasts for given date and/or place.
        </td>
      </tr>
      <tr v-if="!loaded">
        <td colspan="7" class="text-center">
          Please press Search button.
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>