package cn.idea.modules.base.vo;

import lombok.Data;

/**
 * 通用QVo
 * 提供分页相关的字段
 */
@Data
abstract public class BaseQVo {
    protected int offset;
    protected int size;

    public void setPageRange(int offset, int size) {
        this.offset = offset;
        this.size = size;
    }
}
