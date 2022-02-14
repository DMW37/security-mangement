package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(SysUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = userDao.findRolesByUid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        userDao.removeRoles(userId);
        for (Integer rid : ids) {
            userDao.addRoles(userId, rid);
        }
    }

    /**
     * @param username 用户输入的用户名
     * @return UserDetails springSecurity自己的用户对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SysUser sysUser = userDao.findByName(username);
            if (sysUser == null) {
                return null;
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<SysRole> roles = sysUser.getRoles();
            Iterator<SysRole> iterator = roles.iterator();
            while (iterator.hasNext()) {
                authorities.add(new SimpleGrantedAuthority(iterator.next().getRoleName()));
            }
            UserDetails userDetails = new User(sysUser.getUsername(),  sysUser.getPassword(), authorities);
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            //认证失败
            return null;
        }
    }
}
