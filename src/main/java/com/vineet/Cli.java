package com.vineet;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {
	private static final Logger log = Logger.getLogger(Cli.class.getName());
	private String[] args = null;
	private Options options = new Options();

	public Cli(String[] args) {

		this.args = args;

		options.addOption("h", "help", false, "show help.");
		options.addOption("v", "var", true, "Here you can set parameter .");
		
		options.addOption("tableName","tableName",true,"this is the table name for the habse");
		

	}

	public void parse() {
		CommandLineParser parser = new BasicParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))
				help();

			if (cmd.hasOption("v")) {
				log.log(Level.INFO, "Using cli argument -v=" + cmd.getOptionValue("v"));
				System.out.println("received v as : " + cmd.getOptionValue("v"));
				// Whatever you want to do with the setting goes here
			} if (cmd.hasOption("tableName")) {
				log.log(Level.INFO, "Using cli argument -tableName=" + cmd.getOptionValue("tableName"));
				// Whatever you want to do with the setting goes here
				System.out.println("received table name as : " + cmd.getOptionValue("tableName"));
			} else {
				log.log(Level.SEVERE, "Missing command line options..!!");
				help();
			}

		} catch (ParseException e) {
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		}
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("Main", options);
		System.exit(0);
	}
}