package edu.vanderbilt.cs278.grouppic.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Taylor on 11/17/2014.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mTitles = {
            "test","test","test","test","test","test","test","test","test","test",
            "test","test","test","test","test","test","test","test","test","test"
    };

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public ImageAdapter(Context c, String[] titles, Integer[] imageIds) {
        mContext = c;
        this.mTitles = titles;
        this.mThumbIds = imageIds;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(mContext); //TODO rename
            grid = inflater.inflate(R.layout.list_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.userNameView);
            ImageView imageView = (ImageView)grid.findViewById(R.id.thumbnailView);
            textView.setText(mTitles[position]);
            imageView.setImageResource(mThumbIds[position]);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }

}
