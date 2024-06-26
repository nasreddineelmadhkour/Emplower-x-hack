package com.example.smart_dor.Controllers;

import com.example.smart_dor.Config.SSLUtils;
import com.example.smart_dor.Services.FaceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/face-verification")
public class FaceVerificationController {

    private final FaceVerificationService faceVerificationService;

    @Autowired
    public FaceVerificationController(FaceVerificationService faceVerificationService) {
        this.faceVerificationService = faceVerificationService;
    }

    @PostMapping("/verify")
    public String verifyFaces(@RequestBody Map<String, String> requestBody) {
        String encodedImage1 = requestBody.get("encoded_image1");
        String encodedImage2 = requestBody.get("encoded_image2");
        SSLUtils.disableSSLVerification();
        return faceVerificationService.verifyFaces(encodedImage1, encodedImage2);
    }
}
