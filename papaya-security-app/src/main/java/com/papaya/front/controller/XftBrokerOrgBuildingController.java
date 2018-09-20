package com.papaya.front.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import com.papaya.front.entity.XftBrokerOrgBuilding;
import com.papaya.front.service.XftBrokerOrgBuildingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RestController
@RequestMapping("xftBrokerOrgBuildings")
@Api("swaggerTestController相关api")
public class XftBrokerOrgBuildingController {

    private final Logger logger = LoggerFactory.getLogger(XftBrokerOrgBuildingController.class);

    @Autowired
    private XftBrokerOrgBuildingService iXftBrokerOrgBuildingService;

    @ApiOperation(value = "根据id查询学生的信息",notes = "查询数据库中某个学生的信息")
    @ApiImplicitParam(name ="id",value = "学生id",paramType = "path",required = true,dataType = "String")
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到")
    })
    @GetMapping("{id}")
    public ResponseEntity< XftBrokerOrgBuilding > get ( @PathVariable String id ){
        return ResponseEntity.ok().body(iXftBrokerOrgBuildingService.selectById( id ));
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
