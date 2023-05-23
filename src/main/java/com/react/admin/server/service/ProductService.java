package com.react.admin.server.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.react.admin.server.domain.entity.Category;
import com.react.admin.server.domain.entity.Product;
import com.react.admin.server.domain.model.product.CategoryAddRequest;
import com.react.admin.server.domain.model.product.CategoryInfoRequest;
import com.react.admin.server.domain.model.product.CategoryListRequest;
import com.react.admin.server.domain.model.product.CategoryUpdateRequest;
import com.react.admin.server.domain.model.product.ProductAddRequest;
import com.react.admin.server.domain.model.product.ProductOperateRequest;
import com.react.admin.server.domain.model.product.ProductPageRequest;
import com.react.admin.server.domain.model.product.ProductUpdateRequest;
import com.react.admin.server.exception.BizException;
import com.react.admin.server.mapper.CategoryMapper;
import com.react.admin.server.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.react.admin.server.constant.BaseConst.NORMAL;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public Page<Product> queryProductPage(ProductPageRequest request) {
        final String productName = request.getProductName();
        final String productDesc = request.getProductDesc();
        return productMapper.selectPage(
                new Page<>(request.getCurrent(), request.getPageSize()),
                Wrappers.lambdaQuery(Product.class)
                        .like(StringUtils.isNotBlank(productName), Product::getProductName, productName)
                        .like(StringUtils.isNotBlank(productDesc), Product::getProductDesc, productDesc)
                        .eq(Product::getDeleted, NORMAL));
    }

    public boolean addProduct(ProductAddRequest request) {
        if (request.getCategoryPid() == null) {
            throw new BizException("添加失败,请选择分类");
        }
        final Product product = BeanUtil.copyProperties(request, Product.class);
        if (productMapper.insert(product) == 0) {
            throw new BizException("添加失败");
        }
        return true;
    }

    public boolean updateProduct(ProductUpdateRequest request) {
        if (request.getCategoryPid() == null) {
            throw new BizException("更新失败,请选择分类");
        }
        final Product product = productMapper.selectById(request.getProductId());
        if (product == null) {
            throw new BizException("查询错误");
        }
        BeanUtil.copyProperties(request, product);
        if (productMapper.updateById(product) == 0) {
            throw new BizException("更新失败");
        }
        return true;
    }

    public boolean operateProductOnSell(ProductOperateRequest request) {
        if (productMapper.updateStatusById(1, request.getProductId()) == 0) {
            throw new BizException("上架失败");
        }
        return true;
    }

    public boolean operateProductOffShelf(ProductOperateRequest request) {
        if (productMapper.updateStatusById(2, request.getProductId()) == 0) {
            throw new BizException("下架失败");
        }
        return true;
    }

    public List<Category> queryCategoryList(CategoryListRequest request) {
        return categoryMapper.selectList(Wrappers.lambdaQuery(Category.class)
                .eq(Objects.nonNull(request.getCategoryPid()), Category::getCategoryPid, request.getCategoryPid())
                .eq(StringUtils.isNotBlank(request.getCategoryName()), Category::getCategoryName, request.getCategoryName())
                .eq(Category::getDeleted, NORMAL));
    }

    public Category queryCategoryInfo(CategoryInfoRequest request) {
        return categoryMapper.selectById(request.getCategoryId());
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
        category.setCategoryPid(request.getCategoryPid());
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
