import '@/assets/css/index.less'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import 'normalize.css'
import './assets/css/index.less'
import elIcon from './global/el-icons'
import useDataListStore from './stores/common/comon'

import type { ILoginByPhoneRequest } from '@/service/modules/users/types'

const app = createApp(App)

// import 'element-plus/dist/index.css' // 引入全局样式
//或者使用插件引入  npm install vite-plugin-style-import consola -D

app.use(createPinia())
app.use(router)
app.use(elIcon) // 注册el 图标

// 获取列表数据
const listDataStore = useDataListStore()
listDataStore.fetchEntireDataAction()

app.mount('#app')
