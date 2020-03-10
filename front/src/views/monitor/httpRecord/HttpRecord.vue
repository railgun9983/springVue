<template>
  <div class="monitor-http-wrapper">
    <div class="monitor-http-search">
      <search-component
        :click-type="'_v_monitor_httpRecord'"
      ></search-component>
    </div>
    <el-table
      ref="multipleTable"
      :data="httpData"
      tooltip-effect="dark"
      style="width: 100%"
      border
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column
        prop="createTime"
        label="请求时间"
        width="130"
      ></el-table-column>
      <el-table-column prop="method" label="请求方法" show-overflow-tooltip></el-table-column>
      <el-table-column prop="operation" label="操作描述"  width="130"></el-table-column>
      <el-table-column prop="username" label="操作人"  width="90"></el-table-column>
      <el-table-column prop="ip" label="ip地址" width="130"></el-table-column>
      <el-table-column prop="location" label="地址"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import { selectLog } from "_api/monitor";
import SearchComponent from "_c/menu/searchComponent";
export default {
  name: "HttpRecord",
  components: { SearchComponent },
  data() {
    return {
      httpData: []
    };
  },
  created() {
    this.$root.eventHub.$on("_v_monitor_httpRecord", this.queryLog);
  },
  mounted() {
    selectLog({}).then(res => {
      if (res.isSuccess) {
        this.httpData = res.data;
      } else {
        this.$message.error("获取请求log失败");
      }
    });
  },
  methods: {
    queryLog(param) {
      selectLog({
        username: param.searchName,
        createTime: param.searchData[0],
        endTime: param.searchData[1]
      }).then(res => {
        if (res.isSuccess) {
          this.httpData = res.data;
        } else {
          this.$message.error("获取请求log失败");
        }
      });
    }
  }
};
</script>

<style lang="scss">
.monitor-http-wrapper {
  padding: 0 50px;
}
</style>
