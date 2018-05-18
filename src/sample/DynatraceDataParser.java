package sample;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class DynatraceDataParser {

    private ArrayList<Integer> numberList;

    DynatraceDataParser(String json){
        JSONObject allData = new JSONObject(json);
        JSONArray numberArray = allData.getJSONArray("data");

        int len = numberArray.length();
        this.numberList = new ArrayList<Integer>();
        for (int i=0;i<len;i++){
            this.numberList.add((Integer) numberArray.get(i));
        }
    }

    public ArrayList<Integer> getNumberList() {
        return numberList;
    }

    public void eliminateDuplicates(){
        Set<Integer> list = new LinkedHashSet<Integer>(this.numberList);

        this.numberList.clear();
        this.numberList.addAll(list);

    }

    public void sortList(){
        Collections.sort(this.numberList);
    }

    public List<Integer> getPrimeNumbers(){
        return this.numberList.stream().filter(s -> isPrime(s)).collect(Collectors.toList());
    }

    private boolean isPrime(int number){
        if(number % 2 == 0)
            return false;
        else if(number % 3 == 0)
            return false;
        else if(number == 2)
            return true;
        else if(number == 3)
            return true;

        int i = 5;
        int w = 2;

        while(i*i <= number) {
            if (number % i == 0)
                return false;

            i += w;
            w = 6 - w;
        }

        return true;
    }

}
