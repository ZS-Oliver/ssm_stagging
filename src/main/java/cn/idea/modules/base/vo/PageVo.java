package cn.idea.modules.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页需要的实体
 */
@Data
@AllArgsConstructor
public class PageVo<Vo> {
    private Integer no; // 当前页码
    private List<Vo> list; // 当前页条目
    private Integer totalPage; // 总页数
    private Integer total; // 总条目数
}
