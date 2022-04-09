<template>
  <el-row>
    <el-col :span="24">
      <el-menu :default-active="this.$route.path" router class="aside-banner">
        <el-menu-item index="/adminOrderManage">
          <i class="el-icon-location-outline"/>
          <span slot="title">订单管理</span>
        </el-menu-item>
        <el-menu-item index="/adminUserManage">
          <i class="el-icon-location-outline"/>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="/adminDoctorManage">
          <i class="el-icon-location-outline"/>
          <span slot="title">医生管理</span>
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
