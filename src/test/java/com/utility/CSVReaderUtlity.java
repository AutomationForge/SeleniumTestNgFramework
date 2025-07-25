package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtlity {

	public static Iterator<User> readCSVFile(String filename) {

		File csvFile = new File(System.getProperty("user.dir") + "//testData//" + filename);

		FileReader fileReader = null;
		CSVReader csvReader;
		String[] line;
		List<User> userList = null;
		User userData;
		try {
			fileReader = new FileReader(csvFile);

			csvReader = new CSVReader(fileReader);
			csvReader.readNext();
			userList = new ArrayList<User>();

			while ((line = csvReader.readNext()) != null) {

				userData = new User(line[0], line[1]);//It passes data from an actual file (e.g., CSV, Excel, or text) into a POJO class (User).
				userList.add(userData);
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		catch (CsvValidationException | IOException e) {

			e.printStackTrace();
		}

		return userList.iterator();
	}

}
