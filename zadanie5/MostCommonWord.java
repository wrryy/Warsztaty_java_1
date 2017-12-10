package zadanie5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sun.misc.IOUtils;

public class MostCommonWord implements Comparable<Integer> {

	public static void main(String[] args) {
		// mostCommonWords();
		mostCommonWords("http://www.onet.pl/");
	}

	static void mostCommonWords(String url) {
		mostcommonWords(url);
	}

	static void mostCommonWords() {
		mostcommonWords("http://www.onet.pl/");
	}

	static void mostcommonWords(String url) {
		Connection connect = Jsoup.connect(url);
		try {
			FileWriter fw = new FileWriter("popular_words.txt", false);
			Document document = connect.get();
			Elements links = document.select("span.title");
			for (Element elem : links) {
				fw.append(elem.text().toString()).append("\n");
			}
			HashMap<String, Integer> words = countWords("popular_words.txt");
			firstTen(words);
			// for(Map.Entry<String, Integer> entry: wordCounter.entrySet()){
			// System.out.println(entry.getKey()+ " " + entry.getValue());
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static HashMap<String, Integer> countWords(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		HashMap<String, Integer> wordCounter = new HashMap<>();

		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split("[-.,\\s:\"!\\/?]");
			for (String string : line) {
				String s = string.toLowerCase();
				if ((s != null) && (s.length() > 3)) {
					if (!wordCounter.containsKey(s)) {
						wordCounter.put(s, 1);
					} else if (wordCounter.containsKey(s)) {
						wordCounter.replace(s, wordCounter.get(s) + 1);
					}
				}
			}
		}
		scan.close();
		return wordCounter;
	}

	static void firstTen(HashMap<String, Integer> map) throws IOException {
		FileWriter fw = new FileWriter("most_popular_words.txt");
		/*
		 * Stream<HashMap.Entry<String,Integer>> sorted =
		 * map.entrySet().stream() .sorted(HashMap.Entry.comparingByValue())
		 */ /* ascending */
		int counter = 0;
		while (counter < 10) {
			Stream<HashMap.Entry<String, Integer>> sorted = map.entrySet().stream()
					.sorted(Collections.reverseOrder(HashMap.Entry.comparingByValue())); /* descending */
			// sorted.filter(s -> s.length() ==
			// 10).forEach(System.out::println);
			for (String s: sorted) {
				fw.append(s);
			}
		}
	}

	@Override
	public int compareTo(Integer o) {
		// TODO Auto-generated method stub
		return 0;
	}
}