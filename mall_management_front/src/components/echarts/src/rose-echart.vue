<script setup lang="ts">
import type { EChartsOption } from 'echarts';
import BaseEchart from './base-echart.vue';
import { computed } from 'vue';
interface IEchartValueType {
  value: number
  name: string
}

interface IProps {
  roseData: IEchartValueType[]
}

const props = defineProps<IProps>()

const options = computed<EChartsOption>(() => { // 响应式监听

  return {
    toolbox: {
      show: true,
      feature: {
        mark: { show: true },
        dataView: { show: true, readOnly: false },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    tooltip: {
      trigger: 'item'
    },
    series: [
      {

        name: '访问来源',
        type: 'pie',
        radius: [10, 100],    // 内半径/外半径的大小
        center: ['50%', '50%'], // 设置区域的位置
        bottom: '-15%',
        roseType: 'area', // 圆心角一样, 通过半径的不同表示大小
        itemStyle: {
          borderRadius: 8
        },
        data: props.roseData,
        label: {
          rotate: 45
        },
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
  <div class="rose-echart">
    <base-echart :options="options"></base-echart>
  </div>
</template>



<style lang="less" scoped>
.rose-echart {}
</style>
