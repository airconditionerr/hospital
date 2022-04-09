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
        <el-page-header @back="goback" content="公告详情"></el-page-header>
        <el-divider></el-divider>
        <h1>{{ noticeInfo.noticeTitle }}</h1>
        <span>{{ noticeInfo.noticeTime }}</span>
        <span>{{ noticeInfo.noticeContent }}</span>
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
        noticeInfo: {},
        reqUrls: {
          getThisNoticeInfoUrl: '/hospital-web/notice/' + this.$route.params.id
        }
      }
    },
    methods: {
      goback() {
        this.$router.push('/notice')
      },
      getThisNoticeInfo () {
        this.$axios.get(this.reqUrls.getThisNoticeInfoUrl, {}).then(response => {
          this.noticeInfo = response.data
        })
      }
    },
    created () {
      this.getThisNoticeInfo()
    }
  }
</script>
