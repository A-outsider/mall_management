interface TableItem {
  id: number
  name: string
  parentId: number
  leader: string
  createAt: string
  updateAt: string
}

export const tableDepartmentData: TableItem[] = [
  {
    id: 1,
    name: '研发部',
    parentId: 0,
    leader: '张三',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
  {
    id: 2,
    name: '市场部',
    parentId: 0,
    leader: '李四',
    createAt: '2024-11-25 09:15:00',
    updateAt: '2024-12-05 16:40:00',
  },
  {
    id: 3,
    name: '测试部',
    parentId: 0,
    leader: '王五',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
  {
    id: 4,
    name: '运维部',
    parentId: 1,
    leader: '赵六',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
  {
    id: 5,
    name: '后勤部',
    parentId: 2,
    leader: '孙七',
    createAt: '2024-12-01 10:20:30',
    updateAt: '2024-12-07 14:50:10',
  },
]
