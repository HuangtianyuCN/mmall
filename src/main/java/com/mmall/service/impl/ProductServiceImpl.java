package com.mmall.service.impl;/*
@author 黄大宁Rhinos
@date 2019/3/4 - 13:38
**/

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.controller.backend.ProductManageController;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.CSSPageRule;

import java.util.List;

@Service("iProductService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse saveOrUpdatePrduct(Product product){
        if(product!=null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length>0){
                    product.setMainImage(subImageArray[0]);
                }
                if(product.getId()!=null){
                    int rowCount = productMapper.updateByPrimaryKey(product);
                    if(rowCount>0){
                        return ServerResponse.createBySuccess("更新产品成功");
                    }
                    return ServerResponse.createByErrorMessage("更新产品失败");
                }
                else{
                    int rowCount = productMapper.insert(product);
                    if(rowCount>0){
                        return ServerResponse.createBySuccess("添加产品成功");
                    }
                    return ServerResponse.createByErrorMessage("添加产品失败");
                }
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品不正确");
    }


    public ServerResponse<String> setSaleStatus(Integer productId,Integer status){
        if(productId==null||status==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount>0){
            return ServerResponse.createBySuccess("修改产品状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品状态失败");
    }

    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId){
        if(productId==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product!=null){
            return ServerResponse.createByErrorMessage("商品不存在");
        }
//        vo对象（value object）
        //pojo->bo(business object)->vo(view object)，bo+vo整体是value object
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        //imageHost
        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymall.com/"));
        //parentCategoryId
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }
        //createTime
        productDetailVo.setCreateTime(DateTimeUtil.date2Str(product.getCreateTime()));
        //updateTime
        productDetailVo.setUpdateTime(DateTimeUtil.date2Str(product.getUpdateTime()));
        return productDetailVo;
    }

    public ServerResponse<PageInfo> getProductList(int pageNum,int pageSize){
/*        startPage--start
        填充自己的sql查询逻辑
        pageHelper-首尾*/
//        只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页。
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectList();
        //其实这边的productList已经是分页之后的结果了
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        //PageInfo包含了非常全面的分页属性
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    private ProductListVo assembleProductListVo(Product product){
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setPrice(product.getPrice());
        productListVo.setMainImage(product.getMainImage());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setName(product.getName());
        productListVo.setStatus(product.getStatus());
        //imageHost
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymall.com/"));
        return productListVo;
    }
}
