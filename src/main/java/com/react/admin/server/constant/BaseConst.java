package com.react.admin.server.constant;

import java.util.Set;

public class BaseConst {
    public static final String SUCCESS_MSG = "请求成功";
    public static final String NOT_SUPPORT_MSG = "不支持的http请求类型";

    public static final String EMPTY = "";
    public static final int NORMAL = 0;
    public static final int DELETE = 1;

    public static final Set<String> PICTURE_TYPE = Set.of("jpg", "png", "jpeg");
    public static final Set<String> ZIP_TYPE = Set.of("zip", "rar", "7z");
    public static final Set<String> VIDEO_TYPE = Set.of("mp4", "avi", "rmvb", "mkv");

    public static final String BAIDU_AK = "CoxiN8MtZtMivWjwuhteQLUwmrPwNKiW";

    public static final String UPLOAD_PATH = "C:\\Users\\Lain\\Desktop\\upload\\";
    public static final String PICTURE_PATH = UPLOAD_PATH + "images\\";
    public static final String VIDEO_PATH = UPLOAD_PATH + "video\\";
    public static final String PICTURE_URL = "http://127.0.0.1:8080/images/";
}
