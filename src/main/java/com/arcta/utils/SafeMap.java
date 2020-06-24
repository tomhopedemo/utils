package com.arcta.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SafeMap {

    private Map<String, String> underlying;

    private Initializer<Map> initializer;

    private boolean initialized = false;

    public Set<String> keys(){
        initialize();
        return underlying.keySet();
    }

    public synchronized String get(String key){
        initialize();

        String s = underlying.get(key);
        return s == null ? "" : s;
    }

    private synchronized void initialize() {
        if (!initialized){
            underlying = initializer.initialize();
            if (underlying == null) underlying = new HashMap<>();
            initialized = true;
        }
    }

    public SafeMap(){
        initialized = true;
    }

    public SafeMap(Initializer initializer){
        this.initializer = initializer;
    }
}
