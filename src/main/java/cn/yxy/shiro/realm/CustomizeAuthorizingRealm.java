package cn.yxy.shiro.realm;

import cn.yxy.domain.User;
import cn.yxy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/7/21.
 */
public class CustomizeAuthorizingRealm extends AuthorizingRealm {

    private static Logger logger = LogManager.getLogger(CustomizeAuthorizingRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 授权信息
     *
     * @param principals
     * @return 当前用户的roles, permissions
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo start");
        //获取当前登录的用户名
        String currentUsername = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(currentUsername));
        authorizationInfo.setStringPermissions(userService.findPermissions(currentUsername));
        return authorizationInfo;
    }

    /**
     * 验证信息
     *
     * @param authenticationToken
     * @return 基于用户名的验证消息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo start");
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();//shiro自带异常, 没找到帐号
        }
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        return new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),//salt
                this.getName()  //realm name
        );
    }
}
