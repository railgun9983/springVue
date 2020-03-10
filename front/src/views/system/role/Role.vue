<template>
  <div class="system-role-wrapper">
    <div class="system-role-search">
      <search-component :click-type="'_v_system_role'"></search-component>
    </div>
    <div class="system-role-button">
      <el-button type="primary" @click="addRoleButton"  v-hasPermission="['role:add']">新增</el-button>
      <el-button type="danger" @click="deleteRoleButton" v-hasPermission="['role:delete']">删除</el-button>
    </div>
    <div class="system-role-list">
      <el-table
        ref="multipleTable"
        :data="roleData"
        tooltip-effect="dark"
        style="width: 100%"
        border
        @selection-change="roleHandleChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="roleName" label="角色" width="120">
        </el-table-column>
        <el-table-column prop="remark" label="描述" width="120">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间"> </el-table-column>
        <el-table-column prop="modifyTime" label="修改时间"> </el-table-column>
        <el-table-column prop="address" label="操作" width="80">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="roleEditButton(scope.$index, scope.row)"
              v-hasPermission="['role:update']"
              >编辑</el-button
            >
            <el-tag v-hasNoPermission="['role:update']" type="danger"
              >没有权限</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="system-role-edit">
      <el-drawer
        :title="roleEdit.title"
        :visible.sync="roleEdit.roleEditShow"
        :direction="'rtl'"
      >
        <el-form
          :model="roleEdit.roleFormData"
          :rules="roleRules"
          ref="roleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="roleEdit.roleFormData.roleName"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="remark">
            <el-input
              type="textarea"
              v-model="roleEdit.roleFormData.remark"
            ></el-input>
          </el-form-item>
          <el-form-item label="角色权限">
            <el-tree
              ref="tree"
              :data="menuTreeData"
              node-key="id"
              :props="{
                label: 'title',
                children: 'children'
              }"
              show-checkbox
              :check-strictly="true"
            >
            </el-tree>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="roleSave('roleForm')">
              保存
            </el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import {
  selectMenu,
  selectRole,
  getMenu,
  updateRole,
  addRole,
  deleteRole
} from "_api/menu";
import SearchComponent from "_c/menu/searchComponent";
export default {
  name: "Role",
  components: { SearchComponent },
  data() {
    return {
      roleEdit: {
        roleFormData: {},
        title: "角色修改",
        roleEditShow: false
      },
      roleData: [],
      roleCheckData: [],
      menuTreeData: [],
      roleRules: {
        roleName: [
          { required: true, message: "请输入角色名称", trigger: "blur" }
        ],
        remark: [{ required: true, message: "请输入角色描述", trigger: "blur" }]
      }
    };
  },
  created() {
    this.$root.eventHub.$on("_v_system_role", this.queryRole);
  },
  beforeMount() {
    selectRole({}).then(res => {
      if (res.isSuccess) {
        this.roleData = res.data;
      }
    });
  },
  methods: {
    queryRole(param) {
      selectRole({
        roleName: param.searchName,
        createTime: param.searchData[0],
        modifyTime: param.searchData[1]
      }).then(res => {
        if (res.isSuccess) {
          this.roleData = res.data;
        }
      });
    },
    deleteRoleButton() {
      this.$confirm("此操作将永久删除选中的角色, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          if (this.roleCheckData.length > 0) {
            let ids = this.roleCheckData.map(item => item.roleId).join();
            deleteRole({ ids: ids }).then(res => {
              if (res.isSuccess) {
                this.$message.success("删除成功");
              } else {
                this.$message.error("删除失败");
              }
            });
          } else {
            this.$message.warning("当前没有勾选任何选项");
          }
        })
        .catch(() => {
          console.log("取消操作");
        });
    },
    addRoleButton() {
      this.roleEdit.roleFormData = {};
      this.roleEdit.title = "角色添加";
      this.roleEdit.roleEditShow = true;
      if (this.menuTreeData.length === 0) {
        selectMenu({}).then(res => {
          if (res.isSuccess && res.data.rows.children.length > 0) {
            this.menuTreeData = res.data.rows.children;
          }
        });
      }
      this.$nextTick(() => {
        this.$refs.tree.setCheckedKeys([], false);
      });
    },
    roleHandleChange(val) {
      this.roleCheckData = val;
    },
    roleEditButton(index, row) {
      this.roleEdit.title = "角色修改";
      this.roleEdit.roleEditShow = true;
      if (this.menuTreeData.length === 0) {
        selectMenu({}).then(res => {
          if (res.isSuccess && res.data.rows.children.length > 0) {
            this.menuTreeData = res.data.rows.children;
            this.getRoleMenu(row.roleId);
          }
        });
      } else {
        this.getRoleMenu(row.roleId);
      }
      this.roleEdit.roleFormData = row;
    },
    getRoleMenu(id) {
      getMenu({ id: id }).then(res => {
        if (res.isSuccess) {
          this.$nextTick(() => {
            this.$refs.tree.setCheckedKeys(res.data, false);
          });
        } else {
          this.$message.error("获取角色权限列表失败");
        }
      });
    },
    roleSave(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (
            this.roleEdit.roleFormData.roleId &&
            this.roleEdit.roleFormData.roleId !== ""
          ) {
            updateRole({
              role: this.roleEdit.roleFormData,
              list: this.$refs.tree.getCheckedKeys()
            }).then(res => {
              if (res.isSuccess) {
                this.$message.success("修改成功");
              } else {
                this.$message.error("修改失败");
              }
            });
          } else {
            addRole({
              role: this.roleEdit.roleFormData,
              list: this.$refs.tree.getCheckedKeys()
            }).then(res => {
              if (res.isSuccess) {
                this.$message.success("添加成功");
              } else {
                this.$message.error("添加失败");
              }
            });
          }
          this.roleEdit.roleEditShow = false;
          this.roleEdit.roleFormData = {};
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style lang="scss">
.system-role-wrapper {
  padding: 0 50px;
}
</style>
