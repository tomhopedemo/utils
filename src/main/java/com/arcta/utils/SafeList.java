package com.arcta.utils;

import java.util.List;

public class SafeList<T> {

    public List<T> underlying;

    private Initializer<List<T>> initializer;

    private boolean initialized = false;

    public SafeList(){
        initialized = true;
    }

    public SafeList(Initializer initializer){
        this.initializer = initializer;
    }
}
