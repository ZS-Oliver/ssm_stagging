package cn.idea.modules.base.vo;



import lombok.Data;

import java.io.Serializable;

/**
 * 通用Vo
 */
@Data
public class BaseVo implements Serializable {

    public static final Byte VALID = 1;
    public static final Byte INVALID = 0;
    protected Integer id; // ID,唯一标示
    protected Byte valid; // 是否有效，支持增删改查的数据进行逻辑删除的标志

    private BaseVo(Integer id, Byte valid) {
        this.id = id;
        this.valid = valid;
    }

    protected BaseVo() {
    }

    public static BaseVo ofValid(Integer id) {
        return new BaseVo(id, VALID);
    }

    public static BaseVo ofInvalid(Integer id) {
        return new BaseVo(id, INVALID);
    }
}
