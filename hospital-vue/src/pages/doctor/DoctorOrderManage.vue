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
            width="360">
          </el-table-column>
          <el-table-column
            prop="userName"
            label="患者姓名"
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
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="medium"
                placeholder="输入关键字搜索"/>
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
        totalNum: 0,
        search: '',
      }
    },
    methods: {
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
      },
      toOrderInfo (row) {
        this.$router.push('/doctorOrderInfo/' + row.orderId)
      },
      cancel (row) {
        this.$axios.delete('/hospital-web/orderRecord/' + row.orderId, {}).then(response => {
          if (response.data) {
            this.$message('取消成功')
            this.getOrderInfoPage()
          }
        })
      },
      finish (row) {
        this.$axios.put('/hospital-web/orderRecord/' + row.orderId).then(response => {
          if (response.data) {
            this.$message('预约完成')
          } else {
            this.$message('预约完成失败')
          }
        })
      },
      getTotalNum () {
        this.$axios.get('/hospital-web/orderRecord/num/doctor/' + sessionStorage.getItem('doctorId'), {}).then(response => {
          this.totalNum = response.data
        })
      },
      /**
       * 获取当前医生当前页预约信息
       */
      getOrderInfoPage () {
        this.$axios.get('/hospital-web/orderRecord/page/doctor/' + this.pageNum + '/' + 10 + '/' + sessionStorage.getItem('doctorId'), {}).then(response => {
          this.orderList = response.data
        })
      },
    },
    watch: {
      search: {
        handler () {
          if (this.search == '') { //搜索框为空
            this.pageNum = 1
            this.$axios.get('/hospital-web/orderRecord/page/doctor/' + this.pageNum + '/' + 10 + '/' + sessionStorage.getItem('doctorId'), {}).then(response => {
              this.orderList = response.data
              this.getTotalNum()
            })
          } else {
            this.pageNum = 1
            this.$axios.get('/hospital-web/orderRecord/page/search/' + this.pageNum + '/' + 10 + '/' + this.search, {}).then(response => {
              this.orderList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        },
        immediate: true
      },
      pageNum: {
        handler () {
          if (this.search == '') {
            this.$axios.get('/hospital-web/orderRecord/page/doctor/' + this.pageNum + '/' + 10 + '/' + sessionStorage.getItem('doctorId'), {}).then(response => {
              this.orderList = response.data
              this.getTotalNum()
            })
          } else {
            this.$axios.get('/hospital-web/orderRecord/page/search/' + this.pageNum + '/' + 10 + '/' + this.search, {}).then(response => {
              this.orderList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        }
      }
    }
  }
</script>
<style></style>
