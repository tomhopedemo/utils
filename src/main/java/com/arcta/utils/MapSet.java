package com.arcta.utils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapSet<K extends Comparable<? super K>,T> implements Serializable {

    public Map<K, HashSet<T>> mapSet;

    public MapSet(){
        this.mapSet = new HashMap<>();
    }

    public Set<K> keys(){
        return this.mapSet.keySet();
    }


    public void put(K key, T listElement){
        mapSet.putIfAbsent(key, new HashSet<T>());
        mapSet.get(key).add(listElement);
    }

    public void merge(K key, Collection<T> listElements){
        mapSet.putIfAbsent(key, new HashSet<T>());
        mapSet.get(key).addAll(listElements);
    }

    public Set<T> get(K key) {
        return mapSet.get(key);
    }

    public Set<T> get(Set<K> keys){
        HashSet<T> to_return = new HashSet<>();
        for (K key : keys) {
            to_return.addAll(this.mapSet.get(key));
        }
        return to_return;
    }

    public List<K> orderedKeys(){
        List<K> list = new ArrayList<>(this.mapSet.keySet());
        Collections.sort(list);
        return list;
    }
}

