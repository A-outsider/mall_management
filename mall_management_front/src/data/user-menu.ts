interface MenuItem {
  id: number
  name: string
  type: number
  url: string
  icon?: string
  sort?: number
  permission?: string
  children?: MenuItem[]
}

// 定义类型
class MenuItem {
  id: number
  name: string
  type: number
  url: string
  icon?: string
  sort?: number
  children?: MenuItem[]
  permission?: string

  constructor(
    id: number,
    name: string,
    type: number,
    url: string,
    icon: string = '',
    sort: number = 0,
    children: MenuItem[] = [],
    permission: string = '',
  ) {
    this.id = id
    this.name = name
    this.type = type
    this.url = url
    this.icon = icon
    this.permission = permission
    this.sort = sort
    this.children = children
  }
}

// 创建实例
export const menuItems: MenuItem[] = [
  new MenuItem(1, '系统总览', 1, '/main/analysis', 'monitor', 1, [
    { id: 2, name: '核心技术', type: 2, url: '/main/analysis/overview' },
    { id: 3, name: '商品统计', type: 2, url: '/main/analysis/dashboard' },
  ]),
  new MenuItem(11, '系统管理', 1, '/main/system', 'setting', 2, [
    { id: 12, name: '用户管理', type: 2, url: '/main/system/user' },
    { id: 13, name: '部门管理', type: 2, url: '/main/system/department' },
    { id: 14, name: '菜单管理', type: 2, url: '/main/system/menu' },
    { id: 15, name: '角色管理', type: 2, url: '/main/system/role' },
  ]),
  new MenuItem(21, '商品中心', 1, '/main/product', 'goods', 3, [
    { id: 22, name: '品类管理', type: 2, url: '/main/product/category' },
    { id: 23, name: '商品管理', type: 2, url: '/main/product/goods' },
    { id: 24, name: '品牌管理', type: 2, url: '/main/product/brand' },
  ]),
  new MenuItem(30, '随便聊聊', 1, '/main/story', 'chat-line-round', 4, []),
]
