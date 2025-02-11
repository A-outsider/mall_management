<script setup lang="ts">
import type { EChartsOption } from 'echarts';
import { computed } from 'vue';
import BaseEchart from './base-echart.vue';
import { convertData } from '../utils/convert-data';

interface IEchartValueType {
  value: number
  name: string
}

interface IProps {
  mapData: IEchartValueType[]
}

const props = defineProps<IProps>()

const options = computed<EChartsOption>(() => { // 响应式监听
  return {
    backgroundColor: '#fff',
    title: {
      text: '全国销量统计',
      left: 'center',
      textStyle: {
        color: '#fff'
      }
    },
    tooltip: {
      trigger: 'item',
      formatter: function (params: any) {
        return params.name + ' : ' + params.value[2]
      }
    },
    visualMap: {
      min: 0,
      max: 60000,
      left: 20,
      bottom: 20,
      calculable: true,
      text: ['高', '低'],
      inRange: {
        color: ['rgb(70, 240, 252)', 'rgb(250, 220, 46)', 'rgb(245, 38, 186)']
      },
      textStyle: {
        color: '#fff'
      }
    },
    geo: {
      map: 'china',
      roam: 'scale', // 漫步: 支持鼠标缩放效果
      emphasis: {
        areaColor: '#f4cccc',
        borderColor: 'rgb(9, 54, 95)',
        itemStyle: {
          areaColor: '#f4cccc'
        }
      }
    },
    series: [
      {
        name: '销量',
        type: 'scatter',       // 散点图在地图上展示数据
        coordinateSystem: 'geo',
        data: convertData(props.mapData),
        // 散点的大小(可以根据数据不同显示不同的大小, 设置为一个函数)
        symbolSize: 12,
        emphasis: {
          itemStyle: {
            borderColor: '#fff',
            borderWidth: 1
          }
        }
      },
      {
        // 会自动生成geo地理坐标系统
        type: 'map',
        // 设置使用的地图名称, 复用的是第0个坐标系统
        map: 'china',
        geoIndex: 0,
        // 缩放地图
        aspectScale: 0.75,
        tooltip: {
          show: false
        }
      }
    ]
  }
})

</script>



<template>
  <div class="map-echart">
    <base-echart :options="options"></base-echart>
  </div>
</template>



<style lang="less" scoped>
.map-echart {}
</style>
