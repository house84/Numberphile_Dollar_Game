/*
*******************************************************************************************************
Title: Dollar Game
Program File: Game.java
External Files Required: Main.java, ListOfVertices.java, Vertices.java, ValidateVerticesEdges.java
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


import java.util.List;
import java.util.Scanner;

public class Game {

	//Create Scanner object to take user input
	Scanner input = new Scanner(System.in);

	//Take User's Next choice and validate input
	public String getNextMove(List<String> vertices) {

		//Boolean to run user input in a validation while loop
		boolean check = false;
		//String to hold Input
		String choice = "";

		System.out.print("Enter Q to quit or the letter of a vertex " + vertices + ": ");

		//Display User's Input Options and prompt for valid response
		while(!check) {

			choice = input.next().toUpperCase();

			//Check for Quit Condition
			if (choice.equals("Q")) {
				return choice;
			}

			//Check Input against vertices list return correct input
			for(int i = 0; i < vertices.size(); i++) {

				if(choice.equals(vertices.get(i))) {
					return choice;
				}
			}
			//Re-prompt User for correct input
			while(!input.hasNextLine()) {
				System.out.print("Invalid input, Enter Q or " + vertices + ": ");
				String tryagain = input.next();
			}

			System.out.print("Invalid input, Enter Q or " + vertices + ": ");
		}

		return choice;
	}

	//Validate user input to give or take dollars for selected vertex
	public int giveOrTake(){

		//Choice to hold user input
		int choice = 0;

		System.out.print("Enter a 1 to Give or a 2 to Take: ");

		//Ensure User input either a 1 or 2
		while (choice < 1 || choice > 2 ){

			while(!input.hasNextInt()){
				System.out.print("Invalid Input, Enter an integer (1 or 2): ");
				String tryagain = input.next();
			}

			choice = input.nextInt();

			if(choice > 2 || choice < 1){
				System.out.print("Invalid Input, Enter an integer (1 or 2): ");
			}
		}

		return choice;
	}
}
