<template>
  <el-container>
    <el-header>
      <AdminHeadTop></AdminHeadTop>
    </el-header>
    <el-container>
      <el-aside>
        <AdminAsideBanner></AdminAsideBanner>
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
  import AdminHeadTop from '../../components/admin/AdminHeadTop'
  import AdminAsideBanner from '../../components/admin/AdminAsideBanner'

  export default {
    components: {
      AdminHeadTop,
      AdminAsideBanner
    },
    data () {
      return {
        orderList: [],
        currentPage: 1,
        pageNum: 1,
        totalNum: 0,
        search: '',
        reqUrls: {
          cancelUrl: '/hospital-web/appoint/'
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
      },
      /**
       * 获取当前用户当前页预约信息
       */
      getOrderInfoPage () {
        this.$axios.get('/hospital-web/orderRecord/page/' + this.pageNum + '/' + 10, {}).then(response => {
          this.orderList = response.data
        })
      },
      toOrderInfo (row) {
        this.$router.push('/adminOrderInfo/' + row.orderId)
      },
      cancel (row) {
        this.$axios.delete(this.reqUrls.cancelUrl + row.orderId, {}).then(response => {
          if (response.data) {
            this.$message('取消成功 ')
            this.getOrderInfoPage()
          }
        })
      },
      getOrderNum () {
        this.$axios.get('hospital-web/orderRecord/num', {}).then(response => {
          this.totalNum = response.data
        })
      }
    },
    watch: {
      search: {
        handler () {
          if (this.search == '') { //搜索框为空
            this.pageNum = 1
            this.$axios.get('/hospital-web/orderRecord/page/' + this.pageNum + '/' + 10, {}).then(response => {
              this.orderList = response.data
              this.getOrderNum()
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
            this.$axios.get('/hospital-web/orderRecord/page/' + this.pageNum + '/' + 10, {}).then(response => {
              this.orderList = response.data
              this.getOrderNum()
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
