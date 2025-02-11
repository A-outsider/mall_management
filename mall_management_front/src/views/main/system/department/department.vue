<script setup lang="ts">

import pageSearch from '@/components/page/page-search.vue';
import pageContent from '@/components/page/page-content.vue';
import pageModal from '@/components/page/page-modal.vue';

import searchConfig from './config/search.config';
import contentConfig from './config/content.config';
import modalConfig from './config/modal.config';

import { ref } from 'vue';

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

</script>



<template>
  <div class="department">
    <page-search :search-config="searchConfig" @query-click="handleQueryClick" @reset-click="handleResetClick" />
    <page-content :content-config="contentConfig" @edit-btn-click="handleEditBtnClick"
      @new-btn-click="handleNewBtnClick" ref="contentRef" />

    <page-modal :modal-config="modalConfig" ref="modalRef" />

  </div>
</template>



<style lang="less" scoped>
.department {}
</style>
