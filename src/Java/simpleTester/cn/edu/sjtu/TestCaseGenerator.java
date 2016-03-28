package cn.edu.sjtu;

import java.io.*;
import java.util.*;

public class TestCaseGenerator {
    public static void main (String [] args ) {
        try {
            if (args.length != 2) {
                System.err.println("2 arguments must be specified");
                System.exit(1);
            }

            BufferedReader reader = new BufferedReader(
                new FileReader(new File(args[0]))
            );

            String line;

            List<String> cps = new ArrayList<String>();
            Map<String, Integer> map = new HashMap<String, Integer>();

            while ( (line = reader.readLine()) != null ) {
                if (line.startsWith(":")) {
                    cps.add(line);
                } else {
                    map.put(line, cps.size() - 1);
                }
            }

            reader.close();
            reader = new BufferedReader(
                new FileReader(new File(args[1]))
            );

            List<String> [] list = new ArrayList[cps.size()];

            for (int i = 0; i < cps.size(); i ++) {
                list[i] = new ArrayList<String>();
            }

            while ( (line = reader.readLine()) != null ) {
                Integer id = map.get(line);

                if (id != null) {
                    list[id].add(line);
                } else {
                    System.err.println(line + " Not Found");
                }
            }

            for (int i = 0; i < cps.size(); i ++) {
                System.out.println(cps.get(i));
                for (String method : list[i] ) {
                    System.out.println(method);
                }
            }

            // Integer id = 


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
