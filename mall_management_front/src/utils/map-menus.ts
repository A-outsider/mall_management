import type { RouteRecordRaw } from 'vue-router'

export let firstRouter: any = null

export function mapMenusToRoutes(userMenus: any[]) {
  // 1.1.读取ts文件中的路由信息
  const files: Record<string, any> = import.meta.glob('../router/main/**/*.ts', {
    eager: true, //马上加载结果
  })

  // 1.2.获取文件中的路由信息
  const localRoutes: RouteRecordRaw[] = []
  for (const key in files) {
    const modules = files[key].default
    localRoutes.push(modules)
  }

  const routes: RouteRecordRaw[] = []

  // 1.3. 根据菜单获取需要添加的动态路由
  for (const menu of userMenus)
    for (const submenu of menu.children) {
      const route = localRoutes.find((item) => item.path === submenu.url)
      if (route) routes.push(route)
      if (!firstRouter && route) firstRouter = route
    }

  return routes
}

export function mapPathToMenu(path: string, userMenus: any[]) {
  for (const menu of userMenus)
    for (const submenu of menu.children) {
      if (submenu.url === path) return submenu
    }

  return null
}

export function mapPathToBreadCrumbs(path: string, userMenus: any[]) {
  const breadCrumbs: any[] = []

  for (const menu of userMenus)
    for (const submenu of menu.children) {
      if (submenu.url === path) {
        breadCrumbs.push(
          { name: menu.name, path: menu.children[0].url }, // 默认让它跳转到第一个
          { name: submenu.name, path: submenu.url },
        )
        return breadCrumbs
      }
    }
  return breadCrumbs
}

/**
 * 从菜单映射到按钮的权限
 * @param menuList 菜单的列表
 * @returns 权限的数组(字符串数组)
 */
export function mapMenusToPermissions(menuList: any[]) {
  const permissions: string[] = []

  function recurseGetPermission(menus: any[]) {
    for (const item of menus) {
      if (item.type === 3) {
        permissions.push(item.permission)
      } else {
        recurseGetPermission(item.children ?? [])
      }
    }
  }
  recurseGetPermission(menuList)

  return permissions
}
