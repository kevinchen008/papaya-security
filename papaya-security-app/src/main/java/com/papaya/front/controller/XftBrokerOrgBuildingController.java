package com.papaya.front.controller;

import com.papaya.front.common.GenericResponse;
import org.springframework.http.ResponseEntity;
import com.papaya.front.entity.XftBrokerOrgBuilding;
import com.papaya.front.service.XftBrokerOrgBuildingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.plugins.Page;

import javax.ws.rs.core.Response;



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
@RestController
@RequestMapping("xftBrokerOrgBuildings")
public class XftBrokerOrgBuildingController {

    private final Logger logger = LoggerFactory.getLogger(XftBrokerOrgBuildingController.class);

    @Autowired
    private XftBrokerOrgBuildingService iXftBrokerOrgBuildingService;


    @GetMapping("{id}")
    public Response get (@PathVariable String id ){
        GenericResponse response = new GenericResponse();
        response.setStatus(0);
        response.setObjValue(iXftBrokerOrgBuildingService.selectById( id ));
        return Response.status(200).entity(response).build();
    }

    @GetMapping
    public ResponseEntity< Page<XftBrokerOrgBuilding> > list ( Page<XftBrokerOrgBuilding> page ){
        return ResponseEntity.ok().body(iXftBrokerOrgBuildingService.listPage( page ));
    }

    @PostMapping
    public ResponseEntity insert ( @RequestBody XftBrokerOrgBuilding requestXftBrokerOrgBuilding ){
        if ( !iXftBrokerOrgBuildingService.insert( requestXftBrokerOrgBuilding ) ){
            return ResponseEntity.badRequest().body("保存失败");
        }
        return ResponseEntity.ok("保存成功");
    }

    @PutMapping("{id}")
    public ResponseEntity update ( @PathVariable String id,
                                              @RequestBody XftBrokerOrgBuilding requestXftBrokerOrgBuilding ){
        if ( ! iXftBrokerOrgBuildingService.updateById( requestXftBrokerOrgBuilding.setId(id)) ){
            return ResponseEntity.badRequest().body("更新失败");
        }
        return ResponseEntity.ok("更新成功");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete ( @PathVariable String id ){
        if ( ! iXftBrokerOrgBuildingService.deleteById(id) ){
            return ResponseEntity.badRequest().body("删除失败");
        }
        return ResponseEntity.ok("删除成功");
    }

    
    
}
