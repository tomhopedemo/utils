package com.arcta.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapList<K extends Comparable<? super K>,T> implements Serializable{

    public Map<K, ArrayList<T>> mapList;

    public MapList(){
        this.mapList = new HashMap<>();
    }

    public Set<K> keys(){
        return this.mapList.keySet();
    }

    //note that we'll
    public void addKeys(List<K> keys){
        if (Util.empty(keys)) return;
        for (K key : keys) {
            if (mapList.get(key) == null) {
                mapList.put(key, Util.list());
            }
        }
    }

    public K getKey(T listelement){
        for (K key : mapList.keySet()) {
            List<T> list = mapList.get(key);
            if (Util.empty(list)) continue;
            if (list.contains(listelement)) return key;
        }
        return null;
    }

    public int put(K key, T listElement){
        mapList.putIfAbsent(key, new ArrayList<>());
        mapList.get(key).add(listElement);
        return mapList.get(key).size();
    }

    public void addAll(K key, Collection<T> elements){
        mapList.putIfAbsent(key, new ArrayList<>());
        mapList.get(key).addAll(elements);
    }

    public void merge(Map<K,T> map){
        for (K k : map.keySet()) {
            mapList.putIfAbsent(k, new ArrayList<T>());
            mapList.get(k).add(map.get(k));
        }
    }

    public void merge(K key, List<T> listElements){
        mapList.putIfAbsent(key, new ArrayList<>());
        mapList.get(key).addAll(listElements);
    }

    public List<T> getsafe(K key) {
        return Util.safe_null(get(key));
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

    public List<T> listvalues(){
        List<T> aggregated = Util.list();
        for (K key : mapList.keySet()) {
            List<T> list = mapList.get(key);
            if (Util.empty(list)) continue;
            aggregated.addAll(list);
        }
        return aggregated;
    }
}
