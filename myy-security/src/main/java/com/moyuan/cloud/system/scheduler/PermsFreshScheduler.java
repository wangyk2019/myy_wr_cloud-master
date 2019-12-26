package com.moyuan.cloud.system.scheduler;

import com.moyuan.cloud.system.entity.MyyPermissions;
import com.moyuan.cloud.system.service.MyyPermissionsService;
import com.moyuan.cloud.system.service.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

@Component
public class PermsFreshScheduler {

    @Autowired
    private MyyPermissionsService permissionsService;

    @Scheduled(initialDelay = 2000, fixedDelay = 3600000)
    public void doThing() {
        List<MyyPermissions> perms = permissionsService.findAll();
        if (perms != null && perms.size() > 0) {
            Jedis jedis = RedisPool.getJedis();
            jedis.select(1);
            MyyPermissions perm = null;
            for (int i = 0; i < perms.size(); i++) {
                perm = perms.get(i);
                jedis.set("ROLE_" + perm.getRole(), perm.getPerms());
            }
            RedisPool.returnResource(jedis);
        }
    }
}
