package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.entity.Product;
import com.itheima.map.ProductMapper;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Product业务层实现类
 */
@Service("productService")
@Transactional()
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 权限控制
     * 某些版块想要让特定的身份进行访问
     * jsr250注解
     * 1.spring security配置文件中开启jsr250注解
     * <security:global-method-security jsr250-annotations="enabled"/>
     * 2.导入jar包坐标
     * 3.在所要控制的方法上加入@RolesAllowed(加入可以访问的身份)
     * @RolesAllowed("ADMIN")
     *
     * @Secured
     * 这个方法和上面步骤基本一致
     * 不需要额外导入依赖
     * 第三步访问身份必须前面带ROLE_
     * @Secured("ROLE_ADMIN")
     *
     * @PreAuthorize
     * 这个方法是可以用spel表达式来写内容
     * 步骤和第二个方法一致
     * 指定角色是可以用hasRole('特权用户')
     * @PreAuthorize("hasRole('ROLE_ADMIN')")
     * 或者使用authentication.principal.字段=‘特定用户所拥有的字段’来指定
     * @PreAuthorize("authentication.principal.username='chen'")
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Product> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return productMapper.findAll();
    }

    @Override
    public Product findProductById(String id) throws Exception {
        return productMapper.findProductById(id);
    }

    @Override
    public void save(Product product) throws Exception{
        productMapper.save(product);
    }
}
