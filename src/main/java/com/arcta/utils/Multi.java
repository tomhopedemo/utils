package com.arcta.utils;

import java.io.Serializable;

public class Multi<A,B> implements Serializable {

    public A a;
    public B b;

    public Multi(A a, B b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multi<?, ?> multi = (Multi<?, ?>) o;

        if (a != null ? !a.equals(multi.a) : multi.a != null) return false;
        return b != null ? b.equals(multi.b) : multi.b == null;

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}
