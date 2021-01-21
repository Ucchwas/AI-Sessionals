/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ainewoff;

/**
 *
 * @author UcChwas
 */
public class Node {
        public State state;
        public Node parent_node;
        public Action action;
        public int path_cost;
        public int depth;
        public Node(State state)
        {
            this.state = state;
            parent_node = null;
            action = new Action("Initial state");
            path_cost = 0;
            depth = 0;
        }
        public Node(State state, Node parent, Action action)
        {
            this.state = state;
            this.parent_node = parent;
            this.action = action;
            this.path_cost = action.cost() + parent.path_cost;
            this.depth = 1 + parent.depth;
        }
        public void printBackTrace()
        {
            if (parent_node != null)
                parent_node.printBackTrace();
            System.out.println("   " + path_cost + ". " + action + " ---> " + state);
        }
}
