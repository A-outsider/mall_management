// 获取菜单列表
export interface getMenuListReq {}

export interface IGetMenuResp {
  id: number
  name: string
  url: string
  icon?: string
  type: number
  parentId: number
  sort?: number
  permission?: string
  children?: IGetMenuResp[]
}
