package com.serialization.chapter8_serializationwithlistcollections;

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
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MarketDataWithListObjectsTests {

	
	private File serFile;
	private List<String> data = List.of("Vinay", "Jay", "Yashraj");
	
	@BeforeEach
	void setUp() throws IOException {
		final var serPath = Path.of("src", "test", "resources", "MarketDataWithListObjectTest.ser");

		serFile = serPath.toFile();

		if (!serFile.exists()) {
			final var success = serFile.createNewFile();
			assertTrue(success);
		}
	}
	
	@Test
	@DisplayName("Test basic serialization for Java POJO with array objects")
	void testSerialize() throws FileNotFoundException, IOException {
		final var marketData1 = new MarketDataWithListObjects();
		marketData1.setSecurityId("Vinay");
		marketData1.setTime(1000L);
		marketData1.setOpen(160.30);
		marketData1.setHigh(165.30);
		marketData1.setLow(150.30);
		marketData1.setClose(163.30);
		marketData1.setLast(161.90);
		marketData1.setLevelOne(true);
		marketData1.setMdProviders(data);

		final var marketData2 = new MarketDataWithListObjects();
		marketData2.setSecurityId("Vinay1");
		marketData2.setTime(2000L);
		marketData2.setOpen(260.30);
		marketData2.setHigh(265.30);
		marketData2.setLow(250.30);
		marketData2.setClose(263.30);
		marketData2.setLast(261.90);
		marketData2.setLevelOne(true);
		marketData2.setMdProviders(data);

		final var marketDataObjects = List.of( marketData1, marketData2 );

		try(final var oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(serFile)));) {
			
			System.out.println("Before Serialization: ");
			marketDataObjects.forEach(System.out::println);
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

			final var marketDataObjects = (List<MarketDataWithListObjects>) fromSerialize;
			marketDataObjects.forEach(System.out::println);
			assertEquals(2, marketDataObjects.size());
			
			for(int i = 0;i<marketDataObjects.size();i++)
			{
				assertEquals(marketDataObjects.get(i).getSecurityId(), marketDataObjects.get(i).getSecurityId());
				assertEquals(1000L, marketDataObjects.get(i).getTime());
				assertEquals(165.30, marketDataObjects.get(i).getHigh(), 87.0);
				assertEquals(160.30, marketDataObjects.get(i).getOpen(), 87.0);
				assertEquals(150.30, marketDataObjects.get(i).getLow(), 87.0);
				assertEquals(163.30, marketDataObjects.get(i).getClose(), 87.0);
				assertEquals(161.90, marketDataObjects.get(i).getLast(), 87.0);
				assertTrue(marketDataObjects.get(i).isLevelOne());
				final var fromSerializeMdProviders = marketDataObjects.get(i).getMdProviders();
				assertNotNull(fromSerializeMdProviders);
				assertEquals(3, fromSerializeMdProviders.size());
				for (int j = 0; j < 3; j++)
					assertEquals(data.get(j), fromSerializeMdProviders.get(j));
			}

			ois.close();
		} 

	}
	
}
