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
        <h1>欢 迎 来 到 智 慧 健 康 预 约 挂 号 系 统</h1>
        <el-carousel indicator-position="outside">
          <el-carousel-item>
            <el-image :src="url"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="url"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="url"></el-image>
          </el-carousel-item>
        </el-carousel>

        <el-row>
          <el-col span="7" style="margin-left: 30px;">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>科室</span>
                <!--<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
              </div>
              <div v-for="o in 10" :key="o" class="text item">
                {{ '列表内容 ' + o }}
              </div>
            </el-card>
          </el-col>
          <el-col span="7" offset="1">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>医生</span>
                <!--<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
              </div>
              <div v-for="o in 10" :key="o" class="text item">
                {{ '列表内容 ' + o }}
              </div>
            </el-card>
          </el-col>
          <el-col span="7" offset="1">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>最新公告</span>
                <!--<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
              </div>
              <div v-for="o in 10" :key="o" class="text item">
                {{ '列表内容 ' + o }}
              </div>
            </el-card>
          </el-col>
        </el-row>

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
