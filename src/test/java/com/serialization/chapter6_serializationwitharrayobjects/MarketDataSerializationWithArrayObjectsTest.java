package com.serialization.chapter6_serializationwitharrayobjects;

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
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MarketDataSerializationWithArrayObjectsTest {

	private File serFile;
	private String[] data = { "Vinay", "Jay", "Yashraj" };

	@BeforeEach
	void setUp() throws IOException {
		final var serPath = Path.of("src", "test", "resources", "MarketDataWithArrayObjectsTest.ser");

		serFile = serPath.toFile();

		if (!serFile.exists()) {
			final var success = serFile.createNewFile();
			assertTrue(success);
		}
	}

	@Test
	@DisplayName("Test basic serialization for Java POJO with array objects")
	void testSerialize() throws FileNotFoundException, IOException {
		final var marketData1 = new MarketDataSerializationWithArrayObjects();
		marketData1.setSecurityId("Vinay");
		marketData1.setTime(1000L);
		marketData1.setOpen(160.30);
		marketData1.setHigh(165.30);
		marketData1.setLow(150.30);
		marketData1.setClose(163.30);
		marketData1.setLast(161.90);
		marketData1.setLevelOne(true);
		marketData1.setMdProviders(data);

		final var marketData2 = new MarketDataSerializationWithArrayObjects();
		marketData2.setSecurityId("Vinay1");
		marketData2.setTime(2000L);
		marketData2.setOpen(260.30);
		marketData2.setHigh(265.30);
		marketData2.setLow(250.30);
		marketData2.setClose(263.30);
		marketData2.setLast(261.90);
		marketData2.setLevelOne(true);
		marketData2.setMdProviders(data);

		final var marketDataObjects = new MarketDataSerializationWithArrayObjects[] { marketData1, marketData2 };

		try(final var oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(serFile)));) {
			
			System.out.println("Before Serialization: ");
			Arrays.stream(marketDataObjects).forEach(System.out::println);
			oos.writeObject(marketDataObjects);
			oos.close();
		} 
	}

	@Test
	@DisplayName("Test basic deserialize for Java POJO with array objects")
	void testDeserialize() throws ClassNotFoundException, FileNotFoundException, IOException {

		try (final var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(serFile)))) {

			final var fromSerialize = ois.readObject();
			System.out.println("After Serialization");
			assertNotNull(fromSerialize);
			System.out.println(fromSerialize);

			assertTrue(fromSerialize.getClass().isArray());
			final var marketDataObjects = (MarketDataSerializationWithArrayObjects[])fromSerialize;
			Arrays.stream(marketDataObjects).forEach(System.out::println);
			assertEquals(2, marketDataObjects.length);

			assertEquals(marketDataObjects[0].getSecurityId(), marketDataObjects[0].getSecurityId());
			assertEquals(1000L, marketDataObjects[0].getTime());
			assertEquals(160.30, marketDataObjects[0].getOpen(), 87.0);
			assertEquals(165.30, marketDataObjects[0].getHigh(), 87.0);
			assertEquals(150.30, marketDataObjects[0].getLow(), 87.0);
			assertEquals(163.30, marketDataObjects[0].getClose(), 87.0);
			assertEquals(161.90, marketDataObjects[0].getLast(), 87.0);
			assertTrue(marketDataObjects[0].isLevelOne());
			final var fromSerializeMdProviders = marketDataObjects[0].getMdProviders();
			assertNotNull(fromSerializeMdProviders);
			assertEquals(3, fromSerializeMdProviders.length);
			for (int j = 0; j < 3; j++)
				assertEquals(data[j], fromSerializeMdProviders[j]);

			assertEquals(marketDataObjects[1].getSecurityId(), marketDataObjects[1].getSecurityId());
			assertEquals(2000L, marketDataObjects[1].getTime());
			assertEquals(260.30, marketDataObjects[1].getOpen(), 88.0);
			assertEquals(265.30, marketDataObjects[1].getHigh(), 88.0);
			assertEquals(250.30, marketDataObjects[1].getLow(), 88.0);
			assertEquals(263.30, marketDataObjects[1].getClose(), 88.0);
			assertEquals(261.90, marketDataObjects[1].getLast(), 88.0);
			assertTrue(marketDataObjects[1].isLevelOne());
			final var fromSerializeMdProviders2 = marketDataObjects[1].getMdProviders();
			assertNotNull(fromSerializeMdProviders2);
			assertEquals(3, fromSerializeMdProviders2.length);
			for (int j = 0; j < 3; j++)
				assertEquals(data[j], fromSerializeMdProviders2[j]);

			ois.close();
		} 

	}
}
