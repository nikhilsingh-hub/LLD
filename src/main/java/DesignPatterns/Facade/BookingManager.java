package DesignPatterns.Facade;

import DesignPatterns.Facade.models.BookingConfirmation;
import DesignPatterns.Facade.models.BookingResult;
import DesignPatterns.Facade.services.*;

import java.time.LocalDate;

/**
 * BookingManager - Simplified class that delegates complex operations to BookingFacade.
 * This demonstrates the Facade pattern by separating concerns and simplifying the interface.
 */
public class BookingManager {

    private BookingFacade bookingFacade;

    public BookingManager(AvailabilityService availabilityService, PaymentService paymentService,
                          NotificationService notificationService, LoyaltyService loyaltyService,
                          AccommodationDetailsService accommodationDetailsService) {
        // Create the facade with all the services
        this.bookingFacade = new BookingFacade(
            availabilityService, paymentService, notificationService, 
            loyaltyService, accommodationDetailsService
        );
    }

    /**
     * Simplified booking method that delegates to the facade.
     */
    public BookingResult bookAccommodation(String userId, String accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return bookingFacade.bookAccommodation(userId, accommodationId, checkInDate, checkOutDate);
    }
}
