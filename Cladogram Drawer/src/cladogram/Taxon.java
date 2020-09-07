package cladogram;

import java.util.ArrayList;

/**
 * 
 * Class representing a taxon for the cladogram. 
 *
 */
public class Taxon {

	/**
	 * Constructor to initialize taxon with name
	 * @param name name of the taxon
	 */
	public Taxon(String name) {
		this.name = name;
		parent = null;
		children = new ArrayList<Taxon>();
	}
	
	/**
	 * Sets children to contents of given array list
	 * @param children ArayList of children taxa
	 */
	public void setChildren(ArrayList<Taxon> children) {
		for (Taxon c : children) {
			c.setParent(this);
			this.children.add(c);
		}
	}
	
	/**
	 * returns children list
	 * @return list of children taxa
	 */
	public ArrayList<Taxon> getChildren(){
		return children;
	}
	
	/**
	 * sets name to given String
	 * @param name new name of taxon
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns name of taxon
	 * @return name of taxon
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets parent to given taxon
	 * @param parent parent taxon of this taxon
	 */
	public void setParent(Taxon parent) {
		this.parent = parent;
	}
	
	/**
	 * return parent of taxon
	 * @return parent taxon
	 */
	public Taxon getParent() {
		return parent;
	}
	
	private String name;
	private Taxon parent;
	private ArrayList<Taxon> children;
}
