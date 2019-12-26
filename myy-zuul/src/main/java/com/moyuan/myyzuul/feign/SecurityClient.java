package com.moyuan.myyzuul.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="MYY-SECURITY",fallback = Fallback.class)
public interface SecurityClient {
    @PostMapping("/auth")
    public boolean auth(@RequestParam String token, @RequestParam String uri);

    @PostMapping("/getToken")
    public boolean getToken(@RequestParam String token);
}
