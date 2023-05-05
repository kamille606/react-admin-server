package com.react.admin.server.controller;

import com.react.admin.server.domain.ResponseBase;
import com.react.admin.server.domain.ResponsePage;
import com.react.admin.server.domain.entity.Category;
import com.react.admin.server.domain.model.CategoryAddRequest;
import com.react.admin.server.domain.model.CategoryListRequest;
import com.react.admin.server.domain.model.CategoryPageRequest;
import com.react.admin.server.domain.model.CategoryUpdateRequest;
import com.react.admin.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @RequestMapping(value = "/category/list", method = RequestMethod.POST)
    public ResponsePage<Category> queryCategoryList(@RequestBody CategoryListRequest request) {
        return ResponsePage.success(productService.queryCategoryList(request));
    }

    @RequestMapping(value = "/category/page", method = RequestMethod.POST)
    public ResponsePage<Category> queryCategoryPage(@RequestBody CategoryPageRequest request) {
        return null;
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public ResponseBase<Boolean> addCategory(@RequestBody CategoryAddRequest request) {
        return ResponseBase.success(productService.addCategory(request));
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    public ResponseBase<Boolean> updateCategory(@RequestBody CategoryUpdateRequest request) {
        return ResponseBase.success(productService.updateCategory(request));
    }

}
