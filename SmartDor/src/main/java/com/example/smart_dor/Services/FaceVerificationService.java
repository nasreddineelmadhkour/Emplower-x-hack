package com.example.smart_dor.Services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaceVerificationService {

    private String subscriptionKey="hIMYbFp9qWlRoePnO8-yqrlhtme2B2673";

    private static final String FACE_VERIFY_URL = "https://faceapi.mxface.ai/api/v3/face/verify";

    public String verifyFaces(String encodedImage1, String encodedImage2) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Subscriptionkey", subscriptionKey);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("encoded_image1", encodedImage1);
        requestBody.put("encoded_image2", encodedImage2);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(FACE_VERIFY_URL).toUriString(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to verify faces: " + response.getStatusCode());
        }
    }
}
