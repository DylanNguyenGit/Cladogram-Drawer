# Cladogram-Drawer
Trying to make a means of drawing cladograms in Java(developed in JavaSE 14)

# Current Running
As of now, the way that this program runs is by running CladogramDrawer.java.
It requires the phylo.txt in the source folder and it has a specific format:
1. All leaves must be listed first and only have a name. Leaves are taxa at the end of the cladogram.
2. Nodes are placed after ALL leaves. They begin with a name and then followed by 
   the number of the taxa they encompass separated by spaces.
   
Ex: A simple relationship of Canis encompassing Wolf and Dog will look like as follows

Wolf <br />
Dog <br />
Canis 1 2 <br />

Note how Canis is followed by 1 and 2 to since Wolf is line 1 and Dog is line 2.
A more complex relationship with the Red Fox can be as follows

Wolf <br />
Dog <br />
RedFox <br />
Canis 1 2 <br />
Canidae 3 4 <br />

Note how RedFox has no spaces as the name can't have spaces. Canidae is followed by 
3 and 4 as it encompasses RedFox and Canis.

# Future Goals
Add a means to use a file other than "phylo.txt" in the src folder <br />
Add a better UI to facilitate this and perhaps frame the cladogram better <br />
Add a means of manipulating cladograms in real time <br />
