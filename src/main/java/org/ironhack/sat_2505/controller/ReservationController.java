package org.ironhack.sat_2505.controller;

import jakarta.validation.Valid;
import org.ironhack.sat_2505.DTO.CreateReservationsRequest;
import org.ironhack.sat_2505.DTO.UpdateReservationRequest;
import org.ironhack.sat_2505.enums.ReservationStatus;
import org.ironhack.sat_2505.model.Reservation;
import org.ironhack.sat_2505.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // localhost:8080/reservations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    // localhost:8080/reservations/1
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservationById(@PathVariable(name = "id") Long reservationId){
        return reservationService.getReservationById(reservationId);
    }

    // localhost:8080/reservations/status
    // localhost:8080/reservations/status?status=value
    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationsByStatus(@RequestParam(name = "status", required = false) ReservationStatus reservationStatus){
        return reservationService.getReservationByStatus(reservationStatus);
    }

    // localhost:8080/reservations
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation (@Valid @RequestBody Reservation reservationRequest){
        return reservationService.createReservation(reservationRequest);
    }

    // localhost:8080/reservations/list
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Reservation> createReservations (@Valid @RequestBody CreateReservationsRequest createReservationsRequest){
        return reservationService.createReservations(createReservationsRequest.getReservationList());
    }

    // to update the whole entity, or various attributes
    // localhost:8080/reservations/1
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable(name = "id") Long reservationId, @Valid @RequestBody UpdateReservationRequest reservationRequest){
        reservationService.updateReservation(reservationId,reservationRequest);
    }

    // localhost:8080/reservations/1/status
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservationStatus (@PathVariable(name = "id") Long reservationId, @RequestParam ReservationStatus status){
        reservationService.updateReservationStatus(reservationId,status);
    }

    // localhost:8080/reservations/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable(name = "id") Long reservationId){
        reservationService.deleteReservation(reservationId);
    }
}
