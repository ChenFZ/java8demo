/**
 * Copyright (C), 2015-2023
 * Description:
 * <author>          <time>          <version>          <desc>
 * chenfz           2023/3/22 16:21           1.0              描述
 */
package com.chenfz.utils.demo;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtilsDemo {
    public static void main(String[] args) {
        String[] strings = ArrayUtils.toArray("a", "b", "c");
        System.out.println(ArrayUtils.toString(strings));
        String[] ds = ArrayUtils.add(strings, "d");
        System.out.println(ArrayUtils.toString(ds));

        System.out.println(ArrayUtils.toString(ArrayUtils.addAll(strings, ds)));

        printArr(ArrayUtils.remove(ds, 3));
        printArr(ArrayUtils.removeElements(ds, "a", "d", "e"));
        printArr(ArrayUtils.removeAll(ds, 1,2));
        ArrayUtils.shuffle(ds);
        printArr(ds);
        ArrayUtils.swap(ds, 0, 2);
        printArr(ds);
    }

    private static void printArr(Object[] addAll) {
        System.out.println(ArrayUtils.toString(addAll));
    }


}
