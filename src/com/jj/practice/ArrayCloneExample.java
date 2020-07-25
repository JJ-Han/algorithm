package com.jj.practice;

public class ArrayCloneExample {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] b = a.clone();

        System.out.println(a == b ? "Same Instance":"Different Instance");
        //Outputs different instance

        b[0] = 5;
        System.out.println(a[0]);
        System.out.println(b[0]);
        //Outputs: 1
        //         5

        //Array containing Objects
        Dog[] myDogs = new Dog[4];

        myDogs[0] = new Dog("Wolf");
        myDogs[1] = new Dog("Pepper");
        myDogs[2] = new Dog("Bullet");
        myDogs[3] = new Dog("Sadie");

        //Dog[] myDogsClone = myDogs.clone();
        Dog[] myDogsClone = new Dog[myDogs.length];
        System.arraycopy(myDogs, 0, myDogsClone, 0, myDogs.length);

        System.out.println(myDogs[0] == myDogsClone[0] ? "Same":"Different");
        System.out.println(myDogs[1] == myDogsClone[1] ? "Same":"Different");
        System.out.println(myDogs[2] == myDogsClone[2] ? "Same":"Different");
        System.out.println(myDogs[3] == myDogsClone[3] ? "Same":"Different");

        myDogsClone[0].setName("Ruff");
        System.out.println(myDogs[0].getName());

        myDogsClone[1] = new Dog("Spot");
        System.out.println(myDogsClone[1].getName());
        System.out.println(myDogs[1].getName());
        myDogs[1] = myDogsClone[1];
        myDogsClone[1].setName("TEST");
        System.out.println(myDogsClone[1].getName());
        System.out.println(myDogs[1].getName());

        System.out.println(myDogs[0] == myDogsClone[0] ? "Same":"Different");
        System.out.println(myDogs[1] == myDogsClone[1] ? "Same":"Different");
        System.out.println(myDogs[2] == myDogsClone[2] ? "Same":"Different");
        System.out.println(myDogs[3] == myDogsClone[3] ? "Same":"Different");
    }

    static class Dog{

        private String name;

        public Dog(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}