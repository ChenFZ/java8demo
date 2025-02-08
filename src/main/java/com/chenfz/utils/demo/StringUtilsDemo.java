/**
 * Copyright (C), 2015-2023
 * Description: lang3 string utils demo
 * <author>          <time>          <version>          <desc>
 * chenfz           2023/3/22 16:11           1.0              描述
 */
package com.chenfz.utils.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class StringUtilsDemo {

    public static void main(String[] args) {
        String blank = " ";
        String empty = "";
        String a = " abc ";
        System.out.println(Objects.isNull(StringUtils.trimToNull(blank)));
        System.out.println(empty.equals(StringUtils.trimToEmpty(blank)));

        String listStr = "a,b,c,d";
        System.out.println(StringUtils.capitalize(StringUtils.deleteWhitespace(a)));

    }
}
