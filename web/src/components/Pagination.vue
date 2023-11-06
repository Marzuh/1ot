<script setup>
import {
  ChevronDoubleLeftIcon,
  ChevronDoubleRightIcon,
  ChevronLeftIcon,
  ChevronRightIcon,
} from '@heroicons/vue/20/solid';
import { computed } from 'vue';
import { generatePageNumbers } from "../utils/generatePaginationNumbers.ts";

defineEmits(['update:pageNumber']);

const props = defineProps({
  pageNumber: {
    type: Number,
    required: true,
    validator: (value) => {
      return value >= 1;
    },
  },
  totalPages: {
    type: Number,
    required: true,
    validator: (value) => {
      return value >= 0;
    },
  },
  pageSize: {
    type: Number,
    required: true,
    validator: (value) => {
      return value >= 1;
    },
  },
  totalElements: {
    type: Number,
    required: true,
    validator: (value) => {
      return value >= 0;
    },
  },
});

const isFirstPage = computed(() => props.pageNumber === 1);
const isLastPage = computed(() => props.pageNumber === props.totalPages);
const numberOfElements = computed(() =>
    props.pageNumber < props.totalPages ? props.pageSize : props.totalElements - (props.totalPages - 1) * props.pageSize,
);
const elementsFrom = computed(() => (props.pageNumber - 1) * props.pageSize + 1);
const elementsTo = computed(() => elementsFrom.value + numberOfElements.value - 1);
const showFastForward = computed(() => props.totalPages - props.pageNumber > 20);
const showFastBackward = computed(() => props.pageNumber > 20);
const pageButtons = computed(() => generatePageNumbers(props.pageNumber, props.totalPages));
</script>

<template>
  <div class="bg-white px-4 py-3 flex items-center justify-between border-t border-gray-200 sm:px-6 sm:rounded-b-md">
    <div class="flex-1 flex justify-between sm:hidden">
      <button
          :disabled="isFirstPage"
          class="relative inline-flex items-center px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white disabled:opacity-50 hover:bg-gray-50"
          @[!isFirstPage&&`click`]="$emit('update:pageNumber', pageNumber - 1)"
      >
        Previous
      </button>
      <button
          :disabled="isLastPage"
          class="ml-3 relative inline-flex items-center px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white disabled:opacity-50 hover:bg-gray-50"
          @[!isLastPage&&`click`]="$emit('update:pageNumber', pageNumber + 1)"
      >
        Next
      </button>
    </div>
    <div class="hidden sm:flex-1 sm:flex sm:items-center sm:justify-between">
      <div class="flex-1">
        <p v-if="totalElements" class="text-sm text-gray-700">
          Showing
          {{ ' ' }}
          <span class="font-medium">{{ elementsFrom }}</span>
          {{ ' ' }}
          to
          {{ ' ' }}
          <span class="font-medium">{{ elementsTo }}</span>
          {{ ' ' }}
          of
          {{ ' ' }}
          <span class="font-medium">{{ totalElements }}</span>
          {{ ' ' }}
          results
        </p>
        <p v-else class="text-sm text-center text-gray-700">No results found</p>
      </div>
      <div v-if="totalElements !== 0">
        <nav class="relative z-0 inline-flex rounded-md shadow-sm -space-x-px" aria-label="Pagination">
          <a
              v-show="showFastBackward"
              href="#"
              class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
              @click="$emit('update:pageNumber', pageNumber - 10)"
          >
            <span class="sr-only">Previous</span>
            <ChevronDoubleLeftIcon class="h-5 w-5" aria-hidden="true" />
          </a>
          <a
              v-show="!isFirstPage"
              href="#"
              class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
              @click="$emit('update:pageNumber', pageNumber - 1)"
          >
            <span class="sr-only">Previous</span>
            <ChevronLeftIcon class="h-5 w-5" aria-hidden="true" />
          </a>
          <a
              v-for="n in pageButtons"
              :key="n"
              href="#"
              :aria-current="n === pageNumber ? 'page' : null"
              class="bg-white border-gray-300 text-gray-500 hover:bg-gray-50 hidden md:inline-flex relative items-center px-4 py-2 border text-sm font-medium"
              :class="{ activePage: n === pageNumber, 'disabled-link': n === '...' }"
              @click="$emit('update:pageNumber', n)"
          >
            {{ n }}
          </a>
          <a
              v-show="!isLastPage"
              href="#"
              class="relative inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
              @click="$emit('update:pageNumber', pageNumber + 1)"
          >
            <span class="sr-only">Next</span>
            <ChevronRightIcon class="h-5 w-5" aria-hidden="true" />
          </a>
          <a
              v-show="showFastForward"
              href="#"
              class="relative inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
              @click="$emit('update:pageNumber', pageNumber + 10)"
          >
            <span class="sr-only">Next</span>
            <ChevronDoubleRightIcon class="h-5 w-5" aria-hidden="true" />
          </a>
        </nav>
      </div>
    </div>
  </div>
</template>



<!--suppress CssInvalidAtRule -->
<style>
.activePage {
  @apply z-10 bg-green-50 border-green-500 text-green-600;
}

.disabled-link {
  pointer-events: none;
}
</style>