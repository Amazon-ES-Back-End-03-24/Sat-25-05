package org.ironhack.sat_2505.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.ironhack.sat_2505.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservations")
// DynamicUpdate updates only selected attributes
// Hibernate: update reservations set customer_name=?,reservation_time=?,status=? where id=?
// Hibernate: update reservations set status=? where id=?
@DynamicUpdate
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is mandatory")
    @Size(max = 100, message = "Customer name must not exceed 100 characters")
    // @Column(name = "name")
    // @Column(name = "customer_name")
    private String customerName;

    @NotNull(message = "Reservation time is mandatory")
    @Future(message = "Reservation time must be in the future")
    private LocalDateTime reservationTime;

    @NotNull(message = "Reservation status is mandatory")
    // default @Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation() {
    }

    public Reservation(String customerName, LocalDateTime reservationTime, ReservationStatus status) {
        this.customerName = customerName;
        this.reservationTime = reservationTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Customer name is mandatory") @Size(max = 100, message = "Customer name must not exceed 100 characters") String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NotBlank(message = "Customer name is mandatory") @Size(max = 100, message = "Customer name must not exceed 100 characters") String customerName) {
        this.customerName = customerName;
    }

    public @NotNull(message = "Reservation time is mandatory") @Future(message = "Reservation time must be in the future") LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(@NotNull(message = "Reservation time is mandatory") @Future(message = "Reservation time must be in the future") LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public @NotNull(message = "Reservation status is mandatory") ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Reservation status is mandatory") ReservationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(customerName, that.customerName) && Objects.equals(reservationTime, that.reservationTime) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, reservationTime, status);
    }
}
