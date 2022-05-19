<template>
  <el-container>
    <el-header>
      <AdminHeadTop></AdminHeadTop>
    </el-header>
    <el-container>
      <el-aside>
        <AdminAsideBanner></AdminAsideBanner>
      </el-aside>
      <el-main>
        <el-table
          :data="userList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="userId"
            label="用户id"
            width="180" v-if="false">
          </el-table-column>
          <el-table-column
            prop="name"
            label="用户姓名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="userSex"
            label="用户性别"
            width="180">
          </el-table-column>
          <el-table-column
            prop="userMobile"
            label="用户手机"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="toUserInfo(scope.row)" type="text" size="mini">详细信息</el-button>
              <el-button @click="deleteUser(scope.row)" type="text" size="mini">删除</el-button>
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
  import AdminHeadTop from '../../components/admin/AdminHeadTop'
  import AdminAsideBanner from '../../components/admin/AdminAsideBanner'

  export default {
    components: {
      AdminHeadTop,
      AdminAsideBanner
    },
    data () {
      return {
        userList: [],
        currentPage: 1,
        pageNum: 1,
        totalNum: 0,
        search: ''
      }
    },
    methods: {
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
      },
      /**
       * 获取用户信息
       */
      getUserInfoPage () {
        this.$axios.get('/hospital-web/user/page/' + this.pageNum + '/' + 10 + '/' + null, {}).then(response => {
          this.userList = response.data.list
        })
      },
      /**
       * 删除用户
       * @param row
       */
      deleteUser (row) {
        this.$axios.delete('/hospital-web/user/' + row.userId, {}).then(response => {
          if (response.data) {
            this.$message('删除成功')
            this.getUserInfoPage()
          }
        })
      },
      /**
       * 到用户详细信息页
       * @param row
       */
      toUserInfo (row) {
        this.$router.push('/adminUserInfo/' + row.userId)
      },
      getUserNum() {
        this.$axios.get('/hospital-web/user/num', {}).then(response => {
          this.totalNum = response.data
        })
      }
    },
    watch: {
      search: {
        handler () {
          if (this.search == '') { //搜索框为空
            this.pageNum = 1
            this.$axios.get('/hospital-web/user/page/' + this.pageNum + '/' + 10 + '/' + null, {}).then(response => {
              this.userList = response.data.list
              this.getUserNum()
            })
          } else {
            this.pageNum = 1
            this.$axios.get('/hospital-web/user/page/' + this.pageNum + '/' + 10 + '/' + this.search, {}).then(response => {
              this.userList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        },
        immediate: true
      },
      pageNum: {
        handler () {
          if (this.search == '') {
            this.$axios.get('/hospital-web/user/page/' + this.pageNum + '/' + 10 + '/' + null, {}).then(response => {
              this.userList = response.data
              this.getUserNum()
            })
          } else {
            this.$axios.get('/hospital-web/user/page/' + this.pageNum + '/' + 10 + '/' + this.search, {}).then(response => {
              this.userList = response.data.list
              this.totalNum = response.data.totalNum
            })
          }
        }
      }
    }
  }
</script>
<style></style>
