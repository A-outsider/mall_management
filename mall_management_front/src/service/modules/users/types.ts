// 登录
export interface ILoginByPwdRequest {
  name: string
  password: string
}

export interface ILoginResponse {
  token: string
}

// 创建用户
export interface ICreateUserRequest {
  name: string
  realName: string
  password: string
  phone: string
  roleId: number
  departmentId: number
}

// 修改用户信息
export interface IUpdateUserRequest {
  name: string
  realName: string
  phone: string
  roleId: number
  departmentId: number
}

// 获取用户列表
export interface IGetUserListRequest {
  name?: string
  realName?: string
  phone?: string
  roleId?: number
  departmentId?: number
  enabled?: number
}

export interface IGetUserResponse {
  id: number
  name: string
  realName: string
  phone: string
  roleId: number
  roleName: string
  departmentId: number
  enabled: number
  createTime: string
  updateTime: string
}
