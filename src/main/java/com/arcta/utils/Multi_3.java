package com.arcta.utils;

import java.io.Serializable;
import java.util.Objects;

public class Multi_3<A,B,C> implements Serializable {

    public A a;
    public B b;
    public C c;

    public Multi_3(A a, B b, C c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multi_3<?, ?, ?> multi_3 = (Multi_3<?, ?, ?>) o;
        return Objects.equals(a, multi_3.a) &&
                Objects.equals(b, multi_3.b) &&
                Objects.equals(c, multi_3.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
