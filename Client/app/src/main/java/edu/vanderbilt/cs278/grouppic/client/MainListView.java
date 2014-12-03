package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.vanderbilt.cs278.grouppic.repository.Picture;

public class MainListView extends Activity {

    @InjectView(R.id.mainListView)
    protected ListView picturesList_;

    ArrayList<Picture> pics;
    ArrayList<String> pictures;
    ArrayAdapter<String> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        ButterKnife.inject(this);

        pictures = new ArrayList<String>();
        pics = new ArrayList<Picture>();

        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pictures);

        picturesList_.setAdapter(listViewAdapter);

        getPicturesList();

        picturesList_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Picture picture = pics.get(i);
                Intent intent = new Intent(getBaseContext(), PictureDetailActivity.class);
                intent.putExtra("picture_id", picture.getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.new_picture) {
            Intent intent = new Intent(getBaseContext(), PicturePostActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
                        pics.add(p);
                        pictures.add(p.getSender());
                        listViewAdapter.notifyDataSetChanged();
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
