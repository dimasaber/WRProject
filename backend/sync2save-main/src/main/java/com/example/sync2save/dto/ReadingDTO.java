package com.example.sync2save.dto;
import com.example.sync2save.model.Reading;

import java.time.LocalDateTime;

public class ReadingDTO {
    private Long id;
    private String type;
    private String description;
    private LocalDateTime timestamp;
    private Double value;
    private Long policyId;

    public ReadingDTO() {

    }

    public ReadingDTO(Reading reading) {
        this.id = reading.getId();
        this.type = reading.getType();
        this.timestamp = reading.getTimestamp();
        this.value = reading.getValue();
        this.description = reading.getDescription();
        if(reading.getPolicy() != null && reading.getPolicy().getPolicyId() >0) {
            this.policyId = reading.getPolicy().getPolicyId();
        }
    }

    public ReadingDTO(long id, String type, LocalDateTime timestamp, double value, String description, long policyId) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.value = value;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }
}
