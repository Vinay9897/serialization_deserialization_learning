package com.serialization.chapter4_serializationversioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MarketDataSerializationVersioningTests {
	
	private File serFile;

	@BeforeEach
	void setUp() throws IOException {
		final var serPath = Path.of("src", "test", "resources", "MarketDataWithVersioningTest.ser");

		serFile = serPath.toFile();

		if (!serFile.exists()) {
			final var success = serFile.createNewFile();
			assertTrue(success);
		}
	}

	@Test
	@DisplayName("Test serialization versioning for Java POJO")
	void testSerialize() {
		final var marketData = new MarketDataSerializationVersioning();
		marketData.setSecurityId("Vinay");
		marketData.setTime(1000L);
		marketData.setOpen(160.30);
		marketData.setHigh(165.30);
		marketData.setLow(150.30);
		marketData.setClose(163.30);
		marketData.setLast(161.90);
		marketData.setLevelOne(true);
		
		
		try {
			final var oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(serFile)));
			oos.writeObject(marketData);
			System.out.println("Before Serialization: ");
			System.out.println(marketData);
			oos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Test deserialize for Java POJO")
	void testDeserialize(){

		try(final var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(serFile)));) {
			
			try {
			final var fromSerialize = (MarketDataSerializationVersioning) ois.readObject();
			System.out.println("After Serialization");
			System.out.println(fromSerialize);
			assertNotNull(fromSerialize);
			assertEquals("Vinay", fromSerialize.getSecurityId());
			assertEquals(1000L, fromSerialize.getTime());
			assertEquals(160.30, fromSerialize.getOpen(),150.0);
			assertEquals(165.30, fromSerialize.getHigh(),150.0);
			assertEquals(150.30, fromSerialize.getLow(),150.0);
			assertEquals(163.30, fromSerialize.getClose(),150.0);
			assertEquals(161.90, fromSerialize.getLast(),150.0);
			assertTrue(fromSerialize.isLevelOne());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
