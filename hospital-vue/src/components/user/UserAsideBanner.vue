<template>
  <el-row>
    <el-col :span="24">
      <el-menu :default-active="this.$route.path" router class="aside-banner">
        <el-menu-item index="/personalCenter">
          <i class="el-icon-location-outline"/>
          <span slot="title">个人信息</span>
        </el-menu-item>
        <el-menu-item index="/orderRecord">
          <i class="el-icon-data-board"></i>
          <span slot="title">预约记录</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>
<script>
  export default {
  }
</script>
<style>
  .aside-banner{
    height: 93vh;
  }

  .el-menu{
    background-color: #F2F6FC;
  }
</style>
