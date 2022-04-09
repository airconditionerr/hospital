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
        <el-card class="card">
          <el-descriptions title="医生信息">
            <el-descriptions-item label="医生姓名">{{ doctorInfo.doctorName }}</el-descriptions-item>
            <el-descriptions-item label="医生性别">{{ doctorInfo.doctorSex }}</el-descriptions-item>
            <el-descriptions-item label="医生科室">{{ doctorInfo.doctorDepartment }}</el-descriptions-item>
            <el-descriptions-item label="医生职称">{{ doctorInfo.doctorPosition }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
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
        doctorInfo: {},
        reqUrls: {
          getDoctorInfoByDoctorIdUrl: '/hospital-web/doctor/info/' + sessionStorage.getItem('doctorId')
        }
      }
    },
    methods: {
      /**
       * 获取医生信息
       */
      getDoctorInfoByDoctorId () {
        this.$axios.get(this.reqUrls.getDoctorInfoByDoctorIdUrl, {}).then(response => {
          this.doctorInfo = response.data
        })
      }
    },
    created () {
      this.getDoctorInfoByDoctorId()
    }
  }
</script>
<style>
</style>
