package com.stysams.selfuse.test;

import lombok.SneakyThrows;

/**
 * @author StysaMS
 */
public class CloneTest {

    @SneakyThrows
    public static void main(String[] args) {

        Person person1 = new Person();
        person1.phone = new Phone();
        System.out.println(person1);
        System.out.println(person1.phone);
        Object clone = person1.clone();
        System.out.println(clone);
        System.out.println(((Person)clone).phone);
    }


    static class Person implements Cloneable{
        String name;
        Phone phone;
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class Phone{
        String name;
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
