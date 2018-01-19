package ca.ualberta.cs.lonelytwitter;
/*
Reference from f15monday from github, and lab2
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	//private EditText bodyText;
	//private ListView oldTweetsList;

	//public Button getSaveButton(){return saveButton;}
	//private Button saveButton;
	private LonelyTwitterActivity activity = this;

	public Button getSaveButton() {return saveButton;}

	private Button saveButton;

	public EditText getBodyText() {
		return bodyText;
	}

	private EditText bodyText;

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	private ListView oldTweetsList;

	private CheckBox impch;

	private TextView Hello;

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;
	//
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		impch = (CheckBox) findViewById(R.id.imp);
		Hello = (TextView) findViewById(R.id.textView2);

		//NormalTweet normalTweet = new NormalTweet("");
		//normalTweet.setMessage("Hello World!");

		//ImportantTweet impTweet = new ImportantTweet("");
		//impTweet.setMessage("Hello World!");

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString(); //controller
				if (impch.isChecked()) {
					tweets.add(new ImportantTweet(text));
					//Hello.setText("Important message");
				}
				else {
					tweets.add(new NormalTweet(text)); // controller
					//Hello.setText("Normal message");
				}
				//tweets.add(new ImportantTweet(text));
				adapter.notifyDataSetChanged(); // view
				saveInFile(); // model
			}

			/*public void onClick(View v) {
				setResult(RESULT_OK);
				String message = bodyText.getText().toString();
				//
				tweets.add(new NormalTweet(message));
				adapter.notifyDataSetChanged();
				saveInFile();
				//saveInFile(message, new Date(System.currentTimeMillis()));
				//finish();*/
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);
		//String[] tweets = loadFromFile();
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				//R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		//ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			/*String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}*/

			Gson gson =new Gson();
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
			tweets = gson.fromJson(in, listType);
		} catch (FileNotFoundException e) {
			tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		/*} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets.toArray(new String[tweets.size()]);*/
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(tweets, writer);
			writer.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		/*try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());
			//changed!!!!!
			fos.write(new String("\n")
					.getBytes());

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}