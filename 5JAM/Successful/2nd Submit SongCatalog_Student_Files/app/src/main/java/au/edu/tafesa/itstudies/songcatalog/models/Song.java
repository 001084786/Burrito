package au.edu.tafesa.itstudies.songcatalog.models;

import java.io.Serializable;

public class Song implements Serializable {

    public static final int DEFAULT_ID = -1;
    public static final boolean DEFAULT_SELECTED = false;
	private static final long serialVersionUID = 1L;

    private int id;
	private String title;
	private double length;
	private boolean selected;

	public Song(int id, String title, double length, boolean selected) {
		this.id =  id;
	    this.title = title;
		this.length = length;
		this.selected = selected;
	}

    public Song(int id, String title, double length) {
        this(id,title, length,DEFAULT_SELECTED);
    }

    public Song(String title, double length) {
        this(DEFAULT_ID,title, length,DEFAULT_SELECTED);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(super.toString());
        builder.append(", id=");
        builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", length=");
		builder.append(length);
		builder.append(" secs");
		builder.append(", selected=");
		builder.append(selected);
		builder.append("]");
		return builder.toString();
	}

}
