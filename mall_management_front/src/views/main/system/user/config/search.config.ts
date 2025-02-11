// search.config.ts
const searchConfig = {
  pageName: 'user',
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '用户名',
      placeholder: '请输入用户名',
    },
    {
      type: 'input',
      prop: 'realName',
      label: '真实姓名',
      placeholder: '请输入真实姓名',
    },
    {
      type: 'input',
      prop: 'phone',
      label: '手机号码',
      placeholder: '请输入手机号码',
    },
    {
      type: 'date-picker',
      prop: 'createAt',
      label: '创建时间',
      placeholder: '请选择创建时间范围',
      range: true,
    },
  ],
  actions: [
    {
      type: 'button',
      label: '重置',
      action: 'reset',
      icon: 'Refresh',
    },
    {
      type: 'button',
      label: '查询',
      action: 'search',
      icon: 'Search',
      primary: true,
    },
  ],
}

export default searchConfig
