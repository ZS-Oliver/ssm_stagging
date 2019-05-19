package cn.idea.modules.login.vo;


import cn.idea.modules.base.group.SaveGroup;
import cn.idea.modules.base.group.UpdateGroup;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 用户实体
 */
@Data
@Alias("userVo")
public class UserVo {
    @NotBlank(groups = SaveGroup.class, message = "用户名不能为空")
    @Length(min = 4, max = 15, message = "用户名长度应为4-15")
    private String username; // 用户名

    @NotBlank(groups = SaveGroup.class, message = "密码不能为空")
    @Pattern(regexp = "^[0-9A-F]{32}$", groups = {SaveGroup.class, UpdateGroup.class}, message = "密码应为32位16进制")
    private String password; // 密码

    private Integer auth; // 角色
}
