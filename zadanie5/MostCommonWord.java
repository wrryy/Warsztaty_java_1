package zadanie5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
			Map<String, Integer> words = countWords("popular_words.txt");
			mostFrequent(words);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static Map<String, Integer> countWords(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		Map<String, Integer> wordCounter = new TreeMap<>();

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

	static void mostFrequent(Map<String, Integer> map) throws IOException {
		FileWriter fw = new FileWriter("most_popular_words.txt");
		// Stream<HashMap.Entry<String, Integer>> sorted = map.entrySet().stream()
		// .sorted(Collections.reverseOrder(HashMap.Entry.comparingByValue()));
		int index = 0;
		String[] arr = new String[map.size()];
		for (Map.Entry<String, Integer> mapData : map.entrySet()) {
			String entry = mapData.getValue() + "" + " " + mapData.getKey();
			arr[index] = entry;
			index++;
		}
		Arrays.sort(arr);
		for (int i = 0; i < 10; i++) {
			fw.append(arr[arr.length-1-i]).append("\n");
		}
		fw.close();
	}
	// while (counter < 1) {
	// for (int i = 0; i<9; i++){
	// System.out.println(l[i]);
	// fw.append((sorted.toArray())[i].toString());
	// }
	// counter++;
	// }
	// fw.append((l[i]).toString());
	// fw.append(l.get(i).toString());
	// fw.append(().get(i).toString());

	@Override
	public int compareTo(Integer o) {
		// TODO Auto-generated method stub
		return 0;
	}
}