<template>
  <div>
    <div class="background">
      <el-image class="backgroundImg" :src="imgUrl"></el-image>
    </div>
    <div class="whole-container">
      <el-row>
        <el-col span="4" offset="10"><h1>医生登录</h1></el-col>
      </el-row>
      <el-row class="loginRow">
        <el-col span="4" offset="10">
          <el-form status-icon="true" :model="doctorLoginForm" :rules="rules" ref="doctorLoginForm">
            <el-form-item prop="username">
              <el-input v-model="doctorLoginForm.doctorAccount" placeholder="请输入账号"/>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="doctorLoginForm.doctorPassword" placeholder="请输入密码"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login('doctorLoginForm')" class="loginBtn">登录</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        imgUrl: require('../../assets/img/login.jpg'),
        doctorLoginForm: {
          doctorAccount: '',
          doctorPassword: '',
        },
        rules: {
          doctorAccount: [
            {required: true, message: '账号不能为空', trigger: 'blur'},
            {min: 5, max: 15, message: '账号长度为5-15个字符', trigger: 'blur'}
          ],
          doctorPassword: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
          ],
        },
        reqUrls: {
          doctorLoginUrl: '/hospital-web/doctor/login',
        }
      }
    },
    methods: {
      login (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post(this.reqUrls.doctorLoginUrl, {
              doctorAccount: this.doctorLoginForm.doctorAccount,
              doctorPassword: this.doctorLoginForm.doctorPassword
            }, {}).then(response => {
              if (response.data.success) {
                this.$message('登录成功')
                localStorage.setItem('token', response.data.token)
                sessionStorage.setItem('doctorId', response.data.doctorId)
                this.$router.push('/doctorPersonalCenter')
              } else {
                this.$message(response.data.errorMsg)
                this.$refs['doctorLoginForm'].resetFields()
              }
            })
          } else {
            this.$message('请正确输入账号和密码')
            console.log('Error Submit')
            return false
          }
        })
      }
    }
  }
</script>
<style>
  .whole-container {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding-top: 200px;
  }

  .backgroundImg {
    position: absolute;
    width: 100%;
    height: 100%;
  }
</style>
