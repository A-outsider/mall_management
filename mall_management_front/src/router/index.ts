import useDataListStore from '@/stores/common/comon'
import { localCache } from '@/utils/cache/cache'
import { Account_TOKEN, User_DATA__MENU } from '@/utils/cache/keys'
import { firstRouter, mapMenusToRoutes } from '@/utils/map-menus'
import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/main',
    },
    {
      path: '/:pathMatch(.*)*',
      component: () => import('@/views/not-found/not-found.vue'),
    },
    {
      path: '/login',
      component: () => import('@/views/auth/auth.vue'),
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('@/views/main/main.vue'),
    },
  ],
})

// 挂载 main 的动态路由
const userMenus = localCache.getCache(User_DATA__MENU)
if (userMenus) {
  const routes = mapMenusToRoutes(userMenus)
  routes.forEach((route) => router.addRoute('main', route))
}

// 导航守卫
// 返回值是最终跳转结果 ,(不返回和undefined是默认跳转)
router.beforeEach((to, from) => {
  if (to.path === '/main' && !localCache.getCache(Account_TOKEN)) return '/login' // 跳转login页面

  if (to.path === '/main') return firstRouter?.path // 默认跳转第一个动态路由
})

export default router
