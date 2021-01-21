/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlenew;

/**
 *
 * @author UcChwas
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;



public class Solver {

    public SearchNode goal;              

    

    



    public Solver(Board initial) {           // find a solution to the initial board (using the A* algorithm)

        PriorityOrder order = new PriorityOrder();

        PriorityQueue<SearchNode> PQ = new PriorityQueue<SearchNode>(order);

        PriorityQueue<SearchNode> twinPQ = new PriorityQueue<SearchNode>(order);

        SearchNode Node = new SearchNode(initial);

        SearchNode twinNode = new SearchNode(initial);

        PQ.add(Node);

        twinPQ.add(twinNode);                // twin created to detect infeasible cases



        SearchNode min = PQ.poll();

        SearchNode twinMin = twinPQ.poll();



        while(!min.board.isGoal() && !twinMin.board.isGoal()) {



            for (Board b : min.board.neighbors()) {      

                if (min.prev == null || !b.equals(min.prev.board)) {   // check if move back this previous state

                    SearchNode n = new SearchNode(b);

                    n.moves = min.moves + 1;

                    n.prev = min;

                    PQ.add(n);

                    }

            }

            

            for (Board b : twinMin.board.neighbors()) {

                if (twinMin.prev == null ||!b.equals(twinMin.prev.board)) {

                    SearchNode n = new SearchNode(b);

                    n.moves = twinMin.moves + 1;

                    n.prev = twinMin;

                    twinPQ.add(n);

                    }

            }

             

             min = PQ.poll();

             twinMin = twinPQ.poll();

         }

         if (min.board.isGoal())  goal = min;

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



    public boolean isSolvable() {            // is the initial board solvable?

        return goal != null;

    }



    public int moves() {                     // min number of moves to solve initial board; -1 if no solution

        if (!isSolvable())  return -1;

        else                   return goal.moves;

    }



    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if no solution

        if (!isSolvable())  return null;

        Stack<Board> s = new Stack<Board>();

        for (SearchNode n = goal; n != null; n = n.prev) 

            s.push(n.board);

        return s;

    }



    public static void main(String[] args) { // solve a slider puzzle

        // create initial board from file

     /*   In in = new In(args[0]);

        int N = in.readInt();

        int[][] blocks = new int[N][N];

        for (int i = 0; i < N; i++)

            for (int j = 0; j < N; j++)

                blocks[i][j] = in.readInt();
*/


        int[][] blocks = { {1, 2, 3}, {5, 6, 0}, {8, 4, 7} };

        int[][] goal    = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        // solve the puzzle
        Board initial = new Board(blocks);      
        Solver solver = new Solver(initial);



        // print solution to standard output

        if (!solver.isSolvable())

            System.out.println("No solution possible");

        else {

            System.out.println("Minimum number of moves = " + solver.moves());

            for (Board board : solver.solution())

                System.out.println(board);

        }

    }

}
