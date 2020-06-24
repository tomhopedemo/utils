package com.arcta.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SList implements Iterable<String> {
    public List<String> underlying = new ArrayList<>();

    public void add(String raw_word) {
        underlying.add(raw_word);
    }


    public boolean contains(Object obj){
        return underlying.contains(obj);
    }

    public String get(int index){
        if (underlying.size() < index + 1){
            return null;
        }
        return underlying.get(index);
    }

    public String get_last(){
        return get(underlying.size() -1);
    }

    public boolean removeIf(Predicate<? super String> filter){
        return underlying.removeIf(filter);
    }

    @Override
    public Iterator iterator() {
        return underlying.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        underlying.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return underlying.spliterator();
    }
}
