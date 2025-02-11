<script setup lang="ts">
import { CountUp } from 'countup.js'
import { onMounted, ref } from 'vue';

interface IProps {
  amount?: string
  title?: string
  tips?: string
  number1?: number
  number2?: number
  subtitle?: string
}

const props = withDefaults(defineProps<IProps>(), {   // 为数据设置默认值
  title: '商品总销量',
  tips: '所有的商品总销量',
  number1: 509989,
  number2: 509989,
  subtitle: '商品总销量'
})


//添加数字动画   -- 动态数字的js库
const count1Ref = ref<HTMLElement>()
const count2Ref = ref<HTMLElement>()

const countOption = {
  duration: 1,
  prefix: props.amount === 'saleroom' ? ' ¥' : ''
}

onMounted(() => {
  const countup1 = new CountUp(count1Ref.value!, props.number1, countOption)
  const countup2 = new CountUp(count2Ref.value!, props.number2, countOption)
  countup1.start()
  countup2.start()
})


</script>



<template>
  <div class="count-card">
    <div class="header">
      <span class="title"> {{ props.title }} </span>

      <el-tooltip class="box-item" effect="dark" :content="tips" placement="top">
        <el-icon>
          <Warning />
        </el-icon>
      </el-tooltip>
    </div>

    <div class="content">
      <div class="count" ref="count1Ref"> {{ props.number1 }} </div>
    </div>

    <div class="footer">
      <span> {{ props.subtitle }} </span>
      <span ref="count2Ref"> {{ props.number2 }} </span>
    </div>


  </div>

</template>



<style lang="less" scoped>
.count-card {
  background: white;
  display: flex;
  flex-direction: column;
  height: 130px;
  padding: 0 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

  .header {
    display: flex;
    height: 38px;
    justify-content: space-between;
    align-items: center;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.45);
  }

  .content {
    display: flex;
    flex: 1;
    align-items: center;
    font-size: 26px;
    color: rgba(0, 0, 0, 0.85);
  }

  .footer {
    height: 38px;
    line-height: 38px;
    font-size: 14px;
    letter-spacing: 1px; // 设置文本间距
    border-top: 1px solid #f0f0f0;
    color: rgba(0, 0, 0, 0.85);
  }

}
</style>
