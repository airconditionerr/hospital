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
        pageNum: 1
        // reqUrls: {
          // getUserInfoPageUrl: '/hospital-web/user/page/' + this.pageNum + '/' + 10,  // 获取当前用户当前页预约信息请求地址
          // deleteUserUrl: '/hospital-web/user/' + row.userId
        // }
      }
    },
    methods: {
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
        this.getUserInfoPage()
      },
      /**
       * 获取用户信息
       */
      getUserInfoPage () {
        this.$axios.get('/hospital-web/user/page/' + this.pageNum + '/' + 10, {}).then(response => {
          this.userList = response.data
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
      }
    },
    created () {
      this.getUserInfoPage()
    }
  }
</script>
<style></style>
