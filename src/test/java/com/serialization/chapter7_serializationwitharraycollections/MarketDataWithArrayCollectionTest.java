package com.serialization.chapter7_serializationwitharraycollections;

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

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MarketDataWithArrayCollectionTest {

	private File serFile;
	private List<String> data = List.of("Vinay", "Jay", "Yashraj");

	@BeforeEach
	void setUp() throws IOException {
		final var serPath = Path.of("src", "test", "resources", "MarketDataWithArrayCollectionTest.ser");

		serFile = serPath.toFile();

		if (!serFile.exists()) {
			final var success = serFile.createNewFile();
			assertTrue(success);
		}
	}

	@Test
	@DisplayName("Test basic serialization for Java POJO with list member fields")
	void testSerialize() throws FileNotFoundException, IOException {
		final var marketData1 = new MarketDataWithArrayCollection();
		marketData1.setSecurityId("Vinay");
		marketData1.setTime(1000L);
		marketData1.setOpen(160.30);
		marketData1.setHigh(165.30);
		marketData1.setLow(150.30);
		marketData1.setClose(163.30);
		marketData1.setLast(161.90);
		marketData1.setLevelOne(true);
		marketData1.setMdProviders(data);

		try(final var oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(serFile)));) {
			System.out.println("Before Serialization: ");
			System.out.println(marketData1);
			oos.writeObject(marketData1);
			oos.close();
		} 
	}

	@Test
	@DisplayName("Test basic deserialize for Java POJO with array objects")
	void testDeserialize() throws ClassNotFoundException {

		try (final var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(serFile)))) {

			final var fromSerialize = (MarketDataWithArrayCollection)ois.readObject();
			assertNotNull(fromSerialize);
			System.out.println("After Serialization");
			System.out.println(fromSerialize);

			final var marketDataObjects = (MarketDataWithArrayCollection) fromSerialize;
			
			assertEquals(marketDataObjects.getSecurityId(), marketDataObjects.getSecurityId());
			assertEquals(1000L, marketDataObjects.getTime());
			assertEquals(160.30, marketDataObjects.getOpen(), 87.0);
			assertEquals(165.30, marketDataObjects.getHigh(), 87.0);
			assertEquals(150.30, marketDataObjects.getLow(), 87.0);
			assertEquals(163.30, marketDataObjects.getClose(), 87.0);
			assertEquals(161.90, marketDataObjects.getLast(), 87.0);
			assertTrue(marketDataObjects.isLevelOne());
			
			final var fromSerializeMdProviders = marketDataObjects.getMdProviders();
			assertNotNull(fromSerializeMdProviders);
			assertEquals(3, fromSerializeMdProviders.size());
			for (int j = 0; j < 3; j++)
				assertEquals(data.get(j), fromSerializeMdProviders.get(j));

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
