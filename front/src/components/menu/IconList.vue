<template>
  <el-dialog
    title="图标选择"
    :visible="iconListShow"
    :show-close="false"
    width="50%"
  >
    <el-tabs type="border-card">
      <el-tab-pane label="指示型">
        <ul class="icon-wrapper">
          <li
            v-for="icon in icons.indicatorIcon"
            :key="icon"
            :class="{ active: iconNew === icon }"
          >
            <i @click="chooseIcon(icon)" :class="icon"></i>
          </li>
        </ul>
      </el-tab-pane>
      <el-tab-pane label="编辑类">
        <ul class="icon-wrapper">
          <li
            v-for="icon in icons.editIcon"
            :key="icon"
            :class="{ active: iconNew === icon }"
          >
            <i @click="chooseIcon(icon)" :class="icon"></i>
          </li>
        </ul>
      </el-tab-pane>
      <el-tab-pane label="其他">
        <ul class="icon-wrapper">
          <li
            v-for="icon in icons.other"
            :key="icon"
            :class="{ active: iconNew === icon }"
          >
            <i @click="chooseIcon(icon)" :class="icon"></i>
          </li>
        </ul>
      </el-tab-pane>
    </el-tabs>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="doClose">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
const indicatorIcon = [
  "el-icon-platform-eleme",
  "el-icon-eleme",
  "el-icon-delete-solid",
  "el-icon-delete",
  "el-icon-user-solid",
  "el-icon-user",
  "el-icon-warning",
  "el-icon-warning-outline",
  "el-icon-picture",
  "el-icon-picture-outline",
  "el-icon-picture-outline-round",
  "el-icon-camera-solid",
  "el-icon-camera",
  "el-icon-video-camera-solid",
  "el-icon-video-camera",
  "el-icon-message-solid",
  "el-icon-bell",
  "el-icon-s-promotion",
  "el-icon-s-home",
  "el-icon-s-release",
  "el-icon-s-ticket",
  "el-icon-s-management",
  "el-icon-s-open",
  "el-icon-s-custom",
  "el-icon-s-opportunity",
  "el-icon-s-data",
  "el-icon-s-check",
  "el-icon-thumb",
  "el-icon-link",
  "el-icon-cpu",
  "el-icon-chat-round",
  "el-icon-microphone",
  "el-icon-full-screen",
  "el-icon-map-location"
];
const editIcon = [
  "el-icon-s-tools",
  "el-icon-setting",
  "el-icon-remove",
  "el-icon-circle-plus",
  "el-icon-success",
  "el-icon-error",
  "el-icon-zoom-in",
  "el-icon-zoom-out",
  "el-icon-remove-outline",
  "el-icon-circle-plus-outline",
  "el-icon-circle-check",
  "el-icon-circle-close",
  "el-icon-check",
  "el-icon-close",
  "el-icon-upload",
  "el-icon-upload2",
  "el-icon-download",
  "el-icon-s-grid",
  "el-icon-menu",
  "el-icon-share",
  "el-icon-d-caret",
  "el-icon-caret-left",
  "el-icon-caret-right",
  "el-icon-caret-bottom",
  "el-icon-caret-top",
  "el-icon-bottom-left",
  "el-icon-bottom-right",
  "el-icon-back",
  "el-icon-right",
  "el-icon-bottom",
  "el-icon-top",
  "el-icon-top-left",
  "el-icon-top-right",
  "el-icon-video-pause",
  "el-icon-video-play",
  "el-icon-refresh"
];
const other = [
  "el-icon-phone",
  "el-icon-phone-outline",
  "el-icon-more",
  "el-icon-more-outline",
  "el-icon-star-on",
  "el-icon-star-off",
  "el-icon-s-goods",
  "el-icon-goods",
  "el-icon-question",
  "el-icon-info",
  "el-icon-s-help",
  "el-icon-help",
  "el-icon-minus",
  "el-icon-plus",
  "el-icon-s-cooperation",
  "el-icon-s-order",
  "el-icon-s-platform",
  "el-icon-s-fold",
  "el-icon-s-unfold",
  "el-icon-s-operation",
  "el-icon-s-shop",
  "el-icon-s-marketing",
  "el-icon-s-flag",
  "el-icon-s-comment",
  "el-icon-s-finance",
  "el-icon-s-claim",
  "el-icon-loading",
  "el-icon-view",
  "el-icon-c-scale-to-original",
  "el-icon-date",
  "el-icon-edit",
  "el-icon-edit-outline",
  "el-icon-folder",
  "el-icon-folder-opened",
  "el-icon-folder-add"
];
export default {
  name: "IconList",
  data() {
    return {
      icons: {
        indicatorIcon,
        editIcon,
        other
      },
      iconNew: ""
    };
  },
  props: {
    iconListShow: {
      type: Boolean,
      default: false
    },
    iconChooseNew: {
      type: String,
      default: ""
    }
  },
  model: {
    prop: "iconChooseNew",
    event: "changeIcon"
  },
  methods: {
    chooseIcon(Icon) {
      this.iconNew = Icon;
    },
    doClose() {
      this.$emit("update:iconListShow", false);
    }
  },
  watch: {
    iconNew: {
      handler(val) {
        this.$emit("changeIcon", val);
      },
      immediate: true
    }
  }
};
</script>

<style lang="scss">
$active-color: #409eff;
.icon-wrapper {
  list-style: none;
  & > li {
    display: inline-block;
    width: 40px;
    height: 40px;
    font-size: 30px;
    line-height: 40px;
    text-align: center;
    &.active,
    &:hover {
      border-radius: 2px;
      border-color: $active-color;
      background-color: $active-color;
      color: #fff;
      transition: all 0.3s;
    }
  }
}
</style>
