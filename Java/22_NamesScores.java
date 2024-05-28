import java.io.*; 
import java.util.*;

public class Solution {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    String[] str = new String[n];


    TreeMap<String, Integer> sorted = new TreeMap<>();


    for (int i = 0; i < n; i++) {
        int sum = 0;

        str[i] = scanner.nextLine();
        char[] characters = str[i].toCharArray();
        for (char ch : characters) {
            sum += Character.getNumericValue(ch) - 9; // - 9 cause the value of A-Z starts from 10-35
        }
        sorted.put(str[i].toUpperCase(), sum);
    }

    // to get the key and value of TreeMap using Index
    Set<Map.Entry<String, Integer>> entrySet = sorted.entrySet();
    Map.Entry<String, Integer>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);


    // updating the assigned value to names after sorted position. this is why case 1 fails
    for (int i = 0; i < sorted.size(); i++) {
        int newNumber = entryArray[i].getValue() * (i + 1);
        sorted.replace(entryArray[i].getKey(), newNumber);
    }


    int q = Integer.parseInt(scanner.nextLine());


    for (int i = 0; i < q; i++) {
        String name = scanner.nextLine();

        for (int j = 0; j < sorted.size(); j++) {
            int value = sorted.get(name);
            if (sorted.containsKey(name)) {
                System.out.println(value);
                break;
            }
        }

    }


    scanner.close();
}
}
