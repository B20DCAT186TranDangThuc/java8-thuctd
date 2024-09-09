package com.dd.thuctd.bai6;

import java.math.BigDecimal;
import java.util.Comparator;

public class Developer {
    private String name;
    private BigDecimal salary;
    private int age;

    public Developer() {

    }

    public Developer(String name, BigDecimal salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Comparator<Developer> byName = new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Comparator<Developer> byName2 = (Developer o1, Developer o2) -> o1.getName().compareTo(o2.getName());


    }
}
