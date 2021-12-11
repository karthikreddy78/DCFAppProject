package com.musku.apigateway;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Configuration
public class ApiGatewayConfig {

    @Autowired
    RouteDefinitionLocator locator;
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
    {
        Function<PredicateSpec, Buildable<Route>> routeFunction=
                p->p.path("/get")
                        .filters(f->f
                                .addRequestHeader("Myheader","Myuri")
                                .addRequestParameter("Myparam","newget")
                        )
                        .uri("http://httpbin.org:80");
        return builder
                .routes()
                .route(routeFunction)
                .route(p->p.path("/admin/**").uri("lb://admin"))
                .route(p->p.path("/users/**").uri("lb://users"))
                .route(p->p.path("/coupons/**").uri("lb://coupons"))
                .route(p->p.path("/company/**").uri("lb://company"))
                .build();
    }

//    @Bean
//    public List<GroupedOpenApi> apis() {
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
////        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*")).forEach(routeDefinition -> {
////            String name = routeDefinition.getId().replaceAll("", "");
////            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build();
////        });
//        System.out.print(definitions);
//        return groups;
//    }
}
