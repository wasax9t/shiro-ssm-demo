package cn.yxy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/20.
 * 只是为了试验功能,都放一起吧
 */
@Controller
public class ShiroController {

    private static Logger logger = LogManager.getLogger(ShiroController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("code", -1001);
            return "common/fail";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("code", -1002);
            return "common/fail";
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return "redirect:/index";
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/admin")
    public String admin() {
        return "success";
    }
}
