package com.arcta.utils;

import java.util.ArrayList;

public class Matchers {

    // -------------------- OTHER MATCHERS -----------------------
    public static final String M__HYPHENS;     //no spaces already grouped
    public static final String M__HYPHENSO;     //no spaces already grouped
    public static final String M__HYPHENS_UNDERSCORES_DOTS;     //no spaces already grouped
    public static final String M__HYPHENS_TO;     //no spaces already grouped
    public static final String M__HYPHENS_TO_UNTIL;     //no spaces already grouped

    static {
        StringBuilder sb__hyphen = new StringBuilder();
        StringBuilder sb__hyph_under = new StringBuilder();
        StringBuilder sb__hyph_to = new StringBuilder();
        StringBuilder sb__hyph_to_until = new StringBuilder();

        for (String hyphen : Util.HYPHENS) {
            sb__hyphen.append(hyphen);
            sb__hyphen.append("|");
        }

        ArrayList<String> hyphens_and_underscores_and_dots = new ArrayList<>(Util.HYPHENS);
        hyphens_and_underscores_and_dots.add("_");
        //hyphens_and_underscores_and_dots.add("");
        hyphens_and_underscores_and_dots.add("\\.");
        for (String symbol : hyphens_and_underscores_and_dots){
            sb__hyph_under.append(symbol);
            sb__hyph_under.append("|");
        }

        ArrayList<String> hyphens_and_to = new ArrayList<>(Util.HYPHENS);
        hyphens_and_to.add("to");
        for (String symbol : hyphens_and_to){
            sb__hyph_to.append(symbol);
            sb__hyph_to.append("|");
        }

        ArrayList<String> hyphens_and_to_until = new ArrayList<>(hyphens_and_to);
        hyphens_and_to_until.add("until");
        hyphens_and_to_until.add("till");
        hyphens_and_to_until.add("\u00bb");
        for (String symbol : hyphens_and_to_until){
            sb__hyph_to_until.append(symbol);
            sb__hyph_to_until.append("|");
        }


        M__HYPHENS = "(" + sb__hyphen.substring(0, sb__hyphen.length() - 1) + ")";
        M__HYPHENSO = M__HYPHENS + "?";
        M__HYPHENS_TO = "(" + sb__hyph_to.substring(0, sb__hyph_to.length() - 1) + ")";
        M__HYPHENS_TO_UNTIL = "(" + sb__hyph_to_until.substring(0, sb__hyph_to_until.length() - 1) + ")";
        M__HYPHENS_UNDERSCORES_DOTS = "(" + sb__hyph_under.substring(0, sb__hyph_under.length() - 1) + ")";
    }
}

