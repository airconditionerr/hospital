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
        <el-page-header @back="goBack" content="最新公告">
        </el-page-header>
        <el-divider></el-divider>
        <el-table
          :data="noticeList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="noticeId"
            label="公告id"
            width="360" v-if="false">
          </el-table-column>
          <el-table-column
            prop="noticeTitle"
            label="公告名称"
            width="360">
          </el-table-column><el-table-column
            prop="noticeTime"
            label="公告时间"
            width="180">
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
    data() {
      return {
        currentPage: 1,
        noticeList: [],
        pageNum: 1,
        totalNum: 0,
        reqUrls: {
          getAllNoticeNumUrl: '/hospital-web/helpCenter/num'
          // getNoticeInfoPageUrl: '/hospital-web/notice/' + this.pageNum + '/' + 10 // 获取当前页公告信息请求地址
        }
      }
    },
    methods: {
      goBack() {
        this.$router.push('/index')
      },
      /**
       * 翻页
       * @param val
       */
      handleCurrentChange (val) {
        this.pageNum = val
        this.getNoticeInfoPage()
      },
      /**
       * 详情按钮
       * @param row
       */
      handleClick (row) {
        this.$router.push('/noticeInfo/' + row.noticeId)
      },
      getNoticeInfoPage() {
        this.$axios.get('/hospital-web/notice/' + this.pageNum + '/' + 10, {}).then(response => {
          this.noticeList = response.data
        })
      },
      getAllNoticeNum() {
        this.$axios.get(this.reqUrls.getAllNoticeNumUrl, {}).then(response => {
          this.totalNum = response.data
        })
      }
    },
    created () {
      this.getAllNoticeNum()
      this.getNoticeInfoPage()
    }
  }
</script>
<style></style>
