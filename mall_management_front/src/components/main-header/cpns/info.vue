<script setup lang="ts">
import router from '@/router';
import { localCache } from '@/utils/cache/cache';
import { Account_TOKEN, User_DATA__MENU, User_DATA_PERMISSION } from '@/utils/cache/keys';

const handleExitClick = () => {
  localCache.removeCache(Account_TOKEN)
  localCache.removeCache(User_DATA_PERMISSION)
  localCache.removeCache(User_DATA__MENU)
  router.push('/login')

}

</script>



<template>
  <div class="header-info">
    <!-- 小图标 -->
    <div class="operation">
      <span>
        <el-icon>
          <Message />
        </el-icon>
      </span>

      <span>
        <span class="dot"></span>
        <el-icon>
          <ChatDotRound />
        </el-icon>
      </span>

      <span>
        <el-icon>
          <Search />
        </el-icon>
      </span>

      <div class="info">

        <el-dropdown>
          <span class="user-info">
            <div>
              <el-avatar :size="28" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
            </div>
            <el-icon>
              <arrow-down />
            </el-icon>
          </span>

          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleExitClick">
                <el-icon>
                  <CircleClose />
                </el-icon>
                <span>退出系统</span>
              </el-dropdown-item>

              <el-dropdown-item divided>
                <el-icon>
                  <InfoFilled />
                </el-icon>
                <span>个人信息</span>
              </el-dropdown-item>

              <el-dropdown-item>
                <el-icon>
                  <Unlock />
                </el-icon>
                <span>修改密码</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

      </div>

    </div>

  </div>

</template>



<style lang="less" scoped>
.header-info {
  display: flex;
  align-items: center;


  .operation {
    display: flex;
    align-items: center;
    margin-right: 15px;

    span {
      position: relative;
      display: flex;
      align-items: center;
      width: 28px;
      height: 30px;
    }

    .dot {
      position: absolute;
      top: 6px;
      right: 6px;
      z-index: 10;
      background-color: red;
      width: 4px;
      height: 4px;
      border-radius: 100%;
    }
  }

  .info {
    margin-left: 15px;

    .user-info {
      display: flex;
      align-items: center;
      cursor: pointer;

      .name {
        margin-left: 15px;
      }
    }


    .el-tooltip__trigger:focus-visible {
      outline: unset;
    }

  }

  .info {
    :global(.el-dropdown-menu__item) {
      line-height: 36px !important;
      padding: 6px 22px;
    }
  }
}
</style>
