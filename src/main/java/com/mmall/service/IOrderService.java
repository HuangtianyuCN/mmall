package com.mmall.service;/*
@author 黄大宁Rhinos
@date 2019/3/7 - 13:54
**/

import com.mmall.common.ServerResponse;

import java.util.Map;

public interface IOrderService {
    public ServerResponse pay(Long orderNo, Integer userId, String path);
    public ServerResponse aliCallBack(Map<String,String> params);
    public ServerResponse<Boolean> queryOrderPayStatus(Integer userId,Long orderNo);
}
