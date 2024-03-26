package com.serialization.chapter9_serializationwithenum;

public enum TradeStatus {
	
	LIVE("Live"),
	STALE("Stale"),
	CLOSED("Closed"),
	UNKNOWN("Unknown Status");
	
	private String desc;
	
	TradeStatus(final String desc){
		this.desc= desc;
	}

	public String getDesc() {
		return desc;
	}
	
	@Override
	public String toString() {
		return "TradeStatus{" + "desc='" + desc +'\''+"}";
	}
	
	
	

}
