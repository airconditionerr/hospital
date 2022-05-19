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
        <!--面包屑-->
        <crumbs></crumbs>
        <!--科室列表-->
        <el-table
          :data="deptList"
          stripe
          style="width: 100%"
          size="medium"
        >
          <el-table-column
            prop="id"
            label="科室id"
            width="180">
          </el-table-column>
          <el-table-column
            prop="departmentName"
            label="科室名称"
            width="180">
          </el-table-column>
          <el-table-column
            prop="doctorNum"
            label="医生人数"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="mini">预约</el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="medium"
                placeholder="输入关键字搜索"/>
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
        deptList: [], // 科室信息列表
        currentPage: 1,
        pageNum: 1, // 当前页
        totalNum: 0,
        search: '',
        reqUrls: {
          getAllDeptNumUrl: '/hospital-web/dept/num'
        }
      }
    },
    methods: {
      /**
       * 获取科室总数
       */
      getAllDeptNum () {
        this.$axios.get(this.reqUrls.getAllDeptNumUrl, {}).then(response => {
          this.totalNum = response.data
        })
      },
      /**
       * 预约按钮
       * @param row
       */
      handleClick (row) {
        this.$router.push('/deptInfo/' + row.id)
      },
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
      }
    },
    watch: {
      search: {
        handler () {
          if (this.search == ''){ //搜索框为空
            this.pageNum = 1
            this.$axios.get('/hospital-web/dept/page/' + this.pageNum + '/' + 10 + '/' + null, {}).then(response => {
              this.deptList = response.data.list
              this.getAllDeptNum()
            })
          } else {
            this.pageNum = 1
            this.$axios.get('/hospital-web/dept/page/' + this.pageNum + '/' + 10 + '/' + this.search, {}).then(response => {
              this.deptList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        },
        immediate: true
      },
      pageNum: {
        handler () {
          if (this.search == ''){
            this.$axios.get('/hospital-web/dept/page/' + this.pageNum + '/' + 10 + '/' + null, {}).then(response => {
              this.deptList = response.data.list
              this.getAllDeptNum()
            })
          } else {
            this.$axios.get('/hospital-web/dept/page/' + this.pageNum + '/' + 10 + '/' + this.search, {}).then(response => {
              this.deptList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        }
      }
    }
  }
</script>
