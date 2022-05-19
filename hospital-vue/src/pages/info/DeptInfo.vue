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
        <!--面包屑-->
        <crumbs></crumbs>
        <el-tabs v-model="activeName" type="card">
          <el-tab-pane label="预约挂号" name="first">
            <!--科室信息-->
            <el-card class="card">
              <el-descriptions title="科室信息">
                <el-descriptions-item label="科室名称">{{ deptInfo.departmentName }}</el-descriptions-item>
                <el-descriptions-item label="科室人数">{{ deptInfo.doctorNum }}</el-descriptions-item>
              </el-descriptions>
            </el-card>
            <el-divider></el-divider>
            <el-table
              :data="doctorList"
              stripe
              style="width: 100%">
              <el-table-column
                prop="id"
                label="医生id"
                width="180">
              </el-table-column>
              <el-table-column
                prop="doctorName"
                label="医生名称"
                width="180">
              </el-table-column>
              <el-table-column
                prop="doctorSex"
                label="医生性别"
                width="180">
              </el-table-column>
              <el-table-column
                prop="doctorPosition"
                label="医生职称"
                width="180">
              </el-table-column>
              <el-table-column
                label="操作">
                <template slot-scope="scope">
                  <el-button @click="handleClick(scope.row)" type="text" size="mini">预约</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!--分页-->
            <div class="block">
              <el-pagination
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage"
                :page-size="10"
                layout="prev, pager, next, jumper"
                :total="totalNum">
              </el-pagination>
            </div>
          </el-tab-pane>
          <el-tab-pane label="科室简介" name="third">{{deptInfo.departmentIntroduction}}</el-tab-pane>
          <el-tab-pane label="科室设备" name="fourth">{{deptInfo.departmentEquipment}}</el-tab-pane>
          <el-tab-pane label="科室荣誉" name="fifth">{{deptInfo.departmentHonor}}</el-tab-pane>
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
    components: {
      HeadTop,
      AsideBanner,
      crumbs
    },
    data () {
      return {
        activeName: 'first',
        deptInfo: {},
        doctorList: [],
        currentPage: 1,
        pageNum: 1, //当前页
        totalNum: 0,
        reqUrls: {
          getThisDeptInfo: '/hospital-web/dept/info/' + this.$route.params.id
        }
      }
    },
    methods: {
      // 获取当前科室信息
      getThisDeptInfo () {
        this.$axios.get(this.reqUrls.getThisDeptInfo, {}).then(response => {
          this.deptInfo = response.data
        })
      },
      /**
       * 预约按钮
       */
      handleClick (row) {
        this.$router.push('/doctorInfo/' + row.id)
      },
      /**
       * 获取当前页医生信息
       */
      getDoctorInfoPageByDept () {
        this.$axios.get('/hospital-web/doctor/page/' + this.pageNum + '/' + 10 + '/' + null + '/' + this.$route.params.id, {}).then(response => {
          this.doctorList = response.data.list
        })
      },
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
        this.getDoctorInfoPageByDept()
      }
    },
    created () {
      this.getThisDeptInfo()
      this.getDoctorInfoPageByDept()
    }
  }
</script>
<style>
</style>
