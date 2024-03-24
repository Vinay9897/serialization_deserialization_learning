package com.serialization.chapter2_serializationwithmemorybuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MaketDataWithMemoryBufferTest {
	
	@Test
	@DisplayName("Test basic serialization and deserialization for Java POJO using memory buffer")
	void testSerializeAndDeserialize() throws IOException, ClassNotFoundException {
		final var marketData = new MarketDataWithMemoryBuffer();
		marketData.setSecurityId("Vinay");
		marketData.setTime(1000L);
		marketData.setOpen(160.30);
		marketData.setHigh(165.30);
		marketData.setLow(150.30);
		marketData.setClose(163.30);
		marketData.setLast(161.90);
		marketData.setLevelOne(true);
		
		
		
		//serialize and write
		final var bout = new ByteArrayOutputStream();
		try(final var oos = new ObjectOutputStream(new BufferedOutputStream(bout))){
			System.out.println("Before Serialization");
			System.out.println(marketData);
			oos.writeObject(marketData);
//			oos.close();
		}
		
		
		// deserialize and read
		
		try(final var ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bout.toByteArray())))){
			final var fromSerialize = (MarketDataWithMemoryBuffer) ois.readObject();
			System.out.println("After Serialization");
			System.out.println(fromSerialize);
			
			assertNotNull(fromSerialize);
			assertEquals("Vinay", fromSerialize.getSecurityId());
			assertEquals(1000L, fromSerialize.getTime());
			assertEquals(160.30, fromSerialize.getOpen());
			assertEquals(165.30, fromSerialize.getHigh());
			assertEquals(150.30, fromSerialize.getLow());
			assertEquals(163.30, fromSerialize.getClose());
			assertEquals(161.90, fromSerialize.getLast());
			assertTrue(fromSerialize.isLevelOne());
//			ois.close();
		}
	}

}
