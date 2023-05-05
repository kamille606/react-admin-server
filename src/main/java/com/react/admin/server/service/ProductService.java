package com.react.admin.server.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.react.admin.server.domain.entity.Category;
import com.react.admin.server.domain.model.CategoryAddRequest;
import com.react.admin.server.domain.model.CategoryListRequest;
import com.react.admin.server.domain.model.CategoryUpdateRequest;
import com.react.admin.server.exception.BizException;
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

    public boolean addCategory(CategoryAddRequest request) {
        final String categoryName = request.getCategoryName();
        if (StringUtils.isBlank(categoryName)) {
            throw new BizException("新增失败,分类名称为空");
        }
        Category category = categoryMapper.selectByName(categoryName);
        if (category != null) {
            throw new BizException("新增失败,存在相同分类名称");
        }
        category = new Category();
        category.setCategoryName(categoryName);
        category.setParentId(request.getParentId());
        if (categoryMapper.insert(category) == 0) {
            throw new BizException("新增失败");
        }
        return true;
    }

    public boolean updateCategory(CategoryUpdateRequest request) {
        final String categoryName = request.getCategoryName();
        if (StringUtils.isBlank(categoryName)) {
            throw new BizException("修改失败,分类名称为空");
        }
        Category category = categoryMapper.selectByName(categoryName);
        if (category != null) {
            throw new BizException("修改失败,存在相同分类名称");
        }
        final Integer categoryId = request.getCategoryId();
        category = categoryMapper.selectById(categoryId);
        if (category == null) {
            throw new BizException("查询错误");
        }
        category.setCategoryName(categoryName);
        if (categoryMapper.updateById(category) == 0) {
            throw new BizException("修改失败");
        }
        return true;
    }

}
