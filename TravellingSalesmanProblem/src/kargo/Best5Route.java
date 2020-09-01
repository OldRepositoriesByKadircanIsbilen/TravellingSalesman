/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kargo;
import java.util.ArrayList;

public class Best5Route {
    ArrayList<ArrayList<String>> cities;
    ArrayList<Integer> dist;

    public Best5Route(){
        this.cities = new ArrayList<>();
        this.dist = new ArrayList<>();
    }

    public Best5Route(ArrayList<ArrayList<String>> cities, ArrayList<Integer> dist){
        this.cities = cities;
        this.dist = dist;
    }

    public ArrayList<Integer> getDist() {
        return dist;
    }

    public ArrayList<ArrayList<String>> getCities() {
        return this.cities;
    }

    public String toString(){
        String s = "";
        for(int i=0;i<this.cities.size();i++){
            String c = this.cities.get(i).get(0);
            String s2 = c;
            for(int j=1;j<this.cities.get(i).size();j++){
                s2 += " --> "+ this.cities.get(i).get(j);
            }
            s2 += ": " + this.dist.get(i);
            s += s2+"\n";
        }
        return(s);
    }
}
