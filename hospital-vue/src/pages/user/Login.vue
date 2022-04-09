<template>
  <div class="whole-container">
    <el-row>
      <el-col span="4" offset="10"><h1>用户登录</h1></el-col>
    </el-row>
    <el-row>
      <el-col span="4" offset="10">
        <el-form status-icon="true" :model="loginForm" :rules="rules" ref="loginForm">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login('loginForm')" class="loginBtn">登录</el-button>
            <el-link :underline="false" class="register-link" href="/register">注册账号</el-link>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        reqUrls: {
          loginFormUrl: '/hospital-web/user/login',
        },
        // 登录表单信息
        loginForm: {
          username: '',
          password: '',
        },
        // 登录表单验证
        rules: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'},
            {min: 5, max: 15, message: '账号长度为5-15个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ],
        }
      }
    },
    methods: {
      // 登录
      login (formName) {
        this.$refs[formName].validate((valid) => {
          // 表单验证通过
          if (valid) {
            this.$axios.post(this.reqUrls.loginFormUrl, {
              username: this.loginForm.username,
              password: this.loginForm.password
            }, {}).then(response => {
              if (response.data.success) {
                this.$message('登录成功')
                localStorage.setItem('token', response.data.token)
                sessionStorage.setItem('userId', response.data.userId)
                this.$router.push('/index')
              } else {
                // 登录失败
                this.$message(response.data.errorMsg)
                this.$refs['loginForm'].resetFields()
              }
            })
          } else {
            // 表单验证不通过
            this.$message('请正确输入账号和密码')
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
    margin-top: 200px;
  }

  .register-link {
    margin-left: 180px;
  }
</style>
