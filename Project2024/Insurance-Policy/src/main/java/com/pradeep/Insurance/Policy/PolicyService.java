package com.pradeep.Insurance.Policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy policyDetails) {
        Policy policy = policyRepository.findById(id).orElseThrow();
        policy.setPolicyNumber(policyDetails.getPolicyNumber());
        policy.setPolicyHolderName(policyDetails.getPolicyHolderName());
        policy.setSumAssured(policyDetails.getSumAssured());
        return policyRepository.save(policy);
    }

    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }

    public double calculatePremium(Policy policy) {
        // Simple premium calculation based on sum assured
        return policy.getSumAssured() * 0.01; // 1% of sum assured as premium
    }
}
