/**
 * 
 */
package es.ull.iaa.pln;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author eleazardd
 *
 */
public class Main {
	private final static String USAGE = "./TwitterProcesing fileInput.txt fileOutput.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParseCommands commands = new ParseCommands(args, USAGE);
		
		Path pathI = Paths.get(commands.getString());
	    try {
	    	Stream<String> lines = Files.lines(pathI);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Path pathO = Paths.get(commands.getString());
	    // Files.write(pathO, )
	}

}
