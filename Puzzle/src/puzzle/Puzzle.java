/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author UcChwas
 */



public class Puzzle {
	public int dimension = 3;
        private SearchNode goal;              

    

    private class SearchNode {            

        private int moves;                 

        private Board board;

        private SearchNode prev;



        public SearchNode(Board initial) {

            moves = 0;

            prev = null;

            board = initial;

        }

    }

    public Puzzle(Board initial) {        

        PriorityOrder order = new PriorityOrder();
        PriorityOrderh orderh = new PriorityOrderh();
        PriorityOrderLinear orderl = new PriorityOrderLinear();
        PriorityQueue<SearchNode> man = new PriorityQueue<SearchNode>(order);

        PriorityQueue<SearchNode> ham = new PriorityQueue<SearchNode>(orderh);

        PriorityQueue<SearchNode> linear = new PriorityQueue<SearchNode>(orderl);
        SearchNode manNode = new SearchNode(initial);

        SearchNode hamNode = new SearchNode(initial);
        
        SearchNode LinearNode = new SearchNode(initial);

        man.add(manNode);

        ham.add(hamNode);                // twin created to detect infeasible cases

        linear.add(LinearNode);

        SearchNode manmin = man.poll();

        SearchNode hamMin = ham.poll();
        
        SearchNode linearmin = linear.poll();
        int h=0,m=0,l=0;


        while(!manmin.board.isGoal()  &&  !hamMin.board.isGoal()) {



            for (Board b : manmin.board.neighbors()) {      

                if (manmin.prev == null || !b.equals(manmin.prev.board)) {   

                    SearchNode n = new SearchNode(b);

                    n.moves = manmin.moves + 1;

                    n.prev = manmin;

                    man.add(n);
                    
                    m++;
                    }

            }

            

            for (Board b : hamMin.board.neighbors()) {

                if (hamMin.prev == null ||!b.equals(hamMin.prev.board)) {

                    SearchNode n = new SearchNode(b);

                    n.moves = hamMin.moves + 1;

                    n.prev = hamMin;

                    ham.add(n);
                    h++;
                    }

            }

            for (Board b : linearmin.board.neighbors()) {

                if (linearmin.prev == null ||!b.equals(linearmin.prev.board)) {

                    SearchNode n = new SearchNode(b);

                    n.moves = linearmin.moves + 1;

                    n.prev = linearmin;

                    linear.add(n);
                    l++;
                    }

            } 

             manmin = man.poll();

             hamMin = ham.poll();
             
             linearmin=linear.poll();

         }
        int sum=h+m+l;
        System.out.println("Total "+ h);
        System.out.println("Total "+ m);
        System.out.println("Total "+ l);
        System.out.println("Manhattan Distance: "+ manmin.moves);
        System.out.println("Hamming Distance: "+ hamMin.moves);
        System.out.println("Linear Conflict: "+ linearmin.moves);
         if (manmin.board.isGoal())  goal = manmin;

         else                     goal = null;                
         
         
    }



    private class PriorityOrder implements Comparator<SearchNode> {

        public int compare(SearchNode a, SearchNode b) {

            int pa = a.board.manhattan() + a.moves;

            int pb = b.board.manhattan() + b.moves;

            if (pa > pb)   return 1;

            if (pa < pb)   return -1;

            else              return 0;

        }

    }

    
    private class PriorityOrderh implements Comparator<SearchNode> {

        public int compare(SearchNode a, SearchNode b) {

            int pa = a.board.hamming()+ a.moves;

            int pb = b.board.hamming()+ b.moves;

            if (pa > pb)   return 1;

            if (pa < pb)   return -1;

            else              return 0;

        }

    }
    
    private class PriorityOrderLinear implements Comparator<SearchNode> {

        public int compare(SearchNode a, SearchNode b) {

            int pa = a.board.LinearConflict()+ a.moves;

            int pb = b.board.LinearConflict()+ b.moves;

            if (pa > pb)   return 1;

            if (pa < pb)   return -1;

            else              return 0;

        }

    }
    
    

    public boolean isSolvable() {           

        return goal != null;

    }



    public int moves() {                   

        if (!isSolvable())  return -1;

        else                   return goal.moves;

    }



    public Iterable<Board> solution() {     

        if (!isSolvable())  return null;

        Stack<Board> s = new Stack<Board>();

        for (SearchNode n = goal; n != null; n = n.prev) 

            s.push(n.board);

        return s;

    }
	public int calculateCost(int[][] initial, int[][] goal) {

		int count = 0;

		int n = initial.length;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {

					count++;

				}

			}

		}

		return count;

	}

	

	public void printMatrix(int[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix.length; j++) {

				System.out.print(matrix[i][j] + " ");

			}

			System.out.println();

		}

	}
	public boolean isSolvableOdd(int[][] matrix) {

		int count = 0;
                
		List<Integer> array = new ArrayList<Integer>();
                
		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix.length; j++) {
				array.add(matrix[i][j]);
			}

		}
		Integer[] anotherArray = new Integer[array.size()];

		array.toArray(anotherArray);

		for (int i = 0; i < anotherArray.length - 1; i++) {

			for (int j = i + 1; j < anotherArray.length; j++) {

				if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {

					count++;

				}

			}

		}
		return count % 2 == 0;
              
                
	}
        
        public boolean isSolvableEven(int[][] matrix) {

		int count = 0;
                
		List<Integer> array = new ArrayList<Integer>();
                
		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix.length; j++) {
				array.add(matrix[i][j]);
                                if(matrix[i][j]==0){
                                    count = count + i;
                                }
			}

		}
		Integer[] anotherArray = new Integer[array.size()];

		array.toArray(anotherArray);

		for (int i = 0; i < anotherArray.length - 1; i++) {

			for (int j = i + 1; j < anotherArray.length; j++) {

				if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {

					count++;

				}

			}

		}
                
		return count % 2 == 1;
              
                
	}
        
	public static void main(String[] args) {
            /*
		int[][] blocks = { {0, 1, 3}, {4, 2, 5}, {7, 8, 6} };

		int[][] goal    = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0} };*/
                
        Scanner in=new Scanner(System.in);
        int n;
        n=in.nextInt();
        int[][] blocks=new int[n][n];
        
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                blocks[i][j]=in.nextInt();
        
        Board initial = new Board(blocks);      
        Puzzle solver = new Puzzle(initial);



        // print solution to standard output
        
        if (!solver.isSolvable())

            System.out.println("No solution possible");

        else {

            System.out.println("Minimum number of moves = " + solver.moves());

            for (Board board : solver.solution())

                System.out.println(board);

        }
      
        /*
        if(blocks.length % 2 == 1){
        if (solver.isSolvableOdd(blocks)) {

			System.out.println("The given initial is solvable");

		} 

		else {

			System.out.println("The given initial is impossible to solve");

		}
               }
                else{
                    if (solver.isSolvableEven(blocks)) {

			System.out.println("The given initial is solvable");

		} 

		else {

			System.out.println("The given initial is impossible to solve");

		}
                }*/

        }
       
}
