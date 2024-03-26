package com.serialization.chapter9_serializationwithenumconstantstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.serialization.chapter9_serializationwithenum.TradeStatus;

public class TradeStatusTest {
	
	@Test
	@DisplayName("Test java.lang.Enum name() and values() method")
	void testEnumNameMethodAndValuesMethod() {
		for(final TradeStatus tradeStatus : TradeStatus.values()) {
			System.out.printf("name()~> %s%n",tradeStatus.name());
			System.out.printf("toSTring()~> %s%n",tradeStatus);
		}
	}
	
	@Test
	@DisplayName("Test java.lang.Enum name() and values() method")
	void testEnumValuesMethod() {
			assertEquals(TradeStatus.LIVE,TradeStatus.valueOf("LIVE"));
			assertEquals(TradeStatus.CLOSED,TradeStatus.valueOf("closed".toUpperCase(Locale.ENGLISH)));
	}

}
