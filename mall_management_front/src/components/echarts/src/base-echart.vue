<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref, watchEffect } from 'vue';
import type { EChartsOption } from 'echarts';
import chinaJson from '../data/china.json';

// 注册地图
echarts.registerMap('china', chinaJson as any);

// 传入配置
interface IProps {
  options: EChartsOption
}

const props = defineProps<IProps>()

const echartRef = ref<HTMLElement>()
onMounted(() => {

  const myChart = echarts.init(echartRef.value!, 'light', { renderer: 'canvas', locale: 'zh-CN' },);

  myChart.setOption(props.options)

  // 监听配置变化
  watchEffect(() => myChart.setOption(props.options))

  // 自适应窗口变化
  window.addEventListener('resize', () => myChart.resize())
})
</script>

<template>
  <div class="base-echart">

    <div id="echart" ref="echartRef" style="height: 300px;"></div>

  </div>
</template>



<style lang="less" scoped>
.base-echart {
  box-sizing: border-box;
  padding: 10px;
}
</style>
