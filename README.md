# Cladogram-Drawer
Trying to make a means of drawing cladograms in Java(developed in JavaSE 14)

# Current Running
As of now, the way that this program runs is by running CladogramDrawer.java.
It requires a text file to exist and be opened. Cladograms can be made with an empty text file by using the add drop down menu.
the leaf option will ask for a name and then it will add a leaf witht he given name. The node option will ask for a name then ask for 
the children. Simply select one child at a time with the drop down menu and click the first option when finished. The other menu options are not yet implemented.

If a certain cladogram wants to be read it must follow the format:
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
Add a better UI functionality like saving, resizing window, and deletion <br />
Fix bug that appears to break manual cladograms at sufficient sizes <br />
