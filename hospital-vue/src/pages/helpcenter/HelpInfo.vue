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
        <el-button @click="goBack" plain="true" icon="el-icon-arrow-left" id="backBtn">返回</el-button>
        <h1 id="helpTitle">{{helpInfo.helpTitle}}</h1>
        <el-divider></el-divider>
        <div class="content">{{ helpInfo.helpContent }}</div>
        <el-row id="switch-row">
          <el-col span="1">
            <el-button @click="previousPage" type="primary" icon="el-icon-arrow-left" v-if="previousPageFlag">上一条
            </el-button>
          </el-col>
          <el-col span="1" offset="21">
            <el-button @click="nextPage" type="primary" v-if="nextPageFlag">下一条<i
              class="el-icon-arrow-right el-icon--right"></i></el-button>
          </el-col>
        </el-row>
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
        previousPageFlag: true,
        nextPageFlag: true,
        totalNum: 0,
        reqUrls: {
          getThisNoticeInfoUrl: '/hospital-web/helpCenter/info/' + this.$route.params.id,
          getAllHelpNumUrl: '/hospital-web/helpCenter/num'
        }
      }
    },
    methods: {
      getThisNoticeInfo () {
        this.$axios.get(this.reqUrls.getThisNoticeInfoUrl, {}).then(response => {
          this.helpInfo = response.data
        })
      },
      previousPage () {
        this.$router.push('/helpInfo/' + (this.$route.params.id - 1))
        location.reload()
      },
      nextPage () {

        var temp = Number(this.$route.params.id) + 1
        this.$router.push('/helpInfo/' + temp)
        location.reload()
      },
      getAllHelpNum () {
        this.$axios.get(this.reqUrls.getAllHelpNumUrl, {}).then(response => {
          this.totalNum = response.data
          if (Number(this.$route.params.id) == this.totalNum) {
            this.nextPageFlag = false
          }
        })
      },
      goBack () {
        this.$router.push('/helpCenter')
      }
    },
    created () {
      this.getAllHelpNum()
      if (Number(this.$route.params.id) == 1) {
        this.previousPageFlag = false
      }
      this.getThisNoticeInfo()
    }
  }
</script>
<style scoped>
  .content {
    font-size: 20px;
  }

  #switch-row {
    margin-top: 10px;
  }

  #backBtn{
    float: left;
    margin-right: 20px;
  }

  #helpTitle{
    margin: 0;
    height: 40px;
    line-height: 40px;
  }
</style>
