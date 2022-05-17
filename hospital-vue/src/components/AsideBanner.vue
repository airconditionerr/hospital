<template>
  <el-menu :default-active="this.$route.path" router class="aside-banner">
    <el-row>
      <el-col>
        <el-menu-item index="/index">
          <i class="el-icon-location-outline"/>
          <span slot="title">首页</span>
        </el-menu-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-files"/>
            <span slot="title">挂号</span>
          </template>
          <el-menu-item index="/appointByDept">
            <i class="el-icon-house"></i>
            <span slot="title">科室挂号</span>
          </el-menu-item>
          <el-menu-item index="/appointByDoctor">
            <i class="el-icon-user"></i>
            <span slot="title">医生挂号</span>
          </el-menu-item>
        </el-submenu>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-menu-item index="/nucleicAcidTesting">
          <i class="el-icon-data-board"></i>
          <span slot="title">核酸检测</span>
        </el-menu-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-menu-item index="/feedback">
          <i class="el-icon-s-comment"/>
          <span slot="title">意见反馈</span>
        </el-menu-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-menu-item index="/notice">
          <i class="el-icon-document"></i>
          <span slot="title">最新公告</span>
        </el-menu-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-menu-item index="/helpCenter">
          <i class="el-icon-position"></i>
          <span slot="title">帮助中心</span>
        </el-menu-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-menu-item index="/contact">
          <i class="el-icon-phone-outline"/>
          <span slot="title">联系我们</span>
        </el-menu-item>
      </el-col>
    </el-row>
  </el-menu>
</template>
<script>
  export default {}
</script>
<style>
  .aside-banner{
    height: 93vh;
  }

  .el-menu{
    background-color: #F2F6FC;
  }
</style>
