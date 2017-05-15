package Code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class IO {
	public static int[] read() {
		File file = new File("filename.txt");//it can also take the file path as string parameter
		BufferedReader br;
		FileReader fr;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			line = line.trim();
			String[] mintermsStrings = line.split(" ");
			int[] minterms = new int[mintermsStrings.length];
			for (int i = 0 ; i < mintermsStrings.length ; i++) {
				minterms[i] = Integer.parseInt(mintermsStrings[i]);
			}
			br.close();
			fr.close();
			return minterms;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static void write(String[] solution) {

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), "utf-8"));
			for (int i = 0; i < solution.length; i++) {
				writer.write(solution[i]);
			}
		} catch (IOException ex) {

		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
	}
}
