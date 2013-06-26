package at.fhhgb;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.

		// editText2 = IP
		Settings s = Settings.getInstance();

		TextView ip = ((TextView) findViewById(R.id.editText2));
		TextView username = ((TextView) findViewById(R.id.editText3));
		TextView password = ((TextView) findViewById(R.id.editText4));

		ip.setText(s.get("IP"));
		username.setText(s.get("username"));
		password.setText(s.get("password"));

		Button b1 = (Button) this.findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Settings s = Settings.getInstance();
				TextView ip = ((TextView) findViewById(R.id.editText2));
				TextView username = ((TextView) findViewById(R.id.editText3));
				TextView password = ((TextView) findViewById(R.id.editText4));

				s.set("IP", ip.getText().toString());
				s.set("username", username.getText().toString());
				s.set("password", password.getText().toString());
				Toast.makeText(SettingsActivity.this, "Settings saved.", Toast.LENGTH_SHORT).show();
			}
		});
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
