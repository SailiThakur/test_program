package sample;

/* Save this in a file called Main.java to compile and test it */

/* 
 Example file showing how to write a program that reads
 input from `input.txt` in the current directory
 and writes output to `output.txt` in the current directory
 */

/* Do not add a package declaration */
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

/* DO NOT CHANGE ANYTHING ABOVE THIS LINE */
/* You may add any imports here, if you wish, but only from the 
 standard library */

/* Do not add a namespace declaration */

public class Main {
	public static void main(String[] args) {
		ArrayList<String> inputDataList = new ArrayList<String>();
		// int numLines = 0;
		String line = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					new File("input.txt")));

			while ((line = bufferedReader.readLine()) != null) {
				// String line = in.nextLine();
				if (!line.isEmpty())
					inputDataList.add(line);
				// numLines++;
			}
			bufferedReader.close();
			ArrayList<String> apiList = new ArrayList<String>();
			HashMap<String, Integer> apiCountMap = new HashMap<String, Integer>();
			for (String str : inputDataList) {
				str = str.substring(str.indexOf(",") + 1);
				apiList.add(str);
				str = str.substring(0, str.indexOf(","));

				if (apiCountMap.containsKey(str)) {
					apiCountMap.put(str, apiCountMap.get(str) + 1);
				} else {
					apiCountMap.put(str, 1);
				}
			}
			Collections.reverse(apiList);
			TreeMap<String, String> sortedApiMap = new TreeMap<String, String>();
			for (String api : apiList) {
				sortedApiMap.put(api.substring(0, api.indexOf(",")),
						api.substring(api.indexOf(",") + 1));

			}
			Iterator<String> it = sortedApiMap.keySet().iterator();
			Set<String> outputApps = new TreeSet<String>();
			while (it.hasNext()) {
				String apiName = it.next();
				if (apiCountMap.get(apiName) > 1) {
					for (String str : inputDataList) {
						if (str.contains(apiName + ","
								+ sortedApiMap.get(apiName))) {
							outputApps.add(str.substring(0, str.indexOf(",")));
							break;
						}
					}

				}
			}
			PrintWriter output = new PrintWriter(new BufferedWriter(
					new FileWriter("output.txt")));
			Iterator<String> it1 = outputApps.iterator();
			while (it1.hasNext()) {
				output.write(it1.next());
			}

			output.close();
		} catch (IOException e) {
			System.out.println("IO error in input.txt or output.txt");
		}
	}
}
