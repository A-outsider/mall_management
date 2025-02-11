<script setup lang="ts">
import CountCard from './cpn/count-card.vue';
import ChartCard from './cpn/chart-card.vue';

import PieEchart from '@/components/echarts/src/pie-echart.vue';
import RoseEchart from '@/components/echarts/src/rose-echart.vue';
import MapEchart from '@/components/echarts/src/map-echart.vue';

// 模拟获取数据
import { cardData, GoodsCategoryCount, GoodsAddressSale } from '@/data/dashboard';


</script>

<template>
  <div class="dashboard">
    <!-- 1.顶部数字的数据展示 -->
    <el-row :gutter="10">
      <template v-for="item in cardData" :key="item" :span="6">
        <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6"> <count-card v-bind="item" /> </el-col>
      </template>
    </el-row>

    <!-- 2.中间部分的图表 -->
    <el-row :gutter="10">
      <el-col :span="7">
        <chart-card header="商品分类饼图">
          <pie-echart :pieData="GoodsCategoryCount" />
        </chart-card>
      </el-col>
      <el-col :span="10">
        <chart-card header="不同城市商品销量">
          <map-echart :mapData="GoodsAddressSale" />
        </chart-card>
      </el-col>
      <el-col :span="7">
        <chart-card header="商品分类玫瑰图">
          <rose-echart :roseData="GoodsCategoryCount" />
        </chart-card>
      </el-col>
    </el-row>

  </div>
</template>



<style lang="less" scoped>
.dashboard {}
</style>
