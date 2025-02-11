import router from '@/router'
import { LoginByPwdSvc } from '@/service/modules/users/auth'
import type { ILoginByPwdRequest } from '@/service/modules/users/types'
import { localCache } from '@/utils/cache/cache'
import { Account_TOKEN, User_DATA__MENU, User_DATA_PERMISSION } from '@/utils/cache/keys'
import { defineStore } from 'pinia'
import { mapMenusToPermissions, mapMenusToRoutes } from '@/utils/map-menus'
import { GetPageListSvc } from '@/service/modules/system/system'
import { MenuPath } from '@/service/request/types'
import type { IGetMenuResp } from '../system/menu/types'
import { ElMessage } from 'element-plus'
import useDataListStore from '../common/comon'

// 指定store的类型
interface ILoginState {
  token: string
  userInfo: any
  userMenus: any
  permissions: string[]
}

const useLoginStore = defineStore('login', {
  state: (): ILoginState => ({
    token: localCache.getCache(Account_TOKEN) ?? '',
    userInfo: {},
    userMenus: [],
    permissions: localCache.getCache(User_DATA_PERMISSION) ?? [],
  }),

  actions: {
    async loginByPwdAction(account: ILoginByPwdRequest) {
      const loginResult = await LoginByPwdSvc(account)
      // 处理请求结果

      // token缓存
      this.token = loginResult.data.token
      localCache.setCache(Account_TOKEN, loginResult.data.token)

      // 获取用户信息
      const menuItems = await GetPageListSvc<IGetMenuResp[]>(MenuPath)

      // 对获取的用户数据进行缓存
      localCache.setCache(User_DATA__MENU, menuItems.data)
      this.userMenus = localCache.getCache(User_DATA__MENU)

      // 获取用户权限 , 先存缓存吧
      const permissions = mapMenusToPermissions(menuItems.data)
      localCache.setCache(User_DATA_PERMISSION, permissions)
      this.permissions = permissions

      // 动态添加路由
      const routes = mapMenusToRoutes(this.userMenus)
      routes.forEach((route) => router.addRoute('main', route))

      // 获取列表数据
      const listDataStore = useDataListStore()
      listDataStore.fetchEntireDataAction()

      // 页面跳转
      router.push('/main')

      return loginResult
    },
  },
})

export default useLoginStore
