package com.react.admin.server.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.react.admin.server.domain.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.react.admin.server.constant.BaseConst.BAIDU_AK;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private static final String WEATHER_API = "https://api.map.baidu.com/weather/v1/?district_id=330110&data_type=all&ak=";
    private static final Map<String, String> CACHE_MAP = new ConcurrentHashMap<>();

    private final RestTemplate restTemplate;

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ResponseBase<String> weatherInfo() {
        if (CACHE_MAP.containsKey("weatherInfo")) {
            return ResponseBase.success(CACHE_MAP.get("weatherInfo"));
        }
        String responseString = restTemplate.getForObject(WEATHER_API + BAIDU_AK, String.class);
        JSONObject response = JSON.parseObject(responseString);
        assert response != null;
        if (response.getInteger("status") == 0) {
            String result = response.getString("result");
            CACHE_MAP.put("weatherInfo", result);
            return ResponseBase.success(result);
        }
        return ResponseBase.fail(response.getString("message"));
    }

}
