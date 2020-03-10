<template>
  <div class="login">
    <el-form
      :model="loginForm"
      status-icon
      :rules="rules"
      ref="loginForm"
      label-width="100px"
    >
      <el-form-item label="用户名" prop="username">
        <el-input
          type="text"
          v-model="loginForm.username"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="loginForm.password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('loginForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('loginForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { login, getMenu } from "_api/login";

export default {
  name: "Login",
  data() {
    let validateUserName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        callback();
      }
    };
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [{ validator: validateUserName, trigger: "blur" }],
        password: [{ validator: validatePass, trigger: "blur" }]
      }
    };
  },
  computed: {
    systemName() {
      return this.$store.state.setting.systemName;
    },
    copyright() {
      return this.$store.state.setting.copyright;
    }
  },
  created() {
    localStorage.removeItem("USER_TOKEN");
    localStorage.removeItem("USER_ROUTER");
    localStorage.removeItem("USER");
    localStorage.removeItem("PERMISSIONS");
    this.$router.options.routes = [];
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          login(this.loginForm).then(res => {
            if (res.isSuccess) {
              this.$store.commit("account/setToken", res.data.token);
              this.$store.commit("account/setUser", {
                userId: res.data.userId,
                userName: res.data.userName
              });
              this.$router.push("/");
            } else {
              this.$message.error(res.message);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      getMenu(13)
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.error(err);
        });
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style lang="scss" scoped>
.login {
  .icon {
    font-size: 24px;
    color: rgba(0, 0, 0, 0.2);
    margin-left: 16px;
    vertical-align: middle;
    cursor: pointer;
    transition: color 0.3s;

    &:hover {
      color: #1890ff;
    }
  }
}
</style>
