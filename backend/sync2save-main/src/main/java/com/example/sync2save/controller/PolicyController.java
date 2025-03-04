package com.example.sync2save.controller;

import com.example.sync2save.dto.PolicyDTO;
import com.example.sync2save.dto.ReadingDTO;
import com.example.sync2save.model.Policy;
import com.example.sync2save.service.PolicyService;
import com.example.sync2save.service.ReadingService;
import com.example.sync2save.repository.PolicyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin("*")
public class PolicyController {

    private final PolicyService policyService;
    private final ReadingService readingService;
    private final PolicyRepository policyRepository;

    public PolicyController(PolicyService policyService, ReadingService readingService, PolicyRepository policyRepository) {
        this.policyService = policyService;
        this.readingService = readingService;
        this.policyRepository = policyRepository;
    }

    @GetMapping
    public ResponseEntity<List<PolicyDTO>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @GetMapping("{policyId}")
    public ResponseEntity<PolicyDTO> getPolicyByID(@PathVariable long policyId) {
        return ResponseEntity.ok(this.policyService.getPolicyById(policyId));
    }


    @GetMapping("/{policyId}/readings")
    public ResponseEntity<?> getPolicyWithReadings(@PathVariable Long policyId, @RequestParam int month) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        if (policyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PolicyDTO policyDTO = new PolicyDTO(policyOptional.get());
        List<ReadingDTO> readings = readingService.getReadingByPolicyIdAndMonth(policyId, month);

        return ResponseEntity.ok(new PolicyWithReadingsResponse(policyDTO, readings));
    }

    public static class PolicyWithReadingsResponse {
        private final PolicyDTO policy;
        private final List<ReadingDTO> readings;

        public PolicyWithReadingsResponse(PolicyDTO policy, List<ReadingDTO> readings) {
            this.policy = policy;
            this.readings = readings;
        }

        public PolicyDTO getPolicy() {
            return policy;
        }

        public List<ReadingDTO> getReadings() {
            return readings;
        }
    }

    @PostMapping
    public ResponseEntity<PolicyDTO> createPolicy(@RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }


   /*
    @PutMapping("/{policyId}/discount")

    public ResponseEntity<?> updateEligibleForDiscount(@PathVariable long policyId, @RequestBody Boolean eligibleForDiscount) {
        Optional<PolicyDTO> optionalPolicy = Optional.ofNullable(this.policyService.getPolicyById(policyId));

        if(optionalPolicy.isPresent()) {
            optionalPolicy.setEligibleForDiscount(eligibleForDiscount);
            return ResponseEntity.ok(optionalPolicy.get());
        }
        return ResponseEntity.notFound().build();
    }
*/





    @DeleteMapping("/{policyId}")
    public ResponseEntity<?> deletePolicyById(@PathVariable long policyId) {
        Optional<PolicyDTO> optionalPolicy = Optional.ofNullable(this.policyService.getPolicyById((policyId)));
        if (optionalPolicy.isPresent()) {
            this.policyService.deletePolicy(policyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<?> updatePolicy(@PathVariable long policyId, @RequestBody Policy policy) {
        Optional<PolicyDTO> optionalPolicy = this.policyService.updatePolicy(policyId, policy);
        if(optionalPolicy.isPresent()) {
            return ResponseEntity.ok(optionalPolicy.get());
        }
        return ResponseEntity.notFound().build();
    }
}
