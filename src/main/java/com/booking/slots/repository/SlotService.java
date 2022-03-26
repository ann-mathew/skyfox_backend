package com.booking.slots.repository;

import com.booking.movieGateway.exceptions.FormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SlotService {
    private final SlotRepository slotRepository;

    @Autowired
    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<SlotResponse> getAllSlots() throws IOException, FormatException {
        return slotRepository.findAll();
    }
}
