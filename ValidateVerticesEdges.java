/*
*******************************************************************************************************
Title: Dollar Game
Program File: ValidateVerticesEdges.java
External Files Required: Game.java, ListOfVertices.java, Vertices.java, Main.java
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



import java.util.Scanner;

public class ValidateVerticesEdges {

	//Declare variables for Class
	private int num_vertices, num_edges, edge_min, edge_max;
	Scanner input  = new Scanner(System.in);

	//Constructor Get input for Vertices and Edges
	ValidateVerticesEdges(){
	}

	//Get user input and validate for number of Vertices
	public void validateVertices(){

		//Flag to check first question
		int flag = 0;
		//Create test condition to enter while loop to validate user input
		num_vertices = -1;

		//Validate User input
		while(num_vertices < 2 || num_vertices > 7) {

			if(flag == 0) {
				System.out.print("Enter the number of vertices (2-7): ");
			}
			else { System.out.print("Not a valid input, choose and integer between 2-7: "); }
			while(!input.hasNextInt()){
				System.out.print("Not a valid input, choose and integer between 2-7: ");
				String tryagain = input.next();
			}
			num_vertices = input.nextInt();
			flag = 1;
		}

		//Call method to set min and max number of allowed edges
		setMinMaxEdges();
	}

	//Getter for Vertices Number
	public int getNum_vertices(){
		return num_vertices;
	}

	//Method to set Min and Max number of allowed Edges
	private void setMinMaxEdges(){
		//Edge Minimum is (V-1)
		edge_min = num_vertices - 1;
		//Edge Maximum without having edges going to and from the same vertex is [V(V-1)/2]
		edge_max = num_vertices *(num_vertices -1)/2;
	}

	//Get user input for number of Edges and Validate
	public void validateNumberEdges(){

		//Flag to tell if first input or not
		int flag = 0;
		//Assign value to enter validation loop
		num_edges = 0;

		//Validate user input for proper integer
		while(num_edges < edge_min || num_edges > edge_max) {

			if(flag == 0) { //Display first message
				System.out.print("Enter the number of edges (must be between " + edge_min +
						"-" + edge_max + "): ");
			}
			else { //Display repeat offender, invalid input message
				System.out.print("Not a valid input, Enter the number of edges (must be between " + edge_min +
						"-" + edge_max + "):");
			}
			while(!input.hasNextInt()){ //Display repeat offender, invalid input message
				System.out.print("Not a valid input, Enter the number of edges (must be between " + edge_min +
								"-" + edge_max+"):");
				String tryagain = input.next();
			}
			//Get user input
			num_edges = input.nextInt();

			//Set Flag
			flag = 1;
		}

	}

	//getter for char array of vertices
	public char[] setVerticesCharArray(int n){
		//Array to hold vertex names as chars
		char[] charArray = new char[n];

		//Assign appropriate ASCii Value to char array
		for(int i = 0; i < n; i++){
			charArray[i] = (char)(i+65);
		}

		return charArray;
	}

	//Getter for number of Edges
	public int getNum_edges(){
		return num_edges;
	}

	//Check for valid edge
	public String[] validateEdge(char[] vertices){

		//returns if edge is valid or not
		boolean goodEdge = true;
		//Array to hold list of edges
		String[] edges = new String[num_edges];

		//Create array of valid vertices
		for(int i = 0; i < num_edges; i++){
			do{
				System.out.print("Enter an edge to connect vertices: ");
				while(!input.hasNextLine()){
					System.out.print("Not a valid edge, enter edge: ");
					String tryagain = input.next();
				}
				edges[i] =  input.next().toUpperCase();
				//Check that Edge is valid
				if(checkEdge(edges, i, vertices) == true){
					goodEdge = true;
				}else { goodEdge = false; }
			} while(!goodEdge);
		}

		return edges;
	}

	//Check edge input and validate
	public boolean checkEdge(String[] edges, int x, char[] vertices){

		//Boolean to check condition of input
		boolean goodEntry = true;
		//Array to hold user input and validate
		char[] chars = edges[x].toCharArray();
		//String to hold input backwards
		String reverse = "";

		//Ensure 2 characters have been entered
		if(edges[x].length() != 2) { //validates input is an edge
			System.out.println("Edge must have only 2 letters");
			return false;
		}
		//checks edges for different Vertices
		else if(Character.compare(chars[0], chars[1]) == 0) {
			System.out.println("Edge cannot have same vertices");
			return false;
		}
		for(int i = 0; i < chars.length; i++) {
			if(Character.isDigit(chars[i])) {
				System.out.println("Edge must have only 2 letters");
				return false;
			}
		}

		int count = 0;
		//Determines if a vertex exists
		for(int i = 0; i < 2; i++) { //Loops through chars[]
			for(int j = 0; j < vertices.length; j++) { //Loops through vertices[]
				if(Character.compare(vertices[j],chars[i]) == 0) {
					count++;
				}
			}
		}
		if(count < 2) {
			System.out.print("Invalid vertex entry ");
			return false;
		}

		//reverses edge
		for(int i = edges[x].length() - 1; i >= 0; i--) {
			reverse = reverse + edges[x].charAt(i);
		}

		//checks if edge already exists
		for(int i = 0; i < x; i++) {
			if(edges[x].compareTo(edges[i]) == 0) {
				System.out.println("This edge already exists");
				return false;
			} else if(reverse.compareTo(edges[i]) == 0) {
				System.out.println("The reverse of this edge already exists");
				return false;
			}
		}

		return goodEntry;
	}
}
