package cn.idea.modules.menu.vo;

import cn.idea.modules.base.group.SaveGroup;
import cn.idea.modules.base.vo.BaseVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Alias("menuVo")
public class MenuVo extends BaseVo {

    @NotBlank(groups = SaveGroup.class, message = "id不能为空")
    private Long parentId;//父类id
    private Long _parentId;//父类id,用来匹配easyui的父类id
    private String name;//菜单名称
    private String url;//点击后的url
    private String icon;//菜单icon图表
}
