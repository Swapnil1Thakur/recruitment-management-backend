package com.recruitment.controller;

import com.recruitment.model.Job;
import com.recruitment.model.Resume;
import com.recruitment.model.User;
import com.recruitment.service.JobService;
import com.recruitment.service.ResumeService;
import com.recruitment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final UserService userService;
    private final ResumeService resumeService;

    // to create a job
    @PostMapping("/admin/job")
    public ResponseEntity<?> createJob(@RequestBody Job job, @RequestParam String adminEmail) {
        User admin = userService.getUserByEmail(adminEmail);
        if (!admin.getUserType().equals("ADMIN"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin can create jobs");

        Job savedJob = jobService.createJob(job, admin);
        return ResponseEntity.ok(savedJob);
    }

    // admin -> to get a job by id
    @GetMapping("/admin/job/{id}")
    public ResponseEntity<?> getJob(@PathVariable Long id, @RequestParam String adminEmail) {
        User admin = userService.getUserByEmail(adminEmail);
        if (!admin.getUserType().equals("ADMIN"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin can access");

        return jobService.getJobById(id)
                .map(job -> ResponseEntity.ok(job))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // admin-> to view all applicants
    @GetMapping("/admin/applicants")
    public ResponseEntity<?> getAllApplicants(@RequestParam String adminEmail) {
        User admin = userService.getUserByEmail(adminEmail);
        if (!admin.getUserType().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin can access");
        }

        List<User> applicants = userService.getAllUsers()
                .stream()
                .filter(u -> u.getUserType().equals("APPLICANT"))
                .toList();

        return ResponseEntity.ok(applicants);
    }

    // admin -> to view individual applicant resume
    @GetMapping("/admin/applicant/{id}")
    public ResponseEntity<?> getApplicantDetails(@PathVariable Long id, @RequestParam String adminEmail) {
        User admin = userService.getUserByEmail(adminEmail);
        if (!admin.getUserType().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin can access");
        }

        User applicant = userService.getUserById(id);
        if (applicant == null || !applicant.getUserType().equals("APPLICANT")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Applicant not found");
        }

        Optional<Resume> resumeOpt = resumeService.getResumeByUser(applicant);
        if (resumeOpt.isPresent()) {
            return ResponseEntity.ok(resumeOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // applicant -> to view all jobs
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // applying to a job
    @GetMapping("/jobs/apply")
    public ResponseEntity<?> applyJob(@RequestParam Long jobId, @RequestParam String applicantEmail) {
        User applicant = userService.getUserByEmail(applicantEmail);
        if (!applicant.getUserType().equals("APPLICANT"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only applicants can apply");

        Job job = jobService.getJobById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Job updatedJob = jobService.applyToJob(job, applicant);
        return ResponseEntity.ok("Applied successfully. Total Applications: " + updatedJob.getTotalApplications());
    }
}
