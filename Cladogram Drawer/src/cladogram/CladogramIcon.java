package cladogram;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

public class CladogramIcon implements Icon {

	public CladogramIcon(int height, int width, String fName) {
		this.height = height;
		this.width = width;
		cladogram = new Cladogram(fName);
		leafCount = 0;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		
		//set space between y values to make it so they all fit on the screen
		int yStep = (height - 100) / (cladogram.getLeaves().size() - 1);
		//ensure the cladogram fits x wise since max tree height is leaves - 1
		int xStep = (width - 105) / (cladogram.getLeaves().size() - 1); 
		
		//picks initial leaf then draws entire tree by going up the tree
		Taxon init = cladogram.getInitialTaxon(1);
		while (init != null) {
			draw(init, g2, xStep, yStep);
			init = init.getParent();
		}
		leafCount= 0;
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
				t.setY(50 + yStep * leafCount);
				leafCount++;
			}
		}
		else {
			//draws all the children first
			for(Taxon c : t.getChildren()) {
				draw(c, g, yStep, xStep);
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
				g.drawLine(t.getX()  + (int)r.getWidth(), t.getY() - fm.getAscent() / 2, c.getX(), c.getY() - fm.getAscent() / 2);
			}
			
		}
		//draw the highlight then the text itself
		g.setColor(Color.WHITE);
		g.fillRect(t.getX(), t.getY() - fm.getAscent(), (int)r.getWidth(), (int)r.getHeight());
		g.setColor(Color.BLACK);
		g.drawString(t.getName(), t.getX(), t.getY());
		t.setDrawn(true);
	}

	private int height;
	private int width;
	private Cladogram cladogram;
	private int leafCount;
}
