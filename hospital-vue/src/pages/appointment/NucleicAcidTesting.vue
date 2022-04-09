<template>
  <el-container>
    <el-header>
      <HeadTop></HeadTop>
    </el-header>
    <el-container>
      <el-aside class="aside-wrapper">
        <AsideBanner></AsideBanner>
      </el-aside>
      <el-main>
        <crumbs></crumbs>
        <el-form :model="natForm"></el-form>
        <template>
          <el-date-picker
            v-model="natForm.date"
            align="right"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
          <el-select v-model="natForm.time" placeholder="请选择时间">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-button @click="natAppoint" type="primary">预约</el-button>
        </template>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeadTop from '../../components/HeadTop'
  import AsideBanner from '../../components/AsideBanner'
  import crumbs from '../../components/crumbs/crumbs'

  export default {
    components: {
      crumbs,
      HeadTop,
      AsideBanner
    },
    data() {
      return {
        natForm: {
          date: '',
          time: ''
        },
        options: [{
          value: '6:00-12:00',
          label: '6:00-12:00'
        }, {
          value: '13:00-16:00',
          label: '13:00-16:00'
        }],
        reqUrls: {
          natAppointUrl: '/hospital-web/nat'
        }
      }
    },
    methods: {
      natAppoint() {
        this.$axios.post(this.reqUrls.natAppointUrl, {
          transactDate: this.natForm.date,
          transactTime: this.natForm.time
        }, {
          headers: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          if (response.data.success || response.data){
            this.$message('预约成功')
          } else {
            this.$message('请先登录')
            this.$router.push('/login')
          }
        })
      }
    }
  }
</script>
<style></style>
