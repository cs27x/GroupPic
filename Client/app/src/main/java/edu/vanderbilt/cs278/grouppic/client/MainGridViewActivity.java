package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Taylor on 11/17/2014.
 */
public class MainGridViewActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid_view); //TODO rename

        ListView listview = (ListView) findViewById(R.id.gridview);
        listview.setAdapter(new ImageAdapter(this));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainGridViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
