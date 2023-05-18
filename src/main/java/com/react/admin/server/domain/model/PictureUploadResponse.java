package com.react.admin.server.domain.model;

public record PictureUploadResponse(String name, String url) {

    public static PictureUploadResponse apply(String name, String url) {
        return new PictureUploadResponse(name, url);
    }

}
