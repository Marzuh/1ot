<template>
  <nav class="bg-green-300 shadow-lg w-full">
    <div class="max-w-6xl mx-auto px-4">
      <div class="flex justify-between items-center">
        <div class="flex items-center py-4 px-2">
          <span class="font-semibold text-gray-500 text-lg">1oT Weather</span>
        </div>
        <div class="hidden md:flex items-center space-x-2">
          <router-link
              class="py-2 px-2 text-gray-500 font-semibold bg-green-100 hover:text-green-500 transition duration-300 rounded-xl"
              to="/"
          >
            Forecast
          </router-link>
          <router-link
              class="py-2 px-2 text-gray-500 font-semibold bg-green-100 hover:text-green-500 transition duration-300 rounded-xl"
              to="/about"
          >
            About
          </router-link>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
</script>