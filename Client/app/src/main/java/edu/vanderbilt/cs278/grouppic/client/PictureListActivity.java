package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.vanderbilt.cs278.grouppic.client.R;

public class PictureListActivity extends Activity implements PictureListFragment.Callbacks {

        public static boolean filtered = false;
        private Button addPictureButton;
        private Button filterPictureButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_picture_list);
            addPictureButton = (Button)findViewById(R.id.add_picture_button);
            filterPictureButton = (Button)findViewById(R.id.filter_picture_button);
            addClickListeners();
        }

    private void addClickListeners() {
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), PicturePostActivity.class);
                //startActivity(intent);
            }
        });

        filterPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtered = true;
                Intent intent = new Intent(v.getContext(), FilterPictureListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Callback method from {@link PictureListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int index) {
            //Intent detailIntent = new Intent(this, PictureDetailActivity.class);
            //startActivity(detailIntent);
    }
}