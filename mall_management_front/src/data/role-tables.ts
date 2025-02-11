interface TableItem {
  id: number
  name: string
  intro: string
  createAt: string
  updateAt: string
}
export const tableRoleData: TableItem[] = [
  {
    id: 1,
    name: '超级管理员',
    intro: '拥有最高权限',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
  {
    id: 2,
    name: '管理员',
    intro: '拥有部分权限',
    createAt: '2024-11-25 09:15:00',
    updateAt: '2024-12-05 16:40:00',
  },
  {
    id: 3,
    name: '编辑',
    intro: '拥有部分权限',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
  {
    id: 4,
    name: '测试',
    intro: '拥有部分权限',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
]
