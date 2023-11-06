<script setup lang="ts">
import Navbar from "../src/components/Navbar.vue"
</script>


<template>
  <div class="flex flex-col h-screen overflow-hidden">

    <!-- Navbar -->
      <Navbar />

    <!-- Content -->
    <div class="flex-1 overflow-auto">
      <router-view />
    </div>

  </div>
</template>