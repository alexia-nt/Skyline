package com.company;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    static int numOfPoints(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/com/company/" + filename));
            String str;
            //read first line (number of points)
            if ((str = br.readLine()) != null) {
                int num0fPoints = Integer.parseInt(str);
                br.close();
                return num0fPoints;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    static int[][] createArrayOfPoints(int numOfPoints, String filename){
        int cnt=0;
        int[][] points = new int[numOfPoints][2];
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/com/company/"+filename));
            String str;
            while ((str = br.readLine()) != null) {
                if(cnt!=0) {
                    String[] point_values = str.split(" ");
                    //System.out.println(cnt+") "+point_values[0]+","+point_values[1]);
                    points[cnt-1][0] = Integer.parseInt(point_values[0]);
                    points[cnt-1][1] = Integer.parseInt(point_values[1]);
                }
                cnt++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }

    static int countNumOfNotDominatedPoints(int[][] points){
        int cnt = 0;
        for(int point1[]:points){
            boolean dominated=false;
            for(int point2[]:points){
                //if point2 dominates point1
                if(point2[0]<=point1[0] && point2[1]<=point1[1] && (point2[0]!=point1[0] || point2[1]!=point1[1])){
                    dominated=true; //point1 is dominated
                }
            }
            if(!(dominated)){
                cnt++;
            }
        }
        return cnt;
    }

    static int[][] createArrayOfNotDominatedPoints(int numOfNotDominatedPoints, int[][] points){
        int[][] notDominatedPoints = new int[numOfNotDominatedPoints][2];
        int k = 0;
        for(int point1[]:points){
            boolean dominated=false;
            for(int point2[]:points){
                //if point2 dominates point1
                if(point2[0]<=point1[0] && point2[1]<=point1[1] && (point2[0]!=point1[0] || point2[1]!=point1[1])){
                    dominated=true; //point1 is dominated
                }
            }
            if(!(dominated)){
                //System.out.println(point1[0]+","+point1[1]);
                notDominatedPoints[k][0] = point1[0];
                notDominatedPoints[k][1] = point1[1];
                k++;
            }
        }
        //notDominatedPoints[numOfPoints][0]=k;
        return notDominatedPoints;
    }

    static void sort(int notDominatedPoints[][], int k){
        for(int i=0; i < k; i++){
            for(int j=1; j < (k-i); j++){
                if(notDominatedPoints[j-1][0] > notDominatedPoints[j][0]){
                    //swap elements
                    int temp = notDominatedPoints[j-1][0];
                    notDominatedPoints[j-1][0] = notDominatedPoints[j][0];
                    notDominatedPoints[j][0] = temp;

                    temp = notDominatedPoints[j-1][1];
                    notDominatedPoints[j-1][1] = notDominatedPoints[j][1];
                    notDominatedPoints[j][1] = temp;
                }
            }
        }
    }

    static void print(int notDominatedPoints[][], int k){
        int prev1 = notDominatedPoints[0][0];
        int prev2 = notDominatedPoints[0][1];
        System.out.println(notDominatedPoints[0][0] + " " + notDominatedPoints[0][1]);
        for(int i=1; i<k; i++){
            if(notDominatedPoints[i][0]!=prev1 && notDominatedPoints[i][1]!=prev2) {
                System.out.println(notDominatedPoints[i][0] + " " + notDominatedPoints[i][1]);
            }
            prev1 = notDominatedPoints[i][0];
            prev2 = notDominatedPoints[i][1];
        }
    }

    public static void main(String[] args) {
        //get number of points
        int numOfPoints = numOfPoints(args[0]); //read first line of file

        //create array of points
        int[][] points = createArrayOfPoints(numOfPoints, args[0]); //read file and return array of points

        //count number of not dominated points
        int numOfNotDominatedPoints = countNumOfNotDominatedPoints(points);

        //create array of not dominated points
        int[][] notDominatedPoints = createArrayOfNotDominatedPoints(numOfNotDominatedPoints, points);

        //sort points
        sort(notDominatedPoints,numOfNotDominatedPoints);

        //print sorted numbers without doubles
        print(notDominatedPoints,numOfNotDominatedPoints);
    }
}




/*
        //create array of not dominated points
        int[][] notDominatedPoints = createArrayOfNotDominatedPoints(numOfPoints, points);
        //sort points
        sort(notDominatedPoints,notDominatedPoints[numOfPoints][0]);
        //print sorted numbers without doubles
        print(notDominatedPoints,notDominatedPoints[numOfPoints][0]);
*/

/*
        //int cnt=0; (?)
        //int[][] points = new int[numOfPoints][2]; (?)
        //read file
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/com/company/"+args[0]));
            String str
            //read first line (number of points)
            str=br.readLine();
            int num0fPoints=Integer.parseInt(str);
            //create an array of points
            int[][] points = new int[numOfPoints][2];
            System.out.println("num of points="+num0fPoints);
            //read file and add the points to the array
            while ((str = br.readLine()) != null) {
                String[] point_values = str.split(" ");
                //System.out.println(cnt+") "+point_values[0]+","+point_values[1]);
                points[cnt][0]=Integer.parseInt(point_values[0]);
                points[cnt][1]=Integer.parseInt(point_values[1]);
                cnt++;
            }
            br.close();
            //create array with not dominated point
            int k = 0;
            int[][] notDominatedPoints = new int[numOfPoints][2];
            for(int point1[]:points){
                boolean dominated=false;
                for(int point2[]:points){
                    //if point2 dominates point1
                    if(point2[0]<=point1[0] && point2[1]<=point1[1] && (point2[0]!=point1[0] || point2[1]!=point1[1])){
                        dominated=true; //point1 is dominated
                    }
                }
                if(!(dominated)){
                    //System.out.println(point1[0]+","+point1[1]);
                    notDominatedPoints[k][0] = point1[0];
                    notDominatedPoints[k][1] = point1[1];
                    k++;
                }
            }
            //sort points
            sort(notDominatedPoints,k);
            //print sorted numbers without doubles
            print(notDominatedPoints,k);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
