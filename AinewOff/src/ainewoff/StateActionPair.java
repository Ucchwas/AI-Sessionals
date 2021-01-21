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
public class StateActionPair {
    public State state;
        public Action action;
        public StateActionPair(State state, Action action)
        {
            this.state = state;
            this.action = action;
        }
}
