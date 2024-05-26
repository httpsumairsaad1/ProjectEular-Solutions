import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     List<Integer> ascii = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++)
            ascii.add(sc.nextInt());
        sc.close();
        
        Map<Integer, Integer> freq = new HashMap<>();
        StringBuilder key = new StringBuilder();
        
        for(int i=0, time=0;; i+=3){
            if(i>=ascii.size()){
                int which=0, count=0;
                for(Map.Entry<Integer, Integer> entry: freq.entrySet()) {
                    if(entry.getValue()>count){
                        which = entry.getKey();
                        count = entry.getValue();
                    }
                }
                key.append((char)(' ' ^ which));
                if(time == 0) i = 1;
                else if(time == 1) i = 2;
                else break;
                time++;
                freq.clear();
            }
            int t = ascii.get(i);
            if (!freq.containsKey(t)) freq.put(t, 1);
            else freq.put(t, freq.get(t) + 1);
        }
        System.out.print(key);
        }
}
