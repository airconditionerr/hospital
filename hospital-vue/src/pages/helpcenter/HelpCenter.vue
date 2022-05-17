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
        <el-page-header @back="goBack" content="帮助中心">
        </el-page-header>
        <el-divider></el-divider>
        <el-table
          :data="helpList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="helpId"
            label="帮助id"
            width="180"
            v-if="false">
          </el-table-column>
          <el-table-column
            prop="helpTitle"
            label="帮助"
            width="540">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="mini">详情</el-button>
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
      crumbs,
      HeadTop,
      AsideBanner
    },
    data () {
      return {
        currentPage: 1,
        helpList: [],
        pageNum: 1,
        totalNum: 0,
        reqUrls: {
          // getHelpInfoPageUrl: '/hospital-web/helpCenter/page/' + this.pageNum + '/' + 10, // 获取当前页公告信息请求地址
          getAllHelpNumUrl: '/hospital-web/helpCenter/num'
        }
      }
    },
    methods: {
      getAllHelpNum() {
        this.$axios.get(this.reqUrls.getAllHelpNumUrl, {}).then(response => {
          this.totalNum = response.data
        })
      },
      goBack () {
        this.$router.push('/index')
      },
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
        this.getHelpInfoPage()
      },
      /**
       * 详情按钮
       * @param row
       */
      handleClick (row) {
        this.$router.push('/helpInfo/' + row.helpId)

      },
      getHelpInfoPage () {
        this.$axios.get('/hospital-web/helpCenter/page/' + this.pageNum + '/' + 10, {}).then(response => {
          this.helpList = response.data
        })
      }
    },
    created () {
      this.getHelpInfoPage()
      this.getAllHelpNum()
    }
  }
</script>
<style></style>
