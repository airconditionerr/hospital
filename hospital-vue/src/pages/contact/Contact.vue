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
          </el-collapse-item>
          <el-collapse-item title="联系邮箱 Contact email">
          </el-collapse-item>
          <el-collapse-item title="地址 Address">
          </el-collapse-item>
          <el-collapse-item title="邮编 Zip">
          </el-collapse-item>
          <el-collapse-item title="主管 Supervisor">
          </el-collapse-item>
          <el-collapse-item title="主办 Host">
          </el-collapse-item>
          <el-collapse-item title="备案登记号 ICP Filing registration number">
          </el-collapse-item>
          <el-collapse-item title="版权所有 All rights reserved">
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
<style></style>
