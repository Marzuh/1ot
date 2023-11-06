<script setup lang="ts">
const props = defineProps<{
  label: string;
}>();

</script>

<template>
  <button
      type="button"
      class="ml-4 px-4 py-2 bg-green-400 text-white rounded hover:bg-green-700 transition duration-300 rounded-full"
  >
    {{ props.label }}
  </button>
</template>