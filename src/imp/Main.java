package imp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] filePath = {"d:/dk_testcase/input000.txt",
                            "d:/dk_testcase/input001.txt",
                            "d:/dk_testcase/input002.txt",
                            "d:/dk_testcase/input003.txt",
                            "d:/dk_testcase/input004.txt",
                            "d:/dk_testcase/input005.txt",
                            "d:/dk_testcase/input006.txt",
                            "d:/dk_testcase/input007.txt",
                            "d:/dk_testcase/input008.txt",
                            "d:/dk_testcase/input009.txt",
                            "d:/dk_testcase/input010.txt",
                            "d:/dk_testcase/input011.txt",
                            "d:/dk_testcase/input012.txt"};
        String[] goldPath = {"d:/dk_testcase/output000.txt",
                            "d:/dk_testcase/output001.txt",
                            "d:/dk_testcase/output002.txt",
                            "d:/dk_testcase/output003.txt",
                            "d:/dk_testcase/output004.txt",
                            "d:/dk_testcase/output005.txt",
                            "d:/dk_testcase/output006.txt",
                            "d:/dk_testcase/output007.txt",
                            "d:/dk_testcase/output008.txt",
                            "d:/dk_testcase/output009.txt",
                            "d:/dk_testcase/output010.txt",
                            "d:/dk_testcase/output011.txt",
                            "d:/dk_testcase/output012.txt"};

        try  {
            for (int i = 0; i <= 12; i++) {
                List<String> testList = readFromTextFile(filePath[i]);
                List<String> goldList = readFromTextFile(goldPath[i]);

                OrgChart orgChart = new OrgChart();

                for (String test : testList) {
                    funcCall(orgChart,test);
                }
                System.out.println("------------------------------");

                for (String gold : goldList) {
                    System.out.println(gold);
                }

                System.out.println("*****************************");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void funcCall(OrgChart orgChart,String cmd) {
        String[] params = cmd.split(",");
        if (params[0].equals("add")) {
            orgChart.add(params[1], params[2], params[3]);
        }
        if (params[0].equals("move")) {
            orgChart.move(params[1], params[2]);
        }
        if (params[0].equals("remove")) {
            orgChart.remove(params[1]);
        }
        if (params[0].equals("count")) {
            System.out.println( orgChart.count(params[1]));
        }
        if (params[0].equals("print")) {
            orgChart.print();
        }
    }

    public static ArrayList<String> readFromTextFile(String pathname) throws IOException {
        ArrayList<String> strArray = new ArrayList<String>();
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while(line != null) {
            strArray.add(line);
            line = br.readLine();
        }
        return strArray;
    }

}
