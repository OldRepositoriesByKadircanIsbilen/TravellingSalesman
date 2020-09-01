
package kargo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Country {
    private Map<String,City> cities = new HashMap<>();
    private Map<Integer,City> cities2 = new HashMap<>();
    long[][] mat;

    public Country() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("prolabII-1.txt"));
        String st = "";
        HashMap<String, Integer> registry = new HashMap<>();

        while((st=br.readLine())!=null){
            String[] sta = st.split("-");
            int reg = Integer.parseInt(sta[0]);
            String city = sta[1];

            registry.put(city,reg);
        }
        br.close();

        this.mat = new long[registry.size()][registry.size()];

        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                mat[i][j] = (long)Integer.MAX_VALUE;
            }
        }

        br = new BufferedReader(new FileReader("prolabII-1.txt"));
        st = "";
        while((st=br.readLine())!=null){
            String[] sta = st.split("-");
            int reg = Integer.parseInt(sta[0]);
            String city = sta[1];
            HashMap<String, Integer> neighs = new HashMap<>();

            for(int i=2;i<sta.length;i++){
                String[] sta2 = sta[i].split(",");
                String neig = sta2[0].split("\\(")[0];
                int dist = Integer.parseInt(sta2[0].split("\\(")[1].split("\\)")[0]);
                neighs.put(neig,dist);

                int ind = registry.get(neig)-1;
                mat[ind][reg-1] = dist;
            }

            this.cities.put(city,new City(city,neighs));
            this.cities2.put(reg,new City(city,neighs));
        }
        br.close();
    }

    public Map<Integer, City> getCities2() {
        return cities2;
    }

    public void setCities2(Map<Integer, City> cities2) {
        this.cities2 = cities2;
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }

    public long[][] getMat() {
        return mat;
    }

    public void setMat(long[][] mat) {
        this.mat = mat;
    }
}
