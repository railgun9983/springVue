<template>
  <div class="container">
    <div class="content">
      <div class="top">
        <div class="header">
          <img alt="logo" class="logo" src="../../assets/images/logo.png" />
          <span class="title">{{ systemName }}</span>
        </div>
        <div class="desc"></div>
      </div>
      <component
        :is="componentName"
        @regist="handleRegister"
        class="main-content"
      ></component>
    </div>
    <view-footer :copyright="copyright"></view-footer>
  </div>
</template>

<script>
import ViewFooter from "_v/common/ViewFooter";
import { getBrowserType } from "_u/browserType";
import Login from "./Login";
import Register from "./Register";

export default {
  name: "Common",
  components: { ViewFooter, Login, Register },
  data() {
    return {
      componentName: "Login"
    };
  },
  created() {
    this.$store.commit("setting/setBrowserType", getBrowserType());
  },
  computed: {
    systemName() {
      return this.$store.state.setting.systemName;
    },
    copyright() {
      return this.$store.state.setting.copyright;
    },
    logoPath() {
      return this.$store.state.settings.logoPath;
    }
  },
  methods: {
    handleRegister(val) {
      this.componentName = val;
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: auto;
  background-size: cover;
  background: #f0f2f5 url(/img/systemLogin.jpg) no-repeat;

  .content {
    padding: 32px 0;
    flex: 1;
    @media (min-width: 768px) {
      padding: 116px 0 10px;
    }
    .top {
      text-align: center;
      .header {
        height: 50px;
        line-height: 50px;
        a {
          text-decoration: none;
        }
        .logo {
          width: 60px;
          height: 60px;
          vertical-align: -26px;
          margin-right: 16px;
        }
        .title {
          font-size: 36px;
          color: rgba(0, 0, 0, 0.85);
          color: #325c80;
          font-family: "微软雅黑", "Myriad Pro", "Helvetica Neue", Arial,
            Helvetica, sans-serif;
          font-weight: 500;
          position: relative;
          top: 6px;
        }
      }
      .desc {
        font-size: 14px;
        color: rgba(0, 0, 0, 0.45);
        margin-top: 12px;
        margin-bottom: 40px;
      }
    }
    .main-content {
      width: 368px;
      margin: 0 auto;
      @media screen and (max-width: 576px) {
        width: 95%;
      }
      @media screen and (max-width: 320px) {
        .captcha-button {
          font-size: 14px;
        }
      }
    }
  }
}
</style>
