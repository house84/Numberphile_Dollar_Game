/*
*******************************************************************************************************
Title: Dollar Game
Program File: Main.java
External Files Required: Game.java, ListOfVertices.java, Vertices.java, ValidateVerticesEdges.java
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

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Game Description
        System.out.println("\n************************** Dollar Game **************************");
        System.out.println("This program recreates Numberphile's Dollar Game. The game is\n" +
                        "pretty simple. Choose how many vertices and assign them a value. \n" +
                "then choose your connecting edges. Once this is complete the\n" +
                "fun starts. You can now give or take dollars between vertices.\n" +
                "When all vertices have a non-negative dollar value, you win!");
        System.out.println("*****************************************************************");
        System.out.println("\t\t     Good Luck and Enjoy!\n\n");

//********************** Initialize Game Setting and User Inputs *******************************************************

        //Game start message
        System.out.println("Time to set up the game graph...");

        //Create object to Validate User Input
        ValidateVerticesEdges valid = new ValidateVerticesEdges();
        //Get and Validate Number of Vertices
        valid.validateVertices();
        System.out.println("");
        //Get and Validate Number of Edges
        valid.validateNumberEdges();
        System.out.println("");
        //Set int = to valid number of vertices
        int v = valid.getNum_vertices();
        //Set int = valid number of edges
        int e = valid.getNum_edges();

        //Create Instance for Vertices and Edge List
        ListOfVertices vertList = new ListOfVertices(v, e);

        //Display vertices for edge selection
        System.out.println("\nSelect edges to connect between the following vertices: "
                + vertList.getVerticeNameList());

        //Create a Valid List of Edges
        List<String> edges;
        edges = Arrays.asList(valid.validateEdge(valid.setVerticesCharArray(v)));
        vertList.setEdgeList(edges);

        //Create Vertices Edge List
        vertList.setVerticesEdgeLists(edges);


//*************************************** Game Loop ********************************************************************

        //Game Start Message
        System.out.println("\nThe graph is now set and the game has started, Good Luck!");

        //Set Variables for game board
        //int to count moves
        int rounds = 0;
        //Instance of Game to get user moves
        Game game = new Game();
        //String to hold vertex choice or quit
        String choice ="";
        //int to indicate whether user wants to give or take to/from vertex
        int give_take;

        while(true){

            //Check win condition
            if( vertList.winCheck() == true ) {
                //If win display Win Message
                System.out.println("\n*************** WINNER ***************");
                System.out.println("Total moves: " + rounds);
                System.out.println("List of Vertices\n----------------");
                System.out.print(vertList.toString());
                System.out.println("----------------");
                System.out.println("**************************************\n");
            }
            else{
                //If not win Display Vertices, their value and adjacent vertices
                System.out.println("\nList of Vertices\n----------------");
                System.out.print(vertList.toString());
                System.out.println("----------------");
            }

            System.out.println("");
            //Get Player's Next Move
            choice = game.getNextMove(vertList.getVerticeNameList());

            //Check for Quit
            if(choice.equals("Q") == true ){
                //Display Exit Message
                System.out.println("\n******* Thanks for Playing *******");
                //Check for win condition and display appropriate message
                if(vertList.winCheck() == true){
                    System.out.println("You won the dollar game!!!");
                }
                else {
                    System.out.println("You did not win, maybe next time.");
                }
                System.out.println("Total Moves: " + rounds);
                System.out.println("List of Vertices\n----------------");
                System.out.print(vertList.toString());
                System.out.println("----------------");
                System.out.println("**********************************");



                break;
            }

            System.out.println("");
            //Choose whether to give or take from selected vertex
            give_take = game.giveOrTake();

            //Give dollars from Vertex to adjacent Vertices
            if( give_take == 1 ){

                //Display choice feedback
                System.out.println("Distributing dollars from " + choice);

                //Distribute Dollars
                vertList.givePoints(choice);
            }

            //Take dollars from adjacent vertices
            if( give_take == 2 ){

                //Display choice feedback
                System.out.println("Taking dollars from all adjacent vertices of vertex " + choice);

                //Take Dollars
                vertList.takePoints(choice);
            }

            //Iterate Round Number
            rounds++;
        }
//**********************************************************************************************************************

    }
}
