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
        A a  = new B();
        // if(a instanceof B) a.iq;  // Commented out incomplete statement
    }
}
