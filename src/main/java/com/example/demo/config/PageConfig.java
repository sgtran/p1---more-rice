package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/uno").setViewName("uno");
        registry.addViewController("/cards").setViewName("unoGame");
        registry.addViewController("/minilabTest").setViewName("minilabTest");
        registry.addViewController("/leaderboard").setViewName("leaderboard");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/GCD").setViewName("GCD");
        registry.addViewController("/data").setViewName("data");
        registry.addViewController("/kevinSort").setViewName("kevinSort");
        registry.addViewController("/andrewSort").setViewName("andrewSort");
        registry.addViewController("/alexSort").setViewName("alexSort");
        registry.addViewController("/kevinSort").setViewName("kevinSort");
        registry.addViewController("/kevinLL").setViewName("kevinLL");
        registry.addViewController("/andrewRealSort").setViewName("andrewRealSort");
        registry.addViewController("/andrewLL").setViewName("andrewLL");
        registry.addViewController("/alexSorts2").setViewName("alexSorts2");
        registry.addViewController("/alexLL").setViewName("alexLL");
        registry.addViewController("/seanLL").setViewName("seanLL");
        registry.addViewController("/seanSort").setViewName("seanSort");
        registry.addViewController("/atharvaLL").setViewName("atharvaLL");
        registry.addViewController("/atharvaSort").setViewName("atharvaSort");
        registry.addViewController("/binSearch").setViewName("binSearch");
        registry.addViewController("/binResult").setViewName("binResult");
        registry.addViewController("/Palindrome").setViewName("Palindrome");
        registry.addViewController("/binSearchAP").setViewName("binSearchAP");
        registry.addViewController("/Palindromeresult").setViewName("Palindromeresult");
        registry.addViewController("/unoInit").setViewName("unoInit");
    }
}



