<template>
  <el-row class="header-row" id="whole-row">
    <el-col :span="4" offset="1">
      <el-link type="primary" :underline="false" href="/index"><span id="title">智 慧 健 康 预 约 挂 号 系 统</span></el-link>
    </el-col>
    <el-col :span="1" v-if="iLogin" offset="16">
      <div id="head-portrait">
        <el-dropdown @command="handleCommand">
          <el-link href="/doctorPersonalCenter" :underline="false">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </el-link>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="toDoctorPersonalCenter">个人中心</el-dropdown-item>
            <el-dropdown-item command="toDoctorOrderManage">预约管理</el-dropdown-item>
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
        iLogin: '',
        reqUrls: {
          isLoginUrl: '/hospital-web/doctor/status/' + sessionStorage.getItem('doctorId')
        }
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
      /**
       * 登出
       */
      logOut () {
        localStorage.removeItem('token')
        sessionStorage.removeItem('doctorId')
        if (this.$route.path != '/index') {
          this.$router.push('/')
        } else {
          location.reload()
        }
      },
      handleCommand (command) {
        switch (command) {
          case 'toDoctorPersonalCenter':
            this.$router.push('/doctorPersonalCenter')
            break
          case 'logOut':
            this.logOut()
            break
          case 'toDoctorOrderManage':
            this.$router.push('/doctorOrderManage')
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
    font-size: 22px;
    line-height: 60px;
    font-weight: bold;
    color: white;
  }

  #head-portrait {
    margin-top: 10px;
  }
</style>
