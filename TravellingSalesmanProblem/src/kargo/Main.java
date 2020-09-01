
package kargo;
import java.util.Scanner;
import java.util.*;

public class Main{

    Country country;

    public Main() throws Exception{
        this.country = new Country();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static void main(String[] args) throws Exception{
        
        Country c = new Country();

        FloydWarshall fw = new FloydWarshall();
        Map<String,Map<String,Route>> allRoutes = fw.floydWarshall(c.getMat());

        //Route r = getBestRoute("Istanbul","Kastamonu",allRoutes,c);
        //System.out.println(r.toString());

        ArrayList<String> cityList = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Gidilecek şehileri yazınız. Her bir şehir isminden sonra enter tuşuna basınız. Sonlandırmak için space tuşuna basarak tekrar enter tuşuna basınız:");
        
        String input = ".";
        while(!(input=sc.nextLine()).equals("")){
            cityList.add(input);
        }
        
        /*
        cityList.add("Ankara");
        cityList.add("Izmir");
        cityList.add("Eskisehir");
        cityList.add("Tekirdag");
        cityList.add("Van");
        cityList.add("Kastamonu");
        */
        
        Best5Route bestlist = BestRoute.bestRoute("Kocaeli",cityList,allRoutes,c);
        for(int i=0;i<bestlist.getCities().size();i++){
            System.out.println(bestlist.getCities().get(i)+": "+bestlist.getDist().get(i));
            for(int j=1;j<bestlist.getCities().get(i).size();j++){
                Route r2 = getBestRoute(bestlist.getCities().get(i).get(j-1),bestlist.getCities().get(i).get(j),allRoutes,c);
                System.out.println(r2.toString());
            }
        }


    }

    public static Route getBestRoute(String cityName1,String cityName2,Map<String,Map<String,Route>> allRoutes, Country c) throws Exception{
        Route r = allRoutes.get(cityName1).get(cityName2);
        for (int i = 0; i < r.getCities().size(); i++) {
            boolean check = true;
            int whilecount = 0;
            while(check) {

                ArrayList<City> cities = r.getCities().get(i);
                int sum = 0;
                for (int j = 1; j < cities.size(); j++) {
                    Route checkroute = allRoutes.get(cities.get(j - 1).getName()).get(cities.get(j).getName());
                    int minindex = checkroute.getDist().indexOf(Collections.min(checkroute.getDist()));
                    int routedistance = checkroute.getDist().get(minindex);
                    sum += routedistance;
                }

                if (r.getDist().get(r.getDist().indexOf(Collections.min(r.getDist()))) == sum) {
                    check = false;
                }else{
                    ArrayList<City> finalcity = new ArrayList<>();
                    finalcity.add(c.getCities().get(cities.get(0).getName()));
                    int finaldist = 0;
                    for (int j = 1; j < cities.size(); j++) {
                        Route newroute = getBestRoute(cities.get(j-1).getName(),cities.get(j).getName(),allRoutes,c);
                        int minindex = newroute.getDist().indexOf(Collections.min(newroute.getDist()));
                        ArrayList<City> newcity = newroute.getCities().get(minindex);
                        int newdistance = newroute.getDist().get(minindex);
                        finaldist += newdistance;

                        for(int h=1;h<newcity.size();h++){
                            finalcity.add(newcity.get(h));
                        }
                    }

                    ArrayList<ArrayList<City>> r2cities = r.getCities();
                    r2cities.set(i,finalcity);
                    r.setCities(r2cities);
                    ArrayList<Integer> r2dist = r.getDist();
                    r2dist.set(i,finaldist);
                    r.setDist(r2dist);
                }
            }
        }

        return(r);
    }

    public static Route getBestRouteMini(String cityName1, String cityName2, Map<String,Map<String,Route>> allRoutes){
        return(allRoutes.get(cityName1).get(cityName2));
    }
}
