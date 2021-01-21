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
public class Action {
    String text;
        public Action(String text)
        {
            this.text = text;
        }
        public String toString()
        {
            return text;
        }
        public int cost()
        {
            return 1;
        }
}
