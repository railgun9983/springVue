package com.kk.novel.common.vueRouter.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Vue路由 Meta
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;

    private Boolean closeable;

    private String isShow; // t：显示在菜单栏和菜单管理列表 f： 不显示菜在单栏和菜单管理列表
}
