package com.zxb.eshop.inventory.controller;

import com.zxb.eshop.inventory.model.ProductInfo;
import com.zxb.eshop.inventory.model.ShopInfo;
import com.zxb.eshop.inventory.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuery on 2018/1/24.
 */
@Controller
public class CacheController {

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private CacheService cacheService;

    @RequestMapping("testPutCache")
    @ResponseBody
    public void testPutCache(ProductInfo productInfo){
        logger.info(">>>testPutCache:"+productInfo);
        cacheService.saveProductInfo2LocalCache(productInfo);
    }

    @RequestMapping("testGetCache")
    @ResponseBody
    public ProductInfo testGetCache(Long id){
        ProductInfo productInfo = cacheService.getProductInfoFromLocalCache(id);
        logger.info(">>>testGetCache:"+productInfo);
        return productInfo;
    }

    @RequestMapping("getProductInfo")
    @ResponseBody
    public ProductInfo getProductInfo(long productId){
        logger.info(">>>getProductInfo start, productId={}",productId);
        ProductInfo productInfo = cacheService.getProductInfoFromRedisCache(productId);
        logger.info(">>>getProductInfo,productId={},redis缓存商品内容为:{}",productId, productInfo);
        return productInfo;
    }

    @RequestMapping("getShopInfo")
    @ResponseBody
    public ShopInfo getShopInfo(long shopId){
        logger.info(">>>getShopInfo start, shopId={}",shopId);
        ShopInfo shopInfo = cacheService.getShopInfoFromRedisCache(shopId);
        logger.info(">>>getShopInfo,shopId={},redis缓存店铺内容为:{}",shopId,shopInfo);
        return shopInfo;
    }
}
