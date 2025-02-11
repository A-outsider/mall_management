import { GetPageListSvc } from '@/service/modules/system/system'
import type { IGetUserResponse } from '@/service/modules/users/types'
import { departMentPath, MenuPath, rolePath, userPath } from '@/service/request/types'
import { defineStore } from 'pinia'
import type { IGetMenuResp } from '../system/menu/types'
import type { IGetRoleResp } from '../system/role/types'
import type { IGetDepartMentResp } from '../system/department/types'
import { Account_TOKEN } from '@/utils/cache/keys'
import { localCache } from '@/utils/cache/cache'

interface IMainState {
  entireRoles: IGetRoleResp[]
  entireDepartments: IGetDepartMentResp[]
  entireMenus: IGetMenuResp[]
  entireUsers: IGetUserResponse[]
}

const useDataListStore = defineStore('listData', {
  state: (): IMainState => ({
    entireRoles: [],
    entireDepartments: [],
    entireMenus: [],
    entireUsers: [],
  }),
  actions: {
    async fetchEntireDataAction() {
      // 判断是否登录
      const token = localCache.getCache(Account_TOKEN)
      if (!token) return

      const rolesResult = await GetPageListSvc<IGetRoleResp[]>(rolePath)
      const departmentsResult = await GetPageListSvc<IGetDepartMentResp[]>(departMentPath)
      const menuResult = await GetPageListSvc<IGetMenuResp[]>(MenuPath)
      const usersResult = await GetPageListSvc<IGetUserResponse[]>(userPath)

      // 保存数据
      this.entireRoles = rolesResult.data
      this.entireDepartments = departmentsResult.data
      this.entireMenus = menuResult.data
      this.entireUsers = usersResult.data
    },
  },
})

export default useDataListStore
