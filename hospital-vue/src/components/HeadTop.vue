<template>
  <div>
    <el-row class="header-row" id="whole-row">
      <el-col :span="4" offset="1">
        <el-link type="primary" :underline="false" href="/index"><span id="title">智 慧 健 康 预 约 挂 号 系 统</span></el-link>
      </el-col>
      <el-col :span="1" offset="16" v-if="flag1">
        <el-link type="primary" :underline="false" href="/login" id="login">登 录</el-link>
        <span class="divide-line">|</span>
      </el-col>

      <el-col :span="1" v-if="flag1">
        <el-link type="primary" :underline="false" href="/register" id="register" v-if="!iLogin">注 册</el-link>
      </el-col>
      <el-col :span="1" v-if="flag2" offset="16">
        <div id="head-portrait">
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
  </div>
</template>
<script>
  export default {
    data () {
      return {
        reqUrls: {
          isLoginUrl: '/hospital-web/user/status/' + sessionStorage.getItem('userId')
        },
        iLogin: '',
        flag1: false,
        flag2: false
      }
    },
    methods: {
      isLogin () {
        if (localStorage.getItem('token') == null) {
          this.flag1 = true
        } else {
          this.$axios.get(this.reqUrls.isLoginUrl, {
            headers: {
              token: localStorage.getItem('token')
            }
          }).then(response => {
            if (response.data.success) {
              this.flag2 = true
            } else {
              this.flag1 = true
            }
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
<style scoped>
  #whole-row {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  #title {
    font-size: 22px;
    line-height: 60px;
    font-weight: bold;
    color: white;
  }

  #login {
    font-size: 22px;
    height: 60px;
    line-height: 60px;
  }

  #register {
    font-size: 22px;
    height: 60px;
    line-height: 60px;
  }

  .el-link {
    color: white;
    font-weight: bold;
  }

  .divide-line {
    margin-top: 5px;
    padding-left: 6px;
    font-size: 20px;
    font-weight: bold;
    color: white;
  }

  #head-portrait {
    margin-top: 10px;
  }
</style>
