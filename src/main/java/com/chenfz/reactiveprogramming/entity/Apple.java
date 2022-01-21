/**
 * Copyright (C), 2015-2022
 * Description:
 * <author>          <time>          <version>          <desc>
 * chenfz           2022/1/10 13:50           1.0              描述
 */
package com.chenfz.reactiveprogramming.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

    private int weight;
    private String  color;
    private String variety;

}
