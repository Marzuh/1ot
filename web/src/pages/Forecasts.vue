<script setup lang="ts">
import {onMounted, reactive, Ref, ref, watch} from "vue";
import {findAllPlaces} from "../api/placeApi.ts";
import {Place} from "../types/Place.ts";
import Button from "../components/Button.vue";
import ForecastTable from "../components/forecast/ForecastTable.vue"
import {Forecast, TimeOfDay} from "../types/Forecast.ts";
import {searchForecasts} from "../api/forecastApi.ts";
import {FilterForm} from "../types/FilterForm.ts";
import Pagination from "../components/Pagination.vue";
import {PaginationData} from "../types/PaginationData.ts";

const places: Ref<Place[]> = ref([]);
const forecasts: Ref<Forecast[]> = ref([]);
const loaded: Ref<boolean> =  ref(false);
const fetchCities = async () => {
  const citiesResponse = await findAllPlaces()
  if (citiesResponse.error) {
    console.error("failed to fetch cities:", citiesResponse.error);
  }
  if (citiesResponse.data) {
    places.value = citiesResponse.data;
  }
}

const filterForm = reactive<FilterForm>({
  timeOfDay: '24H',
  date: "",
  place: "",
  pageSize: 3,
  pageNumber: 1,
})

const paginationData: PaginationData = reactive<PaginationData>({
  totalElements: 0,
  pageNumber: 1,
  numberOfElements: 0,
  pageSize: 3,
  totalPages: 0
});
function resetFiltersForm() {
  filterForm.date = "";
  filterForm.timeOfDay = '24H';
  filterForm.place = "";
  filterForm.pageNumber = 1
}

const searchForecast = async () => {
  if (!loaded.value) {
    loaded.value = true;
  }
  const searchResponse = await searchForecasts(filterForm);
  if (searchResponse.error) {
    console.error("failed to fetch forecast search:", searchResponse.error);
  }
  if (searchResponse.data){
    forecasts.value = searchResponse.data.content;
    paginationData.pageSize = searchResponse.data.size;
    paginationData.pageNumber = searchResponse.data.number + 1;
    paginationData.numberOfElements = searchResponse.data.numberOfElements;
    paginationData.totalPages = searchResponse.data.totalPages;
    paginationData.totalElements = searchResponse.data.totalElements;
  }

}

watch(
    () => paginationData.pageNumber,
    async (newPageNumber: number, oldPageNumber: number) => {
      filterForm.pageNumber = newPageNumber;
      await searchForecast();
    },
);

onMounted(() => {
  resetFiltersForm();
  fetchCities();
})
</script>

<template>
  <div class="p-8 mt-4">
    <div class="flex justify-between mb-4">
      <div class="flex flex-col gap-4 items-center">
        <label for="date" class="font-semibold">Date<span class="font-medium text-gray-500 uppercase">(MM/DD/YYYY)</span> :</label>
        <input type="date" id="date" class="p-2 border rounded" v-model="filterForm.date" />
      </div>
      <div class="flex flex-col gap-4 items-center">
        <label for="place" class="font-semibold">Place:</label>
        <select v-model="filterForm.place" id="place" class="p-2 border rounded">
          <!-- Dynamically populate this dropdown with cities -->
          <option value="" disabled selected hidden> Please select place </option>
          <option v-for="place in places"
                  :key="place.name"
                  :value="place.name"
                  >{{ place.name }}</option>
        </select>
      </div>
      <div class="flex flex-col gap-4 items-center">
        <label for="timeOfDay" class="font-semibold">Time of day</label>
        <select
            v-model="filterForm.timeOfDay"
            id="timeOfDay"
            class="p-2 border rounded"
        >
          <option value="24H">24H</option>
          <option :value="TimeOfDay.DAY">Day</option>
          <option :value="TimeOfDay.NIGHT">Night</option>
        </select>
      </div>
      <div class="flex gap-4 item-center p-4">
        <Button label="Reset" @click="resetFiltersForm"></Button>
        <Button label="Search" @click="searchForecast"></Button>
      </div>
    </div>

    <div>
      <ForecastTable :forecasts="forecasts" :loaded="loaded"></ForecastTable>
    </div>
    <div>
      <Pagination v-if="loaded"
          v-model:page-number="paginationData.pageNumber"
          :total-pages="paginationData.totalPages"
          :total-elements="paginationData.totalElements"
          :page-size="paginationData.pageSize"
      />
    </div>
  </div>
</template>
