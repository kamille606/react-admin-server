package com.react.admin.server.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.react.admin.server.domain.entity.Category;
import com.react.admin.server.domain.model.CategoryListRequest;
import com.react.admin.server.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.react.admin.server.constant.BaseConst.NORMAL;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryMapper categoryMapper;

    public List<Category> queryCategoryList(CategoryListRequest request) {
        return categoryMapper.selectList(Wrappers.lambdaQuery(Category.class)
                .eq(Category::getParentId, request.getParentId())
                .eq(StringUtils.isNotBlank(request.getCategoryName()), Category::getCategoryName, request.getCategoryName())
                .eq(Category::getDeleted, NORMAL));
    }



}
