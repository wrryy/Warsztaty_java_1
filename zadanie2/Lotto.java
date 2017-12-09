package zadanie2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Lotto {

	public static void main(String[] args) {
		graLotto();
	}

	static void graLotto() {
		
		int[] lottoNumbers = randomIntArray(6, 49);
		System.out.println(Arrays.toString(lottoNumbers));
		
		// pobieram liczby od uzytkownika i jednoczesnie sprawdzam czy sa
		// trafione
		Scanner scan = new Scanner(System.in);
		System.out.println("Wprowadz 6 typowanych liczb");
		int intTyped = 0;		//licznik wprowadzonych liczb
		int intGuessed = 0;		//licznik trafionych liczb
		int[] playerNumbers = new int[6];
		while (intTyped < 6) {
			int guess = isPresent(scan, playerNumbers);
			playerNumbers[intTyped] = guess;
			intTyped++;
			if (elementExist(lottoNumbers, guess) >= 0) {
				intGuessed++;
			}
		}
		// sprawdzam ile liczb bylo trafionych i wyswietlam wynik
		if (intGuessed == 3) {
			System.out.println("Trafiono trojke!");
		} else if (intGuessed == 4) {
			System.out.println("Trafiono czworke!");
		} else if (intGuessed == 5) {
			System.out.println("Trafiono piatke!");
		} else if (intGuessed == 6) {
			System.out.println("Trafiono szostke!");
		} else {
			System.out.println("same pudla");
		}
	}

	/*
	  * Tworze liste 6 unikalnych liczb z zakresu 1-49
	  */
	static int[] randomIntArray(int length, int maxVal) {
		Random r = new Random();
		int[] intArr = new int[length];
		for (int i = 0; i < intArr.length; i++) {
			int j = r.nextInt(maxVal-1) + 1;
			while (elementExist(intArr, j) >= 0) {
				j = r.nextInt(maxVal-1) + 1;
			}
			intArr[i] = j;
		}
		return intArr;
	}

	/*
	 * Sprawdza czy wprowadzona liczba znajduje sie w tablicy
	 */
	static int isPresent(Scanner scan, int[] arr) {
		int guess = intInput0(scan);
		while (elementExist(arr, guess) >= 0) {
			System.out.println("Wprowadzono juz te liczbe, wpisz inna!");
			guess = intInput0(scan);
		}
		return guess;
	}

	/*
	 * Sprawdza czy wartosc jest typu integer i czy jest wieksza od zera
	 */
	static int intInput0(Scanner scan) {
		while (!scan.hasNextInt()) {
			System.out.println("To nie jest liczba");
			scan.next();
		}
		int integer = scan.nextInt();
		while ((integer <= 0) || (integer > 49)) {
			System.out.println("Wprowadz liczbe z zakresu 1-49");
			while (!scan.hasNextInt()) {
				System.out.println("To nie jest liczba");
				scan.next();
			}
			integer = scan.nextInt();
		}
		return integer;
	}

	// Sprawdza czy element wystepuje w tablicy
	static int elementExist(int[] elements, int value) {
		Integer[] arr = IntStream.of(elements).boxed().toArray(Integer[]::new);
		int result = -1;
		result = Arrays.asList(arr).indexOf(value);
		return result;
	}
}
