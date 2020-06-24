package com.arcta.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MultiList<S,T> {

    public List<Multi<S,T>> underlying = new ArrayList();

    public static <S,T> MultiList<S,T> create(Map<S,T> map){
        if (map == null) return null;
        MultiList<S,T> multiList = new MultiList<S, T>();
        for (S key : map.keySet()) {
            multiList.add(new Multi(key, map.get(key)));
        }
        return multiList;
    }

    public void add(Multi<S,T> multi){
        underlying.add(multi);
    }

    public void add(S a,T b){
        underlying.add(new Multi<>(a, b));
    }

    public void remove(Multi<S,T> multi){
        underlying.remove(multi);
    }

    public Map<S,ArrayList<T>> map(){
        MapList__NC<S,T> map = new MapList__NC<>();
        for (Multi<S, T> multi : underlying) {
            map.put(multi.a, multi.b);
        }
        return map.mapList;
    }

    public Map<S,T> map_random(){
        Map<S,T> map_random = new HashMap<>();
        Map<S, ArrayList<T>> map = map();
        for (S s : map.keySet()) {
            map_random.put(s, map.get(s).get(0));
        }
        return map_random;
    }

    public List<S> geta_list(){
        List<S> to_return = new ArrayList<S>();
        for (Multi<S, T> multi : underlying) {
            to_return.add(multi.a);
        }
        return to_return;
    }

    public List<S> a_list(){
        return geta_list();
    }

    public List<T> b_list(){
        return getb_list();
    }

    public List<Multi<S,T>> get_multis_a(S key){
        List<Multi<S,T>> to_return = new ArrayList<Multi<S,T>>();
        for (Multi<S, T> multi : underlying) {
            if (Objects.equals(multi.a, key)){
                to_return.add(multi);
            }
        }
        return to_return;
    }


    public List<Multi<S,T>> get_multis_b(S key){
        List<Multi<S,T>> to_return = new ArrayList<Multi<S,T>>();
        for (Multi<S, T> multi : underlying) {
            if (Objects.equals(multi.b, key)){
                to_return.add(multi);
            }
        }
        return to_return;
    }

    public void remove_a(S key){
        underlying.removeIf(e -> Objects.equals(e.a, key));
    }

    public void remove_b(S key){
        underlying.removeIf(e -> Objects.equals(e.a, key));
    }


    public Multi<S,T> get_multis_a_one(S key){
        List<Multi<S, T>> multis_a = get_multis_a(key);
        return Util.get(multis_a, 0);
    }


    public Multi<S,T> get_multis_b_one(S key){
        List<Multi<S, T>> multis_b = get_multis_b(key);
        return Util.get(multis_b, 0);
    }


    public List<T> getb_list(){
        List<T> to_return = new ArrayList<>();
        for (Multi<S, T> multi : underlying) {
            to_return.add(multi.b);
        }
        return to_return;
    }

    public List<T> getb(S key){
        List<T> to_return = new ArrayList<>();
        for (Multi<S, T> multi : underlying) {
            if (key.equals(multi.a)) {
                to_return.add(multi.b);
            }
        }
        return to_return;
    }

    public T getb_one(S key){
        List<T> getb = getb(key);
        if (Util.empty(getb)) return null;
        return getb.get(0);
    }

    public S geta_one(T key){
        List<S> geta = geta(key);
        if (Util.empty(geta)) return null;
        return geta.get(0);
    }


    public List<S> geta(T key){
        List<S> to_return = new ArrayList<>();
        for (Multi<S, T> multi : underlying) {
            if (key.equals(multi.b)) to_return.add(multi.a);
        }
        return to_return;
    }

    public Multi<S,T> get(Integer index){
        return underlying.get(index);
    }

    public Integer size(){
        return underlying.size();
    }
}
