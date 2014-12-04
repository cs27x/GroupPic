package edu.vanderbilt.cs278.grouppic.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

import java.util.ArrayList;

import edu.vanderbilt.cs278.grouppic.repository.Picture;

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

    private ArrayList<Picture> pictures;

    public ImageAdapter(Context c) {
        pictures = new ArrayList<Picture>();
        mContext = c;
    }

    public ImageAdapter(Context c, String[] titles, Integer[] imageIds) {
        pictures = new ArrayList<Picture>();
        mContext = c;
        this.mTitles = titles;
        this.mThumbIds = imageIds;
    }

    public int getCount() {
        return pictures.size();
    }

    public Object getItem(int position) {
        return pictures.get(position);
    }

    public long getItemId(int position) {
        return pictures.get(position).getId();
    }

    public void setPictures(ArrayList<Picture> p) {
        pictures = p;
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
           // textView.setText(mTitles[position]);
           // imageView.setImageResource(mThumbIds[position]);
            if (position < pictures.size()) {
                Picture p = pictures.get(position);
                textView.setText(p.getSender());
                try {
                    byte[] img = p.imageToByteArray();
                    Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
                    imageView.setImageBitmap(bmp);
                } catch (Base64DecodingException e) {
                    Log.e(this.getClass().getName(), e.toString());
                }
            }
        } else {
            grid = (View) convertView;
        }
        return grid;
    }

}
