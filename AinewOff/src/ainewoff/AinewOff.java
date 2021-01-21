/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ainewoff;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author UcChwas
 */
public class AinewOff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number Of Missionaries: ");
        int m = sc.nextInt();
        System.out.println("Number Of Cannibals: ");
        int c = sc.nextInt();
        State initial_state = new State(m, c, 'L', 0, 0);
        Node node = solve(initial_state);
        System.out.println();
        if (node == null) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("The solution is:\n");
            node.printBackTrace();
        }
        System.out.println();
    }

    public static Node solve(State initial_state) {
        LinkedList l = new LinkedList<>();
        Vector visited = new Vector();
        l.add(new Node(initial_state));
        while (true) {
            if (l.isEmpty()) {
                return null;
            }
            Node node = (Node) l.removeFirst();  // or change to removeLast
            Vector successors = node.state.successor_function();
            for (int i = 0; i < successors.size(); i++) {
                StateActionPair successor = (StateActionPair) successors.elementAt(i);
                if (!visited.contains(successor.state)) {
                    Node newNode = new Node(successor.state, node, successor.action);
                    if (successor.state.goal_test()) {
                        return newNode;
                    }
                    l.add(newNode);
                    visited.add(successor.state);
                }
            }
        }
    }

    public static boolean containsState(Vector visitedStates, State state) {
        for (int i = 0; i < visitedStates.size(); i++) {
            if (visitedStates.equals(state)) {
                return true;
            }
        }
        return false;
    }
}
