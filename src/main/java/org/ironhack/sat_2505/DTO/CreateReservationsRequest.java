package org.ironhack.sat_2505.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.ironhack.sat_2505.model.Reservation;

import java.util.List;

public class CreateReservationsRequest {

    // @Valid annotation doesn't work directly on a list of objects
    // To validate each item in a list, you need to use @Valid in combination with @RequestBody in Controller
    // and also apply the validation at the collection level -> in the DTO
    @Valid
    @NotEmpty(message = "Reservation list must not be empty")
    private List<Reservation> reservationList;

    public CreateReservationsRequest() {
    }

    public CreateReservationsRequest(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public @Valid @NotEmpty(message = "Reservation list must not be empty") List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(@Valid @NotEmpty(message = "Reservation list must not be empty") List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
