package comp3111G15;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * The Statistics shows table for students information
 * @author LiChunTak
 *
 */
public class Statistics {
	
	private final SimpleStringProperty index;
	private final SimpleStringProperty entry;
	private final SimpleStringProperty value;

	/**
	 * Constructor of Statistics
	 * @param index index
	 * @param fName first name
	 * @param lName last name
	 */
	public Statistics(int index, String fName, String lName) {
		this.index = new SimpleStringProperty(Integer.toString(index));
		this.entry = new SimpleStringProperty(fName);
		this.value = new SimpleStringProperty(lName);
	}
	
	/**
	 * Get index of Statistics in a string
	 * @return String
	 */
	public String getIndex() {
		return index.get();
	}

	/**
	 * Set index of Statistics
	 * @param val index
	 */
	public void setIndex(String val) {
		index.set(val);
	}

	/**
	 * Get entry of Statistics in a string
	 * @return String
	 */
	public String getEntry() {
		return entry.get();
	}

	/**
	 * Set entry of Statistics
	 * @param val entry
	 */
	public void setEntry(String val) {
		entry.set(val);
	}

	/**
	 * Get value of Statistics in a string
	 * @return String
	 */
	public String getValue() {
		return value.get();
	}

	/**
	 * Set value of Statistics
	 * @param val value
	 */
	public void setValue(String val) {
		value.set(val);
	}

}
