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
              <el-button @click="appoint(scope.row)" type="text" size="mini">详细信息</el-button>
              <el-button @click="cancelAppoint(scope.row)" type="text" size="mini">取消</el-button>
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
  import HeadTop from '../../components/HeadTop'
  import UserAsideBanner from '../../components/user/UserAsideBanner'

  export default {
    components: {
      HeadTop,
      UserAsideBanner
    },
    data () {
      return {
        orderList: [],
        currentPage: 1,
        pageNum: 1,
        totalNum: 0,
        search: '',
        reqUrls: {
          cancelAppointUrl: '/hospital-web/orderRecord/'
        }
      }
    },

    methods: {
      // 翻页
      handleCurrentChange (val) {
        this.pageNum = val
      },
      // 获取当前用户当前页预约信息
      getOrderInfoPage () {
        this.$axios.get('/hospital-web/orderRecord/page/user/' + this.pageNum + '/' + 10 + '/' + sessionStorage.getItem('userId'), {}).then(response => {
          this.orderList = response.data
        })
      },
      appoint (row) {
        this.$router.push('/orderRecordInfo/' + row.orderId)
      },
      /**
       * 取消预约
       * @param row
       */
      cancelAppoint (row) {
        this.$axios.delete(this.reqUrls.cancelAppointUrl + row.orderId, {}).then(response => {
          if (response.data) {
            this.$message('取消成功')
            this.getOrderInfoPage()
          }
        })
      },
      getAllOrderNum () {
        this.$axios.get('/hospital-web/orderRecord/num/user/' + sessionStorage.getItem('userId'), {}).then(response => {
          this.totalNum = response.data
        })
      }
    },
    watch: {
      search: {
        handler () {
          if (this.search == '') { //搜索框为空
            this.pageNum = 1
            this.$axios.get('/hospital-web/orderRecord/page/user/' + this.pageNum + '/' + 10 + '/' + sessionStorage.getItem('userId'), {}).then(response => {
              this.orderList = response.data
              this.getAllOrderNum()
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
            this.$axios.get('/hospital-web/orderRecord/page/user/' + this.pageNum + '/' + 10 + '/' + sessionStorage.getItem('userId'), {}).then(response => {
              this.orderList = response.data
              this.getAllOrderNum()
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
