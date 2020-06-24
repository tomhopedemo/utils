package com.arcta.utils;

public class Multi__C<T extends Comparable<T>,S extends Comparable<S>> implements Comparable<Multi__C<T,S>> {

    public T a;
    public S b;

    public Multi<T,S> multi;

    public Multi__C(T a, S b){
        this.multi = new Multi<T, S>(a,b);
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multi__C<?, ?> multi__c = (Multi__C<?, ?>) o;

        return multi != null ? multi.equals(multi__c.multi) : multi__c.multi == null;

    }

    @Override
    public int hashCode() {
        return multi != null ? multi.hashCode() : 0;
    }

    @Override
    public int compareTo(Multi__C<T,S> o) {
        int comparison;
        if (multi.b == null && o.multi.b == null){
            comparison = 0;
        } else if (multi.b == null){
            comparison = -1;
        } else {
            comparison = multi.b.compareTo(o.multi.b);
        }
        return comparison == 0 ? multi.a.compareTo(o.multi.a) : comparison;
    }

}
