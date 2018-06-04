package com.tbjj.portal.test;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by liudong on 2017/5/9.
 */
public class Test {
    public static void main(String[] args) {
        PathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/role/?*","/role/1"));
    }
}
