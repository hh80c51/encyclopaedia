package com.athongkun.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OBJECT_T_MALL_SKU {
	private int id;
	private int shp_id;
	private int kc;
	private BigDecimal jg;
	private Date chjshj;
	private String sku_mch;
	private String kcdz;
	private int sku_xl;

	private T_MALL_PRODUCT spu;

	private T_MALL_TRADE_MARK tm;

	private List<OBJECT_T_MALL_ATTR_VALUE_NAME> list_attr_value_name;

	public List<OBJECT_T_MALL_ATTR_VALUE_NAME> getList_attr_value_name() {
		return list_attr_value_name;
	}

	public void setList_attr_value_name(List<OBJECT_T_MALL_ATTR_VALUE_NAME> list_attr_value_name) {
		this.list_attr_value_name = list_attr_value_name;
	}

	public int getSku_xl() {
		return sku_xl;
	}

	public void setSku_xl(int sku_xl) {
		this.sku_xl = sku_xl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public int getKc() {
		return kc;
	}

	public void setKc(int kc) {
		this.kc = kc;
	}

	public BigDecimal getJg() {
		return jg;
	}

	public void setJg(BigDecimal jg) {
		this.jg = jg;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public String getKcdz() {
		return kcdz;
	}

	public void setKcdz(String kcdz) {
		this.kcdz = kcdz;
	}

	public T_MALL_PRODUCT getSpu() {
		return spu;
	}

	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}

	public T_MALL_TRADE_MARK getTm() {
		return tm;
	}

	public void setTm(T_MALL_TRADE_MARK tm) {
		this.tm = tm;
	}

}
