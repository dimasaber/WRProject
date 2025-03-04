package com.example.sync2save.service;

import com.example.sync2save.dto.ReadingDTO;
import com.example.sync2save.model.Reading;
import com.example.sync2save.model.Policy;
import com.example.sync2save.repository.ReadingRepository;
import com.example.sync2save.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReadingService {

    private final ReadingRepository readingRepository;
    private final PolicyRepository policyRepository;

    public ReadingService(ReadingRepository readingRepository, PolicyRepository policyRepository) {
        this.readingRepository = readingRepository;
        this.policyRepository = policyRepository;
    }

    public List<ReadingDTO> getAllReadings() {
        return readingRepository.findAll()
                .stream()
                .map(ReadingDTO::new)
                .collect(Collectors.toList());
    }

    public ReadingDTO getReadingById(long id) {
        Optional<Reading> optional = this.readingRepository.findById(id);
        if(optional.isPresent()) {
            return new ReadingDTO(optional.get());
        } else {
            return null;
        }
    }

    public List<ReadingDTO> getReadingByPolicyIdAndMonth(Long policyId, int month) {
        List<Reading> readings = readingRepository.findAll(); // Fetch all readings

        List<ReadingDTO> filteredReadings = readings.stream()
                .filter(r -> r.getPolicy().getPolicyId().equals(policyId)) // Match policyId
                .filter(r -> r.getTimestamp().getMonthValue() == month) // Match month
                .map(ReadingDTO::new) // Convert to DTO
                .collect(Collectors.toList());

        return filteredReadings;
    }





    public Optional<ReadingDTO> updateReading(Long policyID, Long readingId, Reading updatedReading) {
        Optional<Reading> existingReading = this.readingRepository.findById(readingId);
        if(existingReading.isPresent()) {
            Reading reading = existingReading.get();

            //ensure reading belongs to the correct policy
            if (!reading.getPolicy().getPolicyId().equals(policyID)) {
                return Optional.empty(); // Policy ID mismatch
            }

            reading.setType(updatedReading.getType());
            reading.setTimestamp(updatedReading.getTimestamp());
            reading.setDescription(updatedReading.getDescription());

            return Optional.of(new ReadingDTO(readingRepository.save(reading)));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ReadingDTO> createReading(long policyId, Reading reading) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        if (policyOptional.isPresent()) {
            reading.setPolicy(policyOptional.get());
            return Optional.of(new ReadingDTO(readingRepository.save(reading)));
        }
        return Optional.empty();
    }

    public boolean deleteReading(Long readingId) {
        Optional<Reading> existingReading = readingRepository.findById(readingId);
        if (existingReading.isPresent()) {
            readingRepository.deleteById(readingId);
            return true;
        } else {
            return false;
        }
    }

}
