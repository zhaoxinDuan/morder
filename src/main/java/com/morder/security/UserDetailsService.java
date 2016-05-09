package com.morder.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amis on 2016/3/15.
 */
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private static Log logger = LogFactory.getLog(UserDetailsService.class);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if(orgUM==null){
//            orgUM = (OrgUM)SpringContextUtil.getBean("orgUMImpl");
//        }
        // TODO Auto-generated method stub
        logger.info("UserDetailsService loadUserByUsername");
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //为了测试方便，用户就写死了，真实环境可以查询数据库
//        AICUser aicUser = orgUM.login(username);
        String userlongid = username;
//        Md5PasswordEncoder encode = new Md5PasswordEncoder();
        String password = null;
//        try {
//            password = encode.encodePassword(PasswordVerify.DecodePassword(aicUser==null?"":aicUser.getPassword()), null);
//        } catch (CryptException e) {
//            e.printStackTrace();
//        }
        return new User(userlongid, password, true, true,
                true, true, authorities);
    }
}
