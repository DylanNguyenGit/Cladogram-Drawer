package cladogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CladogramDrawerLabel extends JLabel implements ActionListener{
	
	public CladogramDrawerLabel(int height, int width) {
		this.height = height;
		this.width = width;
		//opens file chooser to choose txt file to open
		JFileChooser fChooser = new JFileChooser();
		//only shows text files
		fChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
		int status = fChooser.showOpenDialog(null);
		//makes selected file the base of the cladogram
		if(status == JFileChooser.APPROVE_OPTION) {
			file = fChooser.getSelectedFile();
			icon = new CladogramIcon(height, width, file);
		}
		setIcon(icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//If open menu item is selected
		if(e.getActionCommand().equals("Open")) {
			//opens file chooser to choose txt file to open
			JFileChooser fChooser = new JFileChooser();
			fChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
			int status = fChooser.showOpenDialog(null);
			//makes selected file the base of the cladogram
			if(status == JFileChooser.APPROVE_OPTION) {
				file = fChooser.getSelectedFile();
				icon = new CladogramIcon(height, width, file);
			}
			setIcon(icon);
		}
		
		//if add a leaf menu item is selected
		else if(e.getActionCommand().equals("Leaf")) {
			JFrame f = new JFrame();
			//ask for a name for the new leaf
			String name = (String) JOptionPane.showInputDialog(f, "Input Leaf Name", "Add Leaf", JOptionPane.PLAIN_MESSAGE, new ImageIcon(), null, "");
			icon.getCladogram().addLeaf(new Taxon(name));
			repaint();
		}
		
		//if add a node menu item is selected
		else if(e.getActionCommand().equals("Node")) {
			JFrame f = new JFrame();
			//ask for a name for the new node
			String name = (String) JOptionPane.showInputDialog(f, "Input Node Name", "Add Node", JOptionPane.PLAIN_MESSAGE, new ImageIcon(), null, "");
			Taxon node = new Taxon(name);
			
			ArrayList<Taxon> leaves = icon.getCladogram().getLeaves();
			ArrayList<Taxon> nodes = icon.getCladogram().getNodes();
			
			//create list of possible children
			Object[] options = new Object[leaves.size() + nodes.size() + 1];
			options[0] = "0 No More Children";
			//list leaves first
			for (int i = 0; i < leaves.size(); i++) {
				options[i + 1] = (i+1) + " " + leaves.get(i).getName();
			}
			//append nodes to end of list
			for(int j = 0; j < nodes.size(); j++) {
				options[j + leaves.size() + 1] = (j + leaves.size() + 1) + " " + nodes.get(j).getName();
			}
			
			//Keep asking for children until a none is picked
			int index = 10000;
			while(index != 0) {
				name = (String) JOptionPane.showInputDialog(f, "Pick Child", "Add Node", JOptionPane.PLAIN_MESSAGE, new ImageIcon(), options, "");
				index = Integer.parseInt(name.charAt(0)+"");
				if(index > 0) {
					// index from list for leaves is actual index + 1
					if(index <= leaves.size()) {
						if(!node.getChildren().contains(leaves.get(index - 1)))
							node.addChild(leaves.get(index - 1));
						leaves.get(index - 1).setParent(node);
					}
					//index from list for nodes is actual index + leaves size + 1
					else {
						if(!node.getChildren().contains(nodes.get(index - leaves.size() - 1)))
							node.addChild(nodes.get(index - leaves.size() - 1));
						nodes.get(index - leaves.size() - 1).setParent(node);
					}
				}
			}
			
			//add node to cladogram then repaint
			icon.getCladogram().addNode(node);
			repaint();
		}
	}
	
	private CladogramIcon icon;
	private File file;
	private int height;
	private int width;

}
