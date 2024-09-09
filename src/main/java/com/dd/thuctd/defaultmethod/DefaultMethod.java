package com.dd.thuctd.defaultmethod;

public interface DefaultMethod {

    default void move() {
        System.out.println("default method move");
    }

    class Dog implements DefaultMethod {

    }

    class Cat implements DefaultMethod {
        @Override
        public void move() {
            System.out.println("cat method move");
        }
    }

    public static void main(String[] args) {
        // class dog khong ghi de method move nen print "default method move"
        Dog a = new Dog();
        a.move();

        // class cat ghi de nen da su dung method move moi dc dinh nghia trong class cat
        Cat b = new Cat();
        b.move();

        /* tai sao java 8 can default method
        * - ho tro bieu thuc lambda
        * - mo rong khong gian doan: khi muon thay them mot phuong thuc vao mot interface, ta khong can phai thay doi code cua
        * cac class dang trien khai interface do
        * */

        /* giai quyet xung dot khi goi cac default method
        * 1. default method ghi de trong lop se duoc uu tien cao nhat
        * 2. default method cua interface cu the hon se duoc chon (neu Animal implement Move va Walk ma Walk lai extdends Move
        * thi default method tu Walk se duoc uu tien)
        * 3. neu 2 interface doc lap va ca 2 cung cung cap mot default method voi cung ten --> ta phai chi ro giao dien nao nen duoc
        * goi: A.super.method()
        * */


    }
}
