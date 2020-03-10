const path = require("path"); // 引入path模块
function resolve(dir) {
  return path.join(__dirname, dir); // path.join(__dirname)设置绝对路径
}

module.exports = {
  publicPath: "/", // 基本路径
  outputDir: "dist", // 输出文件目录
  lintOnSave: false, // eslint-loader 是否在保存的时候检查
  productionSourceMap: true, // 生产环境是否生成 sourceMap 文件
  // 静态文件名称
  assetsDir: "assets",

  // css相关配置
  css: {
    // 是否开启支持 foo.module.css 样式
    requireModuleExtension: true,
    // 是否使用 css 分离插件 ExtractTextPlugin，采用独立样式文件载入，不采用<style>     方式内联至 html 文件中
    extract: true,
    // 是否构建样式地图，false 将提高构建速度
    sourceMap: false,
    // css预设器配置项
    loaderOptions: {
      sass: {
        //prependData: `@import "~@/variables.sass"`
      },
      less: {
        globalVars: {
          primary: "#fff"
        }
      }
    }
  },
  configureWebpack: {
    resolve: {
      alias: {
        _c: "@/components",
        _v: "@/views",
        _u: "@/utils",
        _m: "@/common/mixin",
        _api: "@/api"
      }
    }
  },
  chainWebpack: config => {
    config.module.rules.delete("svg");
    config.module
      .rule("svg-smart")
      .test(/\.svg$/)
      .include.add(resolve("src/assets/svgs"))
      .end()
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]"
      });
  },
  // webpack-dev-server 相关配置
  devServer: {
    open: false, // 是否在构建完成后打开默认浏览器
    host: "127.0.0.1", // 监听地址
    port: 7777, // 端口
    https: false, // 是否有必须通过https的服务
    hotOnly: false,
    proxy: {
      "/api": {
        target: "http://127.0.0.1:7777",
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/"
        }
      },
      "/foo": {
        target: "<other_url>"
      }
    },
    before: app => {}
  },
  // 第三方插件配置
  pluginOptions: {}
};
