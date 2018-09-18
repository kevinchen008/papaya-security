package com.papaya.front.service.impl;

import com.papaya.front.entity.XftBrokerOrgBuilding;
import com.papaya.front.mapper.XftBrokerOrgBuildingMapper;
import com.papaya.front.service.XftBrokerOrgBuildingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;


/**
 *code is far away from bug with the animal protecting
 *  ┏┓　　　┏┓
 *┏┛┻━━━┛┻┓
 *┃　　　　　　　┃ 　
 *┃　　　━　　　┃
 *┃　┳┛　┗┳　┃
 *┃　　　　　　　┃
 *┃　　　┻　　　┃
 *┃　　　　　　　┃
 *┗━┓　　　┏━┛
 *　　┃　　　┃神兽保佑
 *　　┃　　　┃代码无BUG！
 *　　┃　　　┗━━━┓
 *　　┃　　　　　　　┣┓
 *　　┃　　　　　　　┏┛
 *　　┗┓┓┏━┳┓┏┛
 *　　　┃┫┫　┃┫┫
 *　　　┗┻┛　┗┻┛
 *
 *   @Description : MybatisPlus代码生成器
 *   ---------------------------------
 *   @Author : Kevin.Chen
 *   @Date : 2018-09-18
 */
@Service
public class iXftBrokerOrgBuildingService extends ServiceImpl<XftBrokerOrgBuildingMapper, XftBrokerOrgBuilding> implements XftBrokerOrgBuildingService {


    @Override
    public Page<XftBrokerOrgBuilding> listPage( Page<XftBrokerOrgBuilding> page ) {
        Page<XftBrokerOrgBuilding> pageData =  super.selectPage(page,null );
        pageData.setTotal(super.selectCount(null));
        return pageData;
    }
    
    
}
