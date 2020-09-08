package cladogram;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 * Class that is a collection of Taxa and their relationships. Can read from text file.
 *
 */
public class Cladogram {

	/**
	 * Constructor that reads from text file and fills up cladogram
	 * @param fName name of file to be read from
	 */
	public Cladogram(String fName) {
		try {
			//get scanner and continue erading while it gets lines
			Scanner fileScanner = new Scanner(new File(fName));
			while (fileScanner.hasNext()){
				Scanner lineScanner = new Scanner(fileScanner.nextLine());
				//format of file will have name first then numbers to indicate children
				Taxon newTaxon = new Taxon(lineScanner.next());
				ArrayList<Taxon> child = new ArrayList<Taxon>();
				
				//a line with more characters means it has children
				while(lineScanner.hasNext()) {
					//the format of the file has the leaves entered first then the nodes
					//the number represents the line in which the taxon was entered
					//so the leaves fill up first then the nodes connect them
					int index = Integer.parseInt(lineScanner.next());
					if(index > leaves.size())
						child.add(nodes.get(index - leaves.size() - 1));
					else
						child.add(leaves.get(index - 1));
				}
				newTaxon.setChildren(child);
				
				//if it has no children it is a leaf and a node otherwise
				if(child.isEmpty())
					leaves.add(newTaxon);
				else
					nodes.add(newTaxon);
				
				lineScanner.close();
			}
			fileScanner.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * returns a designated leaf if it is within the bounds of leaves. Returns null otherwise.
	 * @param n index of leaf to be taken
	 * @return
	 */
	public Taxon getInititalTaxon(int n) {
		if(n <= leaves.size() && n > 0)
			return leaves.get(n - 1);
		else
			return null;
	}
	
	/**
	 * returns leaves of the cladogram
	 * @return leaves of the cladogram
	 */
	public ArrayList<Taxon> getLeaves(){
		return leaves;
	}
	
	/**
	 * return higher order nodes of the cladogram
	 * @return nodes of the cladogram
	 */
	public ArrayList<Taxon> getNodes(){
		return nodes;
	}
	
	private ArrayList<Taxon> leaves;
	private ArrayList<Taxon> nodes;
}
