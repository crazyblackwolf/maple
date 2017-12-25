package com.zjf.utils.code.sort.bubble;

import com.zjf.utils.code.sort.Sorter;

/**
 * @author: linziye
 * @description: 
 * @date: 16:24 2017/12/21 .
 */
public class BubbleSorter implements Sorter {
    
    @Override
    public <T extends Comparable<T>> void sort(T[] list) {
        boolean swapped = true;
        for (int i = 1, len = list.length; i < len && swapped; ++i) {
            swapped = false;
            for (int j = 0; j < len - i; ++j) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

}
