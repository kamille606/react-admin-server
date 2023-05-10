package com.react.admin.server.controller;

import com.react.admin.server.domain.ResponseBase;
import com.react.admin.server.domain.ResponsePage;
import com.react.admin.server.domain.entity.Category;
import com.react.admin.server.domain.entity.Product;
import com.react.admin.server.domain.model.product.CategoryAddRequest;
import com.react.admin.server.domain.model.product.CategoryInfoRequest;
import com.react.admin.server.domain.model.product.CategoryListRequest;
import com.react.admin.server.domain.model.product.CategoryPageRequest;
import com.react.admin.server.domain.model.product.CategoryUpdateRequest;
import com.react.admin.server.domain.model.product.ProductOperateRequest;
import com.react.admin.server.domain.model.product.ProductPageRequest;
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

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponsePage<Product> queryProductPage(@RequestBody ProductPageRequest request) {
        return ResponsePage.success(productService.queryProductPage(request));
    }

    @RequestMapping(value = "/on-sell", method = RequestMethod.POST)
    public ResponseBase<Boolean> operateProductOnSell(@RequestBody ProductOperateRequest request) {
        return ResponseBase.success(productService.operateProductOnSell(request));
    }

    @RequestMapping(value = "/off-shelf", method = RequestMethod.POST)
    public ResponseBase<Boolean> operateProductOffShelf(@RequestBody ProductOperateRequest request) {
        return ResponseBase.success(productService.operateProductOffShelf(request));
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.POST)
    public ResponsePage<Category> queryCategoryList(@RequestBody CategoryListRequest request) {
        return ResponsePage.success(productService.queryCategoryList(request));
    }

    @RequestMapping(value = "/category/page", method = RequestMethod.POST)
    public ResponsePage<Category> queryCategoryPage(@RequestBody CategoryPageRequest request) {
        return null;
    }

    @RequestMapping(value = "/category/info", method = RequestMethod.POST)
    public ResponseBase<Category> queryCategoryInfo(@RequestBody CategoryInfoRequest request) {
        return ResponseBase.success(productService.queryCategoryInfo(request));
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
