package com.recruitment.controller;

import com.recruitment.model.Resume;
import com.recruitment.model.User;
import com.recruitment.service.ResumeService;
import com.recruitment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {
        try {
            User user = userService.getUserByEmail(email);
            Resume savedResume = resumeService.uploadResume(file, user);
            return ResponseEntity.ok("Resume uploaded successfully: " + savedResume.getFileName());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload resume");
        }
    }

    @GetMapping("/download/{email}")
    public ResponseEntity<?> downloadResume(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return resumeService.getResumeByUser(user)
                .map(resume -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(resume.getFileType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getFileName() + "\"")
                        .body(resume.getData()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume not found".getBytes()));
    }
}