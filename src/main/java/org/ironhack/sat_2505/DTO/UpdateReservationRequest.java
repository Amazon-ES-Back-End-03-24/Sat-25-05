package org.ironhack.sat_2505.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import org.ironhack.sat_2505.enums.ReservationStatus;

import java.time.LocalDateTime;

public class UpdateReservationRequest {

    @Size(max = 100, message = "Customer name must not exceed 100 characters")
    private String customerName;

    @Future(message = "Reservation time must be in the future")
    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public UpdateReservationRequest() {
    }

    public UpdateReservationRequest(String customerName, LocalDateTime reservationTime, ReservationStatus status) {
        this.customerName = customerName;
        this.reservationTime = reservationTime;
        this.status = status;
    }

    public @Size(max = 100, message = "Customer name must not exceed 100 characters") String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@Size(max = 100, message = "Customer name must not exceed 100 characters") String customerName) {
        this.customerName = customerName;
    }

    public @Future(message = "Reservation time must be in the future") LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(@Future(message = "Reservation time must be in the future") LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
