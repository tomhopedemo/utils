package com.arcta.utils.jutil;

import com.arcta.utils.IntegerMutable;
import com.arcta.utils.Multi;
import com.arcta.utils.MultiList;
import com.arcta.utils.Util;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.arcta.utils.Util.newArrayList;

public class JUtil {

    public static List<String> heading_tags = newArrayList("h1","h2","h3","h4");
    public static List<String> major_groupings = newArrayList("h1","h2","h3","h4","h5","h6","p");
    public static List<String> title_classes = newArrayList(
            "title","name", "summary-title",
    //taxonomy_classes
            "event-type"
    );

    public static String title_class_ending = "-tags";

    public static String text_only(Element element) {
        if (element == null) return null;
        Multi<String, MultiList<Integer, Element>> text = text(element);
        return Util.safe_a(text);
    }

    public static Multi<String, MultiList<Integer, Element>> text(Element element) {
        return text(element, null, null, null, null, null, null, null, null, null);
    }

    public static String text_children_version(Element element){
        if (element == null) return null;
        List child_nodes = element.children();
        if (Util.empty(child_nodes)){
            return element.text();
        }
        Multi<String, MultiList> text = text(child_nodes, null, null, null, null, null, null, null, null, null);
        return Util.safe_a(text);
    }


    public static Multi<String, MultiList<Integer, Element>> text(Element element, List<String> excluded_tags,
                                                            List<String> excluded_classes, Multi<String,String> exclusion_attribute,
                                                            List<String> restricted_to_tags, List<String> restricted_to_classes,
                                                                  List<String> restricted_to_ids, Multi<String,String> restricted_to_attribute,
                                                                  String gap_class, String gap_tag){
        if (element == null) return null;
        List<Node> child_nodes = element.childNodes();
        if (Util.intersection(element.classNames(),excluded_classes)) return null;
        if (child_nodes == null || child_nodes.size() == 0){
            return new Multi(element.text(), Util.newHashMap(0, element));
        }
        return text(child_nodes, excluded_tags, excluded_classes, exclusion_attribute,
                restricted_to_tags, restricted_to_classes, restricted_to_ids, restricted_to_attribute, gap_class, gap_tag);
    }

    public static Multi<String, MultiList<Integer, Element>> text(List<Node> elements, List<String> excluded_tags,
                                                            List<String> excluded_classes, Multi<String,String> excluded_attributes,
                                                            List<String> restricted_to_tags, List<String> restricted_to_classes,
                                                                  List<String> restricted_to_ids, Multi<String,String> restricted_to_attribute,
                                                                  String gap_class, String gap_tag) {

        MultiList<String,Element> string_elements = text_finder__recursive(elements, new MultiList<>(),
                excluded_tags, excluded_classes, excluded_attributes, restricted_to_tags, restricted_to_classes,
                restricted_to_ids, restricted_to_attribute, gap_class, gap_tag);
        StringBuilder sb = new StringBuilder();
        MultiList<Integer, Element> index_element = new MultiList<>();
        for (Multi<String,Element> string_element : string_elements.underlying) {
            String string = string_element.a;
            if (string.length() == 0 && string_element.b != null){
                index_element.add(new Multi<>(sb.length(), string_element.b));
            } else {
                index_element.add(new Multi<>(sb.length(), string_element.b));
                sb.append(string);
                sb.append(" ");
            }
        }
        String string = (sb.length() == 0) ? "" : sb.substring(0, sb.length() -1);
        return new Multi<>(string, index_element);
    }

    public static MultiList<String, Element> text_recursive(List<Node> elements) {
        return text_finder__recursive(elements, new MultiList(), null, null, null, null, null, null, null, null, null);
    }

    public static MultiList<Integer,Element> index_element(Element document, String class_identifier){
        Elements children = document.children();
        MultiList<Integer, Element> index_elements = new MultiList<>();
        index_element__recursive(children, class_identifier, index_elements, new IntegerMutable());
        return index_elements;
    }

    public static MultiList<Integer,Element> index_element_by_tag(Element document, String class_identifier){
        Elements children = document.children();
        MultiList<Integer, Element> index_elements = new MultiList<>();
        index_element_by_tag__recursive(children, class_identifier, index_elements, new IntegerMutable());
        return index_elements;
    }

    public static void index_element_by_tag__recursive(List<Element> elements, String tag_identifier,
                                                MultiList<Integer, Element> index_elements, IntegerMutable current_index){
        for (Element element : elements) {
            if (tag_identifier.equals(element.tagName())){
                index_elements.add(new Multi(current_index.integer, element));
            }
            current_index.integer = current_index.integer + 1;
            index_element_by_tag__recursive(element.children(), tag_identifier, index_elements, current_index);
        }

    }



    public static void index_element__recursive(List<Element> elements, String class_identifier,
                                                                       MultiList<Integer, Element> index_elements, IntegerMutable current_index){
        for (Element element : elements) {
            if (element.classNames().contains(class_identifier)){
                index_elements.add(new Multi(current_index.integer, element));
            }
            current_index.integer = current_index.integer + 1;
            index_element__recursive(element.children(), class_identifier, index_elements, current_index);
        }

    }


    public static MultiList<String, Element> text_finder__recursive(List<Node> elements, MultiList<String,Element> list,
                                                                     List<String> excluded_tags, List<String> excluded_classes,
                                                                    Multi<String, String> exclude_attribute, List<String> restricted_to_tags,
                                                                    List<String> restricted_to_classes, List<String> restricted_to_ids,
                                                                    Multi<String,String> restricted_to_attribute, String gap_class, String gap_tag
                                                                    ) {
        for (Node node: elements) {
            if (node instanceof Element) {
                Element element = (Element) node;
                //Indicate the start of a Header Element
                if (heading_tags.contains(element.tagName())){
                    list.add(new Multi<>("\u2021", element));
//                    list.add(new Multi<>("", element));
                }
                if (element.tagName().equals("a") && !Util.empty(element.attributes().get("href"))) {
                    list.add(new Multi<>("", element));
                }
                if (element.tagName().equals("br")) list.add(new Multi<>("\u2021", null));
                if (excluded_tags != null && excluded_tags.contains((element).tagName())) continue;
                if (excluded_classes != null && Util.intersection(Util.safe_null(element.classNames()),excluded_classes)) continue;
                if (exclude_attribute != null && element.attr(exclude_attribute.a).equals(exclude_attribute.b)) continue;
                if ("element-invisible".equals(element.className())) continue;
                if (element.childNodes() == null || element.childNodes().size() == 0) {
                    String own_text = element.ownText();
                    if (own_text != null && !"".equals(own_text.trim())) {
                        if (!Util.empty(restricted_to_classes)){
                            if (!Util.intersection(JUtil.all_parent_classes(element),restricted_to_classes)) continue;
                        }
                        if (!Util.empty(restricted_to_tags)){
                            if (!Util.intersection(JUtil.all_parent_tags(element),restricted_to_tags)) continue;
                        }
                        if (!Util.empty(restricted_to_ids)){
                            if (!Util.intersection(JUtil.all_parent_ids(element), restricted_to_ids)) continue;
                        }
                        if (!Util.empty(restricted_to_attribute)){
                            if (!JUtil.all_parent_attr(element, restricted_to_attribute.a).contains(restricted_to_attribute.b)) continue;
                        }
                        list.add(new Multi<>(own_text, null));
                    }
                } else {
                    text_finder__recursive(element.childNodes(), list, excluded_tags, excluded_classes,
                            exclude_attribute, restricted_to_tags, restricted_to_classes, restricted_to_ids,
                            restricted_to_attribute, gap_class, gap_tag);
                }
                //Indicate the end of a Header Element - insert additional text
                if (heading_tags.contains(element.tagName()) || Util.intersection(Util.lowercase(element.classNames()),title_classes)) {
                    list.add(new Multi<>("\u2021", element));
                } else if (Util.endsWith_reverse(title_class_ending, Util.lowercase(element.classNames()))){
                    list.add(new Multi<>("\u2021", element));
                    //indicate the end of another type of major grouping element -
                } else if (major_groupings.contains(element.tagName())){
                    list.add(new Multi<>("\u2021",null));
                } else if (gap_class != null && Util.safe_null(element.classNames()).contains(gap_class)){
                    list.add(new Multi<>("\u2021", null));
                } else if (gap_tag != null && Util.safe_null(element.tagName()).equals(gap_tag)){
                    list.add(new Multi<>("\u2021", null));
                }
                //indicate end of a href
                if (element.tagName().equals("a") && !Util.empty(element.attributes().get("href"))) {
                    list.add(new Multi<>("", element));
                }
            } else if (node instanceof TextNode){

                if (!Util.empty(restricted_to_classes)){
                    if (!Util.intersection(JUtil.all_parent_classes_node(node),restricted_to_classes)) continue;
                }
                if (!Util.empty(restricted_to_tags)){
                    if (!Util.intersection(JUtil.all_parent_tags_node(node),restricted_to_tags)) continue;
                }
                if (!Util.empty(restricted_to_ids)){
                    if (!Util.intersection(JUtil.all_parent_ids_node(node), restricted_to_ids)) continue;
                }

                String text = ((TextNode) node).text();
                if (text != null && !"".equals(text.trim())) {
                    list.add(new Multi<>(text, null));
                }
            }
        }
        return list;
    }

    private static Set<String> all_parent_classes_node(Node node) {
        Set<String> class_names = new HashSet<>();
        Element parent_element = (Element) node.parent();
        Util.add_all(class_names, parent_element.classNames());
        for (Element parent : parent_element.parents()) {
            Util.add_all(class_names, parent.classNames());
        }
        return class_names;
    }


    private static Set<String> all_parent_tags_node(Node node) {
        Set<String> tag_names = new HashSet<>();
        Element parent_element = (Element) node.parent();
        Util.add_if_not_empty(tag_names, parent_element.tagName());
        for (Element parent : parent_element.parents()) {
            Util.add_if_not_empty(tag_names, parent.tagName());
        }
        return tag_names;
    }

    private static Set<String> all_parent_classes(Element element) {
        Set<String> class_names = new HashSet<>();
        Util.add_all(class_names, element.classNames());
        for (Element parent : element.parents()) {
            Util.add_all(class_names, parent.classNames());
        }
        return class_names;
    }


    private static Set<String> all_parent_ids_node(Node node) {
        Set<String> id_names = new HashSet<>();
        Element parent_element = (Element) node.parent();
        Util.add_if_not_empty(id_names, parent_element.id());
        for (Element parent : parent_element.parents()) {
            Util.add_if_not_empty(id_names, parent.id());
        }
        return id_names;
    }

    private static Set<String> all_parent_ids(Element element) {
        Set<String> id_names = new HashSet<>();
        Util.add_if_not_empty(id_names, element.id());
        for (Element parent : element.parents()) {
            Util.add_if_not_empty(id_names, parent.id());
        }
        return id_names;
    }

    private static Set<String> all_parent_attr(Element element, String attr) {
        Set<String> attr_values = new HashSet<>();
        Util.add_if_not_empty(attr_values, element.attr(attr));
        for (Element parent : element.parents()) {
            Util.add_if_not_empty(attr_values, parent.attr(attr));
        }
        return attr_values;
    }

    private static Set<String> all_parent_tags(Element element) {
        Set<String> tag_names = new HashSet<>();
        Util.add_if_not_empty(tag_names, element.tagName());
        for (Element parent : element.parents()) {
            Util.add_if_not_empty(tag_names, parent.tagName());
        }
        return tag_names;
    }

}
