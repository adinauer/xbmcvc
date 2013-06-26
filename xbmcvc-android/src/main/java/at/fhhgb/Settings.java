package at.fhhgb;

import java.util.HashMap;

public class Settings {
	private static Settings s = null;
	private HashMap<String, String> map = new HashMap<String, String>();

	public void set(String key, String value) {
		map.put(key, value);
	}

	public String get(String key) {
		return map.get(key);
	}

	// ((Switch) MainActivity.this.findViewById(R.id.switch1)).isChecked()

	private Settings() {
		set("IP", "81.10.163.18");
		set("SimonSays", "false");
		set("PORT", "8082");
		// set("username", "user1");
		// set("password", "pass1");
		set("username", "");
		set("password", "");

	}

	public static Settings getInstance() {
		if (s == null) {
			s = new Settings();
		}
		return s;
	}
}
