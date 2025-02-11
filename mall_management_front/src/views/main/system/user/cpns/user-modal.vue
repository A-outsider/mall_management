<script setup lang="ts">
// import { userPath } from '@/service/request/types';
// import userSystemStore from '@/stores/system/system';
// import userUserStore, { ShowDialogType } from '@/stores/user/user';
// import { storeToRefs } from 'pinia';
// import { computed, reactive, ref, watch } from 'vue';


// const userStore = userUserStore()

// let editId = ref<number>(0)

// // 处理数据绑定
// const { formData, currentShowDialog } = storeToRefs(userStore);

// // 处理弹窗
// const dialogVisible = computed({  // 使用computer属性实现双向绑定
//   get: () => userStore.currentShowDialog > 0,
//   set: (value) => {
//     if (!value) userStore.changeDialogShow(ShowDialogType.None);
//   },
// });


// const systemStore = userSystemStore()
// const handleConfirmClick = () => {

//   //  发送请求创建数据
//   if (currentShowDialog.value === ShowDialogType.Add) {
//     console.log(formData.value)
//     systemStore.createPageData(userPath, formData.value)
//   } else if (currentShowDialog.value === ShowDialogType.Edit) {
//     systemStore.editPageData(userPath, formData.value, formData.value)
//   }

//   // 关闭弹窗
//   userStore.changeDialogShow(ShowDialogType.None)

//   // 刷新拉取数据
//   console.log("发送获取数据请求")

//   // 清空状态
//   userStore.resetFormData()
// }

// const handleCancelClick = () => {
//   userStore.changeDialogShow(ShowDialogType.None)
// }


</script>


<template>
  <div class="user-modal">
    <el-dialog v-model="dialogVisible" center width="30%"
      :title="currentShowDialog === ShowDialogType.Add ? '新建用户' : '编辑用户'">
      <div class="form">
        <el-form label-width="70px" :model="formData" size="large">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="formData.username" placeholder="请输入用户名" />
          </el-form-item>

          <el-form-item label="真实姓名" prop="realname">
            <el-input v-model="formData.realname" placeholder="请输入真实姓名" />
          </el-form-item>

          <el-form-item label="密码" prop="password" v-show="currentShowDialog === ShowDialogType.Add">
            <el-input v-model="formData.password" placeholder="请输入密码" show-password />
          </el-form-item>

          <el-form-item label="手机号码" prop="cellphone">
            <el-input v-model="formData.cellphone" placeholder="请输入手机号码" />
          </el-form-item>

          <el-form-item label="选择角色" prop="roleId">
            <el-select v-model="formData.roleId" placeholder="请选择角色">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>

          <el-form-item label="选择部门" prop="departmentId">
            <el-select v-model="formData.departmentId" placeholder="请选择部门">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-form>

      </div>


      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleCancelClick">取消</el-button>
          <el-button type="primary" @click="handleConfirmClick">
            确认
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>



<style lang="less" scoped>
.user-modal {
  .form {
    padding: 0 50px;
  }
}
</style>
