package com.arcta.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountMap<T> {
    public Map<T, Integer> map = new HashMap<>();

    public List<T> order(){
        ArrayList<T> list = new ArrayList<>(map.keySet());
        list.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        return list;
    }

    public void greater_than(int greater_than_exclusive){
        List<T> to_remove = new ArrayList<T>();
        for (T t : map.keySet()) {
            if (map.get(t) <= greater_than_exclusive){
                to_remove.add(t);
            }
        }
        for (T t : to_remove) {
            map.remove(t);
        }
    }

    public List<T> greater_than__get(int greater_than_exclusive){
        List<T> keys = new ArrayList<T>();
        for (T key : map.keySet()) {
            if (map.get(key) > greater_than_exclusive){
                keys.add(key);
            }
        }
        return keys;
    }


    public void addAll(Collection<T> collection){
        if (Util.empty(collection)) return;
        for (T t : collection) {
            add(t);
        }
    }

    public void remove(T key){
        map.remove(key);
    }

    public int add(T key){
        return add(key, 1);
    }

    public int add(T key, Integer value){
        Integer value__existing = map.get(key);
        if (value__existing == null){
            map.put(key,value);
            return value;
        } else {
            int value_sum = map.get(key) + value;
            map.put(key, value_sum);
            return value_sum;
        }
    }

    public void decrement(T key){
        Integer value__existing = map.get(key);
        if (value__existing != null){
            map.put(key, map.get(key) - 1);
        }
    }

    public Integer get(T key){
        return map.get(key);
    }

    public T get_largest(){
        return get_largest(-1);
    }

    public T get_largest(Integer minimum){
        int largest = minimum;
        T largest_key = null;
        for (T key : map.keySet()) {
            Integer integer = map.get(key);
            if (integer >= largest) {
                largest = integer;
                largest_key = key;
            }
        }
        return largest_key;
    }

    public void addAll(CountMap<T> count_map) {
        for (T t : count_map.map.keySet()) {
            add(t, count_map.get(t));
        }
    }
}
