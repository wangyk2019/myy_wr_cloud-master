package com.moyuaninfo.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.HashMap;


@Order(1)
public class StartUpTask implements CommandLineRunner {


	@Override
	public void run(String... args) throws Exception {

		System.out.println("运行系统：" + System.getProperty("os.name"));
//		System.out.println("运行模式：" + (runmodel.equalsIgnoreCase("prod") ? "生产模式" : "开发模式"));
//		System.out.println("NETTY地址：" + MyConstants.NETTYHOST + ":" + MyConstants.NETTYPORT);

		MyConstants.clientsmap = new HashMap<String, String>();
		MyConstants.clientsmap.put("cameraclient","http://127.0.0.1:8040");
		MyConstants.clientsmap.put("liveplayclient","http://127.0.0.1:8111");
		MyConstants.clientsmap.put("warnclient","http://127.0.0.1:8060");
		MyConstants.clientsmap.put("baseclient","http://127.0.0.1:8050");
		System.out.println("*****************************系统参数初始化完毕**************************");
	}

}
