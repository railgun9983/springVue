<template>
  <div class="view-header-wrapper">
    <div class="view-header-logo-wrapper">
      <img src="../../assets/images/logo.png" alt="logo" />
      <span>KKNOVEL权限管理系统</span>
    </div>
    <div class="view-header-menu-wrapper" v-if="menuType">
      <view-menu></view-menu>
    </div>
    <div class="view-header-ava-wrapper">
      <span>{{ userName }}</span>
      <el-dropdown @command="handleCommand">
        <i class="el-icon-setting" style="margin-right: 15px; color: #FFF"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="1">个人信息</el-dropdown-item>
          <el-dropdown-item command="2">密码修改</el-dropdown-item>
          <el-dropdown-item command="3">主题设置</el-dropdown-item>
          <el-dropdown-item command="4">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-drawer
      title="系统设置"
      :visible.sync="settingShow"
      direction="rtl"
      size="26%"
      custom-class="setting-drawer-wrapper"
    >
      <div class="setting-wrapper">
        <div>
          <p>导航栏位置</p>
          <el-button type="primary" @click="left_right">左右结构</el-button>
          <el-button type="primary" @click="on_off">上下结构</el-button>
        </div>
        <div>
          <p>主体颜色设置</p>
          <ThemePicker></ThemePicker>
        </div>
      </div>
    </el-drawer>
    <el-dialog title="修改密码" :visible.sync="updatePwdVisible">
      <el-form
        :model="updatePwdData"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
      >
        <el-form-item label="原密码" prop="oldPwd">
          <el-input type="password" v-model="updatePwdData.oldPwd"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPwd">
          <el-input type="password" v-model="updatePwdData.newPwd"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPwd">
          <el-input
            type="password"
            v-model="updatePwdData.confirmPwd"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updatePwdVisible = false">取 消</el-button>
        <el-button type="primary" @click="updatePwdMethod('ruleForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <el-dialog title="修改个人基础信息" :visible.sync="updateUserVisible">
      <el-form
        :model="updateUserData"
        ref="userForm"
        label-width="100px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateUserData.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="ssex">
          <el-radio v-model="updateUserData.ssex" label="男">男</el-radio>
          <el-radio v-model="updateUserData.ssex" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="电话" prop="mobile">
          <el-input v-model="updateUserData.mobile"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="updateUserData.email"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="roleName">
          <el-input v-model="updateUserData.roleName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="deptName">
          <el-input v-model="updateUserData.deptName" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateUserMethod"
          >保存</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ViewMenu from "./ViewMenu";
import ThemePicker from "_c/themChoose/ThemePicker";
import { updatePwd, getUser, updateUser } from "_api/login";
export default {
  name: "ViewHeader",
  components: { ViewMenu, ThemePicker },
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.updatePwdData.newPwd) {
        callback(new Error("两次输入密码不一致!"));
      } else if (value === this.updatePwdData.oldPwd) {
        callback(new Error("原密码与新密码一样!"));
      } else {
        callback();
      }
    };
    return {
      settingShow: false,
      updatePwdVisible: false,
      updateUserVisible: false,
      updatePwdData: {},
      updateUserData: {},
      userName: this.$store.state.account.user.userName,
      rules: {
        oldPwd: [
          { required: true, message: "请输入原密码", trigger: "blur" },
          { min: 6, message: "最少6位", trigger: "blur" }
        ],
        newPwd: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          { min: 6, message: "最少6位", trigger: "blur" }
        ],
        confirmPwd: [{ validator: validatePass, trigger: "blur" }]
      }
    };
  },
  computed: {
    themeColor() {
      return this.$store.state.setting.theme;
    },
    menuType() {
      return this.$store.state.setting.sidebar;
    }
  },
  methods: {
    handleCommand(command) {
      switch (command) {
        case "1":
          if (!this.updateUserData.userId) {
            this.queryUserInfo();
          }
          this.updateUserVisible = true;
          break;
        case "2":
          this.updatePwdVisible = true;
          break;
        case "3":
          this.settingShow = true;
          break;
        case "4":
          this.$router.push("/login");
      }
    },
    left_right() {
      this.$store.commit("setting/setSidebar", false);
    },
    on_off() {
      this.$store.commit("setting/setSidebar", true);
    },
    updatePwdMethod(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          updatePwd({
            newPwd: this.updatePwdData.newPwd,
            oldPwd: this.updatePwdData.oldPwd,
            id: this.$store.state.account.user.userId
          }).then(res => {
            if (res.isSuccess) {
              this.$alert("密码修改成功，重新登录！", "密码修改", {
                confirmButtonText: "确定",
                callback: action => {
                  this.$router.push("/login");
                }
              });
            } else {
              this.$message.error("密码修改失败！");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    updateUserMethod() {
      updateUser(this.updateUserData).then(res => {
        if (res.isSuccess) {
          this.$message.success("修改成功");
          this.updateUserVisible = false;
        } else {
          this.$message.error("修改失败");
        }
      });
    },
    queryUserInfo() {
      getUser({ id: this.$store.state.account.user.userId }).then(res => {
        if (res.isSuccess) {
          this.updateUserData = res.data;
        } else {
          this.$message.error("获取用户信息失败！");
        }
      });
    }
  }
};
</script>

<style lang="scss">
.view-header-wrapper {
  display: flex;
  height: 60px;
  width: 100%;
  line-height: 60px;
  overflow: hidden;
  background-color: #545c64;
  .view-header-logo-wrapper {
    flex: 0 0 300px;
    width: 300px;
    img {
      width: 45px;
      height: 45px;
    }
    span {
      display: inline-block;
      height: 100%;
      width: auto;
      font-size: 18px;
      color: #fff;
      vertical-align: center;
    }
  }
  .view-header-menu-wrapper {
    flex: 1;
  }
  .view-header-ava-wrapper {
    position: fixed;
    top: 0;
    right: 40px;
    span {
      display: inline-block;
      height: 100%;
      width: 70px;
      font-size: 14px;
      color: #fff;
    }
  }
  .setting-drawer-wrapper {
    font-size: 14px;
  }
}
</style>
