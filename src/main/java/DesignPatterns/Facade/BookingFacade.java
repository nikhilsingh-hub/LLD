package DesignPatterns.Facade;

import DesignPatterns.Facade.models.BookingConfirmation;
import DesignPatterns.Facade.models.BookingResult;
import DesignPatterns.Facade.models.PaymentStatus;
import DesignPatterns.Facade.services.*;

import java.time.LocalDate;

/**
 * BookingFacade implements the Facade pattern by encapsulating complex interactions
 * with multiple external services and providing a simplified interface.
 * 
 * This facade handles all the complex logic that was previously in BookingManager:
 * - Availability checking
 * - Payment processing
 * - Notification sending
 * - Loyalty points management
 * - Accommodation details updates
 */
public class BookingFacade {

    private AvailabilityService availabilityService;
    private PaymentService paymentService;
    private NotificationService notificationService;
    private LoyaltyService loyaltyService;
    private AccommodationDetailsService accommodationDetailsService;

    public BookingFacade(AvailabilityService availabilityService, PaymentService paymentService,
                         NotificationService notificationService, LoyaltyService loyaltyService,
                         AccommodationDetailsService accommodationDetailsService) {
        this.availabilityService = availabilityService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.loyaltyService = loyaltyService;
        this.accommodationDetailsService = accommodationDetailsService;
    }

    /**
     * Complex booking logic moved from BookingManager to this facade.
     * This method orchestrates multiple service interactions.
     */
    public BookingResult bookAccommodation(String userId, String accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        // Step 1: Check availability
        boolean isAvailable = availabilityService.checkAvailability(accommodationId, checkInDate, checkOutDate);
        if (!isAvailable) {
            return BookingResult.notAvailable("Accommodation not available for the given dates");
        }

        // Step 2: Process payment
        PaymentStatus paymentStatus = paymentService.makePayment(userId, accommodationId);
        if (paymentStatus != PaymentStatus.SUCCESS) {
            return BookingResult.paymentFailed("Payment failed with status: " + paymentStatus);
        }

        // Step 3: Create booking confirmation
        BookingConfirmation confirmation = new BookingConfirmation(userId, accommodationId, checkInDate, checkOutDate);
        
        // Step 4: Send notification
        notificationService.sendBookingConfirmation(confirmation);

        // Step 5: Update loyalty points
        loyaltyService.updateLoyaltyPoints(userId, paymentService.calculatePaymentAmount(accommodationId));
        
        // Step 6: Update accommodation details
        accommodationDetailsService.updateAccommodationDetails(accommodationId, checkInDate, checkOutDate);

        return BookingResult.success(confirmation);
    }
}