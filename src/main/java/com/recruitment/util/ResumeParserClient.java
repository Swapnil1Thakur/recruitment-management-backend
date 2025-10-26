package com.recruitment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class ResumeParserClient {

    @Value("${resume.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.apilayer.com/resume_parser/upload";

    public String parseResume(byte[] fileBytes) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("apikey", apiKey);

        HttpEntity<byte[]> request = new HttpEntity<>(fileBytes, headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
