/**
 * Copyright (C), 2015-2021, 航天广信有限公司
 * FileName: FunctionDemo
 * Author:   chenfz
 * Date:     2021/3/23 10:59
 * Description: Function接口的使用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package functionalprogramming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Function接口的使用〉
 *
 * @author chenfz
 * @create 2021/3/23
 * @since 1.0.0
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    private static void test3() {
        int[] arr = {1,2,3,4};
        // fix commit
        System.out.println(arr.toString());
    }

    private static void test2() {
        Set<Object> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("Event","EventLifeCycle","Comment"));

        Arrays.asList("Event","EventLifeCycle","Comment").stream().forEach(System.out::println);
        String[] strs = {"Event","EventLifeCycle","Comment"};
        Set<String> set = Stream.of("Event", "EventLifeCycle", "Comment").collect(Collectors.toSet());
        System.out.println(set.contains("Event"));
        System.out.println(set.add("Event"));
        Map<String, String> map = set.stream().collect(Collectors.toMap(a -> a, a -> a));
        map.forEach((key, value)-> System.out.println(key+"--"+value));

        set.forEach(str-> System.out.println(str));
    }

    private static void test1() {
        Map<String, String> map = new HashMap();
        map.put("1", "xiaoming");
        map.put("2", "hongxiang");
        if(map.containsKey("1")){
            map.remove("1");
            System.out.println("contains");
        }else{
            map.remove("1");
            System.out.println("don't contains");
        }
        System.out.println(map.get("1"));
        map.forEach((key, value)-> System.out.println(key+"--"+value));
    }

}
