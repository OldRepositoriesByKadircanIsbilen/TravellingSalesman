/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kargo;
import java.util.*;

public class City {
    String name;
    private Map<String,Integer> neighbours = new HashMap<>();

    public City(String name, HashMap neighbours){
        this.name = name;
        this.neighbours.putAll(neighbours);
    }

    public Map<String, Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Map<String, Integer> neighbours) {
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int distToNeig(City city){
        String cityname = city.getName();
        int dist = this.neighbours.get(cityname);
        return(dist);
    }
}
