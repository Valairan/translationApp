package com.valairan.mobiledata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    final String LANGUAGES_URL = "https://libretranslate.de/languages";
    final String TRANSLATE_URL = "https://libretranslate.de/translate";

    String languagesListJSON = new String();

    DatabaseManager dm;

    public TextView translatedText;
    public RecyclerView recentTranslations;
    public RecyclerView.Adapter recentsAdapter;
    public RecyclerView.LayoutManager layoutManager;

    public EditText toBeTranslated;

    public Spinner translateFrom;
    public Spinner translateTo;

    public Button translateButton;

    public List<String> languageNames = new ArrayList<String>();
    public List<String> languageCodes = new ArrayList<String>();
    public List<RecentTranslation> recents =  new ArrayList<RecentTranslation>();
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translateFrom = findViewById(R.id.fromLanguage);
        translateTo = findViewById(R.id.toLanguage);

        translatedText = findViewById(R.id.translatedText);
        recentTranslations = findViewById(R.id.recentTranslations);
        recentTranslations.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recentsAdapter =  new RecentsAdapter((ArrayList<RecentTranslation>) recents);
        recentTranslations.setLayoutManager(layoutManager);
        recentTranslations.setAdapter(recentsAdapter);


        toBeTranslated = findViewById(R.id.translationText);

        translateButton = findViewById(R.id.translateButton);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate();
            }
        });

        dm = new DatabaseManager(this);

        populateSpinner();
        populateListView();
    }

    public void populateSpinner() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(LANGUAGES_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String mResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray array = new JSONArray(mResponse);
                                for (int i = 0; i < array.length(); i++) {
                                    languageNames.add(array.getJSONObject(i).getString("name"));
                                    languageCodes.add(array.getJSONObject(i).getString("code"));
                                }
                                adapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, languageNames);
                                translateFrom.setAdapter(adapter);
                                translateTo.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });

    }

    public void translate() {
        String cleaned = toBeTranslated.getText().toString().trim();

        if (cleaned.isEmpty()) {
            Toast.makeText(this, "Enter text to translate...", Toast.LENGTH_SHORT).show();
            return;

        }

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder().add("q", cleaned)
                .add("source", languageCodes.get(translateFrom.getSelectedItemPosition()).toString())
                .add("target", languageCodes.get(translateTo.getSelectedItemPosition()).toString())
                .build();

        Request request = new Request.Builder()
                .url(TRANSLATE_URL)
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String mResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject result = new JSONObject(mResponse);
                                String resultText = result.getString("translatedText");
                                translatedText.setText(resultText);
                                dm.dao.save(new RecentTranslation(
                                        languageNames.get(translateFrom.getSelectedItemPosition()).toString(),
                                        languageNames.get(translateTo.getSelectedItemPosition()).toString(),cleaned, resultText));
                                populateListView();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });

    }

    public void populateListView() {
        recents.clear();
        recents.addAll( dm.dao.getAll());
        //Log.e("---------->",dm.dao.getAll().toString());
        recentsAdapter.notifyDataSetChanged();
    }

}