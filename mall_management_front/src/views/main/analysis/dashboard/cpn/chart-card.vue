<script setup lang="ts">

interface IProps {
  header?: string
}
const props = withDefaults(defineProps<IProps>(), { header: '卡片头部信息' })



</script>



<template>
  <div class="chart-card">
    <div class="card-header">{{ props.header }}</div>
    <slot class="card-content"></slot>
  </div>
</template>



<style lang="less" scoped>
.chart-card {
  background: white;
  display: flex;
  flex-direction: column;
  margin: 10px 0;
  padding: 0;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

  .card-header {
    height: 38px;
    line-height: 38px;
    font-size: 14px;
    color: #666;
    padding: 0 10px;
    border-bottom: 1px solid #999;
  }

}
</style>
