package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Taylor on 11/17/2014.
 */
public class MainNewsFeedActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news_feed);

        ListView listview = (ListView) findViewById(R.id.feedview);
        listview.setAdapter(new ImageAdapter(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainNewsFeedActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
