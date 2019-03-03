package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geely
 */

public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
