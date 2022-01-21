/**
 * Copyright (C), 2015-2022
 * Description: 响应式编程
 * <author>          <time>          <version>          <desc>
 * chenfz           2022/1/10 13:45           1.0              描述
 */
package com.chenfz.reactiveprogramming;

import com.chenfz.reactiveprogramming.entity.Apple;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * 什么是响应式编程
 * reactive programming is a declarative programming paradigm concerned with data streams and the propagation of change

 我提炼一下这个定义的关键词 声明式，数据流， 传递变化（响应），我自己再给加一个异步，因为实际上生产级别代码都是进行异步响应的，几乎很少进行同步响应。
 */
public class FluxDemo {

    /**
     *         declarative programming
     *         声明式编程
     *         声明式和指令式对应。指令式大家比较熟悉，就是依次写出完成某个任务的每条指令。
     *         比如从一个苹果列表里，找出所有红色的苹果，指令式编程是这样做的。
     *
     *         声明式编程，只要写出你想要什么就OK了。
     *          典型的声明式语言的就是sql，对应上面的找红色苹果的需求，应该是这样的 select * from apple where color = red。
     */
    public void declarativeProgramming(){
        List<Apple> apples = new ArrayList<Apple>();

        for (Apple apple : apples){
            if (apple.getColor() == "red"){
                System.out.println(apple);
            }
        }
    }

    public void reactiveProgramming(){
        //Flux.just(1,2,3,4,5,6).subscribe(System.out::println);
        //Flux.range(1,6).map(x->x*x).subscribe(System.out::println);
        //Flux.just("apple-1", "apple-2").flatMap(i->
        //        Flux.fromArray(i.split("-"))).subscribe(System.out::println);
        //
        //Flux.range(1,6).filter(i -> i>3).subscribe(System.out::println);
        Flux.zip(
                Flux.just(1,2,3),
                Flux.just("A","B","C"),
                (x,y)->y+x
        ).subscribe(System.out::println);
    }

    /*
        //创建一个线程
        Scheduler single = Schedulers.single();
        //创建等于CPU核心数量的线程
        Scheduler parallel = Schedulers.parallel();
        //创建有界限的线程池，不传参数的默认创建10倍于CPU核心数量
        Scheduler elastic = Schedulers.boundedElastic();
     */
    public void scheduler(){
        Flux.just("hello")
                .map(s -> {
                    System.out.println("[map] Thread name: " + Thread.currentThread().getName());
                    return s.concat(" world!");
                })
                //只改变publishOn()之后的操作的线程。
                .publishOn(Schedulers.newSingle("thread-publishOn"))
                .filter(s -> {
                    System.out.println("[filter] Thread name: " + Thread.currentThread().getName());
                    return s.startsWith("h");
                })
                //从源头变整个操作链的线程
                .subscribeOn(Schedulers.newSingle("thread-subscribeOn"))
                .subscribe(s -> {
                    System.out.println("[subscribe] Thread name: " + Thread.currentThread().getName());
                    System.out.println(s);
                });
    }

    public static void main(String[] args) {
        FluxDemo fluxDemo = new FluxDemo();
        //fluxDemo.reactiveProgramming();
        fluxDemo.scheduler();
    }
}
