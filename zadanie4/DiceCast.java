package zadanie4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DiceCast {

	public static void main(String[] args) {
		diceGame();
	}

	static int diceGame() {
		Scanner scan = new Scanner(System.in);
		String data = scan.next().trim();
		boolean check = inputCheck(data);
		while (!check) {
			System.out.println("The correct format is \"xDy+z\". Please try again:");
			data = scan.next();
			check = inputCheck(data);
		}
		int[] dataArray = inputParse(data);
		scan.close();
		int num = diceThrow(dataArray);
		System.out.println("Result: " + num);
		return num;
	}

	static boolean inputCheck(String str) {
		String[] diceSize = { "3", "4", "6", "8", "10", "12", "20", "100" };
		String[] strArr = str.split("[D+-]");
		if ((str.length() < 2) && (str.length() > 8) && !(str.contains("D"))
				&& ((str.contains("+")) && str.indexOf('+') > str.indexOf('D') + 1)
				&& ((str.contains("-")) && str.indexOf('-') > str.indexOf('D') + 1) 
				|| ((strArr.length == 3) && (whatInteger(strArr[2]))== -1)) {
			System.out.println("Wrong input format.");
			return false; 
		}
		if (!Arrays.asList(diceSize).contains(strArr[1].trim())) {
			System.out.println(
					"Error: You might want to check if dice size is correct.\nChoose from: {3,4,6,8,10,12,20,100}");
			return false;
		}
		return true;
	}

	/**
	 * Parses data according to cube cast naming convention. "xDy(+/-)z". y =
	 * {3,4,6,8,10,12,20,100}, z optional. Return int[] {x,y,z,1/-1}
	 * 
	 * @param str
	 * @return int[]
	 */
	static int[] inputParse(String str) {
		int[] dataArr = { -1, -1, 0, 1 };
		String[] tempArr = str.split("[D+-]");
		// determining value of x
		int x = whatInteger(str.charAt(0) + "");
		if (x < 0) {
			x = 1;
		}
		dataArr[0] = x;

		// determining if + or -
		if (str.contains("-")) {
			dataArr[3] = -1;
		} // else 1 meaning '+' or nothing

		dataArr[1] = whatInteger(tempArr[1]); // y
		if (tempArr.length == 3) {
			dataArr[2] = whatInteger(tempArr[2]); // z
		}
		return dataArr;
	}

	static int diceThrow(int[] arr) {
		Random r = new Random();
		int result = arr[2] * arr[3];
		for (int i = 0; i < arr[0]; i++) {
			int cast = r.nextInt(arr[1]) + 1;
			System.out.printf("%d throw: %d\n", i + 1, cast);
			result += cast;
		}
		System.out.println();
		return result;
	}

	/**
	 * Checks whether String object can be parsed to integer. If it is possible,
	 * return parsed integer, otherwise returns -1.
	 * 
	 * @param str
	 * @return int
	 */
	static int whatInteger(String str) {
		int i = -1;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}
		return i;
	}
}
