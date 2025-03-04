package com.example.sync2save.dto;

import com.example.sync2save.model.Policy;

import java.time.LocalDate;


public class PolicyDTO {
    private Long policyId;
    private String type;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long customerId;
    private String customerName;
    private Boolean eligibleForDiscount;

    public PolicyDTO() {
    }

    public PolicyDTO(Policy policy) {
        this.policyId = policy.getPolicyId();
        this.type = policy.getType();
        this.description = policy.getDescription();
        this.startDate = policy.getStartDate();
        this.endDate = policy.getEndDate();
        this.customerId = policy.getCustomerId();
        this.customerName = policy.getCustomerName();
        this.eligibleForDiscount = policy.getEligibleForDiscount();

    }

    public PolicyDTO(Long policyId, String type, String description, LocalDate startDate, LocalDate endDate, Long customerId, String customerName, Boolean eligibleForDiscount) {
        this.policyId = policyId;
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.eligibleForDiscount = eligibleForDiscount;
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
}
