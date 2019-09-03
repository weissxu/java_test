package com.weiss.json;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class HuayishengsanRuleModel implements Serializable {

	    private static final long serialVersionUID = 4979499641171807180L;

	    /**
	     * 花一生三的核销量
	     */
	    private int huayishengsanThreshold;

	    /**
	     * 卡号宽带的订单量
	     */
	    private int broadbandAndSimThreshold;

	    /**
	     * 省编码
	     */
	    private List<String> cityCodeList;

	    //Gradient increasing
	    /**
	     * 扩展点，当前默认梯度递增
	     */
	    private String algorithm;

	    public int getHuayishengsanThreshold() {
	        return huayishengsanThreshold;
	    }

	    public void setHuayishengsanThreshold(int huayishengsanThreshold) {
	        this.huayishengsanThreshold = huayishengsanThreshold;
	    }

	    public int getBroadbandAndSimThreshold() {
	        return broadbandAndSimThreshold;
	    }

	    public void setBroadbandAndSimThreshold(int broadbandAndSimThreshold) {
	        this.broadbandAndSimThreshold = broadbandAndSimThreshold;
	    }

	    public List<String> getCityCodeList() {
	        return cityCodeList;
	    }

	    public void setCityCodeList(List<String> cityCodeList) {
	        this.cityCodeList = cityCodeList;
	    }

	    public String getAlgorithm() {
	        return algorithm;
	    }

	    public void setAlgorithm(String algorithm) {
	        this.algorithm = algorithm;
	    }

	    public static void main(String[] args) {
	        HuayishengsanRuleModel model = new HuayishengsanRuleModel();

	        model.setBroadbandAndSimThreshold(5);
	        model.setHuayishengsanThreshold(200);
	        model.setCityCodeList(Lists.newArrayList("30010", "30020"));

	        String json = JSON.toJSONString(model);
	        System.out.println(json);
	    }
	}
