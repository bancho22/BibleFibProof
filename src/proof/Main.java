/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Bancho
 */
public class Main {

    //the 46th fib num is the last one below Integer.MAX_VALUE
    public static final int LIMIT = 46;
    
    public static void main(String[] args) {
        ArrayList<Integer> numericValues = convertTextFileIntoNumericValues();
        ArrayList<Integer> fibSeq = generateFibSeq(LIMIT);
        Collections.reverse(fibSeq);
        
        System.out.println("Number of letters: " + numericValues.size());
                
        ArrayList<Match> matches = performCalculations(numericValues, fibSeq);
        
        System.out.println("Matches: " + matches.size());
    }
    
    
    public static ArrayList<Match> performCalculations(ArrayList<Integer> letters, ArrayList<Integer> fibSeq) {
        ArrayList<Match> existingMatches = new ArrayList<>();
        int numOfLettersFibsMadeOf = 5;
        while(numOfLettersFibsMadeOf > 0){
            for (int i = 0; i < letters.size(); i+=numOfLettersFibsMadeOf) {
                String strNum = "";
                for (int j = 0; j < numOfLettersFibsMadeOf; j++) {
                    int index = i+j;
                    if(index >= letters.size() == false){
                        int val = letters.get(i+j);
                        String strVal = Integer.toString(val);
                        strNum = strNum.concat(strVal);
                    }
                }
                if (strNum.equals("")) {
                    continue;
                }
                int num = Integer.parseInt(strNum);
                
                boolean matchPossible;
                ArrayList<Integer> numsInSeq = null;
                if(fibSeq.contains(num)){
                    matchPossible = true;
                    numsInSeq = new ArrayList<>();
                    numsInSeq.add(num);
                }
                else{
                    matchPossible = false;
                }

                while(matchPossible){
                    //test next ones
                    strNum = "";
                    for (int j = numsInSeq.size() * numOfLettersFibsMadeOf; j < numsInSeq.size() * numOfLettersFibsMadeOf + numOfLettersFibsMadeOf; j++) {
                        int index = i+j;
                        if(index >= letters.size() == false){ //avoiding IndexOutOfBoundsException
                            int val = letters.get(i+j);
                            String strVal = Integer.toString(val);
                            strNum = strNum.concat(strVal);
                        }
                    }
                    if (strNum.equals("")) {
                        matchPossible = false;
                        continue;
                    }
                    num = Integer.parseInt(strNum);
                    if(fibSeq.contains(num)){
                        int last = numsInSeq.get(numsInSeq.size() - 1);
                        int index = fibSeq.indexOf(last);
                        if(index + 1 >= fibSeq.size() == false){ //avoiding IndexOutOfBoundsException
                            if(num == fibSeq.get(index + 1)){
                                numsInSeq.add(num);
                                if(numsInSeq.size() >= 3){ //only three in a row or above count as a match
                                    //ding ding ding
                                    for (Match match : existingMatches) {
                                        if(match.getStartIndex() == i){
                                            existingMatches.remove(match);
                                        }
                                    }
                                    existingMatches.add(new Match(numOfLettersFibsMadeOf, numsInSeq.size(), i, null)); //TODO: provide actual magical letters
                                }
                            }
                            else{
                                matchPossible = false;
                            }
                        }
                        else{
                            matchPossible = false;
                        }
                    }
                    else{
                        matchPossible = false;
                    }
                }
            }
            numOfLettersFibsMadeOf--;
        }
        return existingMatches;
    }
    
    

    public static ArrayList<Integer> convertTextFileIntoNumericValues() {
        File file = new File("C:\\Users\\Bancho\\Documents\\hello.txt");
        FileInputStream fis = null;
        ArrayList<Integer> values = new ArrayList();

        try {
            fis = new FileInputStream(file);

//            System.out.println("Total file size to read (in bytes) : "
//                    + fis.available());
            int content;
            while ((content = fis.read()) != -1) {

                char c = (char) content;
                int numVal = Character.getNumericValue(c);
                if(numVal >= 10){
                    values.add(numVal - 9);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return values;
    }
    
    
    public static ArrayList<Integer> generateFibSeq(int limit){
        ArrayList<Integer> fibs = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            fibs.add(fib(i));
        }
        return fibs;
    }
    
    
    //Iteration method for calculating fibs
    private static int fib(int n) {
        int x = 0, y = 1, z = 1;
        for (int i = 0; i < n; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return x;
    }
}
