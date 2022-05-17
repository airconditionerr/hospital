<template>
  <el-container>
    <el-header>
      <HeadTop></HeadTop>
    </el-header>
    <el-container>
      <el-aside class="aside-wrapper">
        <AsideBanner></AsideBanner>
      </el-aside>
      <el-main>
        <el-page-header @back="goBack" content="联系我们">
        </el-page-header>
        <el-divider></el-divider>
        <el-collapse accordion>
          <el-collapse-item title="联系电话 Contact number">
            <div>（025）12320</div>
          </el-collapse-item>
          <el-collapse-item title="联系邮箱 Contact email">
            <div>nj12320@yeah.net</div>
          </el-collapse-item>
          <el-collapse-item title="地址 Address">
            <div>Air Conditioner</div>
          </el-collapse-item>
          <el-collapse-item title="邮编 Zip">
            <div>215200</div>
          </el-collapse-item>
          <el-collapse-item title="主管 Supervisor">
            <div>南京市卫生健康委员会</div>
          </el-collapse-item>
          <el-collapse-item title="主办 Host">
            <div>南京市卫生信息中心</div>
          </el-collapse-item>
          <el-collapse-item title="备案登记号 ICP Filing registration number">
            <div></div>
          </el-collapse-item>
          <el-collapse-item title="版权所有 All rights reserved">
            <div>南京市卫生信息中心　Copyright ©2009-2017 All Rights Reserved</div>
          </el-collapse-item>
          <el-collapse-item title="技术支持 Technical support">
            <div>Air Conditioner</div>
          </el-collapse-item>
        </el-collapse>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeadTop from '../../components/HeadTop'
  import AsideBanner from '../../components/AsideBanner'
  import crumbs from '../../components/crumbs/crumbs'

  export default {
    components: {
      crumbs,
      HeadTop,
      AsideBanner
    },
    methods: {
      goBack () {
        this.$router.push('/index')
      }
    }
  }
</script>
<style scoped>
</style>
