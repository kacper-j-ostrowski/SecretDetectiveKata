package main.pl.ostrowski;

import java.util.HashSet;

public class SecretDetective {


    public String recoverSecret(char[][] triplets) {

        StringBuilder sb = new StringBuilder();
        char previous = 0;
        int wordLength = resolveWordLength(triplets);

        for (int i=0; i<triplets.length; i++) {
            boolean found = true;
            char potentialFirst = triplets[i][0];
            for (int j=0; j<triplets.length; j++) {
                if(potentialFirst == triplets[j][1]) {
                    found = false;
                    break;
                }
            }
            if(found) {
                sb.append(potentialFirst);
                previous = potentialFirst;
                break;
            }
        }

        while (sb.length() < wordLength) {
            int col = 0;
            while (col < 2) {
                for (int i = 0; i < triplets.length; i++) {
                    if (triplets[i][col] == previous) {
                        char[] candidates = getCandidates(previous, triplets);
                        if (candidates.length > 1) {
                            previous = getFirstLetterInOrder(candidates, triplets);
                        } else {
                            previous = candidates[0];
                        }
                        sb.append(previous);
                        col = 0;
                        break;
                    }
                }
                col++;
            }
        }

        return sb.toString();
    }

    public int resolveWordLength(char[][] triplets) {
        HashSet<Character> uniqueLetters = new HashSet<>();
        for(int i=0; i<triplets.length; i++) {
            for(int col=0; col<3; col++) {
                uniqueLetters.add(triplets[i][col]);
            }
        }
        return uniqueLetters.size();
    }

    public boolean isBefore(char charBefore, char charAfter, char[][] triplets) {
        int col = 0;
        while(col < 3) {
            for(int i=0; i<triplets.length; i++) {
                if(triplets[i][col] == charBefore) {
                    if(col == 0 && (triplets[i][1] == charAfter || triplets[i][2] == charAfter)) {
                        return true;
                    }
                    if (col == 1 && triplets[i][2] == charAfter) {
                        return true;
                    }
                    if (col == 1 && triplets[i][0] == charAfter) {
                        return false;
                    }
                    if (col == 2 && (triplets[i][0] == charAfter || triplets[i][1] == charAfter)) {
                        return false;
                    }
                }
            }
            col++;
        }
        return false;
    }


    public char getFirstLetterInOrder(char[] letters, char[][] triplets) {

        if(letters.length == 0) {
            return 0;
        }

        char potentialFirst = letters[0];
        for(char l : letters) {
            if(isBefore(l, potentialFirst, triplets)) {
                potentialFirst = l;
            }
        }

        return potentialFirst;
    }


    public char[] getCandidates(char previous, char[][] triplets) {
        HashSet<Character> candidates = new HashSet<>();

        int col = 0;
        while (col < 2) {
            for(int i=0; i<triplets.length; i++) {
                if(triplets[i][col] == previous) {
                    candidates.add(triplets[i][col+1]);
                }
            }
            col++;
        }
        char[] result = new char[candidates.size()];
        int i=0;
        for(Character c : candidates)
            result[i++] = c.charValue();
        return result;
    }
}
