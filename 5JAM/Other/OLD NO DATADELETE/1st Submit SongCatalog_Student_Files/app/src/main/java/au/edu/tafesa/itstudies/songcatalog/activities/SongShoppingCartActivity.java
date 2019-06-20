package au.edu.tafesa.itstudies.songcatalog.activities;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;

import au.edu.tafesa.itstudies.songcatalog.models.Song;
import au.edu.tafesa.itstudies.songcatalog.models.SongShoppingCartModel;

/**
 * Uses the Android provided ListActivity class to display the songs in the cart in the SongShoppingCartModel passed
 * in by the Intent
 * @author sruiz
 *
 */
public class SongShoppingCartActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SongShoppingCartModel songShoppingCartModel=null;
        Intent theIntent;
        //TODO
        // Retrieve the Intent and then the SongShoppingCartModel object passed in as bundled data ("Extra" data)
        // so we can pass that on to the ListActivities Adapter
        //...
        //...
        theIntent = this.getIntent();
        songShoppingCartModel = (SongShoppingCartModel) theIntent.getSerializableExtra(SongShoppingCartModel.INTENT_IDENTIFIER);


        // If there is no Model passed in we create a default Model, which will show no data.
        if (songShoppingCartModel == null) {
            songShoppingCartModel = new SongShoppingCartModel();
        }

        ArrayAdapter<Song> adapter;
		// Could just create the "selected" list by traversing the Catalogue and using the isSelected field - 
		// no need to maintain the cart list separately
//        adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songShoppingCartModel.getCart());
        adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songShoppingCartModel.getListOfSelectedSongs());

        setListAdapter(adapter);
    }

}
