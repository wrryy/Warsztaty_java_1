package zadanie4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BLBLB {

	private static Random random = new Random();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int[] params = readCode("D12");
		System.out.println(throwDime(params));
	}

	private static int[] readCode(String s) {
		String temp = s;
		return codeToParams(temp);
	}

	private static int throwDime(int[] params) {
		return params[0] * (random.nextInt(params[1]) + 1) + params[2];
	}

	private static int[] codeToParams(String temp) {
		String[] strings = temp.split("[+D-]");
		if (strings[0].equals("")) {
			strings[0] = "1";
		}
		if (strings.length == 2) {
			strings = Arrays.copyOf(strings, 3);
			strings[2] = "0";
		}
		System.out.println(Arrays.toString(strings));
		int[] params = new int[strings.length];
		System.out.println(temp);
		for (int i = 0; i < strings.length; i++) {
			params[i] = Integer.parseInt(strings[i]);
		}
		if (temp.contains("-")) {
			params[2] *= (-1);
		}
		System.out.println(Arrays.toString(params));
		return params;
	}

	private static String inputCode() {
		String temp;
		System.out.println("Input code");
		try {
			temp = scanner.nextLine();
			isCode(temp);
		} catch (Exception e) {
			System.out.println("Not a code *D*(+or-)*");
			return inputCode();
		}
		return temp;
	}

	private static void isCode(String temp) throws Exception {
		if (!(temp.contains("D") && (!temp.contains("-") || temp.indexOf("-") > temp.indexOf("D") + 1)
				&& (!temp.contains("+") || temp.indexOf("+") > temp.indexOf("D") + 1))) {
			throw new Exception("Not a code");
		}
	}
}