package com.mmall.service;/*
@author 黄大宁Rhinos
@date 2019/3/4 - 19:53
**/

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String upload(MultipartFile file, String path);
}
