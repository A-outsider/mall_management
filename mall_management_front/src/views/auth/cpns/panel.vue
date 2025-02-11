<script setup lang="ts">

import { ref, watch } from 'vue';

import panelAccount from './panel-account.vue';
import panelPhone from './panel-phone.vue';
import { localCache } from '@/utils/cache/cache';
import { User_REM_PWD } from '@/utils/cache/keys';

// 激活的面板
const activeName = ref('account')

// 记住密码
const isRemPwd = ref<boolean>(localCache.getCache(User_REM_PWD) ?? false) // 缓存用户行为
watch(isRemPwd, (newValue) => localCache.setCache(User_REM_PWD, newValue))

// 获取子组件
const accountRef = ref<InstanceType<typeof panelAccount>>()
const phoneRef = ref<InstanceType<typeof panelPhone>>()

// 登录逻辑
const OnLoginBtn = () => {

  // 逻辑调用
  if (activeName.value === 'account') accountRef.value?.LoginByAccount(isRemPwd.value)
  else phoneRef.value?.LoginByPhone()

}


</script>



<template>
  <div class="panel">
    <h1 class="title">后台管理系统</h1>
    <div class="tabs">
      <el-tabs type="border-card" stretch v-model="activeName">
        <el-tab-pane e label="帐号登录" name="account">
          <template #label>

            <div class="label">
              <el-icon>
                <UserFilled />
              </el-icon>

              <span class="text">帐号登录</span>
            </div>

          </template>

          <panel-account ref="accountRef" />

        </el-tab-pane>

        <el-tab-pane label="手机登录" name="phone">
          <template #label>
            <div class="label">
              <el-icon>
                <Iphone />
              </el-icon>

              <span class="text">手机登录</span>
            </div>
          </template>

          <panel-phone ref="phoneRef" />
        </el-tab-pane>

      </el-tabs>

    </div>
    <div class="controls">
      <el-checkbox label="记住密码" v-model="isRemPwd" />
      <el-link type="primary">忘记密码</el-link>
    </div>

    <el-button class="login-btn" type="primary" size="large" @click="OnLoginBtn">立即登录</el-button>


  </div>
</template>



<style lang="less" scoped>
.panel {
  width: 330px;
  margin-bottom: 150px;

  .title {
    text-align: center;
  }

  .tabs .label {
    display: flex;
    justify-content: center;
    align-items: center;

    .text {
      margin-left: 5px;
    }
  }

  .controls {
    display: flex;
    justify-content: space-between;
  }

  .login-btn {
    margin-top: 30px;
    width: 100%;
  }

}
</style>
