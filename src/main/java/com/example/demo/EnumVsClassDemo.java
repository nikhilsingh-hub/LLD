package com.example.demo;


//enum Operations{
//    PLUS(1, 2);
//    private int x,  y;
//    Operations(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}

class testStatic{
    public static int x;
    int y = 2;
     int print(){
        x = 10;
        System.out.println("assigning x:"+x);
        return 1;
    }
}

public class EnumVsClassDemo{
    public static void main(String[] args) {
       testStatic y = new testStatic();
        System.out.println(testStatic.x);
    }
}