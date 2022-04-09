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
        <el-page-header @back="goback" content="用户信息"></el-page-header>
        <el-divider></el-divider>
        <el-card class="card">
          <el-descriptions title="用户信息">
            <template slot="extra">
              <el-button type="primary" size="small" @click="updateDialogVisible = true">修改</el-button>
              <el-button type="primary" size="small" @click="deleteUser">删除</el-button>
            </template>
            <el-descriptions-item label="用户姓名">{{ userInfo.name }}</el-descriptions-item>
            <el-descriptions-item label="用户性别">{{ userInfo.userSex }}</el-descriptions-item>
            <el-descriptions-item label="用户邮箱">{{ userInfo.userEmail }}</el-descriptions-item>
            <el-descriptions-item label="用户手机">{{ userInfo.userMobile }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
        <el-dialog title="修改信息"
                   :visible.sync="updateDialogVisible"
                   width="30%"
                   center>
          <el-form ref="form" :model="updateForm" label-width="80px">
            <el-row>
              <el-col>
                <el-input v-model="updateForm.userEmail" placeholder="请输入邮箱"></el-input>
              </el-col>
              <el-col>
                <el-input v-model="updateForm.userMobile" placeholder="请输入手机"></el-input>
              </el-col>
              <el-col>
                <el-button @click="updateUserInfo" type="primary">修改</el-button>
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
        userInfo: {},
        updateForm: {},
        updateDialogVisible: false,
        reqUrls: {
          getThisUserInfoUrl: '/hospital-web/user/info/' + this.$route.params.userId,
          updateUserInfoUrl: 'hospital-web/user',
          deleteUserUrl: '/hospital-web/user/' + this.$route.params.userId
        }
      }
    },
    methods: {
      getThisUserInfo () {
        this.$axios.get(this.reqUrls.getThisUserInfoUrl, {}).then(response => {
          this.userInfo = response.data
        })
      },
      goback() {
        this.$router.push('/adminUserManage')
      },
      updateUserInfo () {
        this.$axios.put(this.reqUrls.updateUserInfoUrl, {
          userId: this.$route.params.userId,
          userEmail: this.updateForm.userEmail,
          userMobile: this.updateForm.userMobile
        }).then(response => {
          if (response.data){
            this.$message('修改成功')
            this.updateDialogVisible = false;
          } else {
            this.$message('修改失败')
            this.updateDialogVisible = false;
          }
        })
      },
      deleteUser() {
        this.$axios.delete(this.reqUrls.deleteUserUrl, {}).then(response => {
          if (response.success){
            this.$message('删除成功')
            this.$router.push('/adminUserManage')
          } else {
            this.$message('删除失败')
          }
        })
      }
    },
    created () {
      this.getThisUserInfo()
    }
  }
</script>
<style>

</style>
