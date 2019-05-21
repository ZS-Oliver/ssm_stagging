package cn.idea.modules.menu.util;

import cn.idea.modules.menu.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ڲ˵�������һЩ���÷���
 *
 * @author llq
 */
public class MenuUtil {
    /**
     * �Ӹ����Ĳ˵��з������ж����˵�
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
     * ��ȡ���еĶ����˵�
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
     * ��ȡĳ�������˵��µİ�ť
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
