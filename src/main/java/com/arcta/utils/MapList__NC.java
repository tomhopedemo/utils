package com.arcta.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapList__NC<K ,T> {
    public Map<K, ArrayList<T>> mapList;

    public MapList__NC(){
        this.mapList = new HashMap<>();
    }

    public void put(K key, T listElement){
        mapList.putIfAbsent(key, new ArrayList<>());
        mapList.get(key).add(listElement);
    }

    public void merge(K key, List<T> listElements){
        mapList.putIfAbsent(key, new ArrayList<>());
        mapList.get(key).addAll(listElements);
    }

    public List<T> get(K key) {
        return mapList.get(key);
    }

    public void output(){
        for (K key : mapList.keySet()) {
            System.out.println(key);
            for (T element : mapList.get(key)) {
                System.out.println("\t" + element);
            }
        }
    }
}
