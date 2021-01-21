/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baal;

/**
 *
 * @author UcChwas
 */
public class Infinity {
    public static void main(String args[]) {

        /*
        Start by assigning your default value to n, here it is 0
        If valid argument is not given, your program runs 
        starting from this value
        */
        int n = 0;

        // If any arguments given, we try to parse it
        if(args.length > 0) {

            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");

                // Program ends
                System.exit(1);
            }

        }

        // All good, n is either default (0) or user entered value
        while(true) {
            System.out.println(n);
            n++;
        }
    }
}
