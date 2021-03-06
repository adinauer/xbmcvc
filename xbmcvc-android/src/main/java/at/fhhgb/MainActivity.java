package at.fhhgb;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import at.fhhgb.command.Command;
import at.fhhgb.xbmc.PlayerNotRunningException;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		Button b = (Button) this.findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.speak(v);
			}
		});
		Button b2 = (Button) this.findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);
				startActivity(myIntent);
			}
		});
		this.checkVoiceRecognition();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public void checkVoiceRecognition() {
		// Check if voice recognition is present
		PackageManager pm = this.getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0) {
			Toast.makeText(this, "Voice recognizer not present", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "VR available", Toast.LENGTH_SHORT).show();
		}
	}

	public void speak(View view) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getClass().getPackage().getName());
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US");

		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up!");
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

		int noOfMatches = 10;
		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, noOfMatches);
		this.startActivityForResult(intent, 1001);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1001) {
			if (resultCode == Activity.RESULT_OK) {

				final ArrayList<String> textMatchList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				if (!textMatchList.isEmpty()) {
					if (textMatchList.get(0).contains("search")) {

						String searchQuery = textMatchList.get(0);
						searchQuery = searchQuery.replace("search", "");
						Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
						search.putExtra(SearchManager.QUERY, searchQuery);
						this.startActivity(search);
					} else {

						TextView t = (TextView) this.findViewById(R.id.editText1);
						if (t != null) {
							t.setText("");
							for (int i = 0; i < textMatchList.size(); i++) {
								t.append((i + 1) + ".) " + textMatchList.get(i) + "\n");
							}
						} else {
							String str = "";
							for (int i = 0; i < textMatchList.size(); i++)
								str += (i + 1) + ".) " + textMatchList.get(i) + "\n";

							showToastMessage(str);
						}

						this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try {
									CommandTranslator locator = new CommandTranslator(new CommandRepositoryFactory().create(
											Settings.getInstance().get("IP"), Settings.getInstance().get("PORT"), Settings
													.getInstance().get("username"), Settings.getInstance().get("password")));
									if (Settings.getInstance().get("SimonSays").equals("true")) {
										locator.enableSimonSaysMode();
									}
									Command command = locator.locateFirstOf(textMatchList);
									try {
										command.execute();
									} catch (PlayerNotRunningException e) {
										MainActivity.this.showToastMessage("PlayerNotRunningException." + e.getMessage());
									} catch (RuntimeException e) {
										MainActivity.this.showToastMessage("RuntimeException." + e.getMessage());
										StringWriter sw = new StringWriter();
										PrintWriter pw = new PrintWriter(sw);
										e.printStackTrace(pw);
										String stacktrace = sw.toString();
										MainActivity.this.showToastMessage(stacktrace.substring(0, 500));

									} catch (Exception e) {
										MainActivity.this.showToastMessage("Execute failed.");
									}
								} catch (Exception e) {
									MainActivity.this.showToastMessage("No command could be found");
									// MainActivity.this.showToastMessage(e.getMessage());
								}
							}
						});
					}
				}
			} else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
				this.showToastMessage("Audio Error");
			} else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
				this.showToastMessage("Client Error");
			} else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
				this.showToastMessage("Network Error");
			} else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
				this.showToastMessage("No Match");
			} else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
				this.showToastMessage("Server Error");
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
