package com.react.admin.server.controller;

import com.react.admin.server.domain.ResponseBase;
import com.react.admin.server.domain.model.PictureUploadResponse;
import com.react.admin.server.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ResponseBase<String> weatherInfo() {
        return ResponseBase.success(dataService.queryWeatherInfo());
    }

    @RequestMapping(value = "/upload/picture", method = RequestMethod.POST)
    public ResponseBase<PictureUploadResponse> uploadPictureFile(@RequestPart("file") MultipartFile file) {
        return ResponseBase.success(dataService.uploadPictureFile(file));
    }

}
