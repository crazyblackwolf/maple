package com.zjf.utils.code.sort;

/**
 * @author: linziye
 * @description:
 * @date: 16:21 2017/12/21 .
 */
public interface Sorter {

    /**
     * 排序
     *
     * @param list 待排序的数组
     */
    <T extends Comparable<T>> void sort(T[] list);

}
