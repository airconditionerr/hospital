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
            label="科室名称"
            width="180">
          </el-table-column>
          <el-table-column
            prop="doctorSex"
            label="医生性别"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="toDoctorInfo(scope.row)" type="text" size="mini">详细信息</el-button>
              <el-button @click="deleteDoctor(scope.row)" type="text" size="mini">删除</el-button>
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
        doctorList: [],
        currentPage: 1,
        pageNum: 1,
        totalNum: 0,
        search: '',
        reqUrls: {
          deleteDoctorUrl: '/hospital-web/doctor/',
          getAllDoctorNumUrl: '/hospital-web/doctor/num'
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
       * 获取用户信息
       */
      getDoctorInfoPage () {
        this.$axios.get('/hospital-web/doctor/page/' + this.pageNum + '/' + 10 + '/' + null + '/' + 0, {}).then(response => {
          this.doctorList = response.data.list
        })
      },
      /**
       * 删除医生信息
       * @param row
       */
      deleteDoctor (row) {
        this.$axios.delete(this.reqUrls.deleteDoctorUrl + row.id, {}).then(response => {
          if (response.data) {
            this.$message('删除成功')
            this.getDoctorInfoPage()
          }
        })
      },
      /**
       * 到医生详细信息页
       * @param row
       */
      toDoctorInfo (row) {
        this.$router.push('/adminDoctorInfo/' + row.id)
      },
      getAllDoctorNum () {
        this.$axios.get(this.reqUrls.getAllDoctorNumUrl, {}).then(response => {
          this.totalNum = response.data
        })
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
              this.getAllDoctorNum()
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
<style></style>
