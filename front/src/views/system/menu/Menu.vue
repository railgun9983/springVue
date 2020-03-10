<template>
  <div class="system-menu-wrapper">
    <div class="system-menu-search">
      <search-component :click-type="'_v_system_menu'"></search-component>
    </div>
    <div class="system-menu-do-button">
      <el-popover placement="top" width="150" v-model="popoverVisible">
        <p>请选择创建类型？</p>
        <div style="text-align: right; margin: 0">
          <el-button
            type="primary"
            size="mini"
            @click="menuShow.menuAddShow = true"
            >菜单</el-button
          >
          <el-button
            type="info"
            size="mini"
            @click="menuShow.menuButtonAddShow = true"
            >按钮</el-button
          >
        </div>
        <el-button slot="reference" type="primary" v-hasPermission="['menu:add']">新增</el-button>
      </el-popover>
      <el-button type="danger" @click="deleteMenuBut"  v-hasPermission="['menu:delete']">删除</el-button>
    </div>
    <div class="system-menu-list">
      <el-table
        :data="tableData"
        style="width: 100%;margin-bottom: 20px;"
        @selection-change="getCheckMenu"
        row-key="id"
        border
        default-expand-all
        :tree-props="{ children: 'children', hasChildren: !'hasChildren' }"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="title" label="名称" width="180">
        </el-table-column>
        <el-table-column label="图标" width="50">
          <template slot-scope="scope">
            <i :class="scope.row.icon"></i>
          </template>
        </el-table-column>
        <el-table-column label="类型" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type === '0'" type="success">菜单</el-tag>
            <el-tag v-else type="warning">按钮</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="path" label="地址"> </el-table-column>
        <el-table-column prop="component" label="组件"> </el-table-column>
        <el-table-column prop="order" label="排序" width="50"> </el-table-column>
        <el-table-column prop="permission" label="权限"> </el-table-column>
        <el-table-column prop="createTime" label="创建时间"> </el-table-column>
        <el-table-column prop="address" label="修改时间"> </el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="menuEdit(scope.$index, scope.row)"
              v-hasPermission="['menu:update']"
              >编辑</el-button
            >
            <el-tag v-hasNoPermission="['menu:update']" type="danger"
              >没有权限</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="system-menu-edit">
      <el-drawer
        title="菜单修改"
        :visible.sync="menuShow.menuEditShow"
        direction="rtl"
        size="30%"
      >
        <el-form
          :model="menuItemData"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="菜单名称" prop="title">
            <el-input v-model="menuItemData.title"></el-input>
          </el-form-item>
          <el-form-item label="路由地址" prop="path">
            <el-input v-model="menuItemData.path"></el-input>
          </el-form-item>
          <el-form-item label="组件名称" prop="component">
            <el-input v-model="menuItemData.component"></el-input>
          </el-form-item>
          <el-form-item label="相关权限" prop="permission">
            <el-input v-model="menuItemData.permission"></el-input>
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon">
            <el-input v-model="menuItemData.icon">
              <el-button
                slot="append"
                icon="el-icon-more"
                @click="showIconList"
              ></el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="菜单排序" prop="order">
            <el-input v-model="menuItemData.order"></el-input>
          </el-form-item>
          <el-form-item label="是否显示" prop="isShow">
            <el-radio-group v-model="menuItemData.isShow">
              <el-radio label="1">是</el-radio>
              <el-radio label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="上级菜单">
            <el-tree
              ref="tree"
              :data="menuTreeData"
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
            <el-button type="primary" @click="menuEditSave">保存修改</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
      <icon-list
        v-model="iconChooseNew"
        :iconListShow.sync="iconListShow"
      ></icon-list>
    </div>
    <div class="system-menu-add">
      <el-drawer
        title="菜单增加"
        :visible.sync="menuShow.menuAddShow"
        direction="rtl"
        size="30%"
      >
        <el-form
          :model="menuAddItemData"
          :rules="menuRules.menuAddRules"
          ref="menuAddForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="menuAddItemData.menuName"></el-input>
          </el-form-item>
          <el-form-item label="路由地址" prop="path">
            <el-input v-model="menuAddItemData.path"></el-input>
          </el-form-item>
          <el-form-item label="组件名称" prop="component">
            <el-input v-model="menuAddItemData.component"></el-input>
          </el-form-item>
          <el-form-item label="相关权限" prop="permission">
            <el-input v-model="menuAddItemData.perms"></el-input>
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon">
            <el-input v-model="menuAddItemData.icon">
              <el-button
                slot="append"
                icon="el-icon-more"
                @click="showIconListAdd"
              ></el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="菜单排序" prop="order">
            <el-input v-model="menuAddItemData.orderNum"></el-input>
          </el-form-item>
          <el-form-item label="是否显示" prop="isShow">
            <el-radio-group v-model="menuAddItemData.isShow">
              <el-radio label="1">是</el-radio>
              <el-radio label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="上级菜单">
            <el-tree
              ref="tree"
              :data="menuTreeData"
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
            <el-button type="primary" @click="menuAdd('menuAddForm')"
              >立即创建</el-button
            >
            <el-button @click="resetForm('menuAddForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
      <icon-list
        v-model="menuAddItemDataIconNew"
        :iconListShow.sync="iconListShowAdd"
      ></icon-list>
    </div>
    <div class="system-button-edit">
      <el-drawer
        title="按钮修改"
        :visible.sync="menuShow.menuButtonEditShow"
        direction="rtl"
        size="30%"
      >
        <el-form
          :model="menuButtonEditData"
          :rules="menuRules.menuButtonEditRules"
          ref="menuButtonEditForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="按钮名称" prop="title">
            <el-input v-model="menuButtonEditData.title"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单">
            <el-tree
              ref="tree"
              :data="menuTreeData"
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
            <el-button
              type="primary"
              @click="menuButtonSave('menuButtonEditForm')"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
    <div class="system-button-add">
      <el-drawer
        title="按钮添加"
        :visible.sync="menuShow.menuButtonAddShow"
        direction="rtl"
        size="30%"
      >
        <el-form
          :model="menuButtonAddData"
          :rules="menuRules.menuButtonEditRules"
          ref="menuButtonAddForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="按钮名称" prop="menuName">
            <el-input v-model="menuButtonAddData.menuName"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单">
            <el-tree
              ref="tree"
              :data="menuTreeData"
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
            <el-button
              type="primary"
              @click="menuButtonAdd('menuButtonAddForm')"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import { selectMenu, updateMenu, addMenu, deleteMenu } from "_api/menu";
import iconList from "_c/menu/IconList";
import SearchComponent from "_c/menu/searchComponent";
export default {
  name: "Menu",
  components: { SearchComponent, iconList },
  data() {
    return {
      popoverVisible: false,
      tableData: [],
      menuTreeData: [],
      selectOrg: [],
      menuCheckData: [],
      menuShow: {
        menuEditShow: false,
        menuAddShow: false,
        menuButtonEditShow: false,
        menuButtonAddShow: false
      },
      menuAddTree: "",
      menuItemData: {},
      menuButtonEditData: {
        type: 1
      },
      menuButtonAddData: {
        type: 1
      },
      menuAddItemData: {
        type: 0
      },
      iconListShow: false,
      iconChooseNew: "",
      menuAddItemDataIconNew: "",
      menuRules: {
        menuAddRules: {
          menuName: [
            { required: true, message: "请输入菜单名称", trigger: "blur" }
          ],
          path: [
            { required: true, message: "请输入路由地址", trigger: "blur" }
          ],
          component: [
            { required: true, message: "请输入组件名称", trigger: "blur" }
          ]
        },
        menuButtonEditRules: {
          menuName: [
            { required: true, message: "请输入按钮名称", trigger: "blur" }
          ],
          title: [
            { required: true, message: "请输入按钮名称", trigger: "blur" }
          ]
        }
      },
      iconListShowAdd: false
    };
  },
  created() {
    this.$root.eventHub.$on("_v_system_menu", this.queryMenu);
  },
  beforeMount() {
    let _this = this;
    new Promise((resolve, reject) => {
      selectMenu({})
        .then(res => {
          if (res.isSuccess && res.data.rows.children.length > 0) {
            _this.tableData = res.data.rows.children;
            resolve(res.data.rows.children);
          }
        })
        .catch(e => {
          reject();
          console.log(e);
        });
    }).then(res => {
      _this.menuTreeData = _this.filterMenu(res);
    });
  },
  methods: {
    queryMenu(param) {
      selectMenu({
        menuName: param.searchName,
        createTime: param.searchData[0],
        modifyTime: param.searchData[1]
      })
        .then(res => {
          if (res.isSuccess && res.data.rows.children) {
            this.tableData = res.data.rows.children;
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    resetParam() {
      this.searchData = ["", ""];
      this.searchName = "";
    },
    menuEdit(index, row) {
      if (row.type === "0") {
        // 菜单
        this.menuItemData = row;
        this.menuShow.menuEditShow = true;
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys([row.parentId], true);
        });
      } else {
        this.menuButtonEditData = row;
        this.menuShow.menuButtonEditShow = true;
        this.$nextTick(() => {
          // 按钮
          this.$refs.tree.setCheckedKeys([row.parentId], true);
        });
      }
    },
    menuEditSave() {
      let parentId = this.$refs.tree.getCheckedKeys();
      if (parentId.length > 0) {
        this.menuItemData.parentId = parentId[0];
      }
      updateMenu({
        ...this.menuItemData,
        menuId: this.menuItemData.id,
        orderNum: this.menuItemData.order,
        perms: this.menuItemData.permission
      }).then(res => {
        if (res.isSuccess) {
          this.$message("修改成功");
        }
      });
    },
    menuAdd(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let parentId = this.$refs.tree.getCheckedKeys();
          if (parentId.length > 0) {
            this.menuAddItemData.parentId = parentId[0];
          } else {
            this.menuAddItemData.parentId = 0;
          }
          addMenu(this.menuAddItemData).then(res => {
            if (res.isSuccess) {
              this.$message.success("菜单添加成功");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    menuButtonSave(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let parentId = this.$refs.tree.getCheckedKeys();
          if (parentId.length > 0) {
            this.menuButtonEditData.parentId = parentId[0];
          } else {
            this.$message.warning("按钮不可作为父菜单");
            return;
          }
          updateMenu({
            ...this.menuButtonEditData,
            menuName: this.menuButtonEditData.title,
            menuId: this.menuButtonEditData.id
          }).then(res => {
            if (res.isSuccess) {
              this.$message.success("按钮修改成功");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    menuButtonAdd(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let parentId = this.$refs.tree.getCheckedKeys();
          if (parentId.length > 0) {
            this.menuButtonAddData.parentId = parentId[0];
          } else {
            this.$message.warning("按钮不可以作为主菜单！");
            return;
          }
          addMenu(this.menuButtonAddData).then(res => {
            if (res.isSuccess) {
              this.$message.success("按钮添加成功");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    showIconList() {
      this.iconListShow = true;
    },
    showIconListAdd() {
      this.iconListShowAdd = true;
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    treeChange(now, tree) {
      if (now.type === "0") {
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys([now.key], true);
        });
      } else {
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys([now.parentId], true);
        });
      }
    },
    filterMenu(nodes) {
      if (!(nodes && nodes.length)) {
        return [];
      }
      let newChildren = [];
      for (let node of nodes) {
        if (node.type && node.type === "0") {
          // 如果节点符合条件，直接加入新的节点集
          newChildren.push(node);
          node.children = this.filterMenu(node.children);
        } else {
          // 如果当前节点不符合条件，递归过滤子节点，
          // 把符合条件的子节点提升上来，并入新节点集
          newChildren.push(...this.filterMenu(node.children));
        }
      }
      return newChildren;
    },
    deleteMenuBut() {
      this.$confirm("此操作将永久删除该菜单及其子菜单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          if (this.menuCheckData.length > 0) {
            let ids = this.menuCheckData.map(item => item.id).join(",");
            console.log(ids);
            deleteMenu({ ids: ids }).then(res => {
              if (res.isSuccess) {
                this.$message.success("删除成功");
              } else {
                this.$message.error("删除失败");
              }
            });
          } else {
            this.$message.warning("当前没有勾选任何菜单！");
          }
        })
        .catch(() => {});
    },
    getCheckMenu(val) {
      this.menuCheckData = val;
    }
  },
  watch: {
    iconChooseNew(val) {
      this.menuItemData.icon = val;
    },
    menuAddItemDataIconNew(val) {
      this.menuAddItemData.icon = val;
    }
  }
};
</script>

<style lang="scss">
.system-menu-wrapper {
  padding: 0 50px;
  .system-menu-do-button {
    margin-bottom: 3px;
  }
}
</style>
