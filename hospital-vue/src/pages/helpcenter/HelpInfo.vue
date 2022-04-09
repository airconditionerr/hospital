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
        <h1>{{ helpInfo.helpTitle }}</h1>
        <el-divider></el-divider>
        <span>{{ helpInfo.helpContent }}</span>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeadTop from '../../components/HeadTop'
  import AsideBanner from '../../components/AsideBanner'

  export default {
    components: {
      HeadTop,
      AsideBanner
    },
    data () {
      return {
        helpInfo: {},
        reqUrls: {
          getThisNoticeInfoUrl: '/hospital-web/helpCenter/info/' + this.$route.params.id
        }
      }
    },
    methods: {
      getThisNoticeInfo () {
        this.$axios.get(this.reqUrls.getThisNoticeInfoUrl, {}).then(response => {
          this.helpInfo = response.data
        })
      }
    },
    created () {
      this.getThisNoticeInfo()
    }
  }
</script>
