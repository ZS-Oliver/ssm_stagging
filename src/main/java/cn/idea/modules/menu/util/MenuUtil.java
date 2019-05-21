package cn.idea.modules.menu.util;

import cn.idea.modules.menu.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于菜单操作的一些公用方法
 *
 * @author llq
 */
public class MenuUtil {
    /**
     * 从给定的菜单中返回所有顶级菜单
     *
     * @param menuList
     * @return
     */
    public static List<MenuVo> getAllTopMenu(List<MenuVo> menuList) {
        List<MenuVo> ret = new ArrayList<MenuVo>();
        for (MenuVo menu : menuList) {
            if (menu.getParentId() == 0) {
                ret.add(menu);
            }
        }
        return ret;
    }

    /**
     * 获取所有的二级菜单
     *
     * @param menuList
     * @return
     */
    public static List<MenuVo> getAllSecondMenu(List<MenuVo> menuList) {
        List<MenuVo> ret = new ArrayList<MenuVo>();
        List<MenuVo> allTopMenu = getAllTopMenu(menuList);
        for (MenuVo menu : menuList) {
            for (MenuVo topMenu : allTopMenu) {
                if (menu.getParentId().longValue() == topMenu.getId().longValue()) {
                    ret.add(menu);
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 获取某个二级菜单下的按钮
     *
     * @param menuList
     * @param secondMenuId
     * @return
     */
    public static List<MenuVo> getAllThirdMenu(List<MenuVo> menuList, Long secondMenuId) {
        List<MenuVo> ret = new ArrayList<MenuVo>();
        for (MenuVo menu : menuList) {
            if (menu.getParentId() == secondMenuId) {
                ret.add(menu);
            }
        }
        return ret;
    }
}
