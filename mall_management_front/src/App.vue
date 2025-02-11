<script setup lang="ts">
import { useRoute } from 'vue-router';
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
</script>



<template>
  <div class="app">
    <el-config-provider :locale="zhCn">
      <router-view></router-view>
    </el-config-provider>
  </div>
</template>



<style lang="less" scoped>
.app {
  width: 100wh;
  height: 100vh;
}
</style>
