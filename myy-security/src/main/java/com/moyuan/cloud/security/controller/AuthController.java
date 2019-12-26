package com.moyuan.cloud.security.controller;

import com.moyuan.cloud.security.constants.SecurityConstants;
import com.moyuan.cloud.security.utils.JwtTokenUtils;
import com.moyuan.cloud.system.entity.MyyPermissions;
import com.moyuan.cloud.system.service.RedisPool;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
//@RequestMapping(value = "/api")
@Api(value = "授权管理", tags = "授权管理")
public class AuthController {


    @PostMapping("/auth")
    public boolean auth(@RequestParam String token, @RequestParam String uri) throws Exception {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(uri)) {
            return false;
        }
        boolean authed = false;
        MyyPermissions perms = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(1);
        String permstr = "";
        try {
            List<String> authes = JwtTokenUtils.getUserRolesStringByToken(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            for (int i = 0; i < authes.size(); i++) {
//                System.out.println(authes.get(i));
                permstr += jedis.get(authes.get(i)) + ",";
            }
//            System.out.println(permstr);
            RedisPool.returnResource(jedis);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (permstr.contains(uri)) {
            authed = true;
        }
        return authed;
    }

    @PostMapping("/getToken")
    public boolean getToken(@RequestParam String token) throws Exception {
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        boolean exist = jedis.exists(token);
        RedisPool.returnResource(jedis);
        return exist;
    }


}
