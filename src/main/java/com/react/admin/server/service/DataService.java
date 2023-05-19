package com.react.admin.server.service;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.react.admin.server.domain.model.PictureUploadResponse;
import com.react.admin.server.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.react.admin.server.constant.BaseConst.BAIDU_AK;
import static com.react.admin.server.constant.BaseConst.PICTURE_PATH;
import static com.react.admin.server.constant.BaseConst.PICTURE_TYPE;
import static com.react.admin.server.constant.BaseConst.PICTURE_URL;
import static com.react.admin.server.constant.BaseConst.VIDEO_PATH;
import static com.react.admin.server.constant.BaseConst.VIDEO_TYPE;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {

    private static final String WEATHER_API = "https://api.map.baidu.com/weather/v1/?district_id=330110&data_type=all&ak=";
    private static final Map<String, String> CACHE_MAP = new ConcurrentHashMap<>();

    private final RestTemplate restTemplate;

    public PictureUploadResponse uploadFile(MultipartFile file) {
        final String originName = file.getOriginalFilename();
        final String fileType = FileTypeUtil.getType(originName);
        final String pathName;
        if (PICTURE_TYPE.contains(fileType)) {
            pathName = PICTURE_PATH;
        } else if (VIDEO_TYPE.contains(fileType)) {
            pathName = VIDEO_PATH;
        } else {
            throw new BizException("不支持的文件类型");
        }

        final String fileName = UUID.randomUUID() + "." + FileUtil.extName(originName);
        final String storageName = pathName + fileName;
        try {
            FileUtil.writeBytes(file.getBytes(), storageName);
        } catch (IOException e) {
            log.error("文件保存失败", e);
            throw new BizException("文件保存失败");
        }
        return PictureUploadResponse.apply(fileName, PICTURE_URL + fileName);
    }

    public String queryWeatherInfo() {
        if (CACHE_MAP.containsKey("weatherInfo")) {
            return CACHE_MAP.get("weatherInfo");
        }
        String responseString = restTemplate.getForObject(WEATHER_API + BAIDU_AK, String.class);
        JSONObject response = JSON.parseObject(responseString);
        assert response != null;
        if (response.getInteger("status") == 0) {
            String result = response.getString("result");
            CACHE_MAP.put("weatherInfo", result);
            return result;
        }
        return response.getString("message");
    }

}
