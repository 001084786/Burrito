package au.edu.tafesa.itstudies.songcatalog.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the data for a Song Catalog (catalog) and for the selection of songs made (cart)
 * @author sruiz
 *
 */
public class SongShoppingCartModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String INTENT_IDENTIFIER = "SHOPPING_CART_MODEL";
	private List<Song> catalog;

	public SongShoppingCartModel() {
		this(new ArrayList<Song>());
	}
	
	public SongShoppingCartModel(List<Song> catalog) {
		this.catalog = catalog;
	}
	
	public List<Song> getCatalog() {
		return catalog;
	}

	/**
	 * Updates the List of songs in the model, known as the catalog, this replace the current list
	 * @param catalog the new list of Songs for the catalog
	 */
	public void setCatalog(List<Song> catalog) {
		this.catalog = catalog;
	}

    /**
     *  Sets the selected flag on song i to indicate it has been slelected for the shopping cart
     * @param i Position in list of song to be marked as selected or not
     * @param selectedChoice Either true for selected or false for not selected
     */
	public void setSelectedForCart(int i, boolean selectedChoice){
		if (i<this.catalog.size()){
			catalog.get(i).setSelected(selectedChoice);
		}
	}

	public List<Song> getListOfSelectedSongs(){
		List<Song> selectedSongs = new ArrayList<Song>();
		for(Song b:this.catalog){
			if (b.isSelected()){
				selectedSongs.add(b);
			}
		}
		return selectedSongs;
	}

    public int getNumSongsSelected(){
        int  numSongsSelecetd;
        numSongsSelecetd=0;
        for(Song b:this.catalog){
            if (b.isSelected()){
                numSongsSelecetd++;
            }
        }
        return numSongsSelecetd;
    }

	/**
	 * A list of dummy Song data to use for testing
	 * @return
	 */
	public static List<Song> getDummyCatalogData(){
		ArrayList<Song> dummyData = new ArrayList<Song>();
		dummyData = new ArrayList<Song>();
		dummyData.add(new Song("From Now On", 399));
		dummyData.add(new Song("Xanadu", 310));
		dummyData.add(new Song("Live and Let Die", 169));
		dummyData.add(new Song("Surfer Girl", 340));
		dummyData.add(new Song("Let it be",100));
		return dummyData;
	}
}
