package com.arcta.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMap<A> {

    private ConcurrentHashMap<String, Map<String, A>> underlying = new ConcurrentHashMap<>();

    public void put(String index, String key, A value){
        if (!underlying.containsKey(index)) {
            underlying.put(index, new HashMap<>());
        }
        underlying.get(index).put(key, value);
    }

    public Collection<List<A>> get_values(){
        Collection<List<A>> to_return = new ArrayList<>();
        Collection<Map<String, A>> values = underlying.values();
        for (Map<String, A> value : values) {
            if (Util.empty(value)) continue;
            to_return.add(new ArrayList<>(value.values()));
        }
        return to_return;
    }

    public A get(String index, String key){
        return underlying.get(index).get(key);
    }

    public Map<String, A> get(String index){
        Map<String, A> map = underlying.get(index);
        if (map != null) {
            return new HashMap<>(map);
        } else {
            return null;
        }
    }

}
