package com.kk.novel.common.vueRouter.router.util;

import com.kk.novel.common.vueRouter.router.MenuTree;
import com.kk.novel.common.vueRouter.router.RouterMeta;
import com.kk.novel.common.vueRouter.router.VueRouter;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    protected TreeUtil() {

    }

    private final static String TOP_NODE_ID = "0";

    /**
     * 用于构建菜单或部门树
     *
     * @param nodes nodes
     * @param <T>   <T>
     * @return <T> Tree<T>
     */
    public static <T> MenuTree<T> build(List<MenuTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<MenuTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(node -> {
            String pid = node.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                topNodes.add(node);
                return;
            }
            for (MenuTree<T> n : nodes) {
                String id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null)
                        n.initChildren();
                    n.getChildren().add(node);
                    node.setHasParent(true);
                    n.setHasChildren(true);
                    n.setHasParent(true);
                    return;
                }
            }
            if (topNodes.isEmpty())
                topNodes.add(node);
        });


        MenuTree<T> root = new MenuTree<>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setHasChildren(true);
        root.setChildren(topNodes);
        root.setText("root");
        return root;
    }


    /**
     * 构造前端路由
     *
     * @param routes routes
     * @param <T>    T
     * @return ArrayList<VueRouter<T>>
     */
    public static <T> ArrayList<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes) {
        if (routes == null) {
            return null;
        }
        List<VueRouter<T>> topRoutes = new ArrayList<>();
        VueRouter<T> router = new VueRouter<>();
        router.setName("系统主页");
        router.setPath("/home");
        router.setComponent("home/Home");
        router.setIcon("el-icon-s-home");
        router.setChildren(null);
        router.setMeta(new RouterMeta(false,  "1"));
        topRoutes.add(router);
        routes.forEach(route -> {
            String parentId = route.getParentId();
            if (parentId == null || TOP_NODE_ID.equals(parentId)) {
                topRoutes.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null)
                        parent.initChildren();
                    parent.getChildren().add(route);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });

        ArrayList<VueRouter<T>> list = new ArrayList<>();
        VueRouter<T> root = new VueRouter<>();
        root.setName("主页");
        root.setComponent("ViewMain");
        root.setIcon("none");
        root.setPath("/");
        root.setRedirect("/home");
        root.setChildren(topRoutes);
        root.setMeta(new RouterMeta(true, "1"));
        list.add(root);

        root = new VueRouter<>();
        root.setName("404");
        root.setComponent("exception/404");
        root.setIcon("none");
        root.setPath("*");
        root.setChildren(topRoutes);
        root.setMeta(new RouterMeta(true, "0"));
        list.add(root);
        return list;
    }
}