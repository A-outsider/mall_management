<script setup lang="ts">
import type { EChartsOption } from 'echarts';
import BaseEchart from './base-echart.vue';
import { computed } from 'vue';

interface IEchartValueType {
  value: number
  name: string
}

interface IProps {
  pieData: IEchartValueType[]
}

const props = defineProps<IProps>()

const options = computed<EChartsOption>(() => { // 响应式监听
  return {
    tooltip: {
      trigger: 'item'// 手指放上去的时候显示的文本
    },
    legend: { // 图例设置
      orient: 'horizontal',
      left: 'left'
    },
    series: [
      {
        name: '访问来源',
        type: 'pie',
        radius: '40%',
        bottom: '-10%', // 图形下移
        data: props.pieData,
        emphasis: { // 鼠标高亮效果
          itemStyle: {  // 设置阴影
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  }
})


</script>



<template>
  <div class="pie-echart">
    <base-echart :options="options"></base-echart>
  </div>
</template>



<style lang="less" scoped>
.pie-echart {}
</style>
