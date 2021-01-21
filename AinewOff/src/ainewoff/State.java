/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ainewoff;

import java.util.Vector;

/**
 *
 * @author UcChwas
 */
public class State {
    int ml, cl;  
        int mr, cr;  
        char boat;  
        public State(int ml, int cl, char boat, int mr, int cr)
        {
            this.ml = ml;
            this.cl = cl;
            this.boat = boat;
            this.mr = mr;
            this.cr = cr;
        }
        public boolean goal_test()
        {
            return ml == 0 && cl == 0;
        }
        public String toString()
        {
            return "(" + ml + " " + cl + " " + boat + " " + mr + " " + cr + ")";
        }
        public boolean equals(Object obj)
        {
            if ( ! (obj instanceof State) )
                return false;
            State s = (State)obj;
            return (s.ml == ml && s.cl == cl && s.boat == boat
                    && s.cr == cr && s.mr == mr);
        }
        public Vector successor_function()
        {
            Vector v = new Vector();
            if (boat == 'L')
            {
                testAndAdd(v,new StateActionPair(
                               new State(ml-2,cl,'R',mr+2,cr),
                               new Action("Two missionaries cross left to right.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml,cl-2,'R',mr,cr+2),
                               new Action("Two cannibals cross left to right.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml-1,cl-1,'R',mr+1,cr+1),
                               new Action("One missionary and one cannibal cross left to right.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml-1,cl,'R',mr+1,cr),
                               new Action("One missionary crosses left to right.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml,cl-1,'R',mr,cr+1),
                               new Action("One cannibal crosses left to right.")));
            }
            else
            {
                testAndAdd(v,new StateActionPair(
                               new State(ml+2,cl,'L',mr-2,cr),
                               new Action("Two missionaries cross right to left.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml,cl+2,'L',mr,cr-2),
                               new Action("Two cannibals cross right to left.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml+1,cl+1,'L',mr-1,cr-1),
                               new Action("One missionary and one cannibal cross right to left.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml+1,cl,'L',mr-1,cr),
                               new Action("One missionary crosses right to left.")));
                testAndAdd(v,new StateActionPair(
                               new State(ml,cl+1,'L',mr,cr-1),
                               new Action("One cannibal crosses right to left.")));
            }
            return v;
        }
        private void testAndAdd(Vector v, StateActionPair pair)
        {
            State state = pair.state;
            if (state.ml >= 0 && state.mr >= 0 && state.cl >= 0 && state.cr >= 0
                    && (state.ml == 0 || state.ml >= state.cl)
                    && (state.mr == 0 || state.mr >= state.cr))
                v.addElement(pair);
        }
}


