package zadanie1;

import java.util.Random;
import java.util.Scanner;

public class GraWZgadywanie {

	public static void main(String[] args) {
		guessInteger();

	}

	static void guessInteger() {
		Scanner scan = new Scanner(System.in);
		Random r = new Random();
		int goal = r.nextInt(100);
		int guess;
		boolean equal = false;
		while (!equal) {
			System.out.print("Zgadnij liczbe:  ");
			guess = intInput0(scan);
			if (guess>goal){
				System.out.println("Za duzo!");
//				guess = intInput0(scan);
			}else if (guess<goal){
				System.out.println("Za malo!");
//				guess = intInput0(scan);
			}else{
				System.out.println("\nZgadles!!!");
				equal = true;
			}
		}
	}

	static int intInput0(Scanner scan) {
		while (!scan.hasNextInt()) {
			System.out.println("To nie jest liczba");
			scan.next();
		}
		int integer = scan.nextInt();
		while (integer <= 0) {
			System.out.println("Wprowadz liczbe wieksza od zera");
			while (!scan.hasNextInt()) {
				System.out.println("To nie jest liczba");
				scan.next();
			}
			integer = scan.nextInt();
		}
		return integer;
	}

}
