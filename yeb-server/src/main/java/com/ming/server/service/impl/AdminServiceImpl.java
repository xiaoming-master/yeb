package com.ming.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Constants;
import com.ming.server.common.ResBean;
import com.ming.server.config.security.JwtTokenUtil;
import com.ming.server.mapper.AdminMapper;
import com.ming.server.mapper.RoleMapper;
import com.ming.server.pojo.Admin;
import com.ming.server.pojo.Role;
import com.ming.server.service.IAdminService;
import com.ming.server.vo.AdminLoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登陆服务
     *
     * @param adminLoginParam
     * @param request
     * @return
     */
    @Override
    public ResBean login(AdminLoginParam adminLoginParam, HttpServletRequest request) {
        //校验验证码
        String kapchaCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isEmpty(adminLoginParam.getCode()) || !kapchaCode.equalsIgnoreCase(adminLoginParam.getCode())) {
            return ResBean.error("验证码错误!");
        }

        //登陆验证
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminLoginParam.getUsername());
        String encodePassword = passwordEncoder.encode(adminLoginParam.getPassword());
        if (userDetails == null || passwordEncoder.matches(encodePassword, adminLoginParam.getPassword())) {
            return ResBean.error("登陆失败，用户名或密码错误");
        }
        if (!userDetails.isEnabled()) {
            return ResBean.error("用户被禁用");
        }

        //将登陆成功的用户信息保存
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);

        //返回结果
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ResBean.success("登陆成功", tokenMap);
    }

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return
     */
    @Override
    public Admin getAdminInfoByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }

    /**
     * 根据用户id获取角色
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> getRolesByAdminId(Integer id) {
        return roleMapper.getRolesByAdminId(id);
    }

}
