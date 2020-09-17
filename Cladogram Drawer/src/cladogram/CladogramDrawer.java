package cladogram;

import java.awt.Dimension;
import java.io.File;
import javax.swing.*;

public class CladogramDrawer {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cladgram Drawer");
		CladogramDrawerLabel label = new CladogramDrawerLabel(800, 800);
		JMenuBar bar = new JMenuBar();
		
		//make File menu with save and open options
		JMenu menu = new JMenu("File");
		JMenuItem item = new JMenuItem("Open");
		item.addActionListener(label);
		item.setActionCommand("Open");
		menu.add(item);
		item = new JMenuItem("Save");
		item.addActionListener(label);
		item.setActionCommand("Save");
		menu.add(item);
		bar.add(menu);
		
		//make add manu with leaf and node options
		menu = new JMenu("Add");
		item = new JMenuItem("Leaf");
		item.addActionListener(label);
		item.setActionCommand("Leaf");
		menu.add(item);
		item = new JMenuItem("Node");
		item.addActionListener(label);
		item.setActionCommand("Node");
		menu.add(item);
		bar.add(menu);
		
		//make window menu with resize option
		menu = new JMenu("Window");
		item = new JMenuItem("Resize");
		item.addActionListener(label);
		item.setActionCommand("Resize");
		menu.add(item);
		bar.add(menu);
		
		//add components to frame
		frame.setJMenuBar(bar);
		JScrollPane scroll = new JScrollPane(label);
	    frame.add(scroll);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setSize(new Dimension(800, 800));
	    frame.setVisible(true);
	}
	
}
