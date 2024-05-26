package org.ironhack.sat_2505.service;

import io.micrometer.common.util.StringUtils;
import org.ironhack.sat_2505.DTO.UpdateReservationRequest;
import org.ironhack.sat_2505.enums.ReservationStatus;
import org.ironhack.sat_2505.model.Reservation;
import org.ironhack.sat_2505.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long reservationId) {
        // Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        return reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    }

    public List<Reservation> getReservationByStatus(ReservationStatus status) {
        if (status != null) {
            return reservationRepository.findByStatus(status);
        } else {
            return reservationRepository.findAll();
        }
    }

    public Reservation createReservation(Reservation reservationRequest) {
        Reservation receivedReservation = new Reservation(reservationRequest.getCustomerName(),
                reservationRequest.getReservationTime(), reservationRequest.getStatus());

        return reservationRepository.save(receivedReservation);
    }

    public List<Reservation> createReservations(List<Reservation> reservations) {

        return reservationRepository.saveAll(reservations);
    }

    public void updateReservation(Long reservationId, UpdateReservationRequest reservationRequest) {
        Reservation foundReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));


        // to cover for the case "customerName": "  " (if it's empty, also null) we use a library method from StringUtils -> isNotBlank
        // we cannot use !reservationRequest.getCustomerName().isEmpty() because if customerName is null,
        // calling isEmpty() on it would throw a NullPointerException.
        if (StringUtils.isNotBlank(reservationRequest.getCustomerName())) {
            foundReservation.setCustomerName(reservationRequest.getCustomerName());
        }

        if (reservationRequest.getReservationTime() != null) {
            foundReservation.setReservationTime(reservationRequest.getReservationTime());
        }

        if (reservationRequest.getStatus() != null) {
            foundReservation.setStatus(reservationRequest.getStatus());
        }

        reservationRepository.save(foundReservation);
    }

    public void updateReservationStatus(Long reservationId, ReservationStatus status) {

        Reservation foundReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        foundReservation.setStatus(status);
        reservationRepository.save(foundReservation);
    }

    public void deleteReservation(Long reservationId) {
        // Reservation foundReservation = reservationRepository.findById(reservationId)
        //       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        // we would delete and return foundReservation

        if (!reservationRepository.existsById(reservationId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation Not found");
        }

        reservationRepository.deleteById(reservationId);
    }
}
