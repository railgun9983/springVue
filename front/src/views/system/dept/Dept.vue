<template>
  <div class="system-dept-wrapper">
    <div class="system-dept-search">
      <search-component :click-type="'_v_system_dept'"></search-component>
    </div>
    <div class="system-dept-button">
      <el-button type="primary" @click="addDeptButton" v-hasPermission="['dept:add']">新增</el-button>
      <el-button type="danger" @click="deleteDeptButton" v-hasPermission="['dept:delete']">删除</el-button>
    </div>
    <div class="system-dept-list">
      <el-table
        ref="multipleTable"
        :data="deptData"
        tooltip-effect="dark"
        style="width: 100%"
        row-key="id"
        :treeCheckBox="false"
        border
        @select-all="selectDeptAll"
        @select="selectDeptCheck"
        :tree-props="{ children: 'children', hasChildren: !'hasChildren' }"
        default-expand-all
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="title" label="部门名称" width="120">
        </el-table-column>
        <el-table-column prop="order" label="排序" width="120">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间"> </el-table-column>
        <el-table-column prop="modifyTime" label="修改时间"> </el-table-column>
        <el-table-column prop="address" label="操作" width="80">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="deptEditButton(scope.$index, scope.row)"
              v-hasPermission="['dept:update']"
              >编辑</el-button
            >
            <el-tag v-hasNoPermission="['dept:update']" type="danger"
              >没有权限</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="system-dept-edit">
      <el-drawer
        :title="deptEdit.title"
        :visible.sync="deptEdit.deptFormShow"
        :direction="'rtl'"
      >
        <el-form
          :model="deptEdit.deptFormData"
          :rules="deptRules"
          ref="deptForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="部门名称" prop="title">
            <el-input v-model="deptEdit.deptFormData.title"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="order">
            <el-input v-model="deptEdit.deptFormData.order"></el-input>
          </el-form-item>
          <el-form-item label="所属部门">
            <el-tree
              ref="tree"
              :data="deptData"
              node-key="id"
              :check-strictly="true"
              @check="treeChange"
              :props="{
                label: 'title',
                children: 'children'
              }"
              show-checkbox
              accordion
            >
            </el-tree>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="deptSave('deptForm')">
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
import { selectByDept, updateDept, addDept, deleteDept } from "_api/menu";
export default {
  name: "Dept",
  components: { SearchComponent },
  data() {
    return {
      deptData: [],
      deptCheckData: [],
      deptEdit: {
        deptFormData: {},
        title: "部门修改",
        deptFormShow: false
      },
      deptRules: {
        title: [{ required: true, message: "请输入部门名称", trigger: "blur" }],
        order: [{ required: true, message: "请输入排序", trigger: "blur" }]
      }
    };
  },
  created() {
    selectByDept({}).then(res => {
      if (res.isSuccess) {
        this.deptData = res.data.children;
      } else {
        this.$message.error("获取部门列表失败");
      }
    });
    this.$root.eventHub.$on("_v_system_dept", this.queryDept);
  },
  methods: {
    queryDept(val) {
      selectByDept({
        deptName: val.searchName,
        createTime: val.searchData[0],
        modifyTime: val.searchData[1]
      }).then(res => {
        if (res.isSuccess) {
          this.deptData = res.data.children;
        } else {
          this.$message.error("获取部门列表失败");
        }
      });
    },
    deptEditButton(index, row) {
      this.deptEdit.title = "修改部门";
      this.deptEdit.deptFormShow = true;
      this.deptEdit.deptFormData = row;
      this.$nextTick(() => {
        this.$refs.tree.setCheckedKeys([row.parentId], true);
      });
    },
    addDeptButton() {
      this.deptEdit.deptFormShow = true;
      this.deptEdit.deptFormData = {};
      this.deptEdit.title = "新增部门";
      this.$nextTick(() => {
        this.$refs.tree.setCheckedKeys([], true);
      });
    },
    deleteDeptButton() {
      this.$confirm("此操作将永久删除部门, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          let arr = this.$refs.multipleTable.selection;
          if (arr.length > 0) {
            let ids = arr.map(item => item.id).join(",");
            deleteDept({ ids: ids }).then(res => {
              if (res.isSuccess) {
                this.$message.success("删除成功");
              } else {
                this.$message.error("删除失败");
              }
            });
          } else {
            this.$message.warning("当前没有勾选任何选项！");
          }
        })
        .catch(() => {
          console.log("放弃操作");
        });
    },
    deptHandleChange(val) {
      this.deptCheckData = val;
    },
    treeChange(now, tree) {
      this.$nextTick(() => {
        this.$refs.tree.setCheckedKeys([now.id], true); //[now.id]表示当前只选择只一个id标识
      });
    },
    deptSave(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let parentId = this.$refs.tree.getCheckedKeys();
          if (this.deptEdit.deptFormData.id) {
            updateDept({
              deptId: this.deptEdit.deptFormData.id,
              deptName: this.deptEdit.deptFormData.title,
              orderNum: this.deptEdit.deptFormData.title.order,
              createTime: this.deptEdit.deptFormData.createTime,
              parentId: parentId
            }).then(res => {
              if (res.isSuccess) {
                this.$message.success("部门修改成功");
              } else {
                this.$message.error("部门修改失败");
              }
            });
          } else {
            addDept({
              deptName: this.deptEdit.deptFormData.title,
              orderNum: this.deptEdit.deptFormData.title.order,
              parentId: parentId
            }).then(res => {
              if (res.isSuccess) {
                this.$message.success("部门添加成功");
              } else {
                this.$message.error("部门添加失败");
              }
            });
          }
        }
      });
    },
    deptSelectItem(data, b) {
      data.map(item => {
        if (b) {
          if (item.children && item.children.length > 0) {
            if (
              this.$refs.multipleTable.selection.findIndex(
                se => se.id === item.id
              ) === -1
            ) {
              this.$refs.multipleTable.selection.push(item);
            }
            this.deptSelectItem(item.children, b);
          } else {
            if (
              this.$refs.multipleTable.selection.findIndex(
                se => se.id === item.id
              ) === -1
            ) {
              this.$refs.multipleTable.selection.push(item);
            }
          }
        } else {
          console.log(this.$refs.multipleTable.selection);
          if (item.children && item.children.length > 0) {
            this.$refs.multipleTable.toggleRowSelection(item, false);
            this.deptSelectItem(item.children, b);
          } else {
            this.$refs.multipleTable.toggleRowSelection(item, false);
          }
        }
      });
    },
    selectDeptAll() {
      this.deptCheckData = [];
      if (this.$refs.multipleTable.selection.length === 0) {
        this.deptSelectItem(this.deptData, true);
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    selectDeptCheck(val, row) {
      if (val.findIndex(item => item.id === row.id) === -1) {
        // 父节点取消选择
        console.log(row);
        this.deptSelectItem([row], false);
      } else {
        // 选择父节点
        this.deptSelectItem([row], true);
      }
    }
  }
};
</script>

<style lang="scss">
.system-dept-wrapper {
  padding: 0 50px;
}
</style>
