/**
 * Copyright (C), 2015-2022
 * Description:
 * <author>          <time>          <version>          <desc>
 * chenfz           2022/1/14 16:30           1.0              描述
 */
package com.chenfz.reactiveprogramming.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int ID;
    private String name;
}
