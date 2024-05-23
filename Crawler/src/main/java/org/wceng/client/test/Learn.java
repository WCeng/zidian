package org.wceng.client.test;

import java.util.*;

public class Learn {


    public static void main(String[] args) {
        Cat cat1 = new Cat("a", 2);
        Cat cat2 = new Cat("a", 2);

        ArrayList<Cat> cats = new ArrayList<Cat>();
        cats.add(cat1);

        Set<Cat> catsSet = new HashSet<>();
        catsSet.add(cat1);
        catsSet.add(cat2);
        System.out.println(catsSet.size());


        System.out.println(cats.contains(cat2));
    }


}

class Cat {
    String name;
    int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return age == cat.age && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
