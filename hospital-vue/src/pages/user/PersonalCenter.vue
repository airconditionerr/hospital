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
        <el-card class="card">
          <el-descriptions title="用户信息">
            <template slot="extra">
              <el-button type="primary" size="small" @click="vcodeDialogVisible = true">修改</el-button>
            </template>
            <el-descriptions-item label="用户姓名">{{ userInfo.name }}</el-descriptions-item>
            <el-descriptions-item label="用户性别">{{ userInfo.userSex }}</el-descriptions-item>
            <el-descriptions-item label="用户邮箱">{{ userInfo.userEmail }}</el-descriptions-item>
            <el-descriptions-item label="用户手机">{{ userInfo.userMobile }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
        <el-dialog title="验证"
                   :visible.sync="vcodeDialogVisible"
                   width="30%"
                   center>
          <el-form ref="vcodeForm" :model="vcodeForm" label-width="80px">
            <el-row>
              <el-col span="6">
                <el-input v-model="vcodeForm.vcode" placeholder="请输入验证码"/>
              </el-col>
              <el-col span="2">
                <el-button class="vcodeBtn" @click="sendVcode">发送验证码</el-button>
              </el-col>
            </el-row>
            <el-row>
              <el-button @click="goUpdate" type="primary">下一步</el-button>
            </el-row>
          </el-form>
        </el-dialog>
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
  import HeadTop from '../../components/HeadTop'
  import UserAsideBanner from '../../components/user/UserAsideBanner'

  export default {
    components: {
      HeadTop,
      UserAsideBanner
    },
    data () {
      return {
        userInfo: {},
        vcodeForm: {},
        updateForm: {},
        vcodeDialogVisible: false,
        updateDialogVisible: false,
        vcode: null,
        reqUrls: {
          getUserInfoByUserIdUrl: '/hospital-web/user/info/' + sessionStorage.getItem('userId'),
          updateUserInfoUrl: 'hospital-web/user',
          sendVcodeUrl: '/hospital-web/user/vcode'
        }
      }
    },
    methods: {
      /**
       * 根据userId获取用户信息
       */
      getUserInfoByUserId () {
        this.$axios.get(this.reqUrls.getUserInfoByUserIdUrl, {}).then(response => {
          this.userInfo = response.data
        })
      },
      /**
       * 更新用户信息
       */
      updateUserInfo () {
        this.$axios.put(this.reqUrls.updateUserInfoUrl, {
          userId: sessionStorage.getItem("userId"),
          userEmail: this.updateForm.userEmail,
          userMobile: this.updateForm.userMobile
        }).then(response => {
          if (response.data){
            this.$message('修改成功')
            this.getUserInfoByUserId();
            this.updateDialogVisible = false;
          } else {
            this.$message('修改失败')
            this.updateDialogVisible = false;
          }
        })
      },
      /**
       * 发送验证码
       */
      sendVcode () {
        this.$axios.post(this.reqUrls.sendVcodeUrl, {
          email: this.userInfo.userEmail
        }).then(response => {
          this.vcode = response.data
        })
      },
      /**
       * 前往修改信息页面
       */
      goUpdate () {
        if (this.vcodeForm != null){
          if (this.vcodeForm.vcode == this.vcode) {
            this.vcodeDialogVisible = false
            this.updateDialogVisible = true
          } else {
            this.$message('验证码错误')
          }
        } else {
          this.$message('请先输入正确的验证码')
        }
      }
    },
    created () {
      this.getUserInfoByUserId()
    }
  }
</script>
<style>
  .vcodeBtn {
    margin-left: 10px;
  }
</style>
