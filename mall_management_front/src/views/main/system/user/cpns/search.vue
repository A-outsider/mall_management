<script setup lang="ts">
// import type { ElForm } from 'element-plus';
// import { reactive, ref } from 'vue';


// const searchForm = reactive({
//   username: '',
//   realname: '',
//   cellphone: '',
//   enable: '',
//   createAt: ''
// })

// const formRef = ref<InstanceType<typeof ElForm>>()

// const handleResetClick = () => {
//   formRef.value?.resetFields()
// }

// const handleSearchClick = () => {
//   // 发送网络请求
//   // 刷新数据
// }

</script>



<template>
  <div class="search">

    <el-form :model="searchForm" ref="formRef" label-width="80px" size="large">

      <el-row :gutter="100">
        <el-col :span="8">
          <el-form-item label="用户名" prop="username">
            <el-input placeholder="请输入用户名" v-model="searchForm.username" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="真实姓名" prop="realname">
            <el-input placeholder="请输入真实姓名" v-model="searchForm.realname" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="电话号码" prop="cellphone">
            <el-input placeholder="请输入电话号码" v-model="searchForm.cellphone" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="状态" prop="enable">
            <el-select placeholder="请选择状态" v-model="searchForm.enable" style="width: 100%">
              <el-option label="启用" value="1" />
              <el-option label="禁用" value="0" />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="创建时间" prop="createAt">
            <el-date-picker type="daterange" range-separator="-" start-placeholder="开始时间" end-placeholder="结束时间"
              v-model="searchForm.createAt" style="width: 100%" />
          </el-form-item>
        </el-col>

      </el-row>

    </el-form>

    <div class="btns">
      <el-button icon="Refresh" @click="handleResetClick">重置</el-button>
      <el-button icon="Search" type="primary" @click="handleSearchClick">查询</el-button>
    </div>

  </div>
</template>



<style lang="less" scoped>
.search {
  background-color: #fff;
  padding: 20px;

  .btns {
    text-align: right;
    padding: 0 50px 10px 0;
  }
}
</style>
