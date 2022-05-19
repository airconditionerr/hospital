<template>
  <el-container>
    <el-header>
      <DoctorHeadTop></DoctorHeadTop>
    </el-header>
    <el-container>
      <el-aside>
        <DoctorAsideBanner></DoctorAsideBanner>
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
            <el-button @click="finish()" type="primary" size="mini">完成</el-button>
            <el-button @click="cancel()" type="primary" size="mini">取消</el-button>
          </el-card>
          <el-card>
            病情描述：<span>{{ orderRecordInfo.diseaseInfo }}</span>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import DoctorAsideBanner from '../../components/doctor/DoctorAsideBanner'
  import DoctorHeadTop from '../../components/doctor/DoctorHeadTop'

  export default {
    components: {
      DoctorHeadTop,
      DoctorAsideBanner
    },
    data () {
      return {
        activities: [{
          content: '订单创建',
          timestamp: '2018-04-12 20:46',
          size: 'large',
          type: 'primary',
          icon: 'el-icon-document-copy',
          visible: false
        }, {
          content: '通知用户',
          timestamp: '2018-04-03 20:46',
          color: '#0bbd87',
          icon: 'el-icon-phone-outline',
          visible: false
        }, {
          content: '订单完成',
          timestamp: '2018-04-03 20:46',
          icon: 'el-icon-check',
          color: '#0bbd87',
          visible: false
        }],
        orderRecordInfo: {},
        reqUrls: {
          cancelUrl: '/hospital-web/appoint/' + this.$route.params.orderId,
          getOrderRecordInfoUrl: '/hospital-web/orderRecord/info/' + this.$route.params.orderId,
          finishUrl: '/hospital-web/orderRecord/' + this.$route.params.orderId
        }
      }
    },
    methods: {
      goBack () {
        this.$router.push('/doctorOrderManage')
      },
      cancel () {
        this.$axios.delete(this.reqUrls.cancelUrl, {}).then(response => {
          if (response.data) {
            this.$message('取消成功')
            this.$router.push('/adminOrderManage')
          } else {
            this.$message('取消失败')
          }
        })
      },
      getOrderRecordInfo () {
        this.$axios.get(this.reqUrls.getOrderRecordInfoUrl, {}).then(response => {
          this.orderRecordInfo = response.data
          if (this.orderRecordInfo.isSuccess == 1) {
            this.activities[0].visible = true
            if (this.orderRecordInfo.isSend == 1) {
              this.activities[1].visible = true
              if (this.orderRecordInfo.isFinish == 1) {
                this.activities[2].visible = true
              }
            }
          }
        })
      },
      finish () {
        this.$axios.put(this.reqUrls.finishUrl).then(response => {
          if (response.data) {
            this.$message('预约完成')
          } else {
            this.$message('预约完成失败')
          }
        })
      }
    },
    created () {
      this.getOrderRecordInfo()
    }
  }
</script>
