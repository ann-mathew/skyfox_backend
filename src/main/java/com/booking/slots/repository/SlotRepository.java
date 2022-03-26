package com.booking.slots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<SlotResponse, Long> {
    List<SlotResponse> findAll();
}
