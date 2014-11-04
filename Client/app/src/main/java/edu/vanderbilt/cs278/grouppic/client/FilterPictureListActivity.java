package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class FilterPictureListActivity extends Activity {
    private String TAG = getClass().getSimpleName();

    private PictureAdapter adapter;
    private Spinner filterOptions;
    private LinearLayout senderOptionView;
    private EditText senderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_picture_list);
        adapter = PictureAdapter.getInstance(this, FilterPictureListActivity.this);
        filterOptions = (Spinner) this.findViewById(R.id.filter_option);
        addItemSelectedListenerOnFilterOptions();
        senderOptionView = (LinearLayout) this.findViewById(R.id.sender_option);
        senderText = (EditText)this.findViewById(R.id.sender_text);
    }

    private void addItemSelectedListenerOnFilterOptions() {
        filterOptions.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        senderOptionView.setVisibility(View.GONE);
                        String selectedFilter = filterOptions.getSelectedItem().toString();
                        if (selectedFilter.equals("By Sender")) {
                            senderOptionView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //Log.d(this.getClass().getSimpleName(), "No item selected for filtering.");
                    }
                });
    }

    public void filterPicturesDoneButtonClick(View view) {
        boolean isDialog = false;

        String filter = filterOptions.getSelectedItem().toString() + "|";
        if (filter.equals("By Sender|")) {
            filter += senderText.toString();
        } else {
            filter += "NULL";
        }
        adapter.getFilter().filter(filter);
        if (!isDialog)
            finish();
    }
}