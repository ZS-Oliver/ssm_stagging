package cn.idea.modules.login.util;


import cn.idea.modules.base.constant.UserConst;
import cn.idea.modules.base.vo.BaseVo;

/**
 * 处理用户的工具
 */
public class UserUtil {

    // 管理员身份
    public static final BaseVo ADMIN = BaseVo.ofValid(UserConst.ADMIN_UID);

    /**
     * 用于判断是否为管理员账号
     *
     * @param code 账号
     * @return 是否为管理员账号
     */
    public static boolean isAdminCode(String code) {
        return UserConst.ADMIN_CODE.equals(code);
    }

}
