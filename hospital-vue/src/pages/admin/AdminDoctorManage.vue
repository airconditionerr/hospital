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
            width="180" v-if="false">
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
        reqUrls: {
          getDoctorInfoPageUrl: '/hospital-web/doctor/' + this.pageNum + '/' + 10, // 获取当前用户当前页预约信息请求地址
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
        this.getDoctorInfoPage()
      },
      /**
       * 获取用户信息
       */
      getDoctorInfoPage () {
        this.$axios.get(this.getDoctorInfoPageUrl, {}).then(response => {
          this.doctorList = response.data
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
    created () {
      this.getDoctorInfoPage()
      this.getAllDoctorNum()
    }
  }
</script>
<style></style>
