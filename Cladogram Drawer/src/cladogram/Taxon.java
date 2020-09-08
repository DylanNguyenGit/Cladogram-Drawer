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
		x = 0;
		y = 0;
		isDrawn = false;
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
	public void setName(String name){
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
	public void setParent(Taxon parent){
		this.parent = parent;
	}
	
	/**
	 * return parent of taxon
	 * @return parent taxon
	 */
	public Taxon getParent(){
		return parent;
	}
	
	/**
	 * sets x coordinate to given value
	 * @param x new x coordinate
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Returns x coordinate
	 * @return x coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * sets y coordinate to given int
	 * @param y new y coordinate
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * returns y coordinate
	 * @return y coordinate
	 */
	public int getY(){
		return y;
	}
	
	public void setDrawn(Boolean drawn) {
		isDrawn = drawn;
	}
	
	public boolean isDrawn() {
		return isDrawn;
	}
	
	private String name;
	private Taxon parent;
	private ArrayList<Taxon> children;
	private int x;
	private int y;
	private boolean isDrawn;
}
