<script setup lang="ts">
import router from '@/router';
import { localCache } from '@/utils/cache/cache';
import { User_DATA__MENU } from '@/utils/cache/keys';
import { mapPathToMenu } from '@/utils/map-menus';
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';


// 获取用户菜单数据
const userMenus = localCache.getCache(User_DATA__MENU) ?? []

const handleItemClick = (item: any) => {
  const url = item.url
  router.push(url)
}

// 默认展开菜单
defineProps({ isFold: { type: Boolean, default: false } })
const route = useRoute()

const defaultActive = computed(() => {  // 使用computed变向监听route.path属性
  const pathMenu = mapPathToMenu(route.path, userMenus) // 获取当前页面的pathId
  return String(pathMenu.id)
})


</script>



<template>
  <div class="main-menu">

    <div class="logo">
      <img src="@/assets/img/logo.svg" alt="">
      <h2 class="title" v-show="!isFold">后台管理系统</h2>
    </div>

    <div class="menu">
      <el-menu :default-active="defaultActive" :collapse="isFold" text-color="#b7bdc3" active-text-color="#fff"
        background-color="#001529">
        <template v-for="item in userMenus" :key="item.id">

          <el-sub-menu :index="String(item.id)">

            <template #title>
              <el-icon>
                <component :is="item.icon"></component>
              </el-icon>
              <span>{{ item.name }}</span>
            </template>

            <template v-for="subitem in item.children" :key="subitem.id">
              <el-menu-item :index="String(subitem.id)" @click="handleItemClick(subitem)">
                {{ subitem.name }}

              </el-menu-item>
            </template>

          </el-sub-menu>
        </template>

      </el-menu>

    </div>


  </div>


</template>



<style lang="less" scoped>
.main-menu {

  .logo {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50px;

    img {
      width: 30px;
      height: 30px;
    }

    .title {
      color: #fff;
      font-size: 16px;
      line-height: 50px;
      margin-left: 10px;

      // 设置隐藏文字
      overflow: hidden;
      white-space: nowrap;
    }

  }

  .el-menu {
    border-right: none;
    user-select: none;

    .el-sub-menu {
      .el-menu-item {
        padding-left: 50px !important;
        background-color: #0c2135;
      }

      .el-menu-item:hover {
        color: #fff;
      }

      .el-menu-item.is-active {
        background-color: #0a60bd;
      }
    }
  }



}
</style>
