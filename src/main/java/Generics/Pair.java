package Generics;

public class Pair<T, U>{
    T first;
    U second;
    public Pair(T x, U y){
        this.first = x;
        this.second = y;
    }

    public T getfirst(){
        return first;
    }

    public U getSecond(){
        return second;
    }
}
