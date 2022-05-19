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
        <!--面包屑-->
        <crumbs></crumbs>
        <el-table
          :data="doctorList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="id"
            label="医生id"
            width="90">
          </el-table-column>
          <el-table-column
            prop="doctorName"
            label="医生姓名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="doctorDepartment"
            label="所属科室"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="mini">预约</el-button>
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
  import AsideBanner from '../../components/AsideBanner'
  import crumbs from '../../components/crumbs/crumbs'

  export default {
    components: {
      crumbs,
      HeadTop,
      AsideBanner,
    },
    data () {
      return {
        doctorList: [],
        currentPage: 1,
        pageNum: 1, // 当前页
        totalNum: 0,
        search: '',
        reqUrls: {
          getAllDoctorNumUrl: '/hospital-web/doctor/num'
        }
      }
    },
    methods: {
      /**
       * 获取医生总数
       */
      getAllDoctorNum () {
        this.$axios.get(this.reqUrls.getAllDoctorNumUrl, {}).then(response => {
          this.totalNum = response.data
        })
      },
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
      },
      /**
       * 预约按钮
       * @param row
       */
      handleClick (row) {
        this.$router.push('/doctorInfo/' + row.id)
      }
    },
    watch: {
      search: {
        handler () {
          if (this.search == '') { //搜索框为空
            this.pageNum = 1
            this.$axios.get('/hospital-web/doctor/page/' + this.pageNum + '/' + 10 + '/' + null + '/' + 0, {}).then(response => {
              this.doctorList = response.data.list
              this.getAllDoctorNum()
            })
          } else {
            this.pageNum = 1
            this.$axios.get('/hospital-web/doctor/page/' + this.pageNum + '/' + 10 + '/' + this.search + '/' + 0, {}).then(response => {
              this.doctorList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        },
        immediate: true
      },
      pageNum: {
        handler () {
          if (this.search == '') {
            this.$axios.get('/hospital-web/doctor/page/' + this.pageNum + '/' + 10 + '/' + null + '/' + 0, {}).then(response => {
              this.doctorList = response.data.list
              this.getAllDeptNum()
            })
          } else {
            this.$axios.get('/hospital-web/doctor/page/' + this.pageNum + '/' + 10 + '/' + this.search + '/' + 0, {}).then(response => {
              this.doctorList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        }
      }
    }
  }
</script>
<style>
</style>
