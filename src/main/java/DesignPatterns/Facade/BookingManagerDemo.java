package DesignPatterns.Facade;

import DesignPatterns.Facade.models.*;
import DesignPatterns.Facade.services.*;

import java.time.LocalDate;

/**
 * Demo class to demonstrate the proper Facade pattern implementation.
 * Shows how BookingManager is simplified and delegates complex logic to BookingFacade.
 */
public class BookingManagerDemo {

    public static void main(String[] args) {
        System.out.println("=== Facade Pattern Demo: BookingManager + BookingFacade ===\n");

        // Initialize services
        AvailabilityService availabilityService = new AvailabilityService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();
        LoyaltyService loyaltyService = new LoyaltyService();
        AccommodationDetailsService accommodationDetailsService = new AccommodationDetailsService();

        // Create the simplified BookingManager (which uses BookingFacade internally)
        BookingManager bookingManager = new BookingManager(
            availabilityService,
            paymentService,
            notificationService,
            loyaltyService,
            accommodationDetailsService
        );

        // Demo: Complete booking process through simplified BookingManager
        System.out.println("Processing booking through simplified BookingManager...");
        String userId = "user-456";
        String accommodationId = "hotel-123";
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(3);
        
        BookingResult result = bookingManager.bookAccommodation(userId, accommodationId, checkIn, checkOut);
        
        System.out.println("Booking Status: " + result.getStatus());
        if (result.getStatus() == BookingStatus.SUCCESS) {
            BookingConfirmation confirmation = result.getConfirmation();
            System.out.println("✅ Booking successful!");
            System.out.println("User ID: " + confirmation.getUserId());
            System.out.println("Accommodation ID: " + confirmation.getAccommodationId());
            System.out.println("Check-in: " + confirmation.getCheckInDate());
            System.out.println("Check-out: " + confirmation.getCheckOutDate());
        } else {
            System.out.println("❌ Booking failed: " + result.getErrorMessage());
        }

        System.out.println("\n=== Facade Pattern Implementation ===");
        System.out.println("📋 BookingManager (Simplified):");
        System.out.println("   - Simple interface with minimal logic");
        System.out.println("   - Delegates complex operations to BookingFacade");
        System.out.println("   - Easy to understand and maintain");
        
        System.out.println("\n🏗️ BookingFacade (Complex Logic):");
        System.out.println("   - Encapsulates all complex service interactions");
        System.out.println("   - Handles availability checking, payment, notifications");
        System.out.println("   - Manages loyalty points and accommodation updates");
        System.out.println("   - Provides unified interface for complex operations");
        
        System.out.println("\n✅ Benefits:");
        System.out.println("   - Separation of concerns: Simple vs Complex logic");
        System.out.println("   - BookingManager stays clean and focused");
        System.out.println("   - Complex logic is encapsulated in BookingFacade");
        System.out.println("   - Easy to test and maintain both components");
    }
}