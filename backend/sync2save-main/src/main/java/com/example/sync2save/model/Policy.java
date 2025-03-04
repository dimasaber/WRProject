package com.example.sync2save.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "policy")

public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;
    private String type;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long customerId;
    private String customerName;
    private Boolean eligibleForDiscount;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reading> readings = new ArrayList<>();

    public Policy() {
    }

    public Policy(Long policyId, String type, String description, LocalDate startDate, LocalDate endDate, Long customerId, String customerName, Boolean eligibleForDiscount, List<Reading> readings) {
        this.policyId = policyId;
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.eligibleForDiscount = eligibleForDiscount;
        this.readings = readings;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getEligibleForDiscount() {
        return eligibleForDiscount;
    }

    public void setEligibleForDiscount(Boolean eligibleForDiscount) {
        this.eligibleForDiscount = eligibleForDiscount;
    }

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }
}
