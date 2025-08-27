package Inheritance;

import Multithreading.Adder;
import Multithreading.Main;
import Multithreading.ScalerThread;
import Multithreading.Subtractor;

public class StartYaar {
    public A test(){
        return new B();
    }
    public static void main(String[] args) {
        StartYaar startYaar = new StartYaar();
        System.out.println(startYaar.hashCode());
        System.out.println(startYaar.hashCode());

    }
}
