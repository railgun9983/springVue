<template>
  <div class="home-page-wrapper">
    <el-row :gutter="20">
      <el-col :span="12">
        <div>
          <p>欢迎，{{ userName }}!</p>
          <p>每天都是开心活力的一天！</p>
          <p>每天进步一点，不如每天进步两点！</p>
          <p>问题总是无法回避，要学着去解决！</p>
          <p>
            因为要毕业论文答辩了，去完善毕业论文的项目，所以本项目将被搁置，马上步入社会去工作，希望能找到一份好工作！
          </p>
          <p>
            凭着一点java老本，自学springBoot及其周边一个多月，结合以前自学的vue，然后GitHub上面找了一大圈大牛的作品，自己也是一边写一边学，参考了GitHub上面很多作品和实训邦的一个SpringBoot + Spring Security视频教程（b站上找不到了）
          </p>
          <p>
            本项目主要采用：springBoot + Spring Security + JWT + MybatisPlus +
            vue + element-ui完成
          </p>
          <p>
            实现了:
            功能权限、数据权限、动态路由生成动态菜单、接口权限认证等等功能。可以作为一个后台权限管理框架。
          </p>
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <div ref="loginRecord" class="login-record-wrapper"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import echarts from "echarts";
import { selectLoginLog } from "_api/login";
const option = {
  title: {
    text: "今天用户登录统计",
    left: "center"
  },
  tooltip: {
    trigger: "item"
  },
  legend: {
    orient: "vertical",
    left: "left",
    data: ["访问量"]
  },
  series: [
    {
      name: "访问来源",
      type: "pie",
      radius: "55%",
      center: ["50%", "60%"],
      data: [],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0, 0, 0, 0.5)"
        }
      }
    }
  ]
};
export default {
  name: "Home",
  data() {
    return {
      userName: this.$store.state.account.user.userName,
      myEcharts: null
    };
  },
  mounted() {
    this.myEcharts = echarts.init(this.$refs.loginRecord);
    this.myEcharts.setOption(option);
    this.queryLoginRecord();
  },
  methods: {
    queryLoginRecord() {
      new Promise((resolve, reject) => {
        selectLoginLog({}).then(res => {
          if (res.isSuccess) {
            resolve(res.data);
          } else {
            this.$message.error("获取用户登录记录失败");
          }
        });
      }).then(res => {
        let data = res.map(item => {
          return {
            value: item.num,
            name: item.username
          };
        });
        this.myEcharts.setOption({
          series: [
            {
              name: "次数",
              data: data
            }
          ]
        });
      });
    }
  }
};
</script>

<style lang="scss">
.home-page-wrapper {
  padding: 0 50px;
  .login-record-wrapper {
    height: 500px;
    width: 500px;
  }
}
</style>
