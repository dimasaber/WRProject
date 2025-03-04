package com.example.sync2save.controller;

import com.example.sync2save.dto.ReadingDTO;
import com.example.sync2save.model.Reading;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import com.example.sync2save.service.ReadingService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
@CrossOrigin("*")
public class ReadingController {

    private final ReadingService readingService;

    public ReadingController(ReadingService readingService) {

        this.readingService = readingService;
    }

    @GetMapping
    public ResponseEntity<List<ReadingDTO>> getAllReadings() {
        return ResponseEntity.ok(readingService.getAllReadings());
    }

    @GetMapping("/{readingId}")
    public ResponseEntity<?> getReadingById(@PathVariable("readingId") long readingId) {
        return ResponseEntity.ok(readingService.getReadingById(readingId));
    }

    @GetMapping("/{policyId}/month/{month}")
    public ResponseEntity<List<ReadingDTO>> getReadingsByPolicyAndMonth(
            @PathVariable Long policyId,
            @PathVariable int month) {
        return ResponseEntity.ok(readingService.getReadingByPolicyIdAndMonth(policyId, month));
    }



    @PostMapping("/{policyId}")
    public ResponseEntity<?> createReading(@PathVariable long policyId, @RequestBody Reading reading) {
        Optional<ReadingDTO> createdReading = readingService.createReading(policyId, reading);
        if(createdReading.isPresent()) {
            return ResponseEntity.ok(createdReading.get());
        } else {
            return ResponseEntity.badRequest().body("Could not create reading");
        }
    }

    @PutMapping("/{policyId}/{readingId}")
    public ResponseEntity<?> updateReading(@PathVariable Long policyId,
                                           @PathVariable Long readingId,
                                           @RequestBody Reading reading) {
        Optional<ReadingDTO> optionalIssueDTO = this.readingService.updateReading(policyId, readingId, reading);
        if(optionalIssueDTO.isPresent()) {
            return ResponseEntity.ok(optionalIssueDTO.get());
        } else {
            return ResponseEntity.badRequest().body("Reading not found");
        }
    }

    @DeleteMapping("/{readingId}")
    public ResponseEntity<Void> deleteReading(@PathVariable Long readingId) {
        boolean deleted = readingService.deleteReading(readingId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
