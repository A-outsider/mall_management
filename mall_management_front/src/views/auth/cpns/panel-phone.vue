<script setup lang="ts">
import type { ILoginByPhoneRequest } from '@/service/modules/users/types';
import type { ElForm } from 'element-plus';
import { reactive, ref } from 'vue';



const account = reactive<ILoginByPhoneRequest>({
  phone: '',
  captcha: '',
})


// 手机号登录逻辑
const ruleFormRef = ref<InstanceType<typeof ElForm>>()

const LoginByPhone = () => {
  console.log('登录')
}

defineExpose({
  LoginByPhone
})


</script>



<template #default>
  <div class="panel-phone">

    <el-form ref="ruleFormRef" label-width="70px" :model="account" status-icon class="demo-ruleForm">

      <el-form-item label="手机号" prop="phone" required>
        <el-input v-model="account.phone" placeholder="输入手机号" size="large" autofocus />
      </el-form-item>

      <el-form-item label="验证码" prop="captcha" required>
        <el-input v-model="account.captcha" placeholder="输入验证码" size="large" style="width: 140px" />
        <div class="captcha-btn"> <el-button type="primary" style="width: 80px"> 获取验证码 </el-button>
        </div>
      </el-form-item>

    </el-form>


  </div>
</template>



<style lang="less" scoped>
.panel-phone {

  .captcha-btn {
    margin-left: 5px;
    --el-font-size-base: 12px;
  }

}
</style>
