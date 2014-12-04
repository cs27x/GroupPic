package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import edu.vanderbilt.cs278.grouppic.repository.Picture;

/**
 * Created by Taylor on 11/17/2014.
 */
public class MainNewsFeedActivity extends Activity {

    private ImageAdapter imageAdapter_;
    private ArrayList<Picture> pictures_;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news_feed);

        ListView listview = (ListView) findViewById(R.id.feedview);

        imageAdapter_ = new ImageAdapter(this);
        pictures_ = new ArrayList<Picture>();
        imageAdapter_.setPictures(pictures_);

        listview.setAdapter(imageAdapter_);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PicturePostActivity.class);
                startActivity(intent);
            }
        });

        getPicturesList();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainNewsFeedActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPicturesList() {
        final PictureSvcApi svc = PictureSvc.getOrShowLogin(this);

        if (svc != null) {
            CallableTask.invoke(new Callable<Collection<Picture>>() {
                @Override
                public Collection<Picture> call() throws Exception {
                    return svc.getPictureList();
                }
            }, new TaskCallback<Collection<Picture>>() {
                @Override
                public void success(Collection<Picture> result) {
                    Log.d("ADD", "Retrieved Pictures " + result.size());
                    for (Picture p : result) {
                        Log.d("Add", "Picture Added to listView " + p.getId());
                        pictures_.add(p);
                        imageAdapter_.notifyDataSetChanged();
                    }
                }

                @Override
                public void error(Exception e) {
                    Log.e("ERROR", "Did not successfully get picture list");
                }
            });
        }
    }

}
