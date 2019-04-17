package com.itheima.shiro;
import com.itheima.domain.Perms;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

     public String getName(String name) {
        return  "userRealm" ;
    }

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println( "------------------------执行授权逻辑---------------------" );
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(  );

        //添加资源授权字符串
//        info.addStringPermission( "user:add" );

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();

        //方法doGetAuthenticationInfo中的 return new SimpleAuthenticationInfo( user, user.getPassword(), "" );就是拿到user对象
        User user = (User) subject.getPrincipal();
        List<Perms> perms = userService.findPerms( user.getId() );
        for (Perms perm:perms){
            System.out.println("-------------"+perm.getPerm()+"------------");
            info.addStringPermission( perm.getPerm() );
        }

        return info;
    }



    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println( "--------------------执行认证逻辑------------------" );

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByname( token.getUsername() );
        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnknownAccountException
        }
        //2判断密码
        //根据用户名到数据库查询密码信息
        String pwd = user.getPassword();
        String salt=user.getPassword_salt();
        //迭代
        Md5Hash md5Hash = new Md5Hash( pwd,salt,2 );
        System.out.println("md5--------------:"+md5Hash);

        return new SimpleAuthenticationInfo( user, md5Hash, ByteSource.Util.bytes( salt ),getName(  ) );
    }
}
