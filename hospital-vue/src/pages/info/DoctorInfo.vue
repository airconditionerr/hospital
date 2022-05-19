<template>
  <el-container>
    <el-header>
      <HeadTop></HeadTop>
    </el-header>
    <el-container>
      <el-aside>
        <AsideBanner></AsideBanner>
      </el-aside>
      <el-main>
        <crumbs></crumbs>
        <el-tabs v-model="activeName" type="card">
          <el-tab-pane label="预约挂号" name="first">
            <el-card class="card">
              <el-descriptions title="医生信息">
                <el-descriptions-item label="医生姓名">{{ doctorInfo.doctorName }}</el-descriptions-item>
                <el-descriptions-item label="医生性别">{{ doctorInfo.doctorSex }}</el-descriptions-item>
                <el-descriptions-item label="医生科室">{{ doctorInfo.doctorDepartment }}</el-descriptions-item>
                <el-descriptions-item label="医生学位">{{ doctorInfo.doctorDegree }}</el-descriptions-item>
              </el-descriptions>
            </el-card>
            <el-divider></el-divider>
            <el-calendar first-day-of-week="7">
              <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
              <div
                slot="dateCell"
                slot-scope="{date, data}"
                @click="allcalendar(data)">
                <p :class="data.isSelected ? 'is-selected' : ''">
                  {{ data.day.split('-').slice(2).join('-') }} {{ data.isSelected ? '✔️' : '' }}
                </p>
              </div>
            </el-calendar>
            <el-dialog
              title="预约"
              :visible.sync="dialogVisible"
              width="30%"
              :before-close="handleClose"
              id="appointDia">
              <el-row>
                <el-col span="4" class="tips">
                  病情描述：
                </el-col>
                <el-col span="10">
                  <el-input v-model="diseaseInfo" placeholder="请输入病情"></el-input>
                </el-col>
              </el-row>
              <el-row>
                <el-col class="tips" span="4">日期：</el-col>
                <el-col span="10">
                  <el-input disabled="true" v-model="date" suffix-icon="el-icon-date"></el-input>
                </el-col>
              </el-row>
              <el-row>
                <el-col class="tips" span="4">时间段：</el-col>
                <el-col span="10">
                  <el-select v-model="value" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-col>
              </el-row>

                <span slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="appoint">预 约</el-button>
                </span>

            </el-dialog>

          </el-tab-pane>
          <el-tab-pane label="医生简介" name="third">{{doctorInfo.doctorIntroduction}}</el-tab-pane>
          <el-tab-pane label="医生特长" name="fourth">{{doctorInfo.doctorForte}}</el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeadTop from '../../components/HeadTop'
  import AsideBanner from '../../components/AsideBanner'
  import crumbs from '../../components/crumbs/crumbs'

  export default {
    data () {
      return {
        options: [{
          value: '14:00-15:00',
          label: '14:00-15:00'
        }, {
          value: '10:00-11:00',
          label: '10:00-11:00'
        }],
        value: '',
        activeName: 'first',
        doctorInfo: {},
        date: '',
        userEmail: '',
        userName: '',
        dialogVisible: false,
        diseaseInfo: '',
        reqUrls: {
          getThisdoctorInfoUrl: '/hospital-web/doctor/info/' + this.$route.params.id,
          appointUrl: '/hospital-web/orderRecord'
        }
      }
    },
    components: {
      HeadTop,
      AsideBanner,
      crumbs
    },
    methods: {
      /**
       * 获取当前医生信息
       */
      getThisdoctorInfo () {
        this.$axios.get(this.reqUrls.getThisdoctorInfoUrl, {}).then(response => {
          this.doctorInfo = response.data
        })
      },
      allcalendar (data) {
        this.dialogVisible = true
        this.date = data.day
      },
      /**
       * 预约
       */
      appoint () {
        this.$axios.post(this.reqUrls.appointUrl, {
          departmentName: this.doctorInfo.doctorDepartment,
          doctorName: this.doctorInfo.doctorName,
          transactDate: this.date,
          transactTime: this.value,
          diseaseInfo: this.diseaseInfo,
          email: this.userEmail,
          userName: this.userName
        }, {
          headers: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          if (response.data.success || response.data) {
            this.$message('预约成功')
          } else {
            this.$message('请先登录')
            this.$router.push('/login')
          }
        })
        this.dialogVisible = false
      },
      getUserEmailByUserId () {
        this.$axios.get('/hospital-web/user/info/' + sessionStorage.getItem('userId'), {}).then(response => {
          this.userEmail = response.data.userEmail
          this.userName = response.data.name
        })
      }
    },
    created () {
      this.getThisdoctorInfo()
      this.getUserEmailByUserId()
    }
  }
</script>
<style scoped>
  .tips {
    text-align: center;
    height: 40px;
    line-height: 40px;
  }

  .el-row{
    margin-bottom: 20px;
  }

  .card {
    /*width: 860px;*/
  }

  .el-calendar {
    text-align: center;
  }

  .is-selected {
    width: 100%;
    height: 100%;
    color: #1989FA;
  }
</style>
