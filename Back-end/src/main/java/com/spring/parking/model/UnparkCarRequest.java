package com.spring.parking.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UnparkCarRequest {
    private LocalDateTime finishTime;

    public UnparkCarRequest() {
    }

    @JsonCreator
    public UnparkCarRequest(@JsonProperty("finishTime") LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }
}
