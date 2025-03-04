package com.example.sync2save.service;

import com.example.sync2save.dto.PolicyDTO;
import com.example.sync2save.dto.ReadingDTO;
import com.example.sync2save.model.Policy;
import com.example.sync2save.repository.PolicyRepository;
import com.example.sync2save.repository.ReadingRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final ReadingRepository readingRepository;

    public PolicyService(PolicyRepository policyRepository, ReadingRepository readingRepository) {

        this.policyRepository = policyRepository;
        this.readingRepository = readingRepository;
    }

    public PolicyDTO createPolicy(Policy policy) {

        return new PolicyDTO(policyRepository.save(policy));
    }

    public List<PolicyDTO> getAllPolicies() {
        return policyRepository.findAll()
                .stream()
                .map(PolicyDTO::new)
                .collect(Collectors.toList());
    }

    public PolicyDTO getPolicyById(long policyId) {
        Optional<Policy> optionalPolicy = this.policyRepository.findById(policyId);
        if(optionalPolicy.isPresent()) {
            return new PolicyDTO(optionalPolicy.get());
        } else {
            return null;
        }
    }

    public Optional<PolicyDTO> getPolicyWithReadingsPerMonth(Long policyId, int month) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        if (policyOptional.isPresent()) {
            Policy policy = policyOptional.get();
            List<ReadingDTO> readings;
            readings = readingRepository.findAll()
                    .stream()
                    .filter(r -> r.getPolicy().getPolicyId().equals(policyId))
                    .filter(r -> r.getTimestamp().getMonthValue() == month)
                    .map(ReadingDTO::new)
                    .collect(Collectors.toList());

            PolicyDTO policyDTO = new PolicyDTO(policy);
            return Optional.of(policyDTO);
        }
        return Optional.empty();
    }

    public Optional<PolicyDTO> updatePolicy(long policyId, Policy policy) {
        Optional<Policy> existingPolicy = this.policyRepository.findById(policyId);
        if(existingPolicy.isPresent()){
            Policy updatedPolicy = existingPolicy.get();
            updatedPolicy.setType(policy.getType());
            updatedPolicy.setDescription(policy.getDescription());
            updatedPolicy.setCustomerId(policy.getCustomerId());
            updatedPolicy.setEligibleForDiscount(policy.getEligibleForDiscount());
            Policy savedPolicy = policyRepository.save(updatedPolicy);
            return Optional.of(new PolicyDTO(this.policyRepository.save(savedPolicy)));
        } else {
            return Optional.empty();
        }
    }

    public void deletePolicy(long policyId) {
        this.policyRepository.deleteById(policyId);
    }

}
