<template>
  <div class="system-user-wrapper">
    <div class="system-user-search">
      <search-component :click-type="'_v_system_user'">
        <label>部门:</label>
        <div class="search-component-item">
          <el-select v-model="deptSelect" clearable placeholder="请选择部门">
            <el-option
              v-for="item in deptOptionData"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId"
            >
            </el-option>
          </el-select>
        </div>
      </search-component>
    </div>
    <div class="system-role-button">
      <el-button type="primary" @click="addUserButton"  v-hasPermission="['user:add']">新增</el-button>
      <el-button type="danger" @click="deleteUserButton" v-hasPermission="['user:delete']">删除</el-button>
    </div>
    <div class="system-user-list">
      <el-table
        ref="userTable"
        :data="userData"
        tooltip-effect="dark"
        style="width: 100%"
        border
        @selection-change="userHandleChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="username" label="用户名" width="120">
        </el-table-column>
        <el-table-column prop="ssex" label="性别" width="120">
        </el-table-column>
        <el-table-column prop="email" label="邮箱"> </el-table-column>
        <el-table-column label="部门">
          <template slot-scope="scope">
            {{ deptName(scope.row.deptId) }}
          </template>
        </el-table-column>
        <el-table-column prop="mobile" label="电话"> </el-table-column>
        <el-table-column label="状态">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.status === '1'">正常</el-tag>
            <el-tag type="danger" v-else>锁定</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间"> </el-table-column>
        <el-table-column prop="address" label="操作" width="80">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="userEditButton(scope.$index, scope.row)"
              v-hasPermission="['user:update']"
              >编辑</el-button
            >
            <el-tag v-hasNoPermission="['user:update']" type="danger"
              >没有权限</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="system-user-edit">
      <el-drawer
        :title="userEdit.title"
        :visible.sync="userEdit.userFormShow"
        :direction="'rtl'"
      >
        <el-form
          :model="userEdit.userFormData"
          :rules="userRules"
          ref="userForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userEdit.userFormData.username"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="ssex">
            <el-select
              v-model="userEdit.userFormData.ssex"
              placeholder="请选择状态"
            >
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userEdit.userFormData.email"></el-input>
          </el-form-item>
          <el-form-item label="角色" prop="roleCheckDatas">
            <el-select
              v-model="roleCheckData"
              multiple
              placeholder="请选择"
              style="width: 100%;"
            >
              <el-option
                v-for="item in roleData"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="部门" prop="deptId">
            <el-select
              v-model="userEdit.userFormData.deptId"
              placeholder="请选择部门"
            >
              <el-option
                v-for="item in deptOptionData"
                :key="item.deptId"
                :label="item.deptName"
                :value="item.deptId"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="电话" prop="mobile">
            <el-input v-model="userEdit.userFormData.mobile"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="userEdit.userFormData.status"
              clearable
              placeholder="请选择状态"
            >
              <el-option label="锁定" value="0"></el-option>
              <el-option label="有效" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="userSave('userForm')">
              保存
            </el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import SearchComponent from "_c/menu/searchComponent";
import {
  selectDept,
  selectUser,
  selectRole,
  addUser,
  updateUser,
  deleteUser
} from "_api/menu";
export default {
  name: "User",
  components: { SearchComponent },
  data() {
    return {
      deptSelect: "",
      deptOptionData: [],
      userData: [],
      roleData: [],
      userCheckData: [],
      roleCheckData: [],
      userEdit: {
        title: "用户修改",
        userFormData: {},
        userFormShow: false
      },
      userRules: {
        username: [
          { required: true, message: "请输入用户名称", trigger: "blur" }
        ],
        ssex: [{ required: true, message: "请输入用户性别", trigger: "blur" }],
        deptId: [
          { required: true, message: "请选择用户部门", trigger: "change" }
        ],
        status: [
          { required: true, message: "请选择用户状态", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.$root.eventHub.$on("_v_system_user", this.queryUserList);
    selectDept().then(res => {
      if (res.isSuccess) {
        this.deptOptionData = res.data;
      } else {
        this.$message.error("获取部门列表失败");
      }
    });
    selectUser({}).then(res => {
      if (res.isSuccess) {
        this.userData = res.data;
      } else {
        this.$message.error("获取用户列表失败");
      }
    });
    selectRole({}).then(res => {
      if (res.isSuccess) {
        this.roleData = res.data;
      } else {
        this.$message.error("获取用户列表失败");
      }
    });
  },
  methods: {
    queryUserList(params) {
      selectUser({
        username: params.searchName,
        createTime: params.searchData[0],
        modifyTime: params.searchData[1],
        deptId: this.deptSelect
      }).then(res => {
        if (res.isSuccess) {
          this.userData = res.data;
        } else {
          this.$message.error("获取用户列表失败");
        }
      });
    },
    userHandleChange(val) {
      this.userCheckData = val;
    },
    userEditButton(index, row) {
      this.userEdit.userFormData = row;
      this.roleCheckData = [Number(row.roleId)];
      this.userEdit.userFormShow = true;
    },
    addUserButton() {
      this.userEdit.userFormData = {};
      this.userEdit.title = "添加用户";
      this.userEdit.userFormShow = true;
      this.roleCheckData = [];
    },
    deleteUserButton() {
      this.$confirm("此操作将永久删除选中的用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          if (this.userCheckData.length > 0) {
            let ids = this.userCheckData.map(item => item.userId).join(",");
            deleteUser({ ids: ids }).then(res => {
              if (res.isSuccess) {
                this.$message.success("删除成功！");
              } else {
                this.$message.error("删除失败！");
              }
            });
          } else {
            this.$message.warning("当前没有勾选任务选项！");
          }
        })
        .catch(() => {});
    },
    userSave(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.userEdit.userFormData.userId) {
            addUser({
              ...this.userEdit.userFormData,
              roleId: this.roleCheckData.join(",")
            }).then(res => {
              if (res.isSuccess) {
                this.$message.success("添加成功！初始密码默认为：123456");
              } else {
                this.$message.error(res.message);
              }
            });
          } else {
            updateUser({
              ...this.userEdit.userFormData,
              roleId: this.roleCheckData.join(",")
            }).then(res => {
              if (res.isSuccess) {
                this.$message.success("修改成功！");
              } else {
                this.$message.error("修改失败");
              }
            });
          }
          this.userEdit.userFormShow = false;
          this.userEdit.userFormData = {};
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    deptName(value) {
      if (this.deptOptionData.length > 0) {
        return this.deptOptionData.find(item => item.deptId === value).deptName;
      } else {
        return value;
      }
    }
  }
};
</script>

<style lang="scss">
.system-user-wrapper {
  padding: 0 50px;
}
</style>
