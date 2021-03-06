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
    name: '??????',
    component: App,
    children: [
      {
        path: '',
        name: '',
        redirect: '/index'
      },
      {
        path: '/index',
        name: '??????',
        component: Index
      },
      {
        path: '/appointByDept',
        name: '????????????',
        component: AppointByDept,
        meta: {breadNumber: 1, toPath: '/appointByDept'}
      },
      {
        path: '/appointByDoctor',
        name: '????????????',
        component: AppointByDoctor,
        meta: {breadNumber: 2, toPath: '/appointByDoctor'}
      },
      {
        path: '/nucleicAcidTesting',
        name: '????????????',
        component: NucleicAcidTesting,
        meta: {breadNumber: 1, toPath: '/nucleicAcidTesting'}
      },
      {
        path: '/feedback',
        name: '????????????',
        component: Feedback
      },
      {
        path: 'notice',
        name: '????????????',
        component: Notice
      },
      {
        path: 'noticeInfo/:id',
        name: '????????????',
        component: NoticeInfo
      },
      {
        path: '/contact',
        name: '????????????',
        component: Contact
      },
      {
        path: '/helpCenter',
        name: '????????????',
        component: HelpCenter
      },
      {
        path: '/helpInfo/:id',
        name: '????????????',
        component: HelpInfo
      },
      {
        path: '/login',
        name: '??????',
        component: Login
      },
      {
        path: '/register',
        name: '??????',
        component: Register
      },
      {
        path: '/personalCenter',
        name: '??????',
        component: PersonalCenter
      },
      {
        path: '/orderRecord',
        name: '??????',
        component: OrderRecord
      },
      {
        path: '/deptInfo/:id',
        name: '????????????',
        component: DeptInfo
      },
      {
        path: '/doctorInfo/:id',
        name: '????????????',
        component: DoctorInfo
      },
      {
        path: '/orderRecordInfo/:orderId',
        name: '????????????',
        component: OrderRecordInfo
      },
      {
        path: '/adminLogin',
        name: '?????????',
        component: AdminLogin
      },
      {
        path: '/adminOrderManage',
        name: '?????????',
        component: AdminOrderManage
      },
      {
        path: '/adminOrderInfo/:orderId',
        name: '?????????',
        component: AdminOrderInfo
      },
      {
        path: '/adminUserManage',
        name: '?????????',
        component: AdminUserManage
      },
      {
        path: '/adminUserInfo/:userId',
        name: '?????????',
        component: AdminUserInfo
      },
      {
        path: '/adminDoctorManage',
        name: '?????????',
        component: AdminDoctorManage
      },
      {
        path: '/adminDoctorInfo/:id',
        name: '?????????',
        component: AdminDoctorInfo
      },
      {
        path: '/doctorLogin',
        name: '?????????',
        component: DoctorLogin
      },
      {
        path: '/doctorPersonalCenter',
        name: '?????????',
        component: DoctorPersonalCenter
      },
      {
        path: '/doctorOrderManage',
        name: '?????????',
        component: DoctorOrderManage
      },
      {
        path: '/doctorOrderInfo/:orderId',
        name: '?????????',
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
