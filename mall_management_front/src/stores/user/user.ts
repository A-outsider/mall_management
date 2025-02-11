import { defineStore } from 'pinia'

export enum ShowDialogType { // 这里定义 0-不显示 1-新增 2-编辑
  None = 0,
  Add = 1,
  Edit = 2,
}

// 指定store的类型
interface IUserState {
  currentShowDialog: number

  formData: IFormData
}

export interface IFormData {
  username: string
  realname: string
  password: string
  cellphone: string
  roleId: number
  departmentId: string
}

const userUserStore = defineStore('user', {
  state: () => ({
    currentShowDialog: 0,

    // 初始化 formData 的结构
    formData: {
      username: '',
      realname: '',
      password: '',
      cellphone: '',
      roleId: 0,
      departmentId: '',
    },
  }),
  actions: {
    changeDialogShow(isShowDialog: number) {
      this.currentShowDialog = isShowDialog
    },

    resetFormData() {
      this.formData = {
        username: '',
        realname: '',
        password: '',
        cellphone: '',
        roleId: 0,
        departmentId: '',
      }
    },

    setFormData(data: IFormData) {
      this.formData = data
    },
  },
})

export default userUserStore
