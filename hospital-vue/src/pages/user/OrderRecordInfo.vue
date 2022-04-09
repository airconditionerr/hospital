<template>
  <el-container>
    <el-header>
      <HeadTop></HeadTop>
    </el-header>
    <el-container>
      <el-aside>
        <UserAsideBanner></UserAsideBanner>
      </el-aside>
      <el-main>
        <el-page-header @back="goBack" content="订单详情页面">
        </el-page-header>
        <el-divider></el-divider>
        <div class="block">
          <el-card>
            <el-timeline>
              <el-timeline-item
                v-for="(activity, index) in activities"
                :key="index"
                :icon="activity.icon"
                :type="activity.type"
                :color="activity.color"
                :size="activity.size"
                :timestamp="activity.timestamp"
                v-if="activity.visible">
                {{ activity.content }}
              </el-timeline-item>
            </el-timeline>
            <el-button @click="cancel()" type="primary" size="mini">取消</el-button>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeadTop from '../../components/HeadTop'
  import UserAsideBanner from '../../components/user/UserAsideBanner'

  export default {
    components: {
      HeadTop,
      UserAsideBanner
    },
    data () {
      return {
        activities: [{
          content: '订单创建',
          timestamp: '2018-04-12 20:46',
          size: 'large',
          type: 'primary',
          icon: 'el-icon-document-copy',
          visible : false
        }, {
          content: '通知用户',
          timestamp: '2018-04-03 20:46',
          color: '#0bbd87',
          icon: 'el-icon-phone-outline',
          visible : false
        }, {
          content: '订单完成',
          timestamp: '2018-04-03 20:46',
          icon: 'el-icon-check',
          color: '#0bbd87',
          visible : false
        }],
        orderRecordInfo: {}
      }
    },
    methods: {
      goBack () {
        this.$router.push('/orderRecord')
      },
      cancel () {
        this.$axios.delete('/hospital-web/appoint/' + this.$route.params.orderId, {}).then(response => {
          if (response.data) {
            this.$message('取消成功')
            this.$router.push('/orderRecord')
          } else {
            this.$message('取消失败')
          }
        })
      },
      getOrderRecordInfo () {
        this.$axios.get('/hospital-web/orderRecord/' + this.$route.params.orderId, {}).then(response => {
          this.orderRecordInfo = response.data
          if (this.orderRecordInfo.isSuccess == 1){
            this.activities[0].visible = true
            if (this.orderRecordInfo.isSend == 1){
              this.activities[1].visible = true
              if (this.orderRecordInfo.isFinish == 1){
                this.activities[2].visible = true
              }
            }
          }
        })
      }
    },
    created () {
      this.getOrderRecordInfo()


    }
  }
</script>
<style>
</style>
