package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MapSum {
	public static void main(String[] args) throws IOException {
		HashMap<String, UserPjtInfo> UserPjtInfos = getUsers("./DS_Sample2.csv");

		for (String key : UserPjtInfos.keySet()) {
			UserPjtInfo user = UserPjtInfos.get(key);
			System.out.println(user.getNum()+ " " + user.getName() + " " + ((int)(user.getApjt() * 100)/100.0) + " " + ((int)(user.getBpjt() * 100)/100.0) + " " + ((int)(user.getCpjt() * 100)/100.0));
		}
	}

	private static HashMap<String, UserPjtInfo> getUsers(String fileNm)
			throws IOException {
		HashMap<String, UserPjtInfo> users = new HashMap<String, UserPjtInfo>();

		InputStream inputStream = new FileInputStream(fileNm);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			String num =  tokens[1];
			double apjt = Double.parseDouble(tokens[3]);
			double bpjt = Double.parseDouble(tokens[4]);
			double cpjt = Double.parseDouble(tokens[5]);

			if (users.containsKey(num)) {
				UserPjtInfo user = users.get(num);
				users.put(num, new UserPjtInfo(num, tokens[2], user.getApjt() + apjt, user.getBpjt() + bpjt, user.getCpjt() + cpjt));
			} else {
				users.put(num, new UserPjtInfo(num, tokens[2], apjt, bpjt, cpjt));
			}
		}

		return users;
	}
}
