package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import edu.vanderbilt.cs278.grouppic.client.R;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import edu.vanderbilt.cs278.grouppic.repository.Picture;

public class PictureAdapter extends BaseAdapter implements Filterable {

    private String TAG = getClass().getSimpleName();

    private static PictureAdapter instance = null;
    private LayoutInflater mInflate;
    private Context mContext;
    private Activity parentActivity;
    private ArrayList<Picture> mData;
    private PictureFilter PictureFilter;
    ArrayList<Picture> filteredPictures;

    public ArrayList<String> categories = new ArrayList<String>();

    public static PictureAdapter getInstance(Context context,
                                           Activity parentActivity) {
        if (instance == null)
            instance = new PictureAdapter(context, parentActivity);
        return instance;
    }

    protected PictureAdapter(Context context, Activity parentActivity) {
        this.mContext = context;
        this.parentActivity = parentActivity;
        mInflate = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mData = new ArrayList<Picture>();
        getFilter();
        loadDataFromDatabase(true);
    }

    public void loadDataFromDatabase(boolean update_view) {
        mData.clear();
        // Need database to handle the Picture id -- discuss
        // Start Pictures at id 0?

        //TODO finish this

        ArrayList<Picture> returned = null;
        Exception e = null;

        if (e == null) {
            mData.addAll(returned);
            notifyDataSetChanged();
        } else {
            Log.e("PictureList", "Error: " + e.getMessage());
        }

        if(update_view)
            this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Picture getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId(); //TODO
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = this.mInflate.inflate(R.layout.list_picture_layout, parent, false);
        }
        Picture curPicture = getItem(position);
        ((TextView)convertView.findViewById(R.id.picture_sender)).setText("Sender: " + curPicture.getSender());
        ((TextView)convertView.findViewById(R.id.picture_date)).setText("Date: " + curPicture.getDate());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(PictureFilter==null)
            PictureFilter = new PictureFilter();
        return PictureFilter;
    }

    private class PictureFilter extends Filter {

        @Override
        protected android.widget.Filter.FilterResults performFiltering(CharSequence constraint) {
            Log.d(this.getClass().getSimpleName(), "Filtering Pictures.");
            FilterResults result = new FilterResults();
            if(constraint.length() == 0) {
                result.values = mData;
                result.count = mData.size();
            } else {
                filteredPictures = new ArrayList<Picture>(mData);
                String constraint_str = constraint.toString();
                String[] filterTypeAndValue = constraint_str.split("\\|");
                String filterType = filterTypeAndValue[0];
                String filterValue =  filterTypeAndValue[1];

                if (filterType.equals("By Date")) {
                    filteredPictures.clear();
                    filteredPictures = mData;
                    Collections.sort(filteredPictures);
                } else if (filterType.equals("By Sender")) {
                    filteredPictures.clear();
                    for(Picture p : mData) {
                        if(p.getSender().equals(filterValue))
                            filteredPictures.add(p);
                    }
                } else if (filterType.equals("Reset Filters")) {
                    loadDataFromDatabase(false);
                    filteredPictures = new ArrayList<Picture>(mData);
                }
                result.values = filteredPictures;
                result.count = filteredPictures.size();
            }
            return result;
        }

        // I know I'm going to have an ArrayList of Pictures so supressing warning
        // rather than dealing with an ArrayList<?>
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (ArrayList<Picture>) results.values;
            notifyDataSetChanged();
        }
    }
}
