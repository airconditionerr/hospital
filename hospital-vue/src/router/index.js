import Vue from 'vue'
import Router from 'vue-router'

import App from '../App'

const Index = r => require.ensure([], () => r(require('../pages/Index')), 'Index')
const AppointByDept = r => require.ensure([], () => r(require('../pages/appointment/AppointByDept')), 'AppointByDept')
const AppointByDoctor = r => require.ensure([], () => r(require('../pages/appointment/AppointByDoctor')), 'AppointByDoctor')
const NucleicAcidTesting = r => require.ensure([], () => r(require('../pages/appointment/NucleicAcidTesting')), 'NucleicAcidTesting')
const Contact = r => require.ensure([], () => r(require('../pages/contact/Contact')), 'Contact')
const Notice = r => require.ensure([], () => r(require('../pages/notice/Notice')), 'Notice')
const NoticeInfo = r => require.ensure([], () => r(require('../pages/notice/NoticeInfo')), 'NoticeInfo')
const Feedback = r => require.ensure([], () => r(require('../pages/feedback/Feedback')), 'Feedback')
const HelpCenter = r => require.ensure([], () => r(require('../pages/helpcenter/HelpCenter')), 'HelpCenter')
const HelpInfo = r => require.ensure([], () => r(require('../pages/helpcenter/HelpInfo')), 'HelpInfo')
const Login = r => require.ensure([], () => r(require('../pages/user/Login')), 'Login')
const Register = r => require.ensure([], () => r(require('../pages/user/Register')), 'Register')
const PersonalCenter = r => require.ensure([], () => r(require('../pages/user/PersonalCenter')), 'PersonalCenter')
const OrderRecord = r => require.ensure([], () => r(require('../pages/user/OrderRecord')), 'OrderRecord')
const DeptInfo = r => require.ensure([], () => r(require('../pages/info/DeptInfo')), 'DeptInfo')
const DoctorInfo = r => require.ensure([], () => r(require('../pages/info/DoctorInfo')), 'DoctorInfo')
const OrderRecordInfo = r => require.ensure([], () => r(require('../pages/user/OrderRecordInfo')), 'OrderRecordInfo')
const AdminLogin = r => require.ensure([], () => r(require('../pages/admin/AdminLogin')), 'AdminLogin')
const AdminOrderManage = r => require.ensure([], () => r(require('../pages/admin/AdminOrderManage')), 'AdminOrderManage')
const AdminOrderInfo = r => require.ensure([], () => r(require('../pages/admin/AdminOrderInfo')), 'AdminOrderInfo')
const AdminDoctorManage = r => require.ensure([], () => r(require('../pages/admin/AdminDoctorManage')), 'AdminDoctorManage')
const AdminDoctorInfo = r => require.ensure([], () => r(require('../pages/admin/AdminDoctorInfo')), 'AdminDoctorInfo')
const AdminUserManage = r => require.ensure([], () => r(require('../pages/admin/AdminUserManage')), 'AdminUserManage')
const AdminUserInfo = r => require.ensure([], () => r(require('../pages/admin/AdminUserInfo')), 'AdminUserInfo')
const DoctorLogin = r => require.ensure([], () => r(require('../pages/doctor/DoctorLogin')), 'DoctorLogin')
const DoctorPersonalCenter = r => require.ensure([], () => r(require('../pages/doctor/DoctorPersonalCenter')), 'DoctorPersonalCenter')
const DoctorOrderManage = r => require.ensure([], () => r(require('../pages/doctor/DoctorOrderManage')), 'DoctorOrderManage')
const DoctorOrderInfo = r => require.ensure([], () => r(require('../pages/doctor/DoctorOrderInfo')), 'DoctorOrderInfo')

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    name: '首页',
    component: App,
    children: [
      {
        path: '',
        name: '',
        redirect: '/index'
      },
      {
        path: '/index',
        name: '首页',
        component: Index
      },
      {
        path: '/appointByDept',
        name: '科室挂号',
        component: AppointByDept,
        meta: {breadNumber: 1, toPath: '/appointByDept'}
      },
      {
        path: '/appointByDoctor',
        name: '医生挂号',
        component: AppointByDoctor,
        meta: {breadNumber: 2, toPath: '/appointByDoctor'}
      },
      {
        path: '/nucleicAcidTesting',
        name: '核酸检测',
        component: NucleicAcidTesting,
        meta: {breadNumber: 1, toPath: '/nucleicAcidTesting'}
      },
      {
        path: '/feedback',
        name: '意见反馈',
        component: Feedback
      },
      {
        path: 'notice',
        name: '最新公告',
        component: Notice
      },
      {
        path: 'noticeInfo/:id',
        name: '公告详情',
        component: NoticeInfo
      },
      {
        path: '/contact',
        name: '联系我们',
        component: Contact
      },
      {
        path: '/helpCenter',
        name: '帮助中心',
        component: HelpCenter
      },
      {
        path: '/helpInfo/:id',
        name: '帮助中心',
        component: HelpInfo
      },
      {
        path: '/login',
        name: '登录',
        component: Login
      },
      {
        path: '/register',
        name: '注册',
        component: Register
      },
      {
        path: '/personalCenter',
        name: '注册',
        component: PersonalCenter
      },
      {
        path: '/orderRecord',
        name: '注册',
        component: OrderRecord
      },
      {
        path: '/deptInfo/:id',
        name: '科室信息',
        component: DeptInfo
      },
      {
        path: '/doctorInfo/:id',
        name: '医生信息',
        component: DoctorInfo
      },
      {
        path: '/orderRecordInfo/:orderId',
        name: '订单信息',
        component: OrderRecordInfo
      },
      {
        path: '/adminLogin',
        name: '管理员',
        component: AdminLogin
      },
      {
        path: '/adminOrderManage',
        name: '管理员',
        component: AdminOrderManage
      },
      {
        path: '/adminOrderInfo/:orderId',
        name: '管理员',
        component: AdminOrderInfo
      },
      {
        path: '/adminUserManage',
        name: '管理员',
        component: AdminUserManage
      },
      {
        path: '/adminUserInfo/:userId',
        name: '管理员',
        component: AdminUserInfo
      },
      {
        path: '/adminDoctorManage',
        name: '管理员',
        component: AdminDoctorManage
      },
      {
        path: '/adminDoctorInfo/:id',
        name: '管理员',
        component: AdminDoctorInfo
      },
      {
        path: '/doctorLogin',
        name: '管理员',
        component: DoctorLogin
      },
      {
        path: '/doctorPersonalCenter',
        name: '管理员',
        component: DoctorPersonalCenter
      },
      {
        path: '/doctorOrderManage',
        name: '管理员',
        component: DoctorOrderManage
      },
      {
        path: '/doctorOrderInfo/:orderId',
        name: '管理员',
        component: DoctorOrderInfo
      }
    ]
  }

  ]

})
//   .beforeEach((to, from, next) => {
//   if (to.path === '/login') {
//     next()
//   } else if (to.path === '/personalCenter') {
//     let token = localStorage.getItem('token')
//     if (token === null || token === '') {
//       next('/login')
//     } else {
//       next()
//     }
//   } else {
//     next()
//   }
// })
