/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof;

import java.util.ArrayList;

/**
 *
 * @author Bancho
 */
public class Match {
    
    private final int numOfLettersFibsMadeOf;
    private final int numOfFibsInSeq;
    private final int startIndex;
    private final ArrayList<String> magicalLetters;

    public Match(int numOfLettersFibsMadeOf, int numOfFibsInSeq, int startIndex, ArrayList<String> magicalLetters) {
        this.numOfLettersFibsMadeOf = numOfLettersFibsMadeOf;
        this.numOfFibsInSeq = numOfFibsInSeq;
        this.startIndex = startIndex;
        this.magicalLetters = magicalLetters;
    }

    public int getNumOfLettersFibsMadeOf() {
        return numOfLettersFibsMadeOf;
    }

    public int getNumOfFibsInSeq() {
        return numOfFibsInSeq;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public ArrayList<String> getMagicalLetters() {
        return magicalLetters;
    }

}
