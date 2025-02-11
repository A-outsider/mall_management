<script setup lang="ts">

import { tableDepartmentData } from '@/data/department-table';
import { tableRoleData } from '@/data/role-tables';
import usePermissions from '@/hooks/usePermissions';
import userSystemStore from '@/stores/system/system';
import { formatUTC } from '@/utils/format';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';

// 定义获取数据模型
interface IProps {
  contentConfig: {
    pageName: string,
    header: {
      title: string,
      btnTitle: string
    },
    propsList: any[]
  }
}
const props = defineProps<IProps>()

// 分页逻辑
const currentPage = ref(1)
const pageSize = ref(100)
const handleSizeChange = (val: number) => {
  console.log(`每页 ${val} 条`);
}
const handleCurrentChange = (val: number) => {
  console.log(`当前页: ${val}`);
}


// .获取是否有对应的增删改查的权限
const isCreate = usePermissions(`${props.contentConfig.pageName}:add`)
const isDelete = usePermissions(`${props.contentConfig.pageName}:delete`)
const isUpdate = usePermissions(`${props.contentConfig.pageName}:update`)
const isQuery = usePermissions(`${props.contentConfig.pageName}:query`)

// 获取列表数据
const systemStore = userSystemStore()

const fetchPageListData = (queryInfo: any = {}) => {
  if (!isQuery) return

  // 发送网络请求
  systemStore.getPageListAction(props.contentConfig.pageName, queryInfo)
}
defineExpose({ fetchPageListData })

// 调用获取数据
fetchPageListData()
const { pageList, pageTotalCount } = storeToRefs(systemStore)


// 定义创建,编辑事件处理
const emit = defineEmits(['newBtnClick', 'editBtnClick'])
const handleCreateBtnClick = () => { emit('newBtnClick') }
const handleEditBtnClick = (data: any) => { emit('editBtnClick', data) }

// 删除按钮
const handleDeleteBtnClick = (id: number) => {
  // 发送删除请求
  systemStore.deletePageDataById(props.contentConfig.pageName, id)
}

</script>



<template>

  <div class="content">
    <div class="header">
      <h3 class="title"> {{ contentConfig?.header?.title ?? '数据列表' }}</h3>

      <div class="create-btn">
        <el-button type="primary" @click="handleCreateBtnClick" v-if="isCreate"> {{ contentConfig?.header?.btnTitle ??
          '新建数据'
          }}</el-button>
        <user-modal />
      </div>

    </div>
    <div class="table">
      <el-table :data="pageList" style="width: 100%">

        <template v-for="item in contentConfig.propsList" :key="item.prop">
          <template v-if="item.type === 'timer'">
            <el-table-column align="center" v-bind="item">
              <template #default="scope">
                <span>{{ formatUTC(scope.row[item.prop]) }}</span>
              </template>
            </el-table-column>
          </template>

          <template v-else-if="item.type === 'handler'">
            <el-table-column align="center" v-bind="item">
              <template #default="scope">
                <el-button size="small" icon="Edit" text type="primary" v-if="isUpdate"
                  @click="handleEditBtnClick(scope.row)">编辑</el-button>
                <el-button size="small" icon="Delete" text type="danger" v-if="isDelete"
                  @click="handleDeleteBtnClick(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>

          </template>

          <!-- 自定义类型插槽 -->
          <template v-else-if="item.type === 'custom'">
            <el-table-column align="center" v-bind="item">
              <template #default="scope">
                <slot :name="item.slotName" v-bind="scope" :prop="item.prop"></slot>
              </template>
            </el-table-column>
          </template>

          <template v-else>
            <el-table-column align="center" v-bind="item" />
          </template>

        </template>

      </el-table>
    </div>


    <div class="pagination">

      <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 20, 30]"
        size="small" layout=" sizes, prev, pager, next, jumper ,total" :total="pageTotalCount"
        @size-change="handleSizeChange" @current-change="handleCurrentChange" />

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
