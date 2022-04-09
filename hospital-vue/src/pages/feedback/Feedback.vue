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
        <el-page-header @back="goBack" content="意见反馈">
        </el-page-header>
        <el-divider></el-divider>
        <el-form ref="form" :model="form" label-width="80px">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="feedbackForm.feedbackContent">
          </el-input>
          <el-button @click="feedback">提交</el-button>
        </el-form>
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
    data () {
      return {
        feedbackForm: {
          feedbackContent: ''
        },
        reqUrls: {
          feedbackUrl: '/hospital-web/feedback'
        }
      }
    },
    methods: {
      goBack () {
        this.$router.push('/index')
      },
      feedback () {
        this.$axios.post(this.reqUrls.feedbackUrl, {
          feedbackContent: this.feedbackForm.feedbackContent
        }, {}).then(response => {
          if (response.data){
            this.$message('反馈成功')
            this.$router.push('/index')
          } else {
            this.$message('反馈失败')
          }
        })
      }
    }
  }
</script>
<style></style>
