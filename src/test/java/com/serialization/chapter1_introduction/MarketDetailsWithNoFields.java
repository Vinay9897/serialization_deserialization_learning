package com.serialization.chapter1_introduction;

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

public class MarketDetailsWithNoFields {

	private File serFile;

	@BeforeEach
	void setUp() throws IOException {
		final var serPath = Path.of("src", "test", "resources", "MarketDataWithNoFieldsTest.ser");

		serFile = serPath.toFile();

		if (!serFile.exists()) {
			final var success = serFile.createNewFile();
			assertTrue(success);
		}
	}

	@Test
	@DisplayName("Test basic serialization for Java POJO")
	void testSerialize() {
		final var marketData = new MarketDataWithNoFields();
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
	@DisplayName("Test basic deserialize for Java")
	void testDeserialize(){

		try {
			final var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(serFile)));
			MarketDataWithNoFields fromSerialize = null;
			try {
				fromSerialize = (MarketDataWithNoFields) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("After Serialization");
			System.out.println(fromSerialize);
			assertNotNull(fromSerialize);
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
