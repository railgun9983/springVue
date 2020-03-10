<template>
  <div class="search-component-wrapper">
    <label>时间:</label>
    <div class="search-component-item">
      <el-date-picker
        v-model="searchParams.searchData"
        type="datetimerange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
      >
      </el-date-picker>
    </div>
    <label>名称:</label>
    <div class="search-component-item">
      <el-input placeholder="请输入内容" v-model="searchParams.searchName">
        <i slot="prefix" class="el-input__icon el-icon-search"></i>
      </el-input>
    </div>
    <slot></slot>
    <div class="search-component-button">
      <el-button type="primary" @click="querySearch">查询</el-button>
      <el-button type="info" @click="resetParam">重置</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "searchComponent",
  data() {
    return {
      searchParams: {
        searchData: ["", ""],
        searchName: ""
      }
    };
  },
  props: {
    clickType: {
      type: String,
      default: ""
    },
    searchWhere: {
      type: Object,
      default() {
        return this.searchParams;
      }
    }
  },
  methods: {
    querySearch() {
      this.$root.eventHub.$emit(this.clickType, this.searchWhere);
    },
    resetParam() {
      this.searchParams.searchData = ["", ""];
      this.searchParams.searchName = "";
    }
  }
};
</script>

<style lang="scss">
.search-component-wrapper {
  position: relative;
  padding: 10px 0;
  display: flex;
  line-height: 32px;
  .search-component-item {
    flex: 0 0 1;
    width: 30%;
    margin-right: 30px;
  }
  & > label {
    flex: 0 0 50px;
    width: 50px;
  }
  .search-component-button {
    position: absolute;
    right: 20px;
    top: 10px;
  }
}
</style>
