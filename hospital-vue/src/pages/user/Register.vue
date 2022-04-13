<template>
  <div>
    <div class="background">
      <el-image class="backgroundImg" :src="imgUrl"></el-image>
    </div>
    <div class="register-container">
      <el-row>
        <el-col span="4" offset="10"><h1>用户注册</h1></el-col>
      </el-row>
      <!-- 用户注册表单 -->
      <el-form :model="registerForm" :rules="rules" ref="registerForm">
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="username">
              <el-input v-model="registerForm.username" placeholder="请输入账号"/>
            </el-form-item>
          </el-col>
          <el-col span="4" class="tips">账号格式：长度为5-15位的数字和字母的组合</el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="password">
              <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"/>
            </el-form-item>
          </el-col>
          <el-col span="4" class="tips">密码格式：长度为5-15位的字符的组合</el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="checkPassword">
              <el-input type="password" v-model="registerForm.checkPassword" placeholder="请确认密码"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="mobile">
              <el-input v-model="registerForm.mobile" placeholder="请输入手机"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="email">
              <el-input v-model="registerForm.email" placeholder="请输入邮箱"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="2" offset="10">
            <el-form-item prop="vcode">
              <el-input v-model="registerForm.vcode" placeholder="请输入验证码"/>
            </el-form-item>
          </el-col>
          <el-col span="10">
            <el-button class="vcodeBtn" @click="sendRegisterVcode('registerForm')">发送验证码</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="name">
              <el-input v-model="registerForm.name" placeholder="请输入姓名"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <el-form-item prop="idCard">
              <el-input v-model="registerForm.idCard" placeholder="请输入身份证"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="4" offset="10">
            <template>
              <el-select v-model="registerForm.sex" placeholder="请选择性别">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </template>
          </el-col>
        </el-row>
        <el-row id="registerRow">
          <el-col span="4" offset="10">
            <el-button type="primary" @click="register('registerForm')">注册</el-button>
            <el-link :underline="false" class="login-link" href="/login">>>去登录</el-link>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      // 确认密码规则
      var validateCheckPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.registerForm.password) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      // 邮箱格式规则
      var validateEmail = (rule, value, callback) => {
        var reg = /^([A-z0-9]{6,18})(\w|\-)+@[A-z0-9]+\.([A-z]{2,3})$/
        if (value === '') {
          callback(new Error('请输入邮箱'))
        } else if (!reg.test(this.registerForm.email)) {
          callback(new Error('邮箱格式错误'))
        }
      }
      return {
        imgUrl: require('../../assets/img/login.jpg'),
        reqUrls: {
          sendRegisterVcodeUrl: '/hospital-web/user/vcode',
          registerFormUrl: '/hospital-web/user/register',
        },
        //注册表单信息
        registerForm: {
          username: '',
          password: '',
          checkPassword: '',
          email: '',
          vcode: '',  // 用户输入的验证码
          mobile: '',
          idCard: '',
          name: '',
          sex: ''
        },
        // 后台发送的验证码
        vcode: '',
        // 性别选项
        options: [{
          value: '男',
          label: '男'
        }, {
          value: '女',
          label: '女'
        }],
        // 注册表单验证
        rules: {
          username: [
            {required: true, message: '账号不能为空', trigger: 'blur'},
            {min: 5, max: 15, message: '账号长度为5-15个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 5, max: 15, message: '密码长度为5-15个字符', trigger: 'blur'}
          ],
          checkPassword: [
            {validator: validateCheckPass, trigger: 'blur'}
          ],
          email: [
            {validator: validateEmail, trigger: 'blur'}
          ],
          vcode: [
            {required: true, message: '验证码不能为空', trigger: 'blur'},
            {min: 4, max: 4, message: '验证码应为4位', trigger: 'blur'}
          ],
          mobile: [
            {required: true, message: '手机不能为空', trigger: 'blur'},
            {min: 11, max: 11, message: '手机号长度为11位', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '姓名不能为空', trigger: 'blur'}
          ],
          idCard: [
            {min: 18, max: 18, message: '身份证长度为18位', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      /**
       * 注册
       * @param formName
       */
      register (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid && this.registerForm.vcode == this.vcode) {
            this.$axios.post(this.reqUrls.registerFormUrl, {
              username: this.registerForm.username,
              password: this.registerForm.password,
              email: this.registerForm.email,
              mobile: this.registerForm.mobile,
              sex: this.registerForm.sex,
              name: this.registerForm.name,
              idCard: this.registerForm.idCard
            }, {}).then(response => {
              if (response.data.success) {
                window.location.href = '/login'
              } else {
                this.errorMsg = response.data.errorMsg
              }
            })
          } else {
            this.$message('请正确输入注册信息')
            console.log('Submit Error')
            return false
          }
        })
      },
      /**
       * 发送注册验证码
       * @param formName
       */
      sendRegisterVcode (formName) {
        this.$refs[formName].validateField('email', (valid) => {
          if (!valid) {
            this.$axios.post(this.reqUrls.sendRegisterVcodeUrl, {
              email: this.registerForm.email
            }).then(response => {
              this.vcode = response.data
            })
          } else {
            this.$message('请先输入邮箱')
            return false
          }
        })
      }
    }
  }
</script>
<style>
  /* 提示信息 */
  .tips {
    font: 12px Extra Small;
    color: dimgray;
    margin-left: 10px;
    height: 40px;
    line-height: 40px;
  }

  #registerRow {
    margin-top: 20px;
  }

  .vcodeBtn {
    margin-left: 10px;
  }

  .register-container {
    padding-top: 100px;
  }

  .login-link {
    margin-left: 20px;
  }

  /* 背景图片 */
  .backgroundImg {
    position: absolute;
    width: 100%;
    height: 100%;
  }
</style>
