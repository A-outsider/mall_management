<script setup lang="ts">
import pageSearch from '@/components/page/page-search.vue';
import pageContent from '@/components/page/page-content.vue';
import pageModal from '@/components/page/page-modal.vue';
import { ref } from 'vue';

import searchConfig from './config/search.config';
import contentConfig from './config/content.config';
import modalConfig from './config/modal.config';

// 绑定模型
const contentRef = ref<InstanceType<typeof pageContent>>()
const modalRef = ref<InstanceType<typeof pageModal>>()

// 中转站 , 处理事件
const handleQueryClick = (queryInfo: any) => {
  contentRef.value?.fetchPageListData(queryInfo)
}

const handleResetClick = () => {
  contentRef.value?.fetchPageListData()
}

const handleEditBtnClick = (data: any) => {
  modalRef.value?.setModalVisible(false, data)
}

const handleNewBtnClick = () => {
  modalRef.value?.setModalVisible(true)
}

// 转化modalConfig 数据

// 处理菜单list点击
const otherInfo = ref<any>({})

const handleCheck = (data1: any, data2: any) => {
  const menuList = [...data2.checkedKeys, ...data2.halfCheckedKeys]
  otherInfo.value.menuList = { menuList }
}

</script>

<template>
  <div class="user">

    <!-- 搜索组件 -->
    <page-search :search-config="searchConfig" @query-click="handleQueryClick" @reset-click="handleResetClick" />

    <!-- 表格内容组件 -->
    <page-content :content-config="contentConfig" @edit-btn-click="handleEditBtnClick"
      @new-btn-click="handleNewBtnClick" ref="contentRef">
      <!-- 自定义插槽示例 -->
      <template #status="scope">
        <el-button plain disabled size="small" :type="scope.row.enabled ? 'success' : 'danger'">
          {{ scope.row.enabled ? '启用' : '禁用' }}
        </el-button>
      </template>
    </page-content>

    <!-- 弹窗组件 -->
    <page-modal :modal-config="modalConfig" :other-info="otherInfo" ref="modalRef">
      <template #menulist>
        <el-tree @check="handleCheck" show-checkbox node-key="id" :props="{ label: 'name', children: 'children' }" />
      </template>
    </page-modal>

  </div>
</template>

<style lang="less" scoped>
.user {
  padding: 20px;
}
</style>
