package com.kk.novel.userInfo.manager;

import com.kk.novel.common.vueRouter.router.RouterMeta;
import com.kk.novel.common.vueRouter.router.VueRouter;
import com.kk.novel.common.vueRouter.router.util.TreeUtil;
import com.kk.novel.userInfo.entity.TMenu;
import com.kk.novel.userInfo.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserManager {

    @Autowired
    private TMenuService tMenuService;

    /**
     * 通过用户名构建 Vue路由
     *
     * @param userId 用户id
     * @return 路由集合
     */
    public Map<String, Object> getUserRouters(Long userId) {
        List<VueRouter<TMenu>> routes = new ArrayList<>();
        List<TMenu> menus = this.tMenuService.queryAllByUserId(userId);
        ArrayList<String> userPermissions =new ArrayList<>();
        Map<String, Object> res = new HashMap<>();
        menus.forEach(menu -> {
            if (menu.getPerms() != null && !"".equals(menu.getPerms())) {
                userPermissions.add(menu.getPerms());
            }
            if ("0".equals(menu.getType())) {
                VueRouter<TMenu> route = new VueRouter<>();
                route.setId(menu.getMenuId().toString());
                route.setParentId(menu.getParentId().toString());
                route.setIcon(menu.getIcon());
                route.setPath(menu.getPath());
                route.setComponent(menu.getComponent());
                route.setName(menu.getMenuName());
                route.setMeta(new RouterMeta(true, menu.getIsShow()));
                route.setOrder(menu.getOrderNum());
                routes.add(route);
            }
        });
        res.put("routers",TreeUtil.buildVueRouter(routes));
        res.put("permissions", userPermissions);
        return res;
    }


}
