package com.xwh.demo.satoken;

/**
 * @Author: 谢宇轩
 * @Description: TODO
 * @DateTime: 2022/5/27 14:59
 **/

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {


    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        //实际开发从mysql拿账号权限集合
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {

        return null;
    }



}










