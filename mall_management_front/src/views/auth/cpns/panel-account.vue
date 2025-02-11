<script setup lang="ts">
import { LoginByPwdSvc } from '@/service/modules/users/auth';
import type { ILoginByPwdRequest } from '@/service/modules/users/types';
import useLoginStore from '@/stores/auth/login';
import { localCache } from '@/utils/cache/cache';
import { Account_PWD, Account_USER } from '@/utils/cache/keys';
import { ElMessage, type ElForm, type FormRules } from 'element-plus';
import { reactive, ref } from 'vue';


const account = reactive<ILoginByPwdRequest>({
  name: localCache.getCache(Account_USER) ?? '',
  password: localCache.getCache(Account_PWD) ?? '',
})

// 校验规则
const accountRules: FormRules = {
  name: [
    { required: true, message: '请输入帐号', trigger: 'blur' },
    { pattern: /^[a-z0-9]{4,20}$/, message: '必须是4-20位的字母或数字', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { pattern: /^[a-z0-9]{3,}$/, message: '必须是3位以上的字母或数字', trigger: 'blur' }
  ]
}

// 账号登录逻辑
const ruleFormRef = ref<InstanceType<typeof ElForm>>()

const LoginStorage = useLoginStore()

const LoginByAccount = (isRemPwd: boolean) => {
  // 校验规则
  ruleFormRef.value?.validate((valid) => {
    if (!valid) {
      ElMessage.error('请输入正确的格式 !')
      return
    }
  })

  // 登录
  LoginStorage.loginByPwdAction(account).then(() => {

    console.log('登录成功1')

    // 记住密码
    if (isRemPwd) {
      localCache.setCache(Account_USER, account.name)
      localCache.setCache(Account_PWD, account.password)
    } else {
      localCache.removeCache(Account_USER)
      localCache.removeCache(Account_PWD)
    }

  })

}

defineExpose({
  LoginByAccount
})

</script>



<template #default>
  <div class="panel-account">


    <el-form ref="ruleFormRef" label-width="70px" style="max-width: 600px" :model="account" status-icon
      :rules="accountRules" class="demo-ruleForm">

      <el-form-item label="帐号" prop="name" required>
        <el-input v-model="account.name" placeholder="输入账号" size="large" autofocus />
      </el-form-item>

      <el-form-item label="密码" prop="password" required>
        <el-input v-model="account.password" placeholder="输入密码" size="large" show-password />
      </el-form-item>

    </el-form>

  </div>
</template>



<style lang="less" scoped>
.panel-account {}
</style>
