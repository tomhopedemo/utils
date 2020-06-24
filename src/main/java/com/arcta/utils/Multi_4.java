package com.arcta.utils;

import java.io.Serializable;
import java.util.Objects;

public class Multi_4<A,B,C,D> implements Serializable {

    public A a;
    public B b;
    public C c;
    public D d;

    public Multi_4(A a, B b, C c, D d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multi_4<?, ?, ?, ?> multi_4 = (Multi_4<?, ?, ?, ?>) o;
        return Objects.equals(a, multi_4.a) &&
                Objects.equals(b, multi_4.b) &&
                Objects.equals(c, multi_4.c) &&
                Objects.equals(d, multi_4.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
}
