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
        <h1>欢迎来到XXXXX医院挂号系统</h1>
        <el-carousel indicator-position="outside">
          <el-carousel-item>
            <el-image :src="url"></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeadTop from '../components/HeadTop'
  import AsideBanner from '../components/AsideBanner'
  import crumbs from '../components/crumbs/crumbs'

  export default {
    components: {
      HeadTop,
      AsideBanner,
      crumbs
    },
    data () {
      return {
        url: require('../assets/img/ad1.jpg')
      }
    }
  }
</script>
<style>
  /* 幻灯片 */
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
