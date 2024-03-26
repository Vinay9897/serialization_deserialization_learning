package com.serialization.chapter4_serializationversioning;

import java.io.Serializable;
import java.util.Objects;

public class MarketDataSerializationVersioning implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String securityId;
	private long time;
	private double open;
	private double high;
	private double low;
	private double close;
	private double last;
	private boolean isLevelOne;
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(final String securityId) {
		this.securityId = securityId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(final long time) {
		this.time = time;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(final double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(final double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(final double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(final double close) {
		this.close = close;
	}
	public MarketDataSerializationVersioning() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MarketDataSerializationVersioning(String securityId, long time, double open, double high, double low, double close,
			double last, boolean isLevelOne) {
		super();
		this.securityId = securityId;
		this.time = time;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.last = last;
		this.isLevelOne = isLevelOne;
	}
	@Override
	public String toString() {
		return "MarketDataSerializationVersioning[securityId=" + securityId + ", time=" + time + ", open=" + open + ", high="
				+ high + ", low=" + low + ", close=" + close + ", last=" + last + ", isLevelOne=" + isLevelOne + "]";
	}
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	public boolean isLevelOne() {
		return isLevelOne;
	}
	public void setLevelOne(boolean isLevelOne) {
		this.isLevelOne = isLevelOne;
	}
	@Override
	public int hashCode() {
		return Objects.hash(close, high, isLevelOne, last, low, open, securityId, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketDataSerializationVersioning other = (MarketDataSerializationVersioning) obj;
		return Double.doubleToLongBits(close) == Double.doubleToLongBits(other.close)
				&& Double.doubleToLongBits(high) == Double.doubleToLongBits(other.high)
				&& isLevelOne == other.isLevelOne
				&& Double.doubleToLongBits(last) == Double.doubleToLongBits(other.last)
				&& Double.doubleToLongBits(low) == Double.doubleToLongBits(other.low)
				&& Double.doubleToLongBits(open) == Double.doubleToLongBits(other.open)
				&& Objects.equals(securityId, other.securityId) && time == other.time;
	}
	
	
	
	

}
