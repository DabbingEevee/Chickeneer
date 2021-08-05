package com.existingeevee.chickeneer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.AlleleSerializer;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {


	/*public static String capitalize(String str) {
	    String firstLetter = str.substring(0, 1);
	    String remainingLetters = str.substring(1, entry.getKey().length());
	    firstLetter = firstLetter.toUpperCase();
	    String name = firstLetter + remainingLetters;
	    return name;
	}*/

	public static void saveParentAndDNAToFile(DNA dnaP1, DNA dnaP2, DNA dna) {
		String path = ClassLoader.getSystemClassLoader().getResource(".").getPath() + "dnapool/" + dna.getUUID().toString();
		new File(path).mkdirs();
		try {
			new File(path + "/parents.log").createNewFile();
		} catch (IOException e) {}
		writeToFile("dnapool/" + dna.getUUID().toString() + "/parents.log", dnaP1.getUUID().toString());
		writeToFile("dnapool/" + dna.getUUID().toString() + "/parents.log", dnaP2.getUUID().toString());
		saveDNAToFile(dna);
	}

	public static void saveDNAToFile(DNA dna) {
		String path = ClassLoader.getSystemClassLoader().getResource(".").getPath() + "dnapool/" + dna.getUUID().toString();
		new File(path).mkdirs();

		try {
			for (Entry<String, Trait> tr : dna.getTraitMap().entrySet()) {
				new File(path + "/" + tr.getKey()).mkdirs();
				new File(path + "/" + tr.getKey() + "/a.json").createNewFile();
				new File(path + "/" + tr.getKey() + "/b.json").createNewFile();
				new File(path + "/" + tr.getKey() + "/traitdata.json").createNewFile();

				writeFile("dnapool/" + dna.getUUID().toString() + "/" + tr.getKey() + "/a.json", AlleleSerializer.serializers.get(tr.getValue().getAlleleA().getClass().getName()).deserializeToJsonH(tr.getValue().getAlleleA()));
				writeFile("dnapool/" + dna.getUUID().toString() + "/" + tr.getKey() + "/b.json", AlleleSerializer.serializers.get(tr.getValue().getAlleleB().getClass().getName()).deserializeToJsonH(tr.getValue().getAlleleB()));
				Map<String,String> map = new HashMap<String,String>();
				map.put("dominant", tr.getValue().getDominantAllele().equals(tr.getValue().getAlleleA()) ? "a" : "b");
				writeJson("dnapool/" + dna.getUUID().toString() + "/" + tr.getKey() + "/traitdata.json", map);
			}
		} catch (IOException | NullPointerException e) {}
	}

	public static Allele<?> deserializeAllele(String jsonData){
		return deserializeAllele(readJson(jsonData));
	}

	public static Allele<?> deserializeAllele(Map<String,String> jsonData){
		try {
			Object obj = Class.forName(jsonData.get("serializer")).getDeclaredConstructor().newInstance();
			if (obj instanceof AlleleSerializer) {
				AlleleSerializer<?> serializer = ((AlleleSerializer<?>) obj);
				return serializer.serializeFromJson(Utils.mapToJson(jsonData));
			}
		} catch (Throwable e) {
		}
		return null;
	}

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm){
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
    }

	public static String fileread(String str) {
		String textfile = "";

		try {
			File myObj = new File(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath())
					.getAbsolutePath().replace("%20", " ") + "/" + str);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				textfile += (data + "\n");

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return textfile;

	}

	public static File getfile(String str) {
		return new File(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath())
					.getAbsolutePath().replace("%20", " ") + "/" + str);

	}
	public static List<String> removeEmpty(List<String> list) {
		list.removeAll(Arrays.asList("", null));
		return list;

	}


	public static void writeToFile(String filename, String write) {
		try {
			String original = fileread(filename);
			BufferedWriter wrtr = new BufferedWriter(
					new FileWriter(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath())
							.getAbsolutePath().replace("%20", " ") + "/" + filename, false));
			wrtr.write(write + "\n" + original);
			wrtr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> readJsonFile(String filename) {
		String jsonInput = fileread(filename + ".json");
		return readJson(jsonInput);
	}

	public static Map<String, String> readJson(String jsonInput) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
		Map<String, String> map = null;
		try {
			map = mapper.readValue(jsonInput, typeRef);
		} catch (JsonProcessingException e) {}
		return map;
	}
	public static void writeFile(String filename, String str) {
		if (str != null) {
			try {
				BufferedWriter wrtr = new BufferedWriter(new FileWriter(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath().replace("%20", " ") + "/" + filename, false));
				wrtr.write(str);
				wrtr.close();
			} catch (IOException e) {}
		}
	}
	public static void writeJson(String filename, Map<String, String> map) {
		String jsonResult = mapToJson(map);
		if (jsonResult != null) {
			try {
				BufferedWriter wrtr = new BufferedWriter(new FileWriter(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath().replace("%20", " ") + "/" + filename, false));
				wrtr.write(jsonResult);
				wrtr.close();
			} catch (IOException e) {}
		}
	}

	public static String mapToJson(Map<String, String> map) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = null;
		try {
			jsonResult = mapper.writerWithDefaultPrettyPrinter()
			  .writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (jsonResult != null) {
			return jsonResult;
		}
		return null;
	}
	public static String getDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

	public static String getDateTime(String str) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(str);
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public static String getID(String mention) {
		if (mention.startsWith("<@!") && mention.endsWith(">")) {
			mention = mention.substring(3);
			mention = mention.substring(0,mention.length()-1);
		}
		if (mention.startsWith("<@") && mention.endsWith(">")) {
			mention = mention.substring(2);
			mention = mention.substring(0,mention.length()-1);
		}
		if(mention.length() == 18) {
			return mention;
		}
		return null;
	}
	public static Boolean parseBoolean(String str) {
		if (str.equalsIgnoreCase("true")) {
			return true;
		} else if (str.equalsIgnoreCase("false")) {
			return false;
		}
		return null;
	}

	public static String stringRepeat(String input, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(input);
		}
		return sb.toString();
	}
	public static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**************** RUN-METHODS-BYOND **********************/


	public static boolean compareBoolean(boolean a, boolean b) {
		int aInt = a ? 1 : 0;
		int bInt = b ? 1 : 0;

		return aInt > bInt;
	}

	public static int clamp(int val, int min, int max) {
	    return Math.max(min, Math.min(max, val));
	}

	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}

	public static String encodeBase64(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}

	public static String decodeBase64(String str) {
		byte[] decodedBytes = Base64.getDecoder().decode(str);
		return new String(decodedBytes);
	}
}
