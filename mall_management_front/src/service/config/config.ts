// vite默认提供的环境变量
// console.log(import.meta.env.MODE)
// console.log(import.meta.env.DEV) // 是否开发环境
// console.log(import.meta.env.PROD) // 是否生产环境
// console.log(import.meta.env.SSR) // 是否是服务器端渲染(server side render)

let BASE_URL = ''
if (import.meta.env.PROD) {
  // 生产环境
  BASE_URL = 'http://47.115.160.54:38080'
} else {
  // 开发环境
  BASE_URL = 'http://127.0.0.1:8080'
}

// console.log(BASE_URL)

// 3.通过创建全局环境env文件直接创建变量    变量命名要求 : VITE_XXX

// .env 文件全环境可识别
// .env.development 文件开发环境可识别
// .env.production 文件生产环境可识别
// console.log(import.meta.env.VITE_APP_NAME)

export const TIME_OUT = 10000
export { BASE_URL }
