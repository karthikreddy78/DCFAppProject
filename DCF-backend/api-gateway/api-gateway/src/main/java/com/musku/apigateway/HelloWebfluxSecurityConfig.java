//package com.musku.apigateway;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@EnableWebFluxSecurity
//public class HelloWebfluxSecurityConfig {
//
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("user")
//            .roles("USER")
//            .build();
//        return new MapReactiveUserDetailsService(user);
//    }
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http
////            .authorizeExchange()
////                .anyExchange().authenticated()
////                .and()
////            .httpBasic().and()
////            .formLogin();
////        return http.build();
////    }
//}
