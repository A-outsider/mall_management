<script setup lang="ts">
import router from '@/router';
import { localCache } from '@/utils/cache/cache';
import { Account_USER } from '@/utils/cache/keys';

import mainMenu from '@/components/main-menu/main-menu.vue';
import mainHeader from '@/components/main-header/main-header.vue';
import { ref } from 'vue';

const OnLoginOutBtn = () => {
  // 清缓存
  localCache.removeCache(Account_USER)

  // 跳转
  router.push('/login')
}

// 处理折叠
const isCollapse = ref(false)
const handleFoldChange = (isFold: boolean) => {
  console.log(isFold)
  isCollapse.value = isFold
}

</script>



<template>
  <div class="main">

    <el-container class="main-container">
      <el-aside :width="isCollapse ? '64px' : '180px'">
        <main-menu :is-fold="isCollapse"></main-menu>
      </el-aside>

      <el-container>
        <el-header height="50px">
          <main-header @fold-change="handleFoldChange"></main-header>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>


    <!-- <button @click="OnLoginOutBtn">退出按钮</button> -->


  </div>
</template>



<style lang="less" scoped>
.main-container {
  height: 100vh;


  .el-aside {
    overflow-x: hidden;
    overflow-y: auto;
    line-height: 200px;
    text-align: left;
    cursor: pointer;
    background-color: #001529;
    scrollbar-width: none;
    /* firefox */
    -ms-overflow-style: none;
    /* IE 10+ */

    transition: width 0.3s ease;

    &::-webkit-scrollbar {
      display: none;
    }
  }

  .el-main {
    background-color: #f0f2f5;
  }
}
</style>
