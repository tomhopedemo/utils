package com.arcta.utils;

import java.io.Serializable;
import java.util.*;

public class TreeMapList<K extends Comparable<? super K>,T> implements Serializable{

    public Map<K, LinkedList<T>> mapList;

    public TreeMapList(){
        this.mapList = new TreeMap<>();
    }

    public Set<K> keys(){
        return this.mapList.keySet();
    }

    public void putFirst(K key, T listElement){
        mapList.putIfAbsent(key, new LinkedList<>());
        mapList.get(key).addFirst(listElement);
    }

    public void put(K key, T listElement){
        mapList.putIfAbsent(key, new LinkedList<>());
        mapList.get(key).add(listElement);
    }

    public void addAll(K key, Collection<T> elements){
        mapList.putIfAbsent(key, new LinkedList<>());
        mapList.get(key).addAll(elements);
    }

    public void merge(Map<K,T> map){
        for (K k : map.keySet()) {
            mapList.putIfAbsent(k, new LinkedList<T>());
            mapList.get(k).add(map.get(k));
        }
    }

    public void merge(K key, List<T> listElements){
        mapList.putIfAbsent(key, new LinkedList<>());
        mapList.get(key).addAll(listElements);
    }

    public List<T> get(K key) {
        return mapList.get(key);
    }

    public List<K> orderedKeys(){
        List<K> list = new ArrayList<>(this.mapList.keySet());
        Collections.sort(list);
        return list;
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
