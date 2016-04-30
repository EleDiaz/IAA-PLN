package es.ull.iaa.pln;

import java.util.*;

/**
 * A helper class to handle args passed by command line its catch exceptions and
 * print "usage" text if an error had happened
 */
public class ParseCommands {
	private LinkedList<String> args;
	private String usage;

	public ParseCommands(String[] args, String usage) {
		this.args = new LinkedList<>();
		for (String arg : args) {
			this.args.add(arg);
		}
		this.usage = usage;
		
	}
	
	public Integer getInteger() throws NumberFormatException {
		try {
			return Integer.parseInt(args.pop());
		}
		catch (NumberFormatException err) {
			printHelp();
			System.exit(-1);
			return null;
		}
		catch (NoSuchElementException err) {
			printHelp();
			System.exit(-1);
			return null;
		}
	}
	
	public String getString() {
		try {
			return args.pop();
		}
		catch (NoSuchElementException err) {
			printHelp();
			System.exit(-1);
			return null;
		}
	}
	
	public void printHelp() {
		System.out.println("USAGE:");
		System.out.println("  " + usage);
	}
}
