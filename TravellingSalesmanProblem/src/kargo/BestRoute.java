/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kargo;
import java.util.*;

public class BestRoute {
    public BestRoute(){

    }
    public static Best5Route bestRoute(String mycity,ArrayList<String> cityList, Map<String, Map<String,Route>> allRoutes, Country c) throws Exception{
        Map<String,Route> routes = new HashMap<>();
        for(int i=0;i<cityList.size()-1;i++){
            for(int j=i+1;j<cityList.size();j++){
                Route r = Main.getBestRoute(cityList.get(i),cityList.get(j),allRoutes,c);
                routes.put(cityList.get(i)+"-"+cityList.get(j),r);
                routes.put(cityList.get(j)+"-"+cityList.get(i),r);
            }
            routes.put(mycity+"-"+cityList.get(i),Main.getBestRoute(mycity,cityList.get(i),allRoutes,c));
            routes.put(cityList.get(i)+"-"+mycity,Main.getBestRoute(mycity,cityList.get(i),allRoutes,c));
        }
        routes.put(mycity+"-"+cityList.get(cityList.size()-1),Main.getBestRoute(mycity,cityList.get(cityList.size()-1),allRoutes,c));
        routes.put(cityList.get(cityList.size()-1)+"-"+mycity,Main.getBestRoute(mycity,cityList.get(cityList.size()-1),allRoutes,c));

        ArrayList<ArrayList<String>> allperm = permutations(cityList);
        ArrayList<Integer> alldist = new ArrayList<>();

        for(int i=0;i<allperm.size();i++){
            boolean check = true;
            for(int j=0;j<allperm.get(i).size()-1;j++){
                for(int k=j+1;k<allperm.get(i).size();k++){
                    if(allperm.get(i).get(j)==allperm.get(i).get(k)){
                        check=false;
                    }
                }
            }
            if(check){
                ArrayList<String> myperm = new ArrayList<>();
                myperm.add(mycity);
                for (int j = 0; j < allperm.get(i).size(); j++) {
                    myperm.add(allperm.get(i).get(j));
                }
                myperm.add(mycity);

                int mydist = 0;
                for (int j = 1; j < myperm.size(); j++) {
                    Route r = routes.get(myperm.get(j - 1) + "-" + myperm.get(j));
                    mydist += r.getDist().get(0);
                }
                alldist.add(mydist);
            }else{
                alldist.add(Integer.MAX_VALUE);
            }
        }

        ArrayList<ArrayList<String>> best5 = new ArrayList<>();
        ArrayList<Integer> best5dist = new ArrayList<>();
        for(int i=0;i<5;i++){
            best5.add(allperm.get(alldist.indexOf(Collections.min(alldist))));
            best5dist.add(alldist.get(alldist.indexOf(Collections.min(alldist))));
            alldist.set(alldist.indexOf(Collections.min(alldist)),Integer.MAX_VALUE);
        }
        
        ArrayList<ArrayList<String>> mybest5 = new ArrayList<>();
        
        for(int i=0;i<best5.size();i++){
            ArrayList<String> added = new ArrayList<>();
            added.add(mycity);
            for(int j=0;j<best5.get(i).size();j++){
                added.add(best5.get(i).get(j));
            }
            added.add(mycity);
            mybest5.add(added);
        }
        
        return(new Best5Route(mybest5,best5dist));
    }

    public static ArrayList<ArrayList<String>> permutations(ArrayList<String> list){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int numSets = list.size();
        String[] tmpResult = new String[numSets];

        permutations(list, 0, tmpResult, result);

        return result;
    }

    static public void permutations(ArrayList<String> list, int n, String[] tmpResult, ArrayList<ArrayList<String>> result)
    {
        if (n == list.size()) {
            result.add(new ArrayList<String>(Arrays.asList(tmpResult)));
            return;
        }

        for(int i=0;i<list.size();i++){
            tmpResult[n] = list.get(i);
            permutations(list, n + 1, tmpResult, result);
        }
    }
}
