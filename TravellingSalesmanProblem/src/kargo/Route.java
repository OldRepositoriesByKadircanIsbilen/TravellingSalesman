/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kargo;
import java.util.ArrayList;

public class Route {
    ArrayList<ArrayList<City>> cities;
    ArrayList<Integer> dist;

    public Route(){
        this.cities = new ArrayList<>();
        this.dist = new ArrayList<>();
    }

    public Route(ArrayList<ArrayList<City>> cities){
        this.cities = cities;
        this.dist = distance(this.cities);
    }

    public ArrayList<Integer> getDist() {
        return dist;
    }

    public void setDist(ArrayList<Integer> dist) {
        this.dist = dist;
    }

    public ArrayList<ArrayList<City>> getCities() {
        return this.cities;
    }

    public void setCities(ArrayList<ArrayList<City>> cities) {
        this.cities = cities;
        this.dist = distance(this.cities);
    }

    public static ArrayList<Integer> distance(ArrayList<ArrayList<City>> mycities){
        ArrayList<Integer> mydist = new ArrayList<>();

        for(int i=0;i<mycities.size();i++){
            int mydist0 = 0;
            for(int j=1;j<mycities.get(i).size();j++){
                City city1 = mycities.get(i).get(j-1);
                City city2 = mycities.get(i).get(j);
                mydist0 += city1.distToNeig(city2);
            }
            mydist.add(mydist0);
        }

        return(mydist);
    }

    public void addCity(City city,int ind){
        this.cities.get(ind).add(city);
        this.dist = distance(this.cities);
    }

    public String toString(){
        String s = "";
        for(int i=0;i<this.cities.size();i++){
            City c = this.cities.get(i).get(0);
            String s2 = c.getName();
            for(int j=1;j<this.cities.get(i).size();j++){
                s2 += " --> "+ this.cities.get(i).get(j).getName();
            }
            s2 += ": " + this.dist.get(i);
            s += s2+"\n";
        }
        return(s);
    }
}
