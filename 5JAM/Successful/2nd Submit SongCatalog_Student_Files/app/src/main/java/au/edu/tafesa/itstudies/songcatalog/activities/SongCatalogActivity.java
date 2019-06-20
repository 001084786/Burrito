package au.edu.tafesa.itstudies.songcatalog.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.io.IOException;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import au.edu.tafesa.itstudies.songcatalog.DAO.SongShoppingCartDBDAO;
import au.edu.tafesa.itstudies.songcatalog.models.Song;
import au.edu.tafesa.itstudies.songcatalog.models.SongShoppingCartModel;
import au.edu.tafesa.itstudies.songcatalog.R;


/**
 * Displays a list of songs that can be added/removed to/from a shopping cart
 *
 * @author sruiz
 */
public class SongCatalogActivity extends Activity {

    private SongShoppingCartModel songShoppingCartModel;
    private ListView listViewCatalog;
    private Button btnViewCart;
    private Button btnDelLong;
    private SongShoppingCartDBDAO songShoppingCartDB;
    private boolean goingToChildActivity;

    //LIFE-CYCLE EVENTS
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

            Log.i("onCreate", "current object = " + this);
        goingToChildActivity = false;
        //Connect to DB
        songShoppingCartDB = SongShoppingCartDBDAO.ConnectToSongDB(this);

        //Create the model
        songShoppingCartModel = new SongShoppingCartModel();

        // Create the Catalog and display in the List using the model to drive teh list Adapter
        listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        listViewCatalog.setAdapter(new CatalogListViewAdapter(songShoppingCartModel));

        //Extract interface objects
        btnViewCart = (findViewById(R.id.ButtonViewCart));
        btnDelLong = (findViewById(R.id.ButtonDeleteLong));

        //Register the Event Handlers
        btnViewCart.setOnClickListener((new ButtonViewCartOnClickHandler()));
        btnDelLong.setOnClickListener((new ButtonDeleteLongOnClickHandler()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFromDB();
    }

    @Override
    protected void onPause() {
        saveToDB();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("onStop", "Entered");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //DO THE WORK METHODS
    private void saveToDB() {
        try {
            songShoppingCartDB.saveToDB(songShoppingCartModel);
        } catch (IOException e) {
            Toast.makeText(this, "Unable to save data. Problem with the Database.", Toast.LENGTH_LONG).show();
        }
    }

    private void loadFromDB() {

        try {
            songShoppingCartDB.loadFromDB(songShoppingCartModel);
            ((CatalogListViewAdapter) listViewCatalog.getAdapter()).notifyDataSetChanged();
        } catch (IOException e) {
            Toast.makeText(this, "Unable to load data. Problem with the DB.", Toast.LENGTH_LONG).show();
            //Use some dummy data (hard-coded in the model class - for testing only)
            songShoppingCartModel.setCatalog(SongShoppingCartModel.getDummyCatalogData());
        } catch (ClassNotFoundException e) {
            Toast.makeText(this, "Unable to load data. Problem with the DB.", Toast.LENGTH_LONG).show();
            //Use some dummy data (hard-coded in the model class - for testing only)
            songShoppingCartModel.setCatalog(SongShoppingCartModel.getDummyCatalogData());
        }

    }

    //View Adapters
    /**
     * Provides the details of how to display each row of the listViewCatalog and makes use of a List of songs
     * (which comes from the songShoppingCartModel) for the data.
     *
     * @author sruiz
     */
    class CatalogListViewAdapter extends BaseAdapter {

        public SongShoppingCartModel getTheData() {
            return theData;
        }

        public void setTheData(SongShoppingCartModel theData) {
            this.theData = theData;
        }

        private SongShoppingCartModel theData;

        public CatalogListViewAdapter(SongShoppingCartModel theData) {
            this.theData = theData;
        }

        public int getCount() {
            return theData.getCatalog().size();
        }

        public Object getItem(int position) {
            return theData.getCatalog().get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewItem rowViewComponents;

            // First time the convertView will be null and we will create it using the "song_list_item" layout
            if (convertView == null) {
                convertView = SongCatalogActivity.this.getLayoutInflater().inflate(R.layout.song_list_item, null);
                rowViewComponents = new ViewItem();
                //Set the components of rowViewComponent from the "song_list_item" layout
                rowViewComponents.textViewTitle = (TextView) convertView.findViewById(R.id.textViewItem);
                rowViewComponents.textViewLength = (TextView) convertView.findViewById(R.id.textViewLength);
                rowViewComponents.btnAdd = (Button) convertView.findViewById(R.id.btnAdd);
                rowViewComponents.btnRemove = (Button) convertView.findViewById(R.id.btnRemove);
                //Register the handlers for the buttons on the rowView
                rowViewComponents.btnAdd.setOnClickListener(new ButtonAddOnClickHandler());
                rowViewComponents.btnRemove.setOnClickListener(new ButtonRemoveOnClickHandler());
                // Ensure we save this in our convertView as a Tag
                convertView.setTag(rowViewComponents);
            } else {
                rowViewComponents = (ViewItem) convertView.getTag();
            }

            //Get the current song from the Model and set the rowView components appropriately
            // Remembering to adjust whether the Add/Remove buttons are enabled or disabled
            Song curSong = theData.getCatalog().get(position);
            rowViewComponents.textViewTitle.setText(curSong.getTitle());
            rowViewComponents.textViewLength.setText(curSong.getLength() + " secs");
            // Adjust whether the Add/Remove buttons are enabled or disabled according to the songs selected field
            if (curSong.isSelected()) {
                rowViewComponents.btnAdd.setEnabled(false);
                rowViewComponents.btnRemove.setEnabled(true);
            } else {
                rowViewComponents.btnAdd.setEnabled(true);
                rowViewComponents.btnRemove.setEnabled(false);
            }


            return convertView;
        }

        private class ViewItem {
            TextView textViewTitle;
            TextView textViewLength;
            Button btnAdd;
            Button btnRemove;
        }

    }

    //EVENT HANDLERS
    /**
     * Handles the Add song to cart button click by adding the song at the position in the models catalog to
     * the models cart.
     *
     * @author sruiz
     */
    class ButtonAddOnClickHandler implements OnClickListener {

        public void onClick(View v) {
            int position;
            View rowView;


            rowView = (View) v.getParent();
            position = listViewCatalog.getPositionForView(rowView);
            // Ask the model to set the song at this position to be selected=true
            // Remember to notify the listview if the data change so the view updates.
            songShoppingCartModel.setSelectedForCart(position, true);
            ((CatalogListViewAdapter) listViewCatalog.getAdapter()).notifyDataSetChanged();
        }

    }

    /**
     * Handles the Remove song from cart button click by removing the song at the position in the models catalog from
     * the models cart.
     *
     * @author sruiz
     */
    class ButtonRemoveOnClickHandler implements OnClickListener {

        public void onClick(View v) {
            int position;
            View rowView;

            rowView = (View) v.getParent();
            position = listViewCatalog.getPositionForView(rowView);
            // Ask the model to set the song at this position to be selected=false
            // Remember to notify the listview if the data change so the view updates.
            songShoppingCartModel.setSelectedForCart(position, false);
            ((CatalogListViewAdapter) listViewCatalog.getAdapter()).notifyDataSetChanged();
        }

    }

    /**
     * Handles the View Cart button click by using an explicit Intent to start the the song at the
     * SongShoppingCartActivity
     *
     * @author sruiz
     */
    class ButtonViewCartOnClickHandler implements OnClickListener {
        public void onClick(View v) {
            goingToChildActivity = true;
            // Use an Explicit Intent to start an Activity called SongShoppingCartActivity that will
            // display the songs in the cart. There is no return result from the Activity we are starting,
            // it is just a view of the songs in the cart.
            Intent viewShoppingCartIntent = new Intent(getBaseContext(), SongShoppingCartActivity.class);
            viewShoppingCartIntent.putExtra(SongShoppingCartModel.INTENT_IDENTIFIER, songShoppingCartModel);
            startActivity(viewShoppingCartIntent);
        }
    }

    class ButtonDeleteLongOnClickHandler implements OnClickListener {
        public void onClick(View v) {
            songShoppingCartDB.deleteLongSongs();
            loadFromDB();
            
        }
    }
 }
