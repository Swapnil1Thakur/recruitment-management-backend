package com.recruitment.service;

import com.recruitment.model.Resume;
import com.recruitment.model.User;
import com.recruitment.repository.ResumeRepository;
import com.recruitment.util.ResumeParserClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeParserClient resumeParserClient;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public Resume uploadResume(MultipartFile file, User user) throws IOException {

        String apiResponse = resumeParserClient.parseResume(file.getBytes());
        JsonNode jsonNode = objectMapper.readTree(apiResponse);


        String skills = jsonNode.path("skills").toString();
        String education = jsonNode.path("education").toString();
        String experience = jsonNode.path("experience").toString();
        String phone = jsonNode.path("phone").asText("");
        String name = jsonNode.path("name").asText("");
        String email = jsonNode.path("email").asText("");


        Resume resume = new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setFileType(file.getContentType());
        resume.setData(file.getBytes());
        resume.setUser(user);
        resume.setSkills(skills);
        resume.setEducation(education);
        resume.setExperience(experience);
        resume.setPhone(phone);


        return resumeRepository.save(resume);
    }

    public Optional<Resume> getResumeByUser(User user) {
        return resumeRepository.findByUser(user);
    }
}
