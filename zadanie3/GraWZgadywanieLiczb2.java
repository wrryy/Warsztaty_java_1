package zadanie3;

import java.util.Scanner;

public class GraWZgadywanieLiczb2 {

	public static void main(String[] args) {
		zgadujemy();
	}

	static void zgadujemy() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Pomysl liczbe od 0 do 1000 a ja ja zgadne w max 10 probach");
		int min = 0;
		int max = 1000;
		int[] arr = new int[1001];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		boolean guessed = false;

		while (!guessed) {
			int guess = (int) ((max - min) / 2) + min;
			int help = getHelp(guess, scan);
			if (help == 2) {
				System.out.println("Wygralem!");
				guessed = true;

			} else if (help == 1) {
				min = guess;
			} else {
				max = guess;
			}
		}
		scan.close();
	}

	/*
	 * Pyta czy trafione czy nie i zwraca liczbe
	 */
	static int getHelp(int integer, Scanner scan) {
		System.out.println("Zgaduje: " + integer);
		System.out.println("mniej/wiecej/zgadles: ");
		String answer = scan.next().toLowerCase();
		int result = -1;

		while (!((answer.equals("mniej")) || (answer.equals("wiecej")) || (answer.equals("zgadles")))) {
			System.out.println("Zle slowo");
			answer = scan.next().toLowerCase();
		}
		switch (answer) {
		case "mniej":
			result = 0;
			break;
		case "wiecej":
			result = 1;
			break;
		case "zgadles":
			result = 2;
			break;
		}
		return result;
	}
}
