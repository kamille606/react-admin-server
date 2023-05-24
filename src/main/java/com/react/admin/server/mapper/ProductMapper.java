package com.react.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.react.admin.server.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Update("update product_info set product_status = #{status} where product_id = #{productId}")
    int updateStatusById(@Param("status") Integer status, @Param("productId") Integer productId);

}
