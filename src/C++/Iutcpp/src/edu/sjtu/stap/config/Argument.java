package edu.sjtu.stap.config;

/**
 * Created by yfy on 6/19/16.
 * Argument
 */
public class Argument {

  public enum Type {
    ALL, INIT, COV, SELECT, HELP
  }

  public Argument(String[] args) throws Exception {
    final String err = "Invalid arguments. Use -help for help.";
    if (args.length > 1)
      throw new Exception(err);
    if (args.length == 0) {
      type = Type.HELP;
    } else {
      switch (args[0]) {
        case "-init":
        case "-i":
          type = Type.INIT;
          break;
        case "-cov":
        case "-c":
          type = Type.COV;
          break;
        case "-select":
        case "-s":
          type = Type.SELECT;
          break;
        case "-help":
        case "-h":
          type = Type.HELP;
          break;
        default:
          throw new Exception(err);
      }
    }
  }

  public final Type type;

}
