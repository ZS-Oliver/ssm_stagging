package cn.idea.modules.login.interceptor;

import cn.idea.modules.menu.util.MenuUtil;
import cn.idea.modules.menu.vo.MenuVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��̨��¼������
 *
 * @author llq
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        // TODO Auto-generated method stub
        String requestURI = request.getRequestURI();
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null) {
            //��ʾδ��¼���ߵ�¼ʧЧ
            logger.info("����" + requestURI + "������������");
            String header = request.getHeader("X-Requested-With");
            //�ж��Ƿ���ajax����
            if ("XMLHttpRequest".equals(header)) {
                //��ʾ��ajax����
                Map<String, String> ret = new HashMap<String, String>();
                ret.put("type", "error");
                ret.put("msg", "The login session has timed out or has not been logged in. Please log in again!");
                response.getWriter().write(JSONObject.parseObject(JSON.toJSONString(ret)).toString());
                return false;
            }
            //��ʾ����ͨ������ת��ֱ���ض��򵽵�¼ҳ��
            response.sendRedirect(request.getServletContext().getContextPath() + "/login/login");
            return false;
        }
        //��ȡ�˵�id
        String mid = request.getParameter("_mid");
        if (!StringUtils.isEmpty(mid)) {
            List<MenuVo> allThirdMenu = MenuUtil.getAllThirdMenu((List<MenuVo>) request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
            request.setAttribute("thirdMenuList", allThirdMenu);
        }
        return true;
    }

}
