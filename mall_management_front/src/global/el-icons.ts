import type { App } from 'vue'

// 引入element-plus全局图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

function registerELIcon(app: App) {
  // app.component('ElIcon', ElIcon)
  for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
}

export default registerELIcon
