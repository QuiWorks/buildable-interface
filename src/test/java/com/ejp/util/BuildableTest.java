package com.ejp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildableTest {

    private final String name = "test";
    private final int age = 3;
    private final Function<Pojo, Pojo> setTestInstructions = b -> b
            .set(b::name, name)
            .set(b::age, age);
    private final Function<Pojo, Pojo> actTestInstructions = b -> b
            .set(b::name, name)
            .set(b::age, age)
            .act(b::incrementAge);

    private Buildable<Pojo> buildable;

    @BeforeEach
    void setUp() {
        buildable = new Pojo();
    }

    @Test
    void getThis() {

        Pojo aThis = buildable.getThis();

        assertEquals(aThis, buildable);
    }

    @Test
    void set() {

        Pojo build = buildable.build(setTestInstructions);

        assertEquals(name, build.name);
        assertEquals(age, build.age);
        assertEquals(build, buildable);
    }

    @Test
    void act() {
        Pojo build = buildable.build(actTestInstructions);

        assertEquals(name, build.name);
        assertEquals(age + 1, build.age);
        assertEquals(build, buildable);
    }


    private class Pojo implements Buildable<Pojo> {
        public String name = "Pojo";
        public int age = 0;

        @Override
        public Pojo getThis() {
            return this;
        }

        public void incrementAge()
        {
            this.age++;
        }

        public String name() {
            return name;
        }

        public void name(String name) {
            this.name = name;
        }

        public int age() {
            return age;
        }

        public void age(int age) {
            this.age = age;
        }
    }
}