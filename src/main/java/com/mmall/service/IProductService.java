package com.mmall.service;/*
@author 黄大宁Rhinos
@date 2019/3/4 - 13:38
**/

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

public interface IProductService {
    public ServerResponse saveOrUpdatePrduct(Product product);
    public ServerResponse<String> setSaleStatus(Integer productId,Integer status);
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);
    public ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);
}
