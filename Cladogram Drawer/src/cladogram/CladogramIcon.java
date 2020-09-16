package cladogram;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

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
		
		if(cladogram.getLeaves().size() > 1) {
			//set space between y values to make it so they all fit on the screen
			yStep = (height - 40) / (cladogram.getLeaves().size() - 1);
		}
		if(cladogram.getTreeHeight() > 1) {
			//ensure the cladogram fits x wise since max tree height is leaves - 1
			xStep = (width - 105) / (cladogram.getTreeHeight() - 1); 
		}
		
		//draws all elements in cladogram
		for(Taxon t : cladogram.getLeaves()) {
			while (t != null) {
				draw(t, g2, xStep, yStep);
				t = t.getParent();
			}
		}
		
		//reset counter and isdrawn for potential additional draws
		leafCount= 0;
		reset();
	}

	@Override
	public int getIconWidth() {
		return width;
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
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(t.getName(), g);
		
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
			//draws all the children first
			for(Taxon c : t.getChildren()) {
				draw(c, g, xStep, yStep);
			}
				
			if(!t.isDrawn()) {
				//x needs to be x step away from children while y is in the middle
				int minX = 9999;
				int average = 0;
				for(Taxon c : t.getChildren()) {
					average += c.getY();
					minX = Math.min(minX, c.getX());
				}
				t.setX(minX - xStep);
				t.setY(average / t.getChildren().size());
			}
				
			//draw connecting lines
			for(Taxon c : t.getChildren()) {
				g.drawLine(t.getX()  + (int)r.getWidth(), c.getY() - fm.getAscent() / 2, c.getX(), c.getY() - fm.getAscent() / 2);
				g.drawLine(t.getX()  + (int)r.getWidth(), t.getY() - fm.getAscent() / 2, t.getX()  + (int)r.getWidth(), c.getY() - fm.getAscent() / 2);
			}
			
		}
		//draw the highlight then the text itself
		g.setColor(Color.WHITE);
		g.fillRect(t.getX(), t.getY() - fm.getAscent(), (int)r.getWidth(), (int)r.getHeight());
		g.setColor(Color.BLACK);
		g.drawString(t.getName(), t.getX(), t.getY());
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
