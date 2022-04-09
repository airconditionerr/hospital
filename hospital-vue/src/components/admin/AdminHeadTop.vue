<template>
  <el-row class="header-row" id="whole-row">
    <el-col :span="4" offset="1" id="title">
      <el-link type="primary" :underline="false" href="/index">XXXXX医院挂号系统</el-link>
    </el-col>
    <el-col :span="1" v-if="iLogin" offset="16">
      <div>
        <el-dropdown @command="handleCommand">
          <el-link href="/adminOrderManage" :underline="false">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </el-link>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="toAdminOrderManage">订单管理</el-dropdown-item>
            <el-dropdown-item command="toAdminUserManage">用户管理</el-dropdown-item>
            <el-dropdown-item command="toAdminDoctorManage">医生管理</el-dropdown-item>
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
          isLoginUrl: "/hospital-web/admin/status/" + sessionStorage.getItem('adminId')
        }
      }
    },
    methods: {
      isLogin () {
        if (localStorage.getItem("token")==null){
          this.iLogin = false
        } else {
          this.$axios.get(this.reqUrls.isLoginUrl, {
            headers: {
              token: localStorage.getItem("token")
            }
          }).then(response => {
            this.iLogin = response.data.success
            localStorage.setItem("token", response.data.token)
          })
        }
      },
      logOut() {
        localStorage.removeItem("token")
        sessionStorage.removeItem('adminId')
        if (this.$route.path != '/index'){
          this.$router.push('/')
        } else {
          location.reload()
        }
      },
      handleCommand(command) {
        switch (command) {
          case 'toAdminOrderManage':
            this.$router.push('/adminOrderManage')
            break
          case 'logOut':
            this.logOut()
            break
          case 'toAdminUserManage':
            this.$router.push('/adminUserManage')
            break
          case 'toAdminDoctorManage':
            this.$router.push('/adminDoctorManage')
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
</style>
