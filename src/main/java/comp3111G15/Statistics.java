package comp3111G15;

import javafx.beans.property.SimpleStringProperty;

public class Statistics {
	
	private final SimpleStringProperty index;
	private final SimpleStringProperty entry;
	private final SimpleStringProperty value;

	public Statistics(int index, String fName, String lName) {
		this.index = new SimpleStringProperty(Integer.toString(index));
		this.entry = new SimpleStringProperty(fName);
		this.value = new SimpleStringProperty(lName);
	}
	
	public String getIndex() {
		return index.get();
	}

	public void setIndex(String val) {
		index.set(val);
	}

	public String getEntry() {
		return entry.get();
	}

	public void setEntry(String val) {
		entry.set(val);
	}

	public String getValue() {
		return value.get();
	}

	public void setValue(String val) {
		value.set(val);
	}

}
