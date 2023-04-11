package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {


    // 1. 创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        List<Brand> brands = mapper.selectAll();

        // 5. 释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.add(brand);

        // 5. 提交并释放资源
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.deleteByIds(ids);

        // 5. 提交并释放资源
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.deleteById(id);

        // 5. 提交并释放资源
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.update(brand);

        // 5. 提交并释放资源
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 计算开始索引
        int begin = (currentPage-1) * pageSize;

        // 计算每页条目数
        int size = pageSize;

        // 5. 查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);

        // 6. 查询总条数
        int totalCount = mapper.selectTotalCount();

        // 7. 封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8. 释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        // 2. 获取session对象
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMappper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 计算开始索引
        int begin = (currentPage-1) * pageSize;

        // 计算每页条目数
        int size = pageSize;

        // 模糊查询字符串处理
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }

        // 5. 查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        // 6. 查询总条数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        // 7. 封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8. 释放资源
        sqlSession.close();
        return pageBean;
    }
}
