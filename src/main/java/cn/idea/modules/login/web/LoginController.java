package cn.idea.modules.login.web;

import cn.idea.modules.base.exception.ServiceException;
import cn.idea.modules.login.service.UserService;
import cn.idea.modules.login.vo.UserVo;
import cn.idea.utils.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {
        model.setViewName("login/login");
        return model;
    }

    /**
     * 系统登录后的欢迎页
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(ModelAndView model, HttpServletRequest request) {
        model.setViewName("login/welcome");
        return model;
    }

    /**
     * 系统登录后的主页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model) {
        model.setViewName("login/index");
        return model;
    }

    /**
     * 登录表单提交
     *
     * @param uv
     * @param cpacha
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> loginAct(UserVo uv, String cpacha, HttpServletRequest request) throws ServiceException {
        Map<String, String> ret = new HashMap<>();

//        ServiceException.when(StringUtils.isEmpty(uv.getUsername()) || StringUtils.isEmpty(uv.getPwd()), "账号或密码为空,请重新填写");
//        ServiceException.when(StringUtils.isEmpty(cpacha), "请填写验证码");
//
//        Object loginCpacha = request.getSession().getAttribute("loginCpacha");
//
//        ServiceException.when(loginCpacha == null, "会话已超时,请刷新页面");
//
//        boolean isSame = cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase());
//
//        ServiceException.when(!isSame, "验证码错误,请重新填写");

        if (StringUtils.isEmpty(cpacha)) {
            ret.put("type", "error");
            ret.put("msg", "please enter verification code");
            return ret;
        }
        if (StringUtils.isEmpty(uv.getUsername()) || StringUtils.isEmpty(uv.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "Information not filled out");
            return ret;
        }

        Object loginCpacha = request.getSession().getAttribute("loginCpacha");

        if (loginCpacha == null) {
            ret.put("type", "error");
            ret.put("msg", "Session timed out, please refresh the page");
            return ret;
        }
        if (!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "Verification code error");
            return ret;
        }

        UserVo uvo = userService.findByUsername(uv.getUsername());
        if (uvo == null) {
            ret.put("type", "error");
            ret.put("msg", "username is not exists");
            return ret;
        }
        if (!uv.getPassword().equals(uvo.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "password is false");
            return ret;
        }

        request.getSession().setAttribute("admin", uvo);

        ret.put("type", "success");
        ret.put("msg", "login successful！");

        return ret;
    }

    /**
     * 验证码生成
     *
     * @param vcodeLen   验证码长度
     * @param width      图片宽度
     * @param height     图片高度
     * @param cpachaType 验证码类型
     * @param request
     * @param response
     */
    @RequestMapping(value = "/cpacha", method = RequestMethod.GET)
    public void generateCpacha(
            @RequestParam(name = "vl", required = false, defaultValue = "4") Integer vcodeLen,
            @RequestParam(name = "w", required = false, defaultValue = "100") Integer width,
            @RequestParam(name = "h", required = false, defaultValue = "30") Integer height,
            @RequestParam(name = "type", required = true, defaultValue = "loginCpacha") String cpachaType,
            HttpServletRequest request,
            HttpServletResponse response) {

        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
        String generatorVCode = cpachaUtil.generatorVCode();

        request.getSession().setAttribute(cpachaType, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
