package com.moyuan.myyzuul;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
	private final RouteLocator routeLocator;

	public DocumentationConfig(RouteLocator routeLocator) {
		this.routeLocator = routeLocator;
	}
	
//	@Override
//	public List<SwaggerResource> get() {
//		List<SwaggerResource> resources = new ArrayList<>();
//		List<Route> routes = routeLocator.getRoutes();
//		routes.forEach(route -> {
//			resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
//		});
//		return resources;
//	}
	@Override
	public List<SwaggerResource> get() {
		List resources = new ArrayList<>();
		resources.add(swaggerResource("基础服务", "/myy-baseserver/v2/api-docs", "1.0"));
		resources.add(swaggerResource("摄像头服务", "/myy-camera/v2/api-docs", "1.0"));
		resources.add(swaggerResource("险情服务", "/myy-dangerinfo/v2/api-docs", "1.0"));
		resources.add(swaggerResource("推送服务", "/myy-push/v2/api-docs", "1.0"));
		resources.add(swaggerResource("登录服务", "/myy-security/v2/api-docs", "1.0"));
		resources.add(swaggerResource("打卡服务", "/myy-signin/v2/api-docs", "1.0"));
		resources.add(swaggerResource("水质信息服务", "/myy-waterinfo/v2/api-docs", "1.0"));
		resources.add(swaggerResource("咨询建议服务", "/myy-suggest/v2/api-docs", "1.0"));
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
