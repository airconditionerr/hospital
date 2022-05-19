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
        <el-page-header @back="goback" content="医生信息"></el-page-header>
        <el-divider></el-divider>
        <el-card class="card">
          <el-descriptions title="医生信息">
            <template slot="extra">
              <el-button type="primary" size="small" @click="updateDialogVisible = true">修改</el-button>
              <el-button type="primary" size="small" @click="deleteDoctor">删除</el-button>
            </template>
            <el-descriptions-item label="医生姓名">{{ doctorInfo.doctorName }}</el-descriptions-item>
            <el-descriptions-item label="医生性别">{{ doctorInfo.doctorSex }}</el-descriptions-item>
            <el-descriptions-item label="医生科室">{{ doctorInfo.doctorDepartment }}</el-descriptions-item>
            <el-descriptions-item label="医生职称">{{ doctorInfo.doctorPosition }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
        <el-dialog title="修改信息"
                   :visible.sync="updateDialogVisible"
                   width="30%"
                   center>
          <el-form ref="form" :model="updateForm" label-width="80px">
            <el-row>
              <el-col>
                <el-input v-model="updateForm.doctorDepartment" placeholder="请输入科室"></el-input>
              </el-col>
              <el-col>
                <el-input v-model="updateForm.doctorPosition" placeholder="请输入职称"></el-input>
              </el-col>
              <el-col>
                <el-button @click="updateDoctorInfo" type="primary">修改</el-button>
              </el-col>
            </el-row>
          </el-form>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import AdminHeadTop from '../../components/admin/AdminHeadTop'
  import AdminAsideBanner from '../../components/admin/AdminAsideBanner'

  export default {
    components: {
      AdminAsideBanner,
      AdminHeadTop
    },
    data () {
      return {
        doctorInfo: {},
        updateForm: {},
        updateDialogVisible: false,
        reqUrls: {
          getThisdoctorInfoUrl: '/hospital-web/doctor/info/' + this.$route.params.id,
          deleteDoctorUrl: '/hospital-web/doctor/' + this.$route.params.id,
          updateDoctorInfoUrl: 'hospital-web/doctor/info'
        }
      }
    },
    methods: {
      getThisdoctorInfo () {
        this.$axios.get(this.reqUrls.getThisdoctorInfoUrl, {}).then(response => {
          this.doctorInfo = response.data
        })
      },
      goback () {
        this.$router.push('/adminDoctorManage')
      },
      deleteDoctor () {
        this.$axios.delete(this.reqUrls.deleteDoctorUrl, {}).then(response => {
          if (response.success) {
            this.$message('删除成功')
            this.$router.push('/adminDoctorManage')
          } else {
            this.$message('删除失败')
          }
        })
      },
      updateDoctorInfo () {
        this.$axios.put(this.reqUrls.updateDoctorInfoUrl, {
          id : this.$route.params.id,
          doctorDepartment: this.updateForm.doctorDepartment,
          doctorPosition: this.updateForm.doctorPosition
        }).then(response => {
          if (response.data){
            this.$message('修改成功')
            this.updateDialogVisible = false;
          } else {
            this.$message('修改失败')
            this.updateDialogVisible = false;
          }
        })
      }
    },
    created () {
      this.getThisdoctorInfo()
    }
  }
</script>
