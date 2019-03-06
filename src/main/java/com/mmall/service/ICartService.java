package com.mmall.service;/*
@author 黄大宁Rhinos
@date 2019/3/5 - 21:32
**/

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

public interface ICartService {
    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    public ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);
    public ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);
    public ServerResponse<CartVo> listProduct(Integer userId);
    public ServerResponse<CartVo> selectOrUnSelect(Integer userId,Integer productId,Integer checked);
    public ServerResponse<Integer> getCartProductCount(Integer userId);
    }
