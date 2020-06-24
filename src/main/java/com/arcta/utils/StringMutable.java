package com.arcta.utils;

public class StringMutable {

    public String string;

    public StringMutable(String string) {
        this.string = string;
    }
    public StringMutable(StringMutable sm) {
        this.string = sm.string;
    }

    public void set(String string){
        this.string = string;
    }

    public String substring(int begin_index){
        return string.substring(begin_index);
    }

    public boolean contains(CharSequence charSequence){
        return string.contains(charSequence);
    }
}
