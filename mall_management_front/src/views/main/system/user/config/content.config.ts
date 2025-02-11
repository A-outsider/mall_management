// content.config.ts
const contentConfig = {
  pageName: 'user',
  header: {
    title: '用户列表',
    btnTitle: '新建用户',
  },
  propsList: [
    { type: 'selection', label: '选择', width: '80px' },
    { type: 'index', label: '序号', width: '80px' },
    { type: 'normal', label: '用户名', prop: 'name', width: '180px' },
    { type: 'normal', label: '真实姓名', prop: 'realName', width: '100px' },
    { type: 'normal', label: '手机号', prop: 'phone', width: '150px' },
    { type: 'custom', label: '状态', prop: 'enabled', width: '80px', slotName: 'status' },
    { type: 'timer', label: '创建时间', prop: 'createTime' },
    { type: 'timer', label: '更新时间', prop: 'updateTime' },
    { type: 'handler', label: '操作', width: '150px' },
  ],
}

export default contentConfig
