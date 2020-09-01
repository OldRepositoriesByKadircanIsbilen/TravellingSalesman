/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kargo;
// A Java program for Floyd Warshall All Pairs Shortest 
// Path algorithm. 
import java.util.*;
import java.lang.*;
import java.io.*;


class FloydWarshall{

    public Map<String,Map<String,Route>> floydWarshall(long graph[][]) throws Exception{
        Country c = new Country();
        int V = graph.length;
        ArrayList<ArrayList<ArrayList<Long>>> dist = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> routes = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> routes2 = new ArrayList<>();

        int i, j, k;

        for(i=0;i<V;i++){
            ArrayList<ArrayList<ArrayList<Integer>>> r2 = new ArrayList<>();
            for(j=0;j<V;j++){
                ArrayList<ArrayList<Integer>> r1 = new ArrayList<>();
                ArrayList<Integer> r0 = new ArrayList<>();
                r0.add(i);
                r0.add(j);
                r1.add(r0);
                r2.add(r1);
            }
            routes.add(r2);
        }

        for (i = 0; i < V; i++) {
            ArrayList<ArrayList<Long>> al1 = new ArrayList<>();
            for (j = 0; j < V; j++) {
                ArrayList<Long> al0 = new ArrayList<>();
                al0.add(graph[i][j]);
                al1.add(al0);
            }
            dist.add(al1);
        }

        for (i = 0; i < V; i++){
            for (k = 0; k < V; k++){
                for (j = 0; j < V; j++){
                    ArrayList<Long> a1 = dist.get(i).get(k);
                    ArrayList<Long> a2 = dist.get(k).get(j);
                    ArrayList<Long> a3 = dist.get(i).get(j);

                    Long a1min = Collections.min(a1);
                    Long a2min = Collections.min(a2);
                    Long a3min = Collections.max(a3);

                    if(dist.get(i).get(j).size()<1) {
                        dist.get(i).get(j).add(a1min + a2min);
                        ArrayList<Integer> r0 = new ArrayList<>();
                        r0.add(i);
                        if(i!=k){
                            r0.add(k);
                        }
                        if(k!=j){
                            r0.add(j);
                        }
                        routes.get(i).get(j).add(r0);
                    }else{
                        if (a1min+a2min<a3min) {
                            int mymax = findMax(dist.get(i).get(j));
                            dist.get(i).get(j).set(mymax,a1min + a2min);
                            ArrayList<Integer> r0 = new ArrayList<>();
                            r0.add(i);
                            if(i!=k){
                                r0.add(k);
                            }
                            if(k!=j){
                                r0.add(j);
                            }

                            routes.get(i).get(j).set(mymax,r0);
                        }
                    }
                }
            }
        }

        for (i = V-1; i >= 0; i--){
            for (k = V-1; k >= 0; k--){
                for (j = V-1; j >= 0; j--){
                    ArrayList<Long> a1 = dist.get(i).get(k);
                    ArrayList<Long> a2 = dist.get(k).get(j);
                    ArrayList<Long> a3 = dist.get(i).get(j);

                    Long a1min = Collections.min(a1);
                    Long a2min = Collections.min(a2);
                    Long a3min = Collections.max(a3);

                    if(dist.get(i).get(j).size()<1) {
                        dist.get(i).get(j).add(a1min + a2min);
                        ArrayList<Integer> r0 = new ArrayList<>();
                        r0.add(i);
                        if(i!=k){
                            r0.add(k);
                        }
                        if(k!=j){
                            r0.add(j);
                        }

                        routes.get(i).get(j).add(r0);
                    }else{
                        if (a1min+a2min<a3min) {
                            int mymax = findMax(dist.get(i).get(j));
                            dist.get(i).get(j).set(mymax,a1min + a2min);
                            ArrayList<Integer> r0 = new ArrayList<>();
                            r0.add(i);
                            if(i!=k){
                                r0.add(k);
                            }
                            if(k!=j){
                                r0.add(j);
                            }

                            routes.get(i).get(j).set(mymax,r0);
                        }
                    }
                }
            }
        }

/*
        for(i=0;i<routes.size();i++){
            ArrayList<ArrayList<ArrayList<Integer>>> a2 = new ArrayList<>();
            for(j=0;j<routes.get(i).size();j++){
                ArrayList<ArrayList<Integer>> a1 = new ArrayList<>();
                for(int m=0;m<routes.get(i).get(j).size();m++){
                    ArrayList<Integer> a0 = new ArrayList<>();
                    for(int n=0;n<routes.get(i).get(j).get(m).size();n++){
                        a0.add(routes.get(i).get(j).get(m).get(n));
                    }
                    a1.add(a0);
                }
                a2.add(a1);
            }
            routes2.add(a2);
        }

 */

        routes2.addAll(routes);
        long[][] mat = c.getMat();

        for(i=0;i<routes.size();i++){
            for(j=0;j<routes.get(i).size();j++){
                for(int m=0;m<routes.get(i).get(j).size();m++){
                    boolean check = false;
                    for(int n=1;n<routes.get(i).get(j).get(m).size();n++){
                        if(i!=j && mat[routes.get(i).get(j).get(m).get(n-1)][routes.get(i).get(j).get(m).get(n)]>=Integer.MAX_VALUE){
                            check = true;
                        }
                    }

                    while(check){
                        check = false;
                        for(int n=1;n<routes.get(i).get(j).get(m).size();n++){
                            int x = routes.get(i).get(j).get(m).get(n-1);
                            int y = routes.get(i).get(j).get(m).get(n);
                            if(x!=y && mat[x][y]>=Integer.MAX_VALUE){
                                ArrayList<Integer> r0 = routes.get(x).get(y).get(m);
                                int middle = r0.get(1);

                                routes2.get(i).get(j).get(m).add(middle);
                                routes2.get(i).get(j).get(m).remove(routes2.get(i).get(j).get(m).size()-1);
                                routes2.get(i).get(j).get(m).add(n,middle);
                            }
                        }

                        for(int n=1;n<routes2.get(i).get(j).get(m).size();n++){
                            boolean check2 = i!=j && mat[routes2.get(i).get(j).get(m).get(n-1)][routes2.get(i).get(j).get(m).get(n)]>=Integer.MAX_VALUE;
                            if(check2){
                                check = true;
                            }
                        }
                    }
                }
            }
        }

        Map<String,Map<String,Route>> routeArray = new HashMap<>();
        for(i=0;i<routes2.size();i++){
            Map<String,Route> ra0 = new HashMap<>();
            for(j=0;j<routes2.get(i).size();j++){
                ArrayList<ArrayList<City>> ca0 = new ArrayList<>();
                for(int m=0;m<routes2.get(i).get(j).size();m++){
                    ArrayList<City> cityArray = new ArrayList<>();
                    for(int n=0;n<routes2.get(i).get(j).get(m).size();n++){
                        City mycity = c.getCities2().get(routes2.get(i).get(j).get(m).get(n)+1);
                        cityArray.add(mycity);
                    }
                    ca0.add(cityArray);
                }

                Route r = new Route(ca0);
                ra0.put(c.getCities2().get(j+1).getName(),r);
            }
            routeArray.put(c.getCities2().get(i+1).getName(),ra0);
        }

        //printSolution(dist);
        //printRoute(routes2);

        return(routeArray);
    }

    public static int findMax(ArrayList<Long> v){
        int pos = 0;
        long val = v.get(0);
        for(int i=1;i<v.size();i++){
            if(val<v.get(i)){
                val = v.get(i);
                pos = i;
            }
        }
        return(pos);
    }

    void printSolution(ArrayList<ArrayList<ArrayList<Long>>> dist){
        for(int i=0;i<dist.size();i++){
            for(int j=0;j<dist.get(i).size();j++){
                String x = "["+dist.get(i).get(j).get(0);
                for(int k=1;k<dist.get(i).get(j).size();k++){
                    x += ","+dist.get(i).get(j).get(k);
                }
                System.out.print(x+"] ");
            }
            System.out.println();
        }
    }

    void printRoute(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> route){
        for(int i=0;i<route.size();i++){
            for(int j=0;j<route.get(i).size();j++){
                String x = "[";
                for(int n=0;n<route.get(i).get(j).size();n++){
                    x += "{"+route.get(i).get(j).get(n).get(0);
                    for(int k=1;k<route.get(i).get(j).get(n).size();k++){
                        x += "-->"+route.get(i).get(j).get(n).get(k);
                    }
                    x += "},";
                }
                System.out.print(x+"] ");
            }
            System.out.println();
        }
    }
}