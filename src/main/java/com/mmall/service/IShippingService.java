package com.mmall.service;/*
@author 黄大宁Rhinos
@date 2019/3/6 - 15:28
**/

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;


public interface IShippingService {
    public ServerResponse add(Integer userId, Shipping shipping);
    public ServerResponse del(Integer userId, Integer shippingId);
    public ServerResponse update(Integer userId, Shipping shipping);
    public ServerResponse select(Integer userId, Integer shippingId);
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
    }
