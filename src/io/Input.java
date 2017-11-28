package io;

import java.util.Scanner;

public class Input {

	public static Scanner getScannerInstance() {
		return new Scanner(System.in);
	}

	public static String get() {
		return getScannerInstance().next();
	}

	public static String getLine() {
		return getScannerInstance().nextLine();
	}

	public static long getLong() {
		return getScannerInstance().nextLong();
	}

	public static int getInt() {
		return getScannerInstance().nextInt();
	}

}
