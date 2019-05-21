package cn.idea.modules.menu.dao;

import cn.idea.modules.menu.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

    /**
     * 添加菜单
     *
     * @param mv
     * @return
     */
    public int add(@Param("mv") MenuVo mv);

    public List<MenuVo> findList(@Param("querryMap") Map<String, Object> queryMap);

    public List<MenuVo> findTopList();

    public int getTotal(@Param("queryMap") Map<String, Object> queryMap);

    public int edit(@Param("mv") MenuVo mv);

    public int delete(@Param("id") Long id);

    public List<MenuVo> findChildernList(@Param("parentId") Long parentId);

    public List<MenuVo> findListByIds(@Param("ids") String ids);

}
