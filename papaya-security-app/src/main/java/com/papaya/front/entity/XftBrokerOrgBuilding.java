package com.papaya.front.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Data
@EqualsAndHashCode( callSuper = true )
@Accessors(chain = true)
@TableName("xft_broker_org_building")

public class XftBrokerOrgBuilding extends Model<XftBrokerOrgBuilding> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableField("id")
	private String id;
    /**
     * 主机构id
     */
	@TableField("broker_org_id")
	private String brokerOrgId;
    /**
     * 项目id
     */
	@TableField("building_id")
	private String buildingId;
    /**
     * 暂未用
     */
	@TableField("user_id")
	private String userId;
    /**
     * 合同截至时间
     */
	@TableField("contract_end_time")
	private String contractEndTime;
    /**
     * 合同开始时间
     */
	@TableField("contract_start_time")
	private String contractStartTime;
    /**
     * 保护器天数
     */
	@TableField("recommend_protected_date")
	private String recommendProtectedDate;
    /**
     * 保护器单位
     */
	@TableField("recommend_protected_unit")
	private String recommendProtectedUnit;
    /**
     * 到访保护器时间
     */
	@TableField("visited_protected_date")
	private String visitedProtectedDate;
    /**
     * 最大天数
     */
	@TableField("max_day")
	private String maxDay;
    /**
     * 最大时间
     */
	@TableField("max_time")
	private String maxTime;
    /**
     * 截止时间
     */
	@TableField("end_day")
	private String endDay;
	@TableField("area_id")
	private String areaId;
	@TableField("area_name")
	private String areaName;
	@TableField("city_id")
	private String cityId;
	@TableField("city_name")
	private String cityName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
