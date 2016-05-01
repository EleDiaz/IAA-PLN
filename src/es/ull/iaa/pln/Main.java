/**
 * 
 */
package es.ull.iaa.pln;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
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
	    	Filter filter = new Filter(lines);
	    	List<String> outputLines = filter.getSet().stream().map(word -> "Palabra:" + word).collect(Collectors.toList());
	    	outputLines.add(0, "Numero de palabras:" + outputLines.size());
	    	
			Path pathO = Paths.get(commands.getString());
		    Files.write(pathO, outputLines, Charset.forName("UTF-8"));
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
