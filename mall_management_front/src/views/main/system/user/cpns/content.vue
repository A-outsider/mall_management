<script setup lang="ts">
// // import { tableData } from '@/data/user-table';
// import { onMounted, ref } from 'vue';
// import userUserStore, { ShowDialogType, type IFormData } from '@/stores/user/user';
// import { GetPageListSvc } from '@/service/modules/system/system';
// import { userPath } from '@/service/request/types';
// import type { IGetUserResponse } from '@/service/modules/users/types';
// import userSystemStore from '@/stores/system/system';
// import usePermissions from '@/hooks/usePermissions';
// import { formatUTC } from '@/utils/format';
// import { storeToRefs } from 'pinia';

// // TODO 待封装 获取用户数据
// // 使用 ref 存储数据
// // .获取是否有对应的增删改查的权限
// const isCreate = usePermissions(`user:add`)
// const isDelete = usePermissions(`user:delete`)
// const isUpdate = usePermissions(`user:update`)
// const isQuery = usePermissions(`user:query`)


// const systemStore = userSystemStore()

// const fetchPageListData = (queryInfo: any = {}) => {
//   if (!isQuery) return

//   // 发送网络请求
//   systemStore.getPageListAction(userPath, queryInfo)
// }
// fetchPageListData()
// const { pageList, pageTotalCount } = storeToRefs(systemStore)
// const tableData = pageList

// // 分页逻辑
// const currentPage = ref(1)
// const pageSize = ref(pageTotalCount)
// const handleSizeChange = (val: number) => {
//   console.log(`每页 ${val} 条`);
// }
// const handleCurrentChange = (val: number) => {
//   console.log(`当前页: ${val}`);
// }

// // 处理创建 , 编辑和删除的按钮
// const userStore = userUserStore()

// const handleCreateBtnClick = () => {

//   userStore.resetFormData()   // 清空数据

//   userStore.changeDialogShow(ShowDialogType.Add)

// }

// const handleEditBtnClick = (data: IFormData) => {
//   userStore.changeDialogShow(ShowDialogType.Edit)

//   // 传递数据
//   userStore.setFormData(data)

//   // 刷新数据
// }
// const handleDeleteBtnClick = (id: number) => {
//   // 发送删除请求
//   systemStore.deletePageDataById(userPath, id)
// }
</script>



<template>

  <div class="content">
    <div class="header">
      <h3 class="title">用户列表</h3>

      <div class="create-btn">
        <el-button type="primary" @click="handleCreateBtnClick" v-if="isCreate"> 新建用户</el-button>
      </div>

    </div>
    <div class="table">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="40px" />
        <el-table-column type="index" label="序号" width="60px" />

        <el-table-column align="center" prop="name" label="用户名" width="180px" />
        <el-table-column align="center" prop="realName" label="真实姓名" width="100px" />
        <el-table-column align="center" prop="phone" label="手机号" width="150px" />
        <el-table-column align="center" prop="enable" label="状态" width="80px">
          <template #default="scope">
            <!-- 作用域插槽 -->
            <el-button plain disabled size="small" :type="scope.row.enabled ? 'success' : 'danger'">
              {{ scope.row.enabled ? '启用' : '禁用' }}
            </el-button>
          </template>
        </el-table-column>

        <el-table-column align="center" prop="createTime" label="创建时间">
          <template #default="scope">
            <span>{{ formatUTC(scope.row.createTime) }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" prop="updateTime" label="更新时间">
          <template #default="scope">
            <span>{{ formatUTC(scope.row.updateTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="160px">

          <template #default="scope">
            <el-button size="small" icon="Edit" text type="primary" v-if="isUpdate"
              @click="handleEditBtnClick(scope.row)">编辑</el-button>
            <el-button size="small" icon="Delete" text type="danger" v-if="isDelete"
              @click="handleDeleteBtnClick(scope.row.id)">删除</el-button>

          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination">

      <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 20, 30]"
        size="small" layout=" sizes, prev, pager, next, jumper ,total" :total="40" @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />

    </div>

  </div>
</template>



<style lang="less" scoped>
.content {
  margin-top: 20px;
  padding: 20px;
  background-color: #fff;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: flex-end; // 底部对齐
    margin: 0 10px;
    margin-bottom: 15px;
  }

  .pagination {
    display: flex;
    justify-content: flex-end;
    margin-top: 15px;
  }
}



.table {
  :deep(.el-table__cell) {
    padding: 12px 0;
  }

  .el-button {
    margin-left: 0;
  }
}
</style>
