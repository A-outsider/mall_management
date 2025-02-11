// 针对页面通用的网络请求 : 增删改查

import createSvcRequest from '@/service/request'
import type NetResponse from '@/service/request/response'

// 创建 common 实例
const commonSvc = createSvcRequest()

export function GetPageListSvc<T = any>(pagePath: string, queryInfo: any = {}) {
  return commonSvc.POST<NetResponse<T>>({
    url: `${pagePath}/list`,
    data: queryInfo,
  })
}

export function DeletePageByIdSvc<T = any>(pageName: string, id: number) {
  return commonSvc.DELETE<NetResponse<T>>({
    url: `${pageName}/${id}`,
  })
}

export function PostPageDataSvc<T = any>(pageName: string, newData: any) {
  return commonSvc.POST<NetResponse<T>>({
    url: `${pageName}`,
    data: newData,
  })
}

export function PutPageDataSvc<T = any>(pageName: string, editData: any, id: number) {
  return commonSvc.PUT<NetResponse<T>>({
    url: `${pageName}/${id}`,
    data: editData,
  })
}
