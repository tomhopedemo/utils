package com.arcta.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.arcta.utils.Util.newArrayList;
import static com.arcta.utils.Util.newHashMap;

public class NList {

    public String split_delimiter;
    public List<String> underlying;

    public String get(int index){
        if (underlying.size() < index + 1){
            return null;
        }
        return underlying.get(index);
    }

    public Set<String> set(){
        if (Util.empty(underlying)) return null;
        return new HashSet<>(underlying);
    }

    public void remove(String element){
        if (Util.empty(underlying)) return;
        underlying.remove(element);
    }

    public void remove_first(){
        if (!Util.empty(underlying)) {
            underlying.remove(0);
        }
    }


    public String get_last(){
        return get(size() -1);
    }

    public List<String> get_word_pairs(){
        List<String> to_return = new ArrayList<>();
        if (underlying.size() == 0) return null;
        if (underlying.size() == 1) return null;
        for (int i = 0; i < underlying.size() -1; i++) {
            to_return.add(underlying.get(i) + " " + underlying.get(i +1));
        }
        return to_return;
    }

    public String get_antepenultimate(){
        if (size() > 2){
            return get(size() -3);
        }
        return null;
    }

    public String get_penultimate(){
        if (size() > 1){
            return get(size() -2);
        }
        return null;
    }

    private String reconstruct(List<String> list){
        return reconstruct(list, null);
    }

    private String reconstruct(List<String> list, String joiner){
        if (list == null) return null;
        if (joiner == null) joiner = split_delimiter;
        if (joiner.equals("\\s+")){
            return Util.string(list," ");
        } else {
            return Util.string(list,joiner);
        }
    }

    public String reconstruct(){
        return reconstruct(size());
    }


    public String reconstruct(int index_from_inclusive, int index_to_exclusive){
        if (underlying == null) return null;
        List<String> sublist = sublist(index_from_inclusive, index_to_exclusive);
        return reconstruct(sublist);
    }

    public String reconstruct(int index_to_exclusive){
        if (underlying == null) return null;
        List<String> sublist = sublist(0, index_to_exclusive);
        return reconstruct(sublist);
    }

    public String reconstruct(int index_to_exclusive, String joiner){
        if (underlying == null) return null;
        List<String> sublist = sublist(0, index_to_exclusive);
        return reconstruct(sublist, joiner);
    }



    public void set(int index, String string){
        underlying.set(index, string);
    }

    public String reconstruct_replacing(Integer index, List<String> strings){
        List<String> words = new ArrayList<>();
        if (index > 0) {
            for (int j = 0; j < index; j++) {
                words.add(underlying.get(j));
            }
        }
        words.addAll(strings);
        if (index < size() - 1) {
            for (int j = index + 1; j < size(); j++) {
                words.add(underlying.get(j));
            }
        }
        return reconstruct(words);
    }


    public String reconstruct_replacing(Map<Integer, String> index_replacement){
        List<String> words = newArrayList();
        for (int i = 0; i < underlying.size(); i++) {
            String replacement = index_replacement.get(i);
            if (replacement == null) {
                words.add(underlying.get(i));
            } else {
                words.add(replacement);
            }
        }
        return reconstruct(words);
    }

    public String reconstruct_replacing(Integer index, String replacement){
        return reconstruct_replacing(newHashMap(index, replacement));
    }

    public String reconstruct_except(List<Integer> except){
        if (underlying == null) return null;
        List<String> sublist = new ArrayList<>();
        for (int i = 0; i < underlying.size(); i++) {
            if (!except.contains(i)){
                sublist.add(underlying.get(i));
            }
        }
        return reconstruct(sublist);
    }

    public String reconstruct_except(int i){
        if (underlying == null) return null;
        if (i >= underlying.size()) return null;
        List<String> sublist = sublist(0, i);
        if (i != underlying.size() -1){
            sublist.addAll(sublist(i + 1, underlying.size()));
        }
        return reconstruct(sublist);
    }

    public String reconstruct_before_last(){
        if (underlying == null) return null;
        List<String> sublist = sublist(0, underlying.size() - 1);
        return reconstruct(sublist);
    }

    public int size(){
        if (underlying == null) return 0;
        return underlying.size();
    }

    public boolean contains(Object o){
        return underlying.contains(o);
    }

    public List<String> sublist(int start_index, int end_index__exclusive){
        if (underlying.size() < start_index + 1){
            return new ArrayList<>();
        }
        return underlying.subList(start_index, Math.min(underlying.size(), end_index__exclusive));
    }

    public void add(String raw_word) {
        underlying.add(raw_word);
    }
}
