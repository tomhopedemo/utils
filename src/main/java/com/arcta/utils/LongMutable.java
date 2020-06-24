package com.arcta.utils;

public class LongMutable {
    public long underlying;

    public LongMutable(Long underlying) {
        this.underlying = underlying;
    }
    public LongMutable(LongMutable lm) {
        this.underlying = lm.underlying;
    }

    public void set(Long underlying){
        this.underlying = underlying;
    }

}
