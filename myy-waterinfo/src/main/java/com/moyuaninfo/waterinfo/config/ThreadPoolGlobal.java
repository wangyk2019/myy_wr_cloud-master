package com.moyuaninfo.waterinfo.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolGlobal
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/14 14:56
 * @Version 1.0
 **/
public class ThreadPoolGlobal {
    public static final ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
}
