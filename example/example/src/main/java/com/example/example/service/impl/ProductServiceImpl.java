package com.example.example.service.impl;

import com.example.example.dto.ProductDto;
import com.example.example.model.ProductModel;
import com.example.example.repository.ProdutRepository;
import com.example.example.service.ProductService;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.dozer.loader.api.BeanMappingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProdutRepository repository;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public void afterPropertiesSet(){
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(ProductModel.class, ProductDto.class);
            }
        };
        dozerBeanMapper.addMapping(builder);
    }
    @Override
    public List<ProductDto> listProduct() throws Exception{
        try{
            List<ProductDto> listReturn = new ArrayList<>();
            List<ProductModel> listModels= repository.findAllByOrderByIdAsc();
            if (listModels==null || listModels.isEmpty()){
                return null;
            }
            listModels.forEach(obj -> listReturn.add(mapProduct(obj)));
            return listReturn;
        }catch (DataAccessException e){
            LOGGER.error("Error DataAccessException: ",e);
            throw new Exception("Error getting data",new Throwable());
        }catch (MappingException e){
            LOGGER.error("Error MappingException: ",e);
            throw new Exception("Error mapping the data",new Throwable());
        }catch (NullPointerException e){
            LOGGER.error("Error NullPointerException: ",e);
            throw new Exception("Null field error",new Throwable());
        }
    }

    @Override
    public boolean save(ProductDto dto) {
        try{
            repository.save(mapProductModel(dto));
            return true;
        }catch (DataAccessException e){
            LOGGER.error("Error save: ",e);
        }
        return false;
    }

    private ProductDto mapProduct(ProductModel model) {
        ProductDto dto = new ProductDto();
        dozerBeanMapper.map(model,dto);
        return dto;
    }
    private ProductModel mapProductModel(ProductDto dto) {
        ProductModel model = new ProductModel();
        dozerBeanMapper.map(dto,model);
        return model;
    }
}
