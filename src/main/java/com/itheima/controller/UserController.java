package com.itheima.controller;

import com.itheima.amqb.send.MqMsgSend;
import com.itheima.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

 import java.util.Map;

@Controller
public class UserController {

    @RequestMapping("/freemark")
    public String test(Map<String, Object> result){
        result.put( "name","xxx" );
        return "index";
    }

    /**
     * 未授权跳转页面
     * @param result
     * @return
     */
    @RequestMapping("/unAuth")
    public String unAuth(Map<String, Object> result){
        result.put( "auth","页面忘记授权咯" );
        return "unAuth";
    }

    @Autowired
    private UserService userService;
    @RequestMapping("/add")
    public String add(){
        return "add";
    }
    @RequestMapping("/update")
    public String update(){
        return "update";
    }
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    /**
     * 登录逻辑处理
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String  name,String password,Model model){
        /**
         * 使用shiro编写认证操作
         */
        //1、获取subject
        Subject subject= SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken( name,password );
        try {
            subject.login( token );
            System.out.println("登入成功");
            return "index";
        } catch (UnknownAccountException e) {
//            e.printStackTrace();
            //登入失败，用户名不存d在
            model.addAttribute( "msg","用户名不存在" );
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute( "msg","密码错误" );
            return "login";
        }
    }

    /**
     * 测试mq Send
     */
    @Autowired
    private MqMsgSend msgSend;
    @RequestMapping("send")
    public String send1() {
        System.out.println("****************** 验证码********************************");
        msgSend.send(  );
        return "index";
    }

}






















