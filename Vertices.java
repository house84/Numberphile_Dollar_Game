/*
*******************************************************************************************************
Title: Dollar Game
Program File: Vertices.java
External Files Required: Game.java, ListOfVertices.java, Main.java, ValidateVerticesEdges.java
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

public class Vertices {

	//String to hold letter of Vertex
	private String name;
	//Int to hold dollar amount of Vertex
	private int value;
	//List to hold vertex's adjacent vertices
	private List<String> edges = new ArrayList<>();

	//Constructor, create vertice name and value
	Vertices(char name, int value){
		//assign name and value
		this.name = Character.toString(name);
		this.value = value;
	}

	//Getter for Name
	public String getName(){
		return name;
	}

	//Getter for Value
	public int getValue(){
		return value;
	}

	//Sum Value
	public void sumValue(int num){
		value += num;
	}

	//Create list of Edges to vertices return true if successful
	public boolean setEdges(String n){ //Maybe set to void and validate in ListOfEdges

		//Redundancy method to Check, the input should already be screened
		if(edges.contains(n)){
			System.out.println("This edge already exists");
			return false;
		}
		//Add adjacent vertex to edge list
		else {
			edges.add(n);
			return true;
		}
	}

	//Return List of Edges
	public List<String> getEdges(){
		return edges;
	}

	//Print Edges
	public void printEdges(){
		for(int i = 0; i < edges.size(); i++){
			System.out.print(edges.get(i)+" ");
		}
	}

	//Getter for Vertice edge list
	public List<String> listEdges(){
		return edges;
	}

	//Check whether the value of Vertex is >0
	public boolean checkValue(){
		if(value >= 0){ return true; }
		else return false;
	}
}
