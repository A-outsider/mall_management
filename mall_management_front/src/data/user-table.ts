interface TableItem {
  id: number
  username: string // 用户名
  realname: string // 真实姓名
  cellphone: string // 手机号
  enable: number // 状态
  roleId: number
  createAt: string // 创建时间
  updateAt: string // 更新时间
}

// 创建数据示例
export const tableData: TableItem[] = [
  {
    id: 1,
    username: 'zhangsan',
    realname: '张三',
    cellphone: '13800138000',
    enable: 0,
    roleId: 1,
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
  {
    id: 2,
    username: 'lisi',
    realname: '李四',
    cellphone: '13900139001',
    enable: 0,
    roleId: 2,
    createAt: '2024-11-25 09:15:00',
    updateAt: '2024-12-05 16:40:00',
  },
  {
    id: 3,
    username: 'wangwu',
    realname: '王五',
    cellphone: '13700137002',
    enable: 1,
    roleId: 2,
    createAt: '2024-10-20 11:30:45',
    updateAt: '2024-12-01 08:25:30',
  },
  {
    id: 4,
    username: 'zhaoliu',
    realname: '赵六',
    cellphone: '13600136003',
    enable: 0,
    roleId: 1,
    createAt: '2024-11-15 08:45:30',
    updateAt: '2024-12-06 12:15:20',
  },
  {
    id: 5,
    username: 'tianqi',
    realname: '田七',
    cellphone: '13500135004',
    enable: 1,
    roleId: 2,
    createAt: '2024-09-10 07:50:15',
    updateAt: '2024-11-30 18:35:45',
  },
]
