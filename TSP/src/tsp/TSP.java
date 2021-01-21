/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author UcChwas
 */
class data{
    int x,y,idN;
    boolean visited;
    public data(int xx,int yy,int id){
        x=xx;
        y=yy;
        idN=id;
        visited=false;
    }
    public data(int id){
        idN=id;
        System.out.println("idN========"+idN);
    }
}

public class TSP {

    static double[][] adjMAT=null;
    static ArrayList<data> nodeCO=new ArrayList<data>();
    static ArrayList<data> paTH=new ArrayList<>();
    
    
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n;
        n=in.nextInt();
        System.out.println("Number of Cities: "+n);
        int[] a, b;
        a=new int[n];
        b=new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
            b[i]=in.nextInt();
        }
        for(int i=0;i<n;i++){
            nodeCO.add(new data(a[i], b[i], i));
            System.out.println("X & Y Axis of City "+(i+1)+": "+nodeCO.get(i).x+"   "+nodeCO.get(i).y);
        }
        adjMAT=new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(j>i){
                    double dis=getEucDIs(nodeCO.get(i).x, nodeCO.get(i).y, nodeCO.get(j).x, nodeCO.get(j).y);
                    adjMAT[i][j]=dis;
                    adjMAT[j][i]=dis;
                }
                else if(i==j)
                    adjMAT[i][j]=0;
            }
        }
        
        for (int i = 0; i < n  ; i++) {
            for (int j = 0; j < n ; j++) {
                System.out.print(String.format("%.2f",adjMAT[i][j])+"  ");
            }
            System.out.println();
        }
        System.out.println();


    }
    
    public static double getEucDIs(int p,int q,int r,int s){
        double m=Math.abs(p-r);
        double n=Math.abs(q-s);
        
        return Math.sqrt((m*m) + (n*n));
    }
}
