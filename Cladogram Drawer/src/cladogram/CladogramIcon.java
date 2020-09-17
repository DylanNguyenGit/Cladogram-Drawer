package cladogram;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 * 
 * The class responsible for drawing and being the icon for the swing ui
 *
 */
public class CladogramIcon implements Icon {

	public CladogramIcon(int height, int width, File file) {
		this.height = height;
		this.width = width;
		cladogram = new Cladogram(file);
		leafCount = 0;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		int yStep = 0;;
		int xStep = 0;
		ArrayList<Taxon> leaves = cladogram.getLeaves();
		int lSize = leaves.size();
		int tHeight = cladogram.getTreeHeight();
		
		if(lSize > 1) {
			//set space between y values to make it so they all fit on the screen
			yStep = (height - 40) / (lSize - 1);
		}
		if(tHeight > 1) {
			//ensure the cladogram fits x wise since max tree height is leaves - 1
			xStep = (width - 105) / (tHeight - 1); 
		}
		
		//draws all elements in cladogram
		for(Taxon t : leaves) {
			while (t != null) {
				draw(t, g2, xStep, yStep);
				t = t.getParent();
			}
		}
		
		//reset counter and isdrawn for potential additional draws
		leafCount= 0;
		reset();
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int getIconHeight() {
		return height;
	}
	
	/**
	 * returns cladogram represetned by icon
	 * @return cladogram represented by icon
	 */
	public Cladogram getCladogram() {
		return cladogram;
	}
	
	/**
	 * Method that draws a taxon and its children 
	 * @param t the taxon to be drawn
	 * @param g the graphical context
	 * @param xStep the uniform distance in x between nodes
	 * @param yStep the uniform distance in y between leaves
	 */
	public void draw(Taxon t, Graphics2D g, int xStep, int yStep) {
		//getting font and text size info
		String name = t.getName();
		int x = t.getX();
		int y = t.getY();
		FontMetrics fm = g.getFontMetrics();
		int a = fm.getAscent();
		Rectangle2D r = fm.getStringBounds(name, g);
		int rWidth = (int) r.getWidth();
		int rHeight = (int) r.getHeight();
		
		// does special action if drawing a leaf
		if(t.getChildren().size() == 0) {
			//leaves are all aligned on the right and get drawn in a column
			if(!t.isDrawn()) {
				t.setX(width - 100);
				t.setY(20 + yStep * leafCount);
				t.setDrawn(true);
				leafCount++;
			}
		}
		else {
			ArrayList<Taxon> children = t.getChildren();
			
			//draws all the children first
			for(Taxon c : children) {
				draw(c, g, xStep, yStep);
			}
				
			if(!t.isDrawn()) {
				//x needs to be x step away from children while y is in the middle
				int minX = Integer.MAX_VALUE;
				int average = 0;
				for(Taxon c : children) {
					average += c.getY();
					minX = Math.min(minX, c.getX());
				}
				t.setX(minX - xStep);
				t.setY(average / children.size());
				x = t.getX();
				y = t.getY();
			}
				
			//draw connecting lines
			for(Taxon c : t.getChildren()) {
				g.drawLine(x  + rWidth, c.getY() - a / 2, c.getX(), c.getY() - a / 2);
				g.drawLine(x  + rWidth, y - a / 2, x  + rWidth, c.getY() - a / 2);
			}
			
		}
		//draw the highlight then the text itself
		g.setColor(Color.WHITE);
		g.fillRect(t.getX(), t.getY() - a, rWidth, rHeight);
		g.setColor(Color.BLACK);
		g.drawString(name, t.getX(), t.getY());
		t.setDrawn(true);
	}
	
	/**
	 * Resets all taxa to not drawn
	 */
	public void reset() {
		for(Taxon t : cladogram.getLeaves()) {
			t.setDrawn(false);
		}
		for(Taxon t : cladogram.getNodes()) {
			t.setDrawn(false);
		}
	}

	private int height;
	private int width;
	private Cladogram cladogram;
	private int leafCount;
}
