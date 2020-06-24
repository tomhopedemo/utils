package com.arcta.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {

    public static final List<String> LETTERS = newArrayList("a","b","c","d","e","f","g","h"
    ,"i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");

    public static final List<String> HYPHENS = newArrayList("-", "\u2010", "\u2013","\u2014", "\u002d");

    public static String safe(String value){
        return value == null ? "" : value;
    }

    public static String safe_null(String value) {
        return safe(value);
    }

    public static void add_if_not_empty(Collection<String> collection, String string){
        if (empty(string) || collection == null) return;
        collection.add(string);
    }

    public static int size(Collection collection){
        if (collection == null) return 0;
        return collection.size();
    }

    public static Set<String> append_each__set(Collection<String> collection, String append){
        List<String> appended = append_each(collection, append);
        return new HashSet<>(appended);
    }

    public static List<String> append_each(Collection<String> collection, String append){
        if (Util.empty(append) || Util.empty(collection)) return null;
        List<String> list = new ArrayList<>();
        for (String s : collection) {
            if (s == null) continue;
            list.add(s + append);
        }
        return list;
    }

    public static List<Integer> indices(String text, List<String> to_find){
        if (text == null) return null;
        if (empty(to_find)) return new ArrayList<>();

        List<Integer> to_return = new ArrayList<>();
        for (String string : to_find) {
            int i = text.indexOf(string);
            if (i > -1) to_return.add(i);
        }
        return to_return;
    }

    public static Matcher matcher(String regex, String text){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text);
    }

    public static Integer max_difference(List<Integer> collection, int value) {
        if (Util.empty(collection)) return null;
        List<Integer> distances_between = new ArrayList<>();
        collection.forEach(avoid_index -> distances_between.add(Math.abs(value - avoid_index)));
        return max(distances_between);
    }

    public static Integer min_difference(List<Integer> collection, int value) {
        if (Util.empty(collection)) return null;
        List<Integer> distances_between = new ArrayList<>();
        collection.forEach(avoid_index -> distances_between.add(Math.abs(value - avoid_index)));
        return min(distances_between);
    }

    public static boolean greater_than(Integer a, Integer b){
        if (a == null && b == null) return false;
        if (a == null) return false;
        if (b == null) return true;
        return a > b;
    }

    public static Integer min_difference__directional(List<Integer> collection, int value) {
        if (Util.empty(collection)) return null;
        List<Integer> distances_between = new ArrayList<>();
        for (Integer element : collection) {
            int difference = value - element;
            if (difference >= 0) distances_between.add(difference);
        }
        return min(distances_between);
    }


    public static boolean empty(Map map){
        return map == null || map.size() == 0;
    }


    public static List<Integer> between(int start_inclusive, int end_inclusive){
        List<Integer> to_return = new ArrayList<>();
        for (int i = start_inclusive; i <= end_inclusive; i++) {
            to_return.add(i);
        }
        return to_return;
    }

    public static <T> List<T> distinct(List<T> list){
        return new ArrayList(new HashSet<T>(list));
    }

    public static boolean between(int middle, int a_inclusive, int b_inclusive) {
        int largest = Math.max(a_inclusive, b_inclusive);
        int smallest = Math.min(a_inclusive, b_inclusive);
        return middle >= smallest && middle <= largest;
    }

    public static boolean overlap(Integer start_a, Integer end_a, Integer start_b, Integer end_b) {
        return !(start_a > end_b || start_b > end_a);
    }

    public static Integer max(Collection<Integer> collection){
        if (Util.empty(collection)) return null;
        Integer max = null;
        for (Integer candidate : collection) {
            if (max == null || candidate > max) max = candidate;
        }
        return max;
    }

    public static Integer min(Collection<Integer> collection){
        if (Util.empty(collection)) return null;
        Integer min = null;
        for (Integer candidate : collection) {
            if (min == null || candidate < min) min = candidate;
        }
        return min;
    }


    public static Integer words__number(String words){
        return words.trim().split("\\s+").length;
    }

    public static <T> List<T> getAll(Collection<List<T>> inputs){
        List<T> all = new ArrayList<>();
        for (List<T> objects : inputs) {
            all.addAll(objects);
        }
        return all;
    }

    public static List<String> characters(String word){
        List<String> letters = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            letters.add(String.valueOf(aChar));
        }
        return letters;
    }

    public static String acronym(String words){
        String[] split = words.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            if (!empty(s)) sb.append(s.substring(0,1).toUpperCase());
        }
        return sb.toString();
    }

    public static String safe_null_S(Integer value) {
        return value == null ? "" : String.valueOf(value);
    }

    public static Integer safe_null(Integer value) {
        return value == null ? 0 : value;
    }

    public static String safe_null_space_bracketed(String day) {
        return day == null ? "" : " [" + day + "]";
    }

    public static boolean contain_one_of(Collection<String> to_compare, String... against){
        for (String s : to_compare) {
            for (String s1 : against) {
                if (s.equals(s1)){
                    return true;
                }
            }
        }
        return false;
    }


    public static String starts_with_get(String input, Collection<String> inputs){
        if (inputs == null || input == null || input.equals("")) return null;
        for (String s : inputs) {
            if (input.startsWith(s)){
                return s;
            }
        }
        return null;
    }

    public static boolean starts_with(String input, Collection<String> inputs){
        return starts_with_get(input, inputs) != null;
    }

    public static Set<String> contained(String input, Collection<String> inputs){
        if (inputs == null || input == null || input.equals("")) return null;
        Set<String> matches = new HashSet<>();
        for (String candidate : inputs) {
            if (input.contains(candidate)){
                matches.add(candidate);
            }
        }
        return matches;
    }

    public static Map<String,String> invert(Map<String,String> map){
        Map<String,String> clean = new HashMap<String, String>();
        for (Map.Entry<String, String> tsEntry : map.entrySet()) {
            if (!Util.empty(tsEntry.getKey()) && !Util.empty(tsEntry.getValue())){
                clean.put(tsEntry.getKey(), tsEntry.getValue());
            }
        }
        if (Util.intersection(clean.keySet(),clean.values())) return null;
        return clean.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }


    public static String contains_get(String input, Collection<String> inputs){
        if (inputs == null || input == null || input.equals("")) return null;
        for (String candidate : inputs) {
            if (input.contains(candidate)){
                return candidate;
            }
        }
        return null;
    }

    public static boolean contains_rev(String input, Collection<String> inputs){
        if (inputs == null || input == null) return false;
        for (String element : inputs) {
            if (element.contains(input)) return true;
        }
        return false;
    }


    public static boolean contains(String input, String substring){
        if (input == null) return false;
        if (substring == null) return false;
        if (substring.length() == 0) return false;
        return input.contains(substring);
    }

    public static boolean contains(String input, Collection<String> inputs){
        return contains_get(input, inputs) != null;
    }

    public static boolean contains__partially(String input, Collection<String> inputs){
        for (String element : inputs) {
            if (input.contains(element)){
                return true;
            }
        }
        return false;
    }


    public static boolean endsWith_reverse(String ending, List<String> strings){
        for (String string : strings) {
            if (string.endsWith(ending))return true;
        }
        return false;
    }

    public static boolean endsWith(String input, List<String> endings){
        for (String string : endings) {
            if (input.endsWith(string))return true;
        }
        return false;
    }

    public static Map<String, String> charmap = new HashMap<>();

    public static String uppercase_first_letter(String input) {
        return uppercase_first_letter(input, charmap);
    }

    public static String uppercase_first_letter(String input, Map<String, String> lowercase_uppercase_charmap){
        if (Util.empty(input)) return input;
        String first_character = input.substring(0, 1);
        String foreignchar = lowercase_uppercase_charmap.get(Util.safe(first_character));
        if (foreignchar != null) return foreignchar + (input.length() > 1 ? input.substring(1) : "");
        if (!Util.safe(first_character).matches("[a-z]")) return input;
        return first_character.toUpperCase() + (input.length() > 1 ? input.substring(1) : "");
    }

    private static List<Character> chars = list('"','(');
    public static String uppercaseFirstLetterComplex(String input){
        if (Util.empty(input)) return input;
        char c = input.charAt(0);
        if (chars.contains(c)){
            if (input.length() == 1) {
                return input;
            } else {
                return c + uppercase_first_letter(input.substring(1));
            }
        } else {
            return uppercase_first_letter(input);
        }
    }


    public static String uppercaseAllWords(String input){
        StringBuilder sb = new StringBuilder();
        String[] split = input.split("\\s");
        for (String s : split) {
            String trim = s.trim();
            if (trim.length() > 0){
                if (s.startsWith("(") && s.length() > 1){
                    sb.append("(" + s.substring(1,2).toUpperCase() + ( s.length() > 2 ? s.substring(2,s.length()) : ""));
                } else {
                    sb.append(uppercase_first_letter(s));
                }
                sb.append(" ");
            }
        }
        return Util.substring_remove_last(sb);
    }

    public static boolean empty(SList list){
        if (list == null) return true;
        return empty(list.underlying);
    }

    public static boolean empty(String string){
        return string == null || string.trim().length() == 0;
    }

    public static <T> T get(List<T> list, int index){
        if (empty(list)) return null;
        if (index >= list.size()) return null;
        if (index == -1) return null;
        return list.get(index);
    }

    public static List<Double> toDoubles(List<String> list){
        if (empty(list)) return Util.list();
        List<Double> toReturn = Util.list();
        for (String s : list) {
            toReturn.add(Double.parseDouble(s));
        }
        return toReturn;
    }

    public static List<Integer> toIntegers(List<String> list){
        if (empty(list)) return Util.list();
        List<Integer> toReturn = Util.list();
        for (String s : list) {
            toReturn.add(Integer.parseInt(s));
        }
        return toReturn;
    }

    public static <T> T get_element(List<T> set, T object){
        int index = set.indexOf(object);
        return get(set, index);
    }

    public static <T> T get(Set<T> set, T object){
        ArrayList<T> list_version= new ArrayList<>(set);
        return list_version.get(list_version.indexOf(object));
    }

    public static List<String> matcher__bespoke(String start_pattern, String end_pattern, String to_match){
        List<Integer> starts = new ArrayList<>();
        int next_index = 0;
        while (true){
            int i1 = to_match.indexOf(start_pattern, next_index);
            if (i1 > 0) {
                starts.add(i1 + start_pattern.length());
                next_index = i1 + start_pattern.length();
            } else {
                break;
            }
        }

        List<Integer> ends = new ArrayList<>();
        int next_index__ends = 0;
        while (true){
            int i1 = to_match.indexOf(end_pattern, next_index__ends);
            if (i1 > 0) {
                ends.add(i1);
                next_index__ends = i1 + end_pattern.length();
            } else {
                break;
            }
        }

        if (starts.size() == 0){
            return new ArrayList<>();
        }

        List<String> substrings = new ArrayList<>();
        List<Integer> ends__filtered = new ArrayList<>();
        for (Integer end : ends) {
            if (end > starts.get(0)){
                ends__filtered.add(end);
            }
        }
        for (int i = 0; i < Math.min(starts.size(), ends__filtered.size()); i++) {
            substrings.add(to_match.substring(starts.get(i), ends__filtered.get(i)));
        }
        return substrings;
    }

    public static boolean empty(StringMutable stringMutable){
        if (stringMutable == null) return true;
        return empty(stringMutable.string);
    }

    public static String word(String word){
        word = word.trim();
        if (word.endsWith(",")) word = word.substring(0,word.length()-1);
        return word;
    }

    public static String word_clean(String word){
        for (int i = 0; i < 3; i++) {
            if (starts_with(word, newArrayList("\""," ",","))){
                word = substring_remove_first(word);
            }
        }
        return word;
    }

    public static boolean capital_first(String word){
        if (empty(word)) return false;
        String first_letter = word.substring(0, 1);
        return first_letter.toUpperCase().equals(first_letter);
    }

    public static Set<String> keysForValues(Set<String> values, Map<String,String> map){
        Set<String> keys = Util.set();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (values.contains(entry.getValue())){
                keys.add(entry.getKey());
            }
        }
        return keys;
    }


    public static <T> void sout(List<T> list) {
        sout(list, null);
    }

    public static <T> void sout(List<T> list, String pad){
        if (Util.empty(list)) return;
        pad = safe(pad);
        for (T s : list) {
            if (s == null) continue;
            System.out.println(pad + s.toString());
        }
    }

    public static <T,S> void put_if_absent(Map<T,S> existing, Map<T,S> additional_map){
        if (existing == null || Util.empty(additional_map)) return;
        for (T t : additional_map.keySet()) {
            existing.putIfAbsent(t, additional_map.get(t));
        }
    }

    public static void sout(String... strings){
        System.out.println(string(strings));
    }

    public static boolean is_lowercase(String string){
        if (Util.empty(string)) return false;
        return string.equals(Util.lowercase(string));
    }

    public static boolean isUppercase(String s){
        if (Util.empty(s)) return false;
        return s.equals(s.toUpperCase());
    }

    public static List<String> lowercase(Collection<String> collection){
        if (collection == null) return null;
        List<String> lowercase = new ArrayList<>();
        for (String s : collection) {
            if (s == null) continue;
            lowercase.add(s.toLowerCase());
        }
        return lowercase;
    }

    static {
        charmap.put("è", "È");
        charmap.put("é", "É");
        charmap.put("ê", "Ê");
        charmap.put("ç", "Ç");
        charmap.put("ö", "Ö");
        charmap.put("ë", "Ë");
        charmap.put("ï", "Ï");
        charmap.put("ü", "Ü");
        charmap.put("ä", "Ä");
        charmap.put("ż", "Ż");

        //polish
        charmap.put("ł", "Ł");

        //icelandic
        charmap.put("á","Á");
        charmap.put("ð","Ð");
        charmap.put("í","Í");
        charmap.put("ó","Ó");
        charmap.put("ú","Ú");
        charmap.put("ý","Ý");
        charmap.put("Þ","þ");
        charmap.put("æ","Æ");

        charmap.put("ô", "Ô");
    }

    public static String substring__safe(String string, int n){
        if (string == null){
            return null;
        }
        return (string.length() < n ? string : string.substring(0,n));
    }


    public static List<String> phrases(List<String> sentences, int phrase_length){
        List<String> phrases = Util.list();
        if (Util.empty(sentences)) return phrases;
        for (String sentence : sentences) {
            List<String> phrases__local = phrases(sentence, phrase_length);
            phrases.addAll(phrases__local);
        }
        return phrases;
    }

    public static List<String> phrases(String sentence, int phrase_length){
        List<String> phrases__local = Util.list();
        List<String> words = Util.split_list(sentence, "\\s+");
        if (words.size() < phrase_length) return phrases__local;
        for (int i = 0; i < words.size() - (phrase_length - 1); i++) {
            String phrase = Util.string(Util.sublist(words, i, i + phrase_length));
            phrases__local.add(phrase);
        }
        return phrases__local;
    }

    public static String uppercaseAllWordsExceptInForEtc(String input, List<String> except){
        String s = uppercaseAllWords(input);
        StringBuilder sb = new StringBuilder();
        String[] split = s.split("\\s");
        boolean firstword = true;
        String previous_word = ".";
        List<String> uppercase__indicators = newArrayList(".", ":");
        uppercase__indicators.addAll(Util.HYPHENS);
        for (String word : split) {
            word = word.trim();
            if (firstword){
                sb.append(word + " ");
                firstword = false;
            } else {
                if (Util.safe_null(except).contains(word.toLowerCase()) && !uppercase__indicators.contains(
                                previous_word.substring(previous_word.length()-1, previous_word.length()))){
                    sb.append(word.toLowerCase() + " ");
                } else {
                    sb.append(word + " ");
                }
            }
            previous_word = word;
        }
        return sb.substring(0, sb.length() -1);
    }

    public static <K,T> int size(MapList<? extends Comparable<?>, T> input) {
        int count = 0;
        for (Comparable key : input.mapList.keySet()) {
            count += input.mapList.get(key).size();
        }
        return count;
    }


    public static int size(MapSet<String, String> input) {
        int count = 0;
        for (String key : input.mapSet.keySet()) {
            count += input.mapSet.get(key).size();
        }
        return count;
    }

    public static String percent(double v) {
        return new BigDecimal(100 * v, new MathContext(2, RoundingMode.DOWN)).toPlainString() + "%";
    }

    public static boolean word_version = true;

    public static String completion(Integer current, Integer total, String type){
        if (word_version) {
            return String.valueOf(current) + " of " + String.valueOf(total) + " " + type + " completed";
        }
        if (current == 0) return "0% COMPLETE";
        return Util.percent(((current)/Double.valueOf(total))) + " COMPLETE";
    }

    public static String completion(Integer current, Integer total) {
        return completion(current, total, "");
    }


    private static final CountMap countMap = new CountMap();
    public static int count(String classfinder) {
        countMap.add(classfinder);
        return countMap.get(classfinder);
    }

    public static List<String> restrictList(List<String> list, int num){
        List restricted = new ArrayList<>();
        for (int i = 0; i < Math.min(num,list.size()); i++) {
            restricted.add(list.get(i));
        }
        return restricted;
    }

    public static boolean empty(MultiList multiList){
        return (multiList == null || empty(multiList.underlying));
    }

    public static boolean empty(NList collection){
        return (collection == null || collection.size() == 0 ||
                (collection.size() == 1 && collection.underlying.iterator().next() == null));
    }

    public static boolean empty(Collection collection){
        return (collection == null || collection.size() == 0 ||
                (collection.size() == 1 && collection.iterator().next() == null));
    }

    public static boolean empty(MapList mapList){
        return (mapList == null ||
                mapList.mapList == null || Util.empty(mapList.mapList));
    }

    public static <A> boolean empty(A[] array){
        return (array == null || array.length == 0);
    }

    public static boolean empty(Multi multi){
        if (multi == null) return true;
        if (multi.a == null && multi.b == null) return true;
        return false;
    }

    public static Map<String, String[]> map(List<String[]> list){
        if (list == null) return null;
        Map<String, String[]> map = new HashMap<>();
        for (String[] strings : list) {
            if (strings.length == 0 || strings[0] == null ) continue;
            map.put(strings[0], strings);
        }
        return map;
    }

    public static Map<String, String> map(List<String> list, String delimiter_character){
        if(list == null) return null;
        Map<String, String> map = new HashMap<>();
        for (String line : list) {
            Multi<String, String> multi = split_one(line, delimiter_character);
            map.put(multi.a, multi.b);
        }
        return map;
    }

    public static int index_of(String string, List<String> to_find){
        if (string == null || Util.empty(to_find)) return -1;
        for (String s : to_find) {
            int index = string.indexOf(s);
            if (index > 0) return index;
        }
        return -1;
    }

    public static boolean empty(Integer integer){
        return (integer == null || integer.equals(0));
    }

    public static boolean eq(Object a, Object b){
        if (a == null && b == null){
            return true;
        } else if (a == null){
            return false;
        } else {
            return a.equals(b);
        }
    }

    public static boolean contains_all(String input, Collection<String> elements){
        for (String element : elements) {
            if (!input.contains(element)) return false;
        }
        return true;
    }

    public static <T extends Comparable> List<T> sort(Collection<T> collection){
        ArrayList<T> sort = new ArrayList<>(collection);
        Collections.sort(sort);
        return sort;
    }

    public static <T> T last(List<T> collection){
        return collection.get(collection.size() -1);
    }

    public static <T,S> Set<T> keys(Map<T,S> a, Map<T,S> b){
        Set<T> combined_keyset = Util.set();
        if (!Util.empty(a)) combined_keyset.addAll(a.keySet());
        if (!Util.empty(b)) combined_keyset.addAll(b.keySet());
        return combined_keyset;
    }

    public static <T,S> Set<T> keys(Map<T,S> a){
        Set<T> combined_keyset = Util.set();
        if (!Util.empty(a)) combined_keyset.addAll(a.keySet());
        return combined_keyset;
    }

    public static <T,S> S get(Map<T,S> map, T object){
        if (Util.empty(map)) return null;
        return map.get(object);
    }




    public static <T> List<T> union(Collection<T> a, Collection<T> b){
        List<T> union = new ArrayList<T>();
        if (!Util.empty(a)) union.addAll(a);
        if (!Util.empty(b)) union.addAll(b);
        return union;
    }

    public static <T> Set<T> intersect(List<Set<T>> sets){
        if (Util.empty(sets)) return null;
        Set<T> intersection = new HashSet<>(sets.get(0));
        for (int i = 1; i < sets.size(); i++) {
            intersection = intersect(intersection, sets.get(i));
        }
        return intersection;
    }

    public static <T> Set<T> intersect(Collection<T> a, Collection<T> b){
        Set<T> intersect = new HashSet<>();
        if (empty(a) || empty(b)) return intersect;
        for (T t : a) {
            if (b.contains(t)){
                intersect.add(t);
            }
        }
        return intersect;
    }

    public static <T,S> Map<T, S> intersect(Map<T,S> map, Collection<T> collection){
        Map<T,S> intersect = new HashMap<T, S>();
        for (T t : map.keySet()) {
            if (collection.contains(t)){
                intersect.put(t,map.get(t));
            }
        }
        return intersect;
    }

    public static Integer diff(Integer a, Integer b){
        if (a == null || b == null) return null;
        return Math.abs(a - b);
    }

    public static <T> List<T> diff(Collection<T> in_a, Collection<T> not_in_b){
        Collection<T> a = in_a; Collection<T> b= not_in_b;
        List<T> to_return = new ArrayList<>();
        if (empty(a)) return to_return;
        to_return.addAll(a);
        if (empty(b)) return to_return;
        to_return.removeAll(b);
        return to_return;
    }

    public static <T> Set<T> diff_set(Collection<T> in_a, Collection<T> not_in_b){
        List<T> diff = diff(in_a, not_in_b);
        if (empty(diff)) return null;
        return new HashSet<>(diff);
    }

    public static boolean intersection(Collection a, Collection b){
        if (empty(a)|| empty(b)) return false;
        for (Object o : a) {
            for (Object o1 : b) {
                if (o.equals(o1)){
                    return true;
                }
            }
        }
        return false;
    }


    public static String random_string(int count) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < count; j++) {
            int index = new Random().nextInt(26);
            sb.append(LETTERS.get(index));
        }
        return sb.toString();
    }

    public static boolean null_check(Object... objects){
        if (objects == null) {
            return true;
        }
        for (Object object : objects) {
            if (object == null){
                return true;
            }
        }
        return false;
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (Exception e){}
    }

    public static <T> List<List<T>> divide(List<T> list, int num_lists){
        if (Util.empty(list)) return null;
        List<List<T>> to_return = newArrayList();
        num_lists = Math.min(list.size(), num_lists);
        for (int i = 0; i < num_lists; i++) {
            to_return.add(newArrayList());
        }
        for (int i = 0; i < list.size(); i++) {
            to_return.get(i % num_lists).add(list.get(i));
        }
        return to_return;
    }

    public static <T> T random_entry(List<T> list){
        return random_entry(list, new Random());
    }

    public static <T> T random_entry(List<T> list, Random random){
        if (Util.empty(list)) return null;
        return list.get(random.nextInt(list.size()));
    }

    public static <T> List<T> random_entries(List<T> list, int num){
        return random_entries(list, Long.valueOf(num));
    }

    public static <T> List<T> random_entries(List<T> list, long num){
        if (Util.empty(list) || num < 1) return null;
        List<T> to_return = Util.list();
        Random random = new Random();
        for (long i = 0; i < num; i++) {
            to_return.add(random_entry(list, random));
        }
        return to_return;
    }

    public static String filename(String url){
        if (url == null) return null;
        List<String> strings = split_list(url, "/");
        if (strings == null) return null;
        return Util.last(strings);
    }

    public static <S> List<S> interlace(Collection<List<S>> lists){
        List<S> interlaced = new ArrayList<>();
        int largest = 0;
        for (List<S> strings : lists) {
            int size = strings.size();
            if (size > largest) largest = size;
        }
        for (int i = 0; i < largest; i++) {
            for (List<S> list : lists) {
                S element = Util.get(list, i);
                if (element == null) continue;
                interlaced.add(element);
            }
        }
        return interlaced;
    }

    public static String first(String string){
        if (Util.empty(string)) return null;
        return string.substring(0,1);
    }

    public static boolean intersection(Multi multi_1, Multi multi_2){
        List list_1 = newArrayList(multi_1.a, multi_1.b);
        List list_2 = newArrayList(multi_2.a, multi_2.b);
        return intersection(list_1, list_2);
    }

    public static List<String> words_last(String sentence, int number_of_words){
        List<String> shorter = new ArrayList<>();
        String[] split = sentence.split("\\s+");
        List<String> endsWithCharacters = newArrayList(".",":",",");
        for (int i = Math.min(number_of_words -1,split.length); i < split.length; i++) {
            String raw_word = split[i];
            if (Util.endsWith(raw_word, endsWithCharacters)) { raw_word = cut(raw_word).trim();}
            if (!empty(raw_word))shorter.add(raw_word);
        }
        return shorter;
    }

    public static MultiList<Integer, String> words(String sentence) {
        if (sentence == null) return null;
        MultiList<Integer, String> index_word = new MultiList<>();
        String[] split = sentence.split("\\s|\u00A0");
        int cumulative_length = 0;
        for (int i = 0; i < split.length; i++) {
            String word = split[i];
            if (Util.empty(word)) {
                cumulative_length++;
                continue;
            }
            index_word.add(new Multi<>(cumulative_length, word));
            cumulative_length = cumulative_length + word.length() + 1;
        }
        return index_word;
    }

    public static String lowercase(String string){
        if (string == null) return null;
        return string.toLowerCase();
    }

    public static String strip_punctuation(String string){
        if (string == null) return null;
        List<String> punctuation = newArrayList(".",":",",");
        for (String character : punctuation) {
            if (string.startsWith(character)){
                string = Util.substring_remove_first(string);
            }
            if (string.endsWith(character)){
                string = Util.substring_remove_last(string);
            }
        }
        return string;
    }

    public static <S,T> MultiList<S,T> sublist(MultiList<S,T> list, int num){
        MultiList<S,T> sublist = new MultiList<S, T>();
        if (list == null || list.underlying == null) return null;
        sublist.underlying = sublist(list.underlying, num);
        return sublist;
    }

    public static <T> List<T> sublist(List<T> list, int num){
        if (list == null) return null;
        return list.subList(0, Math.min(num, list.size()));
    }


    public static <T> List<T> sublist(List<T> list, int from_inc, int to_exc){
        if (list == null) return null;
        if (list.size() == 0 || from_inc >= list.size()) return newArrayList();
        return list.subList(from_inc, Math.min(to_exc, list.size()));
    }


    public static String subsentence(String sentence, int number_of_words){
        MultiList<Integer, String> index_words = sublist(words(sentence), number_of_words);
        List<String> words = Util.blist_safe(index_words);
        return Util.string(words);
    }

    private static String cut(String raw_word) {
        return raw_word.substring(0,raw_word.length() -1);
    }

    public static int num(String sub, String word){
        Matcher matcher = matcher(Pattern.quote(sub), word);
        int count = 0;
        while(matcher.find()){
            count++;
        }
        return count;
    }

    public static String remove_indices(String string, Collection<Integer> indices_to_remove){
        char[] chars = string.toCharArray();
        for (Integer integer : indices_to_remove) {
            chars[integer] = '\u30F5';
        }
        List<Character> new_characters = new ArrayList<>();
        for (char aChar : chars) {
            if (aChar != '\u30F5') new_characters.add(aChar);
        }
        return string_char(new_characters);
    }

    public static String string_char(List<Character> character_array){
        char[] array = new char[character_array.size()];
        for (int i = 0; i < character_array.size(); i++) {
            array[i] = character_array.get(i);
        }
        return new String(array);
    }

    public static List<Integer> removeAll(String regex, StringMutable string){
        Set<Integer> indices_to_remove = new HashSet<>();
        Matcher matcher = matcher(regex, string.string);
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            indices_to_remove.addAll(between(start, end));
        }
        String removed = Util.remove_indices(string.string, indices_to_remove);
        string.set(removed);
        ArrayList<Integer> indices_removed = new ArrayList<>(indices_to_remove);
        Collections.sort(indices_removed);
        return indices_removed;
    }


    public static int num(char character, String word){
        int count = 0;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            if (aChar == character) count++;
        }
        return count;
    }

    public static <S,T> List<T> blist_safe(MultiList<S, T> multiList) {
        if (Util.empty(multiList)) return newArrayList();
        return multiList.getb_list();
    }


    public static <S,T> List<S> alist_safe(MultiList<S, T> multiList) {
        if (Util.empty(multiList)) return newArrayList();
        return multiList.geta_list();
    }

    public static List<Integer> integer_list(int length){
        return integer_list(0,length -1);
    }


    public static String remove(String to_match, String input, Integer matched_instance_to_remove){
        String to_return = input;
        Matcher matcher = matcher(Pattern.quote(to_match),input);
        int instance = 1;
        while (matcher.find()){
            if (instance < matched_instance_to_remove) {instance++; continue;}
            to_return = Util.remove_between(matcher.start(), matcher.end(), input);
            break;
        }
        return to_return;
    }

    public static String get_ignore_case(Collection<String> collection, String word){
        for (String s : collection) {
            if (s.equalsIgnoreCase(word)){
                return s;
            }
        }
        return null;
    }

    public static String remove_last(String to_remove, String input){
        if (Util.empty(input)) return input;
        if (Util.empty(to_remove)) return input;
        String quote = Pattern.quote(to_remove);
        Matcher matcher = matcher(quote, input);
        Integer start_index = null;
        Integer end_index = null;
        while (matcher.find()){
            start_index = matcher.start();
            end_index = matcher.end();
        }
        if (start_index == null) return input;
        return remove_between(start_index, end_index, input);
    }

    public static void remove_between(int start_inclusive, int end_exclusive, StringMutable input) {
        if (input == null) return;
        String s = remove_between(start_inclusive, end_exclusive, input.string);
        input.set(s);
    }

    public static String remove_between(int start_inclusive, int end_exclusive, String input){
        if (Util.empty(input)) return input;
        String part_1 = substring(0, start_inclusive, input);
        String part_2 = substring(end_exclusive, input.length(), input);
        return part_1 + part_2;
    }

    public static Integer proximal_index(String whole, int index, String search_term, int radius){
        if (Util.empty(whole)) return null;
        if (Util.empty(search_term)) return null;
        int start_index = Math.max(0, index - radius);
        int end_index = Math.min(whole.length() - 1, index + radius);
        String substring = Util.substring(start_index, end_index, whole);
        int index_of_search_term = substring.indexOf(search_term);
        if (index_of_search_term == -1) return null;
        return index_of_search_term + start_index;
    }

    public static String substring(int start_inclusive, int end_exclusive, String input){
        int length = input.length();
        return input.substring(Math.max(0,Math.min(start_inclusive, length)), Math.min(end_exclusive, length));
    }

    public static String range(Integer index, Integer range, String text){
        if (text == null) return null;
        return text.substring(Math.max(0,index -range), Math.min(index + range, text.length()));
    }

    public static List<Integer> integer_list(int start_inclusive, int end_inclusive){
        List<Integer> to_return = newArrayList();
        for (int i = start_inclusive; i <= end_inclusive; i++) {
            to_return.add(i);
        }
        return to_return;
    }

    public static <T> CountMap<T> countmap(Collection<T> collection){
        CountMap<T> countMap = new CountMap<>();
        countMap.addAll(collection);
        return countMap;
    }

    public static <S extends Comparable<S>,T> void sort_a(MultiList<S,T> multiList){
        if (empty(multiList)) return;
        List<Multi<S, T>> list = multiList.underlying;
        list.sort(new Comparator<Multi>() {
            @Override
            public int compare(Multi multi_1, Multi multi_2) {
                return ((Comparable<S>)multi_1.a).compareTo((S)multi_2.a);
            }
        });
    }

    public static <S,T extends Comparable<T>> void sort_b(MultiList<S,T> multiList){
        if (empty(multiList)) return;
        List<Multi<S, T>> list = multiList.underlying;
        list.sort(new Comparator<Multi>() {
            @Override
            public int compare(Multi multi_1, Multi multi_2) {
                return ((Comparable<T>)multi_1.b).compareTo((T)multi_2.b);
            }
        });
    }


    public static <T> T interpolate_before(MultiList<Integer,T> multiList, Integer key){
        if (Util.empty(multiList)) return null;
        Integer best_match = null;
        for (Integer s : multiList.geta_list()) {
            if (s > key) continue;
            if (best_match == null) best_match = s;
            if (s > best_match) best_match = s;
        }
        if (best_match == null) return null;
        return multiList.getb_one(best_match);
    }

    public static <T> T interpolate_before_strictly(MultiList<Integer,T> multiList, Integer key){
        if (Util.empty(multiList)) return null;
        Integer best_match = null;
        for (Integer s : multiList.geta_list()) {
            if (s >= key) continue;
            if (best_match == null) best_match = s;
            if (s > best_match) best_match = s;
        }
        if (best_match == null) return null;
        return multiList.getb_one(best_match);
    }

    public static <S,T extends Comparable<T>> List<S> sort(Map<S,T> map){
        ArrayList<S> list = new ArrayList<>(map.keySet());
        list.sort(new Comparator<S>() {
            @Override
            public int compare(S o1, S o2) {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        return list;
    }

    public static String trim(String string){
        if (string == null) return null;
        return string.trim().replaceAll("^\u00A0","").trim();
    }

    public static Double divide(Integer numerator, Integer denominator){
        Double n = Double.valueOf(numerator);
        Double d = Double.valueOf(denominator);
        return n / d;
    }

    public static Double average_length_words(String title){
        if (Util.empty(title)) return null;
        MultiList<Integer, String> index_words = words(title);
        List<String> words = blist_safe(index_words);
        int size = words.size();
        int length = title.length();
        return divide(length, size);
    }


    public static <T extends Comparable> T ordered_last(Collection<T> collection){
        if (Util.empty(collection)) return null;
        ArrayList<T> list = new ArrayList<>(collection);
        Collections.sort(list);
        return last(list);
    }


    public static String remove_word_safely(String input, String word_to_remove) {
        return remove_word_safely(input, word_to_remove, false);
    }

    public static String remove_word_safely(String input, String word_to_remove, boolean case_insensitive) {
        return replace_word_safely(input, word_to_remove, case_insensitive, " ");
    }

    public static String replace_word_safely(String input, String word_to_remove, boolean case_insensitive, String replacement){
        if (Util.empty(input) || Util.empty(word_to_remove)) return input;
        String to_return = input;
        List<String> variations = newArrayList(
                "^" + word_to_remove + "$",
                "^" + word_to_remove + "[\\.|:|,|\\?|\\s]",
                "[\\s|\\(]" + word_to_remove+ "$",
                "[\\s|\\(]" + word_to_remove + "[\\.|:|,|\\?|\\s]",
                "[\\s|\\(]" + word_to_remove + "s(?![a-z|A-Z])"
        );
        for (String variation : variations) {
            if (case_insensitive) variation = "(?i)" + variation;
            to_return = to_return.replaceAll(variation, replacement);
        }
        return to_return;
    }




    public static Matcher matcher__classic(String text, String matched_string){
        text = text.toLowerCase();
        Pattern pattern = Pattern.compile(".*" + matched_string + ".*");
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

    public static <T> T safe_null(T preferred, T fallback){
        return (preferred != null) ? preferred : fallback;
    }

    public static NList safe_null(NList nList) {
        if ((empty(nList))){
            nList = new NList();
            nList.underlying = new ArrayList<>();
            return nList;
        } else {
            return nList;
        }
    }

    public static <T> List<T> safe_null(List<T> a) {
        return empty(a) ? new ArrayList<T>() : a;
    }

    public static <T> Set<T> safe_null(Set<T> a) {
        return empty(a) ? new HashSet<T>() : a;
    }

    public static <T> void add_all(Collection<T> existing, Collection<T> new_elements, Integer limit){
        if (existing == null || new_elements == null) return;
        if (limit != null){
            int remaining = limit - existing.size();
            if (remaining <= 0) return;
            List<T> sublist = Util.sublist(new ArrayList<T>(new_elements), remaining);
            existing.addAll(sublist);
        } else {
            existing.addAll(new_elements);
        }
    }

    public static void add_all(Collection existing, Collection new_elements) {
        add_all(existing, new_elements, null);
    }

    public static void add_all(MultiList existing, MultiList new_elements) {
        if (existing == null || new_elements == null) return;
        add_all(existing.underlying, new_elements.underlying);
    }

    public static <T> void safe_add(Collection<T> collection, T o){
        if (collection == null || o == null) return;
        collection.add(o);
    }

    public static <T> void safe_add(Collection<T> existing, Collection<T> new_items){
        if (existing == null) return;
        if (new_items == null) return;
        existing.addAll(new_items);
    }

    public static Collection<String> safe_null_space(Collection<String> event_urls) {
        event_urls.removeIf(Util::empty);
        return event_urls;
    }

    public static <T> List<Integer> indexes_of_all(T object, List<T> list){
        List<Integer> to_return = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (object.equals(list.get(i))) to_return.add(i);
        }
        return to_return;
    }
    public static String file_name(String additional_string){
        return additional_string + "_"+ Util.getTimestamp() + ".txt";
    }

    public static String getTimestamp() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String name = Thread.currentThread().getName();
        return "_" + System.currentTimeMillis() + "_" + name.charAt(name.length() -1);
    }



    public static <T,S> HashMap<T,S> newHashMap(T key, S value) {
        HashMap hashMap = new HashMap();
        hashMap.put(key, value);
        return hashMap;
    }


    public static <A,B> void add_safe(Map<A,B> existing, Map<A,B> to_add){
        if(existing == null) return;
        if (Util.empty(to_add)) return;
        existing.putAll(to_add);
    }

    public static <E> ArrayList<E> newArrayList_safe(E... objects){
        ArrayList<E> objectsList = new ArrayList<>();
        for (E object : objects) {
            if (object == null) continue;
            objectsList.add(object);
        }
        return objectsList;
    }

    public static <E> ArrayList<E> newArrayList(){
        return new ArrayList();
    }

    public static <E> ArrayList<E> newArrayList(E... objects){
        ArrayList<E> objectsList = new ArrayList<>();
        for (E object : objects) {
            objectsList.add(object);
        }
        return objectsList;
    }

    public static <E> ArrayList<E> list(E... objects){
        return newArrayList(objects);
    }

    public static <E> ArrayList<E> list(){
        return newArrayList();
    }

    public static <T,S> HashMap<T,S> map() {
        return new HashMap<>();
    }

    public static <K extends Comparable<? super K>,T> MapList<K,T> mapList(){
        return new MapList<>();
    }

    public static <E> Set<E> set(){
        return new HashSet<>();
    }


    public static <E> Set<E> set(Collection<E> collection){
        return new HashSet<>(collection);
    }

    public static <T> String string(T[] collection) {
        if (collection == null) return null;
        return out(Arrays.asList(collection)," ");
    }

    public static <T> String string(NList list){
        return string(list.underlying);
    }

    public static <T> String string(SList list, String delimiter){
        return string(list.underlying, delimiter);
    }

    public static <T> String string(SList list){
        return string(list.underlying);
    }

    public static <T> String string(Collection<T> collection) {
        if (collection == null ) return null;
        return out(collection, " ");
    }

    public static String string(Collection collection, String delimiter) {
        if (collection == null) return null;
        return Util.out(collection, delimiter);
    }

    public static String string(String delimiter, String... strings){
        if (strings == null || strings.length == 0) return null;
        return string(Arrays.asList(strings), delimiter);
    }

    public static <T> String paragraph(Collection<T> collection) {
        if (collection == null) return null;
        return out(collection, "\n");
    }

    private static <T> String out(Collection<T> collection, String separator){
        StringBuilder sb = new StringBuilder();
        for (T object : collection) {
            if (object != null){
                String str = object.toString();
                if (str != null){
                    sb.append(str);
                }
            }
            sb.append(separator);
        }
        return Util.substring_remove_last(sb, separator.length());
    }

    public static <T> List<T> safe_1(List<T> collection){
        if (Util.empty(collection)){
            List x = new ArrayList(); x.add(null);
            return x;
        } else return collection;
    }

    public static void add_max(Collection add_to, List new_items, int max){
        if (add_to == null) return;
        if (empty(new_items)) return;
        for (int i = 0; i < Math.min(max, new_items.size()); i++) {
            if (add_to.size() >= max) return;
            add_to.add(new_items.get(i));
        }
    }

    public static <E> HashSet<E> newHashSet(){
        return new HashSet<E>();
    }

    public static <E> HashSet<E> newHashSet(E... objects){
        HashSet<E> objectsSet = new HashSet<>();
        for (E object : objects) {
            objectsSet.add(object);
        }
        return objectsSet;
    }

    public static String padleft(String input, Character pad, Integer max_chars){
        if (input == null || pad == null) return null;
        if (input.length() >= max_chars) return input;
        int chars_to_add = max_chars - input.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars_to_add; i++) {
            sb.append(String.valueOf(pad));
        }
        sb.append(input);
        return sb.toString();
    }


    public static String first_word(String sentence){
        if (sentence == null) return null;
        return split(sentence, " ").get(0);
    }


    public static List<String> phrase_intersection(String name, String name_, int phrase_length,
                                                   Integer start_restriction) {
        List<String> words_ = split(name_, "\\s+").underlying;
        List<String> words = split(name, "\\s+").underlying;
        words_.removeIf(w -> w.length() < 3);
        words.removeIf(w -> w.length() < 3);
        words_ = Util.sublist(words_, 20);
        words = Util.sublist(words, 20);
        if (words.size() < phrase_length || words_.size() < phrase_length) return null;
        int limit = words.size()- (phrase_length-1);
        if (start_restriction != null){
            limit = Math.min(limit, start_restriction);
        }
        for (int i = 0; i < limit; i++) {
            for (Integer index_ : indexes_of_all(words.get(i), sublist(words_, start_restriction == null ? words_.size() : start_restriction))) {
                if (words.get(i + 1).equals(Util.get(words_,index_ + 1))) {
                    if (phrase_length > 2){
                        if (words.get(i + 2).equals(Util.get(words_,index_ + 2))) {
                            if (phrase_length > 3) {
                                if (words.get(i + 3).equals(Util.get(words_, index_ + 3))) {
                                    if (phrase_length > 4) {
                                        if (words.get(i + 4).equals(Util.get(words_, index_ + 4))) {
                                            if (phrase_length > 5) {
                                                if (words.get(i + 5).equals(Util.get(words_, index_ + 5))) {
                                                    return newArrayList(words.get(i), words.get(i + 1), words.get(i + 2), words.get(i + 3), words.get(i + 4), words.get(i + 5));
                                                }
                                            } else {
                                                return newArrayList(words.get(i), words.get(i + 1), words.get(i + 2), words.get(i + 3), words.get(i + 4));
                                            }
                                        }
                                    } else {
                                        return newArrayList(words.get(i), words.get(i + 1), words.get(i + 2), words.get(i + 3));
                                    }
                                }
                            } else {
                                return newArrayList(words.get(i), words.get(i + 1), words.get(i + 2));
                            }
                        }
                    } else {
                        return newArrayList(words.get(i), words.get(i + 1));
                    }
                }
            }
        }
        return null;
    }


    public static String remove_quotes(String string){
        if (Util.empty(string)) return string;
        if (string.startsWith("\"")){
            string = string.substring(1);
        }
        if (string.endsWith("\"")){
            string = string.substring(0, string.length() -1);
        }
        return string;
    }

    public static String is_null(String preferred, String fallback) {
        return preferred != null ? preferred : fallback;
    }

    public static <T> List<T> except(Collection<T> collection, Collection<T> exceptions) {
        if (collection == null) return null;
        if (Util.empty(exceptions)) return new ArrayList<>(collection);
        return collection.stream().filter(object -> !exceptions.contains(object)).collect(Collectors.toList());
    }

    public static String after_last_slash(String url){
        if (url == null) return null;
        return split(url.replace("/#","#"),"/").get_last();
    }

    public static List<String> except(Collection<String> collection, String exception) {
        if (collection == null) return null;
        if (exception == null) return new ArrayList<>(collection);
        List<String> list = new ArrayList<>();
        list.add(exception);
        return except(collection, list);
    }

    public static <T> List<T> filter(List<T> list, List<T> exclusions) {
        return list.stream().filter(e -> !exclusions.contains(e)).collect(Collectors.toList());
    }

    public static String first_sentence(String paragraph){
        NList split = Util.split(paragraph, "\\s+");
        Integer end_index = null;
        for (int i = 0; i < split.size(); i++) {
            String s = split.underlying.get(i);
            if (s.endsWith(".")){
                end_index = i;
                break;
            }
        }
        if (end_index != null){
            paragraph = split.reconstruct(end_index + 1);
        }
        return paragraph;
    }

    public static List<String> underlying(NList nlist){
        if (nlist == null) return null;
        return nlist.underlying;
    }

    public static List<String> word_list(String string, Integer min_length){
        List<String> strings = blist_safe(words(string));
        if (Util.empty(strings) || min_length == null) return strings;
        strings.removeIf(s -> s.length() < min_length);
        return strings;
    }

    public static List<String> word_list(String string){
        return word_list(string, null);
    }

    public static int word_count(String string){
        List<String> strings = word_list(string);
        return Util.size(strings);
    }

    public static boolean complete(Multi multi){
        if (multi == null) return false;
        if (multi.a == null) return false;
        if (multi.b == null) return false;
        return true;
    }

    public static NList split_safe(String string, String delimiter_regex){
        if (string == null){
            return split("", delimiter_regex);
        } else {
            return split(string, delimiter_regex);
        }
    }

    public static Multi<String, String> split_one(String string, String delimiter_character){
        if (string == null) return null;
        int i = string.indexOf(delimiter_character);
        if (i == -1 || i == string.length() - 1) return new Multi(string, null);
        return new Multi(Util.substring(0, i, string), Util.substring(i + 1, string.length(), string));
    }



    public static NList split(String string, String delimiter_regex) {
        if (string == null) return null;
        List<String> list = new ArrayList<>(Arrays.asList(string.split(delimiter_regex)));
        NList nlist = new NList();
        nlist.underlying = list;
        nlist.split_delimiter = delimiter_regex;
        return nlist;
    }

    public static List<String> split_list(String string, String delimiter_regex){
        NList split = split(string, delimiter_regex);
        if (split == null) return null;
        return split.underlying;
    }

    public static <T> boolean safe_contains(Collection<T> collection, T element){
        if (Util.empty(collection)) return false;
        return collection.contains(element);
    }

    public static <T,S> T safe_a(Multi<T,S> multi){
        if (multi == null) return null;
        return multi.a;
    }

    public static <T,S> T a(Multi<T,S> multi){
        return safe_a(multi);
    }

    public static <T,S> S safe_b(Multi<T,S> multi){
        if (multi == null) return null;
        return multi.b;
    }



    public static boolean contains_ignore_case(String string, String substring){
        if (string == null || substring == null) return false;
        return Util.lowercase(string).contains(Util.lowercase(substring));
    }

    public static boolean contains_ignore_case(String string, List<String> list){
        return !Util.empty(get_ignore_case(list, string));
    }

    public static <T,S> S b(Multi<T,S> multi){
        return safe_b(multi);
    }

    public static <A,B,C> A safe_a(Multi_3<A,B,C> multi){
        if (multi == null) return null;
        return multi.a;
    }

    public static <A,B,C> B safe_b(Multi_3<A,B,C> multi){
        if (multi == null) return null;
        return multi.b;
    }

    public static <A,B,C> C safe_c(Multi_3<A,B,C> multi){
        if (multi == null) return null;
        return multi.c;
    }

    public static String substring_remove_last(StringBuilder sb, Integer num_to_remove){
        if (sb.length() == 0){
            return "";
        } else {
            if (num_to_remove >= sb.length()) return "";
            return sb.substring(0, sb.length() - num_to_remove);
        }
    }

    public static String substring_remove_last(String string, Integer num_to_remove){
        if (string.length() == 0){
            return "";
        } else {
            if (num_to_remove >= string.length()) return "";
            return string.substring(0, string.length() - num_to_remove);
        }
    }

    public static String substring_remove_last(String sb){
        if (sb.length() == 0){
            return "";
        } else {
            return sb.substring(0, sb.length() - 1);
        }
    }


    public static List<String> uppercase(List<String> input){
        List<String> to_return = new ArrayList<>();
        for (String s : input) {
            to_return.add(s == null ? s : s.toUpperCase());
        }
        return to_return;
    }

    public static String substring_remove_first(String sb){
        if (sb.length() == 0){
            return "";
        } else {
            return sb.substring(1, sb.length());
        }
    }

    public static String substring_remove_first(StringBuilder sb){
        if (sb.length() == 0){
            return "";
        } else {
            return sb.substring(1, sb.length());
        }
    }

    public static String substring_remove_last(StringBuilder sb){
        if (sb.length() == 0){
            return "";
        } else {
            return sb.substring(0, sb.length() - 1);
        }
    }

    public static boolean equal_to(String a, String b) {
        if (a != null){
            return a.equals(b);
        } else if (b != null){
            return b.equals(a);
        } else {
            return false;
        }
    }

}
