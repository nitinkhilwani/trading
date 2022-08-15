package com.xm.trading.dto;



public class Result {	
	private Double oldest;
	private Double newest;
	private Double min;
	private Double max;
	private Double normalizeValue;
	
	public Double getNormalizeValue() {
		return normalizeValue;
	}
	public void setNormalizeValue(Double normalizeValue) {
		this.normalizeValue = normalizeValue;
	}
	public Double getOldest() {
		return oldest;
	}
	public void setOldest(Double oldest) {
		this.oldest = oldest;
	}
	public Double getNewest() {
		return newest;
	}
	public void setNewest(Double newest) {
		this.newest = newest;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}

}
