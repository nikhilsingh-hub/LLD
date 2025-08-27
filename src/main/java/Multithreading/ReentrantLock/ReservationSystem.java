package Multithreading.ReentrantLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationSystem {
    Map<String, Integer> availableSeats;
    Map<String, ReentrantLock> seatTypeLocks;

    public ReservationSystem(Map<String, Integer> availableSeats){
        this.availableSeats = new HashMap<>(availableSeats);
        this.seatTypeLocks = new HashMap<>();
        for(String seatType : availableSeats.keySet()){
            seatTypeLocks.put(seatType, new ReentrantLock());
        }
    }

    public boolean reserveSeats(String seatType, int numSeats){
        ReentrantLock currLock =  seatTypeLocks.get(seatType);
        currLock.lock();
        try{
            if(availableSeats.get(seatType) >= numSeats){
                availableSeats.put(seatType, availableSeats.get(seatType)-numSeats);
                return true;
            }
        }finally {
            currLock.unlock();
        }
        return false;
    }

    public int getAvailableSeats(String seatType) {
        return availableSeats.get(seatType);
    }
}
