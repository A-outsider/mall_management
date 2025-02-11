<script setup lang="ts">
import userSystemStore from '@/stores/system/system';
import { reactive, ref } from 'vue';

// interface IModalConfig

interface IModalProps {
  modalConfig: {
    pageName: string
    header: {
      newTitle: string
      editTitle: string
    }
    formItems: any[]
  },
  otherInfo?: any
}


// 定义props接受数据
const props = defineProps<IModalProps>()


// 处理数据绑定
const initialData: any = {}
for (const item of props.modalConfig.formItems) {
  initialData[item.prop] = ''
}
const formData = reactive<any>(initialData)

// 处理弹窗
const dialogVisible = ref<boolean>(false)
const isNewRef = ref<boolean>(true)
let editId = ref<number>(0)

const setModalVisible = (isNew: boolean = true, itemData?: any) => {
  // 修改状态
  dialogVisible.value = true
  isNewRef.value = isNew

  if (!isNew && itemData) {
    for (const key in formData) {     // 填充数据
      formData[key] = itemData[key]
      editId.value = itemData.id
    }
  } else {
    for (const key in formData.value) {     // 刷新数据
      formData.value[key] = ''
    }
  }
}


// 确认逻辑
const systemStore = userSystemStore()
const handleConfirmClick = () => {

  let information = formData
  if (props.otherInfo) information = { ...information, ...props.otherInfo }

  //  发送请求创建数据
  if (isNewRef.value) {
    systemStore.createPageData(props.modalConfig.pageName, information)

  } else {
    console.log(information)
    systemStore.editPageData(props.modalConfig.pageName, editId.value, information)
  }

  // 关闭弹窗
  dialogVisible.value = false

  // 刷新拉取数据
  console.log("发送获取数据请求")

}

const handleCancelClick = () => {
  dialogVisible.value = false
}

defineExpose({ setModalVisible })
</script>


<template>
  <div class="user-modal">
    <el-dialog v-model="dialogVisible" center width="30%" :title="isNewRef ? '新建用户' : '编辑用户'">
      <div class="form">
        <el-form label-width="80px" :model="formData" size="large">
          <template v-for="item in modalConfig.formItems" :key="item.prop">
            <el-form-item :label="item.label" :prop="item.prop">

              <template v-if="item.type === 'input'">
                <el-input v-model="formData[item.prop]" :placeholder="item.placeholder" />
              </template>

              <template v-else-if="item.type === 'date-picker'">
                <el-date-picker v-model="formData[item.prop]" :placeholder="item.placeholder" type="daterange"
                  start-placeholder="开始时间" end-placeholder="结束时间" range-separator="-" />
              </template>

              <template v-else-if="item.type === 'select'">
                <el-select v-model="formData[item.prop]" :placeholder="item.placeholder" style="width: 100%">
                  <template v-for="option in item.options" :key="option.value">
                    <el-option :label="option.label" :value="option.value" />
                  </template>
                </el-select>
              </template>

              <template v-else-if="item.type === 'custom'">
                <slot :name="item.slotName"></slot>
              </template>
            </el-form-item>
          </template>
        </el-form>
      </div>


      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleCancelClick">取消</el-button>
          <el-button type="primary" @click="handleConfirmClick">
            确认
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>



<style lang="less" scoped>
.user-modal {
  .form {
    padding: 0 50px;
  }
}
</style>
