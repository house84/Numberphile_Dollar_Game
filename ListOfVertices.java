/*
*******************************************************************************************************
Title: Dollar Game
Program File: ListOfVertices.java
External Files Required: Game.java, Main.java, Vertices.java, ValidateVerticesEdges.java
External Files Created: None
Contributing Programmers: Nick House & Donovan McDonough
Email: Nick House: nh6gy@umsystem.edu        Donovan McDonough: dmmv2r@mail.umsl.edu
Course: FS20-CMPSCI4500-001 :: Intro to the Software Profession
Date Written: 10 Sep 2020
Explanation:
    This program is a game based on Numberphile's Dollar game. The user chooses the number of vertices
    in the game and assigns them a positive or negative dollar amount. The user then chooses the number
    of edges min (V-1) and max V(V-1)/2 (no vertex can have an edge to itself). After the appropriate
    number of vertices and edges are selected the user then chooses their edges, they define which
    vertices will be connected. Once a good game condition is met the User is then allowed to exchange
    dollars by either taking a dollar from each adjacent vertex and giving it to a target vertex or is
    allowed to give 1 dollar from a vertex to each of its adjacent vertices. A game win scenario is set
    when all vertices have a non-negative dollar amount.

External Resources: Numberphile's Dollar Game video url :: https://www.youtube.com/watch?v=U33dsEcKgeQ
*******************************************************************************************************
*/



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfVertices {

	//variables to hold the int amount of vertices and edges
	int numberVertices, numberEdges;
	//List to hold vertices objects
	List<Vertices> verticesList = new ArrayList<>();
	//Scanner object to get user input
	Scanner input = new Scanner(System.in);
	//List to hold edges
	List<String> edgeList;
	//List to hold the names off all Vertices
	List<String> verticeNameList = new ArrayList<>();

	//Constructor, create list of vertices and give dollar amount
	ListOfVertices(int n, int e) {
		//set number of edges
		numberEdges = e;
		//Set number of Vertices
		numberVertices = n;
		//Call Method to create a list of new vertices
		setVerticesList(n);
	}

	//Create list of Vertices
	private void setVerticesList(int n) {

		//Create list length of given number for vertices
		for (int i = 0; i < n; i++) {
			//call validateValue to get valid dollar amount for vertex
			int value = validateValue((char) (i + 65));
			//Turn char value into a Letter (increments through alphabet)
			String name = Character.toString((char) (i + 65));
			//Add new element to vertices list and create new Vertices object with name and dollar
			verticesList.add(new Vertices((char) (i + 65), value));
			//Add the name of the added vertex to a list of names
			verticeNameList.add(Character.toString((char)(i+65)));
		}
	}

	//Getter for list of Vertice Names
	public List<String> getVerticeNameList(){ return verticeNameList;  }

	//Get List of Vertices
	public List<Vertices> getVerticesList(){
		return verticesList;
	}

	//Initialize list of Edges
	public void setEdgeList(List<String> edgeList){
		this.edgeList = edgeList;
	}

	//Getter for list of Edges
	public List<String> getEdgeList(){
		return edgeList;
	}

	//Validate User input for Dollar Amount
	private int validateValue(char a){

		int dollar;

		//Prompt user for int
		System.out.print("Enter a positive or negative number of dollars for vertex " + a + " : ");
			while(!input.hasNextInt()){
				System.out.print("Not a valid input, choose a positive or negative integer: ");
				String tryagain = input.next();
			}
			dollar = input.nextInt();

		return dollar;
	}

	//Make list in vertices for adjacent vertices
	public void setVerticesEdgeLists(List<String> edges){

		//Iterate through edge list and get vertices
        for(int i = 0; i < edges.size(); i++){

        	//Assign first vertex of edge to "a"
			String a = edges.get(i).substring(0,1);
			//Assign second vertex of edge to "b"
			String b = edges.get(i).substring(1,2);

			//Search verticeList for Vertice in edge List
			for(int j = 0; j < verticesList.size(); j++){

				//When vertex "a" is found add adjacent vertex "b" to its edge list
				if( a.equals(verticesList.get(j).getName())){
					verticesList.get(j).setEdges(b);

					//Find Vertex "b" and add vertex "a" to its edge list
					for(int k = 0; k < verticesList.size(); k++){
						if(b.equals(verticesList.get(k).getName())){
							verticesList.get(k).setEdges(a);
						}
					}
				}
			}
        }
	}

	//Sum the vertices with a value 0 or above and check win condition
	public boolean winCheck(){

		//Int to sum the number of vertices with dollar >0
		int winCondition = 0;

		//Sum the total number of vertex values > 0
		for(int i = 0; i < verticesList.size(); i++ ){
			if(verticesList.get(i).checkValue() == true){
				winCondition++;
			}
		}
		//If All vertex values >0 Return win condition
		if(winCondition == numberVertices){ return true; }

		return false;
	}

	//Check vertices list and distribute dollars
	public void givePoints(String vertex){

		//Holds value to give or take from chosen vertex
		int dollars;

		//List contains adjacent vertices to chosen vertex
		List<String> vortexEdge = new ArrayList<>();

		//Find proper Vertex and give dollars
		for(int i = 0; i < verticesList.size(); i++){

			//Find correct vertex object
			if(vertex.equals(verticesList.get(i).getName())){

				//Get number of dollars to take from vertex (sum of adjacent vertices)
				dollars = -(verticesList.get(i).getEdges().size());
				//Take appropriate number of dollars from chosen Vertex
				verticesList.get(i).sumValue(dollars);

				//Create List of vertex's adjacent vertices
				vortexEdge = verticesList.get(i).getEdges();

				//Iterate through adjacent vertices
				for(int j = 0; j < vortexEdge.size(); j++){
					//Iterate through vertice List of Vertex Objects
					for(int k = 0; k < verticesList.size(); k++){
						//When adjacent vertex is found in the vertices Obeject List distribute dollar
						if( vortexEdge.get(j).equals(verticesList.get(k).getName())){
							verticesList.get(k).sumValue(1);
						}
					}
				}
			}
		}
	}

	//Check vertices list and distribute dollars
	public void takePoints(String vertex){

		//Holds value to give or take from chosen vertex
		int dollars;

		//List contains adjacent vertices to chosen vertex
		List<String> vortexEdge = new ArrayList<>();

		//Find the proper Vertex and take dollars
		for(int i = 0; i < verticesList.size(); i++){

			//Find the correct vertex object
			if(vertex.equals(verticesList.get(i).getName())){

				//Get number of dollars to give to vertex (sum of adjacent vertices)
				dollars = (verticesList.get(i).getEdges().size());
				//Add appropriate number of dollars to chosen Vertex
				verticesList.get(i).sumValue(dollars);

				//Create list of adjacent vertices
				vortexEdge = verticesList.get(i).getEdges();

				//Iterate through adjacent Vertices and take dollar
				for(int j = 0; j < vortexEdge.size(); j++){
					//Iterate through vertice List of Vertex Objects
					for(int k = 0; k < verticesList.size(); k++){
						//When adjacent vertex is found in the vertices Obeject List take dollar
						if( vortexEdge.get(j).equals(verticesList.get(k).getName())){
							verticesList.get(k).sumValue(-1);
						}
					}
				}

			}
		}
	}

	//toString list all vertices, values and edges
	public String toString() {

		//Create string to hold return
		String list = "";

		//Sort through list of objects and add to list
		for(int i = 0; i < verticesList.size(); i++){
			//Print list of Vertices, their dollar and their adjacent vertices
			list += (verticesList.get(i).getName() + "[" + verticesList.get(i).getValue()
					+ "]: " + verticesList.get(i).listEdges() + "\n");
		}

		return list;
	}
}
