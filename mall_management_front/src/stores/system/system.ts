import {
  DeletePageByIdSvc,
  GetPageListSvc,
  PostPageDataSvc,
  PutPageDataSvc,
} from '@/service/modules/system/system'
import { defineStore } from 'pinia'

interface ISystemState {
  pageList: any[]
  pageTotalCount: number
}

const userSystemStore = defineStore('system', {
  state: (): ISystemState => ({
    pageList: [],
    pageTotalCount: 0,
  }),
  actions: {
    async getPageListAction(pageName: string, queryInfo: any) {
      const pageResult = await GetPageListSvc<any[]>(pageName, queryInfo)
      this.pageList = pageResult.data
      this.pageTotalCount = pageResult.data.length
    },

    async deletePageDataById(pageName: string, id: number) {
      await DeletePageByIdSvc(pageName, id)
      this.getPageListAction(pageName, { offset: 0, size: 10 })
    },

    async createPageData(pageName: string, newData: any) {
      await PostPageDataSvc(pageName, newData)
      this.getPageListAction(pageName, { offset: 0, size: 10 })
    },

    async editPageData(pageName: string, id: number, editData: any) {
      await PutPageDataSvc(pageName, editData, id)
      this.getPageListAction(pageName, { offset: 0, size: 10 })
    },
  },
})

export default userSystemStore
