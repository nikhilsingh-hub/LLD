package Multithreading.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class MovieReservationSystem {
    public int availableSeats;
    public ReentrantLock reservationLock;

    public MovieReservationSystem(int totalSeats){
        this.availableSeats = totalSeats;
        this.reservationLock = new ReentrantLock();
    }

    public boolean reserveSeats(int bookSeatCount){
        reservationLock.lock();
        try{
            if(availableSeats >= bookSeatCount){
                availableSeats -= bookSeatCount;
                return true;
            }
        }finally {
            reservationLock.unlock();
        }
        return false;
    }

    public int getAvailableSeats(){
        return this.availableSeats;
    }

}
