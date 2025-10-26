package com.recruitment.service;

import com.recruitment.model.Job;
import com.recruitment.model.User;
import com.recruitment.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Job createJob(Job job, User admin) {
        job.setPostedBy(admin);
        return jobRepository.save(job);
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job applyToJob(Job job, User applicant) {
        job.getApplicants().add(applicant);
        job.setTotalApplications(job.getApplicants().size());
        return jobRepository.save(job);
    }
}
