package com.kk.novel.common.vueRouter.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuTree<T> {

    private String id;

    private String key;

    private String icon;

    private String title;

    private String value;

    private String text;

    private String permission;

    private String type;

    private Integer order;

    private String path;

    private String component;

    private List<MenuTree<T>> children;

    private String parentId;

    private boolean hasParent = false;

    private boolean hasChildren = false;

    private String createTime;

    private String modifyTime;

    private String isShow;

    public void initChildren(){
        this.children = new ArrayList<>();
    }

}
