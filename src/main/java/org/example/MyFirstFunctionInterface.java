package org.example;

@FunctionalInterface
public interface MyFirstFunctionInterface {
    // mot function interface chi co duy nhat 1 phuong thuc truu tuong
    public void function();

    // default method khong anh huong den tinh hop le cua function interface
    default void defaultMethod() {
        System.out.println("default method");
    }

    // cac phuong thuc duoc ghi de tu lava.lang.Object cung khong duoc tinh vao so luong phuong thuc truu tuong cua interface
    @Override
    boolean equals(Object obj);
}
