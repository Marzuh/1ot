<script setup lang="ts">
import {PlaceForecast} from "../../types/PlaceForecast.ts";

const props = defineProps<{
  placeForecast: PlaceForecast;
}>();
</script>

<template>
  <div class="flex flex-col mx-4 border-b-2">
    <div class="grid grid-cols-4 gap-4 bg-green-50 p-2">
      <div class="font-medium text-center">Place Name</div>
      <div class="font-medium text-center">Phenomenon</div>
      <div class="font-medium text-center">Temp Min</div>
      <div class="font-medium text-center">Temp Max</div>
    </div>
    <div class="grid grid-cols-4 gap-4 p-2">
      <div class="text-center">{{ placeForecast.pfPlaceName }}</div>
      <div class="text-center">{{ placeForecast.pfPhenomenon }}</div>
      <div class="text-center">{{ placeForecast.pfTempMin ?? '-' }}</div>
      <div class="text-center">{{ placeForecast.pfTempMax ?? '-' }}</div>
    </div>
  </div>
</template>