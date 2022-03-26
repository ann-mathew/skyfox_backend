package com.booking.slots.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.sql.Time;

@ApiModel("Show Response")
public class SlotResponse {

    @JsonProperty
    private Time startTime;

    @JsonProperty
    private Time endTime;

    public SlotResponse(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}