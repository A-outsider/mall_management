<script setup lang="ts">
import { reactive, ref } from 'vue'
import type { ElForm } from 'element-plus'

// 定义接受配置props
interface IProps {
  searchConfig: {
    formItems: any[]
  }
}

const props = defineProps<IProps>()

// 定义form的数据

const initialForm: any = {} // 初始化对象
props.searchConfig.formItems.forEach((item) => { initialForm[item.prop] = '' })

const searchForm = reactive(initialForm)


// 处理事件
const emit = defineEmits(['queryClick', 'resetClick'])
const formRef = ref<InstanceType<typeof ElForm>>()

function handleResetClick() {
  // 1.form中的数据全部重置
  formRef.value?.resetFields()

  // 2.将事件出去, content内部重新发送网络请求
  emit('resetClick')
}

function handleQueryClick() {
  emit('queryClick', searchForm)
}
</script>

<template>
  <div class="search">
    <!-- 1.输入搜索关键字的表单 -->
    <el-form :model="searchForm" ref="formRef" label-width="80px" size="large">

      <el-row :gutter="20">
        <template v-for="item in searchConfig.formItems" :key="item.prop">
          <el-col :span="8">

            <el-form-item :label="item.label" :prop="item.prop">
              <template v-if="item.type === 'input'">
                <el-input v-model="searchForm[item.prop]" :placeholder="item.placeholder" />
              </template>
              <template v-else-if="item.type === 'date-picker'">
                <el-date-picker v-model="searchForm[item.prop]" :placeholder="item.placeholder" type="daterange"
                  start-placeholder="开始时间" end-placeholder="结束时间" range-separator="-" />
              </template>

            </el-form-item>
          </el-col>
        </template>
      </el-row>
    </el-form>

    <!-- 2.重置和搜索的按钮 -->
    <div class="btns">
      <el-button icon="Refresh" @click="handleResetClick">重置</el-button>
      <el-button icon="Search" type="primary" @click="handleQueryClick">查询</el-button>
    </div>
  </div>
</template>


<style lang="less" scoped>
.search {
  background-color: #fff;
  padding: 20px;

  .el-form-item {
    padding: 20px 30px;
    margin-bottom: 0;
  }

  .btns {
    text-align: right;
    padding: 0 50px 10px 0;

    .el-button {
      height: 36px;
    }
  }
}
</style>
