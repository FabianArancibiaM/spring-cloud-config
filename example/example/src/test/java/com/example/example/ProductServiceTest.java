package com.example.example;

import com.example.example.dto.ProductDto;
import com.example.example.model.ProductModel;
import com.example.example.repository.ProdutRepository;
import com.example.example.service.impl.ProductServiceImpl;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl service = new ProductServiceImpl();
    @Mock
    ProdutRepository repository;
    @Mock
    DozerBeanMapper mapper;
    @Before
    public void initListProduct(){
        Mockito.when(repository.findAllByOrderByIdAsc()).thenReturn(getListFindAll());
    }

    private List<ProductModel> getListFindAll() {
        List<ProductModel> list = new ArrayList<>();
        list.add(new ProductModel(1L,"LML","S8", BigInteger.valueOf(599990L),23L,"Samsung"));
        return list;
    }

    @Test
    public void getListProductTest() throws Exception {
        Assert.assertNotNull(service.listProduct());
    }
    @Test
    public void getSaveTest(){
        Assert.assertTrue(service.save(new ProductDto(1L,"LML","S8", BigInteger.valueOf(599990L),23L,"Samsung")));
    }
}
