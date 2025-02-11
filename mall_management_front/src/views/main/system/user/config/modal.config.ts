// modal.config.ts
const modalConfig = {
  pageName: 'user',
  header: {
    newTitle: '新建用户',
    editTitle: '编辑用户',
  },
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
      prop: 'password',
      label: '密码',
      placeholder: '请输入密码',
      showPassword: true,
      required: true, // Only for add dialog
    },
    {
      type: 'input',
      prop: 'phone',
      label: '手机号码',
      placeholder: '请输入手机号码',
    },
    {
      type: 'select',
      prop: 'roleId',
      label: '角色',
      placeholder: '请选择角色',
      options: [
        { label: '管理员', value: 1 },
        { label: '总经理', value: 2 },
        { label: '测试人员', value: 6 },
      ],
    },
    {
      type: 'select',
      prop: 'departmentId',
      label: '部门',
      placeholder: '请选择部门',
      options: [
        { label: '研发部', value: 1 },
        { label: '市场部', value: 2 },
        { label: '测试部', value: 3 },
        { label: '运维部', value: 4 },
        { label: '后勤部', value: 5 },
        { label: '人事部', value: 6 },
      ],
    },
  ],
}

export default modalConfig
