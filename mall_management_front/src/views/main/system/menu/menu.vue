<template>
  <div class="menu">
    <page-content :content-config="contentConfig" />
  </div>
</template>

<script setup lang="ts" name="menu">
import PageContent from '@/components/page/page-content.vue'
import contentConfig from './config/content.config'
</script>

<style scoped>
.menu {}
</style>
