import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';


// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 7000,
    proxy: {
      '/api': {
        target: 'http://localhost:7001',
        changeOrigin: true,
      },
    },
  }
})