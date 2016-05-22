package com.morder.security;

import com.morder.mapper.TuserMapper;
import com.morder.model.Tuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
    private static Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private TuserMapper tuserMapper;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        logger.info("UserDetailsService loadUserByUsername");
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //为了测试方便，用户就写死了，真实环境可以查询数据库
        String userlongid = username;
//        Md5PasswordEncoder encode = new Md5PasswordEncoder();
//        String password = "";
        Tuser tuser = tuserMapper.selectByUname(username);

//        password = encode.encodePassword(password, null);

        return new User(userlongid, tuser.getUpwd(), true, true,
                true, true, authorities);
    }
}
