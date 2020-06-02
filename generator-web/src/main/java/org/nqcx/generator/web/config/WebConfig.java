/*
 * Copyright 2019 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.web.config;

import org.nqcx.doox.commons.lang.url.UrlBuilder;
import org.nqcx.doox.commons.web.cookie.CookieCipherTools;
import org.nqcx.doox.commons.web.cookie.CookieUtils;
import org.nqcx.doox.commons.web.cookie.NqcxCookie;
import org.nqcx.doox.commons.web.interceptor.WebContextInterceptor;
import org.nqcx.doox.commons.web.values.NqcxValues;
import org.nqcx.generator.web.WebInformation;
import org.nqcx.generator.web.interceptor.AppContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author naqichuan 2019-08-02 09:53
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // ========================================================================

    private final ThymeleafViewResolver thymeleafViewResolver;

    // ========================================================================

    public WebConfig(ThymeleafViewResolver thymeleafViewResolver) {
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

    @Bean("homeUrl")
    public UrlBuilder getHomeUrl() {
        return new UrlBuilder("//$baseUrl$");
    }

    // ========================================================================

    private String jdbcCookie = "jdbcCookie";
    private String wsCookie = "wsCookie";
    private String projectCookie = "projectCookie";
    private String authorCookie = "authorCookie";

    @Bean("cookieCipherTools")
    public CookieCipherTools getCookieCipherTools() {
        CookieCipherTools cct = new CookieCipherTools();
        cct.setCharsetName("UTF-8");
        return cct;
    }

    @Bean
    public CookieUtils getCookieUtils() {
        CookieUtils cookieUtils = new CookieUtils();

        Map<String, NqcxCookie> cookieMap = new HashMap<>(1);
        cookieMap.put(jdbcCookie, getJdbcCookie());
        cookieMap.put(wsCookie, getWsCookie());
        cookieMap.put(projectCookie, getProjectCookie());
        cookieMap.put(authorCookie, getAuthorCookie());
        cookieUtils.setCookieMap(cookieMap);

        return cookieUtils;
    }

    private NqcxCookie newCookie(String cookieName) {
        String cookieNormalPath = "/generator-api";
        String cookieNormalDomain = "";
        String cookieNormalKey = "kVgKySMDePHOwrGtTJqA9CfSCrh81wEo";
        int cookieNormalExpires = 31536000;

        NqcxCookie cookie = new NqcxCookie();
        cookie.setCookieCipherTools(getCookieCipherTools());
        cookie.setPath(cookieNormalPath);
        cookie.setDomain(cookieNormalDomain);
        cookie.setKey(cookieNormalKey);
        cookie.setExpiry(cookieNormalExpires);
        cookie.setEncrypt(true);
        cookie.setHttpOnly(true);

        cookie.setName(cookieName);

        return cookie;
    }

    @Bean("jdbcCookie")
    public NqcxCookie getJdbcCookie() {
        return newCookie(jdbcCookie);
    }

    @Bean("wsCookie")
    public NqcxCookie getWsCookie() {
        return newCookie(wsCookie);
    }

    @Bean("projectCookie")
    public NqcxCookie getProjectCookie() {
        return newCookie(projectCookie);
    }

    @Bean("authorCookie")
    public NqcxCookie getAuthorCookie() {
        return newCookie(authorCookie);
    }

    // ========================================================================

    @Bean
    public NqcxValues getNqcxValues() {
        Map<String, String> values = new HashMap<>();

        NqcxValues nvs = new NqcxValues();
        nvs.setValues(values);

        return nvs;
    }

    // ========================================================================

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        Map<String, Object> map = new HashMap<>();
        map.put("nqcxValues", getNqcxValues());

        map.put("homeUrl", getHomeUrl());
//        map.put("WI", new WebInformation());

        if (thymeleafViewResolver != null) {
            thymeleafViewResolver.setStaticVariables(map);
        }
    }

    // ========================================================================

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. web context
        registry.addInterceptor(new WebContextInterceptor())
                .order(0)
                .addPathPatterns("/**")
                .excludePathPatterns("/fonts", "/assets");
        // 2. app context
        registry.addInterceptor(getAppContextInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/fonts", "/assets");
    }

    private AppContextInterceptor getAppContextInterceptor() {
        AppContextInterceptor aci = new AppContextInterceptor();

        List<UrlBuilder> urls = new ArrayList<>(5);

        urls.add(getHomeUrl());

        aci.putNeedProtocolUrls(urls);
        aci.putNeedDomainUrls(urls);

        return aci;
    }

    // ========================================================================

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "classpath:/META-INF/resources/");
    }
}
