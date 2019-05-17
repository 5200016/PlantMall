package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.repository.ProductRepository;
import com.ybb.mall.service.dto.product.ProductBriefDTO;
import com.ybb.mall.service.dto.product.ProductDTO;
import com.ybb.mall.service.dto.product.ProductImageDTO;
import com.ybb.mall.service.mapper.SysProductMapper;
import com.ybb.mall.service.wx.WXProductService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description : 微信小程序-商品管理
 * @Author 黄志成
 * @Date 2019-05-16
 * @Version
 */

@Service
@Transactional
public class WXProductServiceImpl implements WXProductService {

    private final ProductRepository productRepository;

    private final SysProductMapper productMapper;

    public WXProductServiceImpl(ProductRepository productRepository, SysProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ResultObj findProductList(Long classifyId, Integer classifyType, String name, Integer sortFlag, Integer pageNum, Integer pageSize) {
        List<ProductDTO> productPage = productRepository.findWXProductList(name).stream().map(productMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getSale).reversed()).collect(Collectors.toList());
        switch (sortFlag){
            case 0:
                productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getSale).reversed()).collect(Collectors.toList());
                break;
            case 1:
                productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getCreateTime).reversed()).collect(Collectors.toList());
                break;
            case 2:
                if(classifyType.equals(0)){
                    productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getPrice)).collect(Collectors.toList());
                }
                if(classifyType.equals(1)){
                    productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getLeasePrice)).collect(Collectors.toList());
                }
                break;
            case 3:
                if(classifyType.equals(0)){
                    productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getPrice).reversed()).collect(Collectors.toList());
                }
                if(classifyType.equals(1)){
                    productPage = productPage.stream().sorted(Comparator.comparing(ProductDTO::getLeasePrice).reversed()).collect(Collectors.toList());                }
                break;
        }
        List<ProductBriefDTO> productList = new ArrayList<>();
        for(ProductDTO data : productPage){
            if(!TypeUtils.isEmpty(data.getClassifies())){
                for (SysClassify sysClassify : data.getClassifies()){
                    if(sysClassify.getId().equals(classifyId)){
                        ProductBriefDTO productBriefDTO = new ProductBriefDTO(data.getId(), data.getName(), data.getSale(), data.getPrice(), data.getLeasePrice(), data.getImage(), classifyType);
                        productList.add(productBriefDTO);
                    }
                }
            }
        }
        return ResultObj.back(200, new PageImpl(productList, PageRequest.of(pageNum, pageSize), productPage.size()));
    }

    @Override
    public ResultObj findWXProductById(Long id) {
        Optional<ProductDTO> productDTO = productRepository.findWXProductById(id).map(productMapper::toDto);
        ProductDTO product = productDTO.get();

        // 查询商品图片
        List<ProductImageDTO> images = new ArrayList<>();
        if(!TypeUtils.isEmpty(product.getImages())){
            for (SysProductImage productImage : product.getImages()){
                images.add(new ProductImageDTO(productImage.getUrl()));
            }
        }

        product.setImageList(images);
        return ResultObj.back(200, product);
    }
}
