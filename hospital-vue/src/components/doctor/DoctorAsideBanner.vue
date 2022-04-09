<template>
  <el-row>
    <el-col :span="24">
      <el-menu :default-active="this.$route.path" router class="aside-banner">
        <el-menu-item index="/doctorPersonalCenter">
          <i class="el-icon-location-outline"/>
          <span slot="title">个人中心</span>
        </el-menu-item>
        <el-menu-item index="/doctorOrderManage">
          <i class="el-icon-location-outline"/>
          <span slot="title">预约管理</span>
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
</style>
