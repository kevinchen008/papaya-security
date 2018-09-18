package com.papaya.front.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的reponse
 * 
 * @author Administrator
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel
public class GenericResponse {

	@ApiModelProperty(value = "状态", required = true, example = "【-1=服务器异常；0=成功；1=异常信息；1000=经纪人或机构token失效，跳到登录页面；1001=机构经纪人账号被禁用；1002=接口签名异常，重新获取token】")
	private Integer status;

	@ApiModelProperty(value = "提示信息", required = true, example = "成功或者失败")
	private String message;

	@ApiParam(value = "信息Object", required = false, example = "信息Object")
	private Object objValue;

	@ApiParam(value = "信息Map", required = false, example = "信息Map")
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GenericResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObjValue() {
		return objValue;
	}

	public void setObjValue(Object objValue) {
		this.objValue = objValue;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public void putParamMap(String key, Object value) {
		this.paramMap.put(key, value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenericResponse [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", objValue=");
		builder.append(objValue);
		builder.append(", paramMap=");
		builder.append(paramMap);
		builder.append("]");
		return builder.toString();
	}

}
