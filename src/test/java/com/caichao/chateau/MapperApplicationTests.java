package com.caichao.chateau;

import com.caichao.chateau.app.entity.Supplier;
import com.caichao.chateau.app.mapper.GoodsMapper;
import com.caichao.chateau.app.mapper.SupplierMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ChateauApplication.class)
@Slf4j
public class MapperApplicationTests {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void testSupplierMapper() {
        List<Supplier> supplierList = supplierMapper.selectByExample(null);
        log.info("supplierList:{}", supplierList);
    }

}
