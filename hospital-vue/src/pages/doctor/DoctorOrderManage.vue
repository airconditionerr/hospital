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
        <el-table
          :data="orderList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="orderId"
            label="订单id"
            width="180">
          </el-table-column>
          <el-table-column
            prop="departmentName"
            label="科室名称"
            width="180">
          </el-table-column>
          <el-table-column
            prop="doctorName"
            label="医生姓名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="transactDate"
            label="预约时间"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="toOrderInfo(scope.row)" type="text" size="mini">详情</el-button>
              <el-button @click="finish(scope.row)" type="text" size="mini">完成</el-button>
              <el-button @click="cancel(scope.row)" type="text" size="mini">取消</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <div class="block">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="10"
            layout="prev, pager, next, jumper"
            :total="totalNum">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import DoctorHeadTop from '../../components/doctor/DoctorHeadTop'
  import DoctorAsideBanner from '../../components/doctor/DoctorAsideBanner'

  export default {
    components: {
      DoctorHeadTop,
      DoctorAsideBanner
    },
    data () {
      return {
        orderList: [],
        currentPage: 1,
        pageNum: 1,
        doctorName: null,
        totalNum : 0,
        reqUrls: {
          getDoctorInfoUrl: '/hospital-web/doctor/info/' + sessionStorage.getItem('doctorId'),
          getOrderInfoPageUrl: '/hospital-web/orderRecord/page/' + this.pageNum + '/' + 10 + '/',
          cancelUrl: '/hospital-web/orderRecord/' + row.orderId,
          finishUrl: '/hospital-web/orderRecord/' + row.orderId,
          getTotalNumUrl: '/hospital-web/orderRecord/num/'
        }
      }
    },
    methods: {
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
        this.getOrderInfoPage()
      },
      /**
       * 获取当前医生当前页预约信息
       */
      getOrderInfoPage () {
        this.$axios.get(this.reqUrls.getDoctorInfoUrl).then(response => {
          this.$axios.get(this.reqUrls.getOrderInfoPageUrl + response.data.doctorName, {}).then(response => {
            this.orderList = response.data
          })
        })
      },
      toOrderInfo (row) {
        this.$router.push('/doctorOrderInfo/' + row.orderId)
      },
      cancel () {
        this.$axios.delete(this.reqUrls.cancelUrl, {}).then(response => {
          if (response.data) {
            this.$message('取消成功')
            this.getOrderInfoPage()
          }
        })
      },
      finish () {
        this.$axios.put(this.reqUrls.finishUrl).then(response => {
          if (response.data){
            this.$message('预约完成')
          } else {
            this.$message('预约完成失败')
          }
        })
      },
      getTotalNum() {
        this.$axios.get(this.reqUrls.getDoctorInfoUrl).then(response => {
          this.$axios.get(this.reqUrls.getTotalNumUrl + response.data.doctorName, {}).then(response => {
            this.totalNum = response.data.size
          })
        })
      }
    },
    created () {
      this.getTotalNum()
      this.getOrderInfoPage()
    }
  }
</script>
<style></style>
