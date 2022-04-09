<template>
  <el-row class="header-row" id="whole-row">
    <el-col :span="4" offset="1">
      <el-link type="primary" :underline="false" href="/index"><span id="title">XXXXX医院挂号系统</span></el-link>
    </el-col>
    <el-col :span="1" offset="16">
      <el-link type="primary" :underline="false" href="/login" id="login" v-if="!iLogin">登录</el-link>
    </el-col>
    <el-col :span="1" v-if="!iLogin">
      <el-link type="primary" :underline="false" href="/register" id="register" v-if="!iLogin">注册</el-link>
    </el-col>
    <el-col :span="1" v-if="iLogin" offset="16">
      <div>
        <el-dropdown @command="handleCommand">
          <el-link href="/personalCenter" :underline="false">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </el-link>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="toPersonalCenter">个人中心</el-dropdown-item>
            <el-dropdown-item command="toOrderRecord">预约记录</el-dropdown-item>
            <el-dropdown-item divided command="logOut">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-col>
  </el-row>
</template>
<script>
  export default {
    data () {
      return {
        reqUrls: {
          isLoginUrl: '/hospital-web/user/status/' + sessionStorage.getItem('userId')
        },
        iLogin: '',
      }
    },
    methods: {
      isLogin () {
        if (localStorage.getItem('token') == null) {
          this.iLogin = false
        } else {
          this.$axios.get(this.reqUrls.isLoginUrl, {
            headers: {
              token: localStorage.getItem('token')
            }
          }).then(response => {
            this.iLogin = response.data.success
            localStorage.setItem('token', response.data.token)
          })
        }
      },
      logOut () {
        localStorage.removeItem('token')
        sessionStorage.removeItem('userId')
        if (this.$route.path != '/index') {
          this.$router.push('/')
        } else {
          location.reload()
        }
      },
      handleCommand (command) {
        switch (command) {
          case 'toPersonalCenter':
            this.$router.push('/personalCenter')
            break
          case 'logOut':
            this.logOut()
            break
          case 'toOrderRecord':
            this.$router.push('/orderRecord')
            break
        }
      }
    },
    created () {
      this.isLogin()
    }
  }
</script>
<style>
  #whole-row {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  #title {
    font-size: 20px;
    line-height: 60px;
  }

  #login {
    line-height: 60px;
  }


  #register {
    line-height: 60px;
  }
</style>
