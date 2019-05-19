package cn.idea.modules.base.dao;


import cn.idea.modules.base.vo.BaseQVo;
import cn.idea.modules.base.vo.BaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用mapper接口
 * 注：
 * 1、为了维持数据的稳定，删除方法默认采用逻辑删除。
 * 2、除特殊指明的方法，均获取valid为0的数据
 */
public interface BaseMapper<V extends BaseVo, Q extends BaseQVo> {

    /**
     * 添加一个实体
     */
    int add(V v);

    /**
     * 查看对应实体的部分信息
     */
    V find(Integer id);

    /**
     * 根据id更新实体的部分信息
     */
    int update(V v);

    /**
     * 根据id删除实体
     */
    int delete(Integer id);

    /**
     * 查询共有多少个条目
     */
    Integer getTotalNum();

    /**
     * 根据开始位置和条目数，查询所有实体的部分信息
     */
    List<V> findPagedList(@Param("offset") int offset, @Param("size") int size);

    /**
     * 获得满足查询条件的所有条目
     */
    Integer getQueryTotalNum(Q q);

    /**
     * 获得指定查询条件的查询列表
     */
    List<V> queryPagedList(Q q);
}
