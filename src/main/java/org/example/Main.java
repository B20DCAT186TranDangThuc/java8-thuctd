package org.example;


public class Main implements MyFirstFunctionInterface {


    public static void main(String[] args) {

        Main main = new Main();
        main.defaultMethod();
        main.function();

    }

    @Override
    public void function() {
        System.out.println("hello world");
    }

}