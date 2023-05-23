package com.react.admin.server.controller;

import com.react.admin.server.domain.ResponseBase;
import com.react.admin.server.domain.model.PictureUploadResponse;
import com.react.admin.server.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ResponseBase<String> weatherInfo() {
        return ResponseBase.success(manageService.queryWeatherInfo());
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseBase<PictureUploadResponse> uploadFile(@RequestPart("file") MultipartFile file) {
        return ResponseBase.success(manageService.uploadFile(file));
    }

}
