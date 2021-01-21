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
public class SearchNode {             // A search node consists of the board, number of moves to reach

        public int moves;                 // this step and pointed to the previous search node

        public Board board;

        public SearchNode prev;



        public SearchNode(Board initial) {

            moves = 0;

            prev = null;

            board = initial;

        }

    }
