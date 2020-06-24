package com.arcta.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    public List<List<String>> underlying;

    public List<String> get_elements(){
        List<String> to_return = Util.list();
        for (List<String> strings : underlying) {
            if (strings == null) continue;
            to_return.addAll(strings);
        }
        return to_return;
    }

    public MultiList<Integer, Integer> get_ignore_case(String value){
        if (Util.empty(value)) return null;
        MultiList<Integer, Integer> to_return = new MultiList<>();
        for (int i = 0; i < underlying.size(); i++) {
            List<String> row = underlying.get(i);
            for (int j = 0; j < row.size(); j++) {
                String element = row.get(j);
                if (value.equalsIgnoreCase(element)){
                    to_return.add(new Multi<>(i,j));
                }
            }
        }
        return to_return;
    }

    public List<String> keySet() {
        List<String> toReturn = new ArrayList<>();
        for (List<String> strings : underlying) {
            strings.get(0);
        }
        return toReturn;
    }

    public MapList<String,String> maplist_create(int key, int columns_after_including){
        MapList<String,String> maplist = new MapList<>();
        for (List<String> row : underlying) {
            if (row.size() >  Math.max(key, columns_after_including)){
                ArrayList<String> values = new ArrayList<>();
                for (int i = columns_after_including; i < row.size(); i++) {
                    String e = row.get(i);
                    if (!e.isEmpty()) {
                        values.add(e);
                    }
                }
                maplist.mapList.put(row.get(key), values);
            }
        }
        return maplist;
    }

    public Map<String, String> map_create(int column_a, int column_b) {
        Map<String, String> map = new HashMap<>();
        for (List<String> row : underlying) {
            if (row.size() >= Math.max(column_a, column_b) + 1){
                map.put(row.get(column_a),row.get(column_b));
            }
        }
        return map;
    }

    public List<String> list_create(int column) {
        List<String> list = new ArrayList<>();
        for (List<String> row : underlying) {
            if (row.size() >= column + 1){
                list.add(row.get(column));
            }
        }
        return list;
    }

    public List<List<String>> rows() {
        return underlying;
    }
}
