package cn.edu.sjtu.stap;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cn.edu.sjtu.stap.differ.FileDiffer;
import cn.edu.sjtu.stap.differ.DifferResult;
import cn.edu.sjtu.stap.inst.Instrument;
import cn.edu.sjtu.stap.path.PathResolver;

public class App {
	/**
	 * App 
	 * options:
	 *   -f comparing two file instead of two directories
	 *   -d <old> <new> directory
	 * @param args
	 */

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		CommandLineParser parser = new BasicParser();
		
		Options options = new Options(); 
		
		options.addOption(OptionBuilder
				.hasArgs(2)
				.withArgName("old new")
				.withDescription("compare two directories")
				.create('d'));
		
		options.addOption(OptionBuilder
				.hasArgs(1)
				.withArgName("dir")
				.withDescription("instrumenting directory")
				.withLongOpt("inst")
				.create('i'));
		// options.addOption(OptionBuilder.)
		
		options.addOption(OptionBuilder
				.hasArgs(2)
				.withArgName("projectName file")
				.withDescription("Read the path execution file & update the database")
				.create('r')
			);
		
		options.addOption(OptionBuilder
				.hasArgs(1)
				.withArgName("dir")
				.withDescription("specify output folder with '-i'")
				.create('o'));
		
		options.addOption("h", false, "display this help message");
		options.addOption(OptionBuilder
				.hasArg(false)
				.withDescription("display this help message")
				.create('h'));
		
		try {
			CommandLine line = parser.parse(options, args);
			// List args = line.getArgList();
			// parser.
			FileDiffer differ = new FileDiffer();
			
			String outputFolder = null;
			
			if (line.hasOption('o')) {
				outputFolder = line.getOptionValue('o');
			}
			
			if (line.hasOption('r')) {
				String [] vals = line.getOptionValues('r');
				new PathResolver(vals[0], vals[1]);
				
			} else if (line.hasOption('d')) {
				String [] dirs = line.getOptionValues('d');
				DifferResult result = differ.diff(dirs[0], dirs[1]);
				System.out.print(result.toString());
			} else if (line.hasOption('i')) {
				new Instrument(line.getOptionValue('i'), outputFolder);
			} else {
				HelpFormatter help = new HelpFormatter();
				help.printHelp("IUT", options);
			}
		} catch (Exception e) {
			System.err.println("[" + e.getClass().getName() + "]" + e.getMessage());
		}

	}

}
