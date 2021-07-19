package com.existingeevee.chickeneer;

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
import java.util.Random;
import java.util.Scanner;

import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.Trait;

public class Utils {


	/*public static String capitalize(String str) {
	    String firstLetter = str.substring(0, 1);
	    String remainingLetters = str.substring(1, entry.getKey().length());
	    firstLetter = firstLetter.toUpperCase();
	    String name = firstLetter + remainingLetters;
	    return name;
	}*/

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

	public static Trait calculateBlend(String id, Allele allele1, Allele allele2, Random rand) {
		if (allele1.getValue() == allele2.getValue()) {
			return new Trait(id, allele1, allele2);
		}
		float blendChance = (allele1.getBlendChance() + allele2.getBlendChance()) / 2;
		if (rand.nextFloat() <= blendChance) {
			if (allele1.isDominent() == allele2.isDominent()) {
				int value = Math.round(allele1.getValue() + allele2.getValue()) / 2;
				return new Trait(id, new Allele(allele1.isDominent() && allele2.isDominent(), value, true, blendChance / 2), rand.nextBoolean() ? allele1 : allele2, rand, true);
			}
			if (Utils.compareBoolean(allele1.isDominent(), allele2.isDominent())) {
				if (allele1.getValue() > allele2.getValue()) {
					int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) + (allele1.getValue() - allele2.getValue()) / 4;
					return new Trait(id, new Allele(false, value, true, blendChance / 2), allele1, rand, true);
				} else {
					int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) - (allele1.getValue() - allele2.getValue()) / 4;
					return new Trait(id, new Allele(false, value, true, blendChance / 2), allele1, rand, true);
				}
			} else {
				if (allele1.getValue() > allele2.getValue()) {
					int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) - (allele1.getValue() - allele2.getValue()) / 4;
					return new Trait(id, new Allele(false, value, true, blendChance / 2), allele2, rand, true);
				} else {
					int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) + (allele1.getValue() - allele2.getValue()) / 4;
					return new Trait(id, new Allele(false, value, true, blendChance / 2), allele2, rand, true);
				}
			}
		}
		return new Trait(id, allele1, allele2);
	}

	public static String encodeBase64(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}

	public static String decodeBase64(String str) {
		byte[] decodedBytes = Base64.getDecoder().decode(str);
		return new String(decodedBytes);
	}
}
