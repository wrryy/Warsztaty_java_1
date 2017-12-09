package zadanie5;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MostCommonWord {

	public static void main(String[] args) {
		mostCommonWord();
	}

	static void mostCommonWord() {
		Connection connect = Jsoup.connect("http://www.onet.pl/");
		try {
			FileWriter fw = new FileWriter("popular_words.txt");
			Document document = connect.get();
			Elements links = document.select("span.title");
			for (Element elem : links) {
				fw.append(elem.toString()).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}