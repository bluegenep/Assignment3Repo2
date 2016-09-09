package edu.gatech.seclass;

public class MyCustomString implements MyCustomStringInterface {

    String currentString = "";

    // Returns the current string.
    public String getString() {
        return currentString;
    }

    // Sets the current String
    public void setString(String str) {
        currentString = str;
    }

    /**
     * This method countNumbers() returns the number of numbers in the current string, where a number is defined as a
     * contiguous sequence of digits.
     * If the current string is empty, the method will return 0.
     * Examples:
     * countNumbers will return 2 for string "My numbers are 11 and 96".
     */
    public int countNumbers() {
        int counter = 0;

        if (currentString == null) {
            throw new NullPointerException("Null Pointer Exception::: String cannot be null");
        }
        for (int i = 0; i < currentString.length(); i++) {
            char c = currentString.charAt(i);
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                counter++;
                for (int j = i + 1; j < currentString.length(); j++) {
                    char aCS = currentString.charAt(j);
                    if (aCS == '0' || aCS == '1' || aCS == '2' || aCS == '3' || aCS == '4' || aCS == '5' || aCS == '6' || aCS == '7' || aCS == '8' || aCS == '9') {
                        i++;
                    } else {
                        break;
                    }

                }

            }
        }
        return counter;
    }

    /**
     * This method getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd)
     * returns a string that consists of all and only the characters in positions n, 2n, 3n, and
     * so on in the current string, starting either from the beginning or from the end of the
     * string. The characters in the resulting string should be in the same order and with the
     * same case as in the current string.
     * <p>
     * If the current string is empty or has less than n characters, the method will return an
     * empty string.
     * <p>
     * Examples:
     * - For n=2 and a string of one character, the method will return an empty string.
     * - For n=2 and startFromEnd=false, the method will return the 2nd, 4th, 6th, and so on
     * characters in the string.
     * - For n=3 and startFromEnd=true, the method will return the 3rd from the last character
     * in the string, 6th from the last character in the string, and so on.
     * <p>
     * Values n and startFromEnd are passed as parameters. The starting character, whether it is
     * the first one or the last one in the string, is considered to be in Position 1.
     */

    public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
        if (n <= 0) {
            throw new IllegalArgumentException("IllegalArgumentException ::: N should be greater than 0");
        }

        if (currentString == null) {
            throw new NullPointerException("NullPointerException ::: String cannot be null");
        }
        String returnString = "";

        int startingPoint = n;
        int endingPoint = currentString.length();
        int step = n;

        if (startFromEnd == true) {
            startingPoint = currentString.length() - n;
            endingPoint = 0;
            step = -n;
            for (int i = startingPoint; i >= endingPoint; i = i + step) {
                char c = currentString.charAt(i);
                returnString = c + returnString;
            }
        } else if (startFromEnd == false) {

            for (int i = startingPoint - 1; i < endingPoint; i = i + step) {
                char c = currentString.charAt(i);
                returnString = returnString + c;
            }
        }
        return returnString;
    }

    /**
     * This method convertDigitsToNamesInSubstring(int startPosition, int endPosition)
     * replaces the individual digits in the current string, between startPosition and endPosition
     * (included), with the corresponding English names of those digits, with the first letter
     * capitalized. The first character in the string is considered to be in Position 1.
     * Unlike for the previous method, digits are converted individually, even if contiguous.
     * Examples:
     * - 460 would be converted to FourSixZero
     * - 416 would be converted to FourOneSix
     */

    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {

        if (startPosition > endPosition) {
            throw new IllegalArgumentException("Start Position is greater than End Position");
        }
        if ((startPosition < 1) && (endPosition > currentString.length())) {
            throw new MyIndexOutOfBoundsException("Check your index Bound");
        }

        if (currentString == null) {
            throw new NullPointerException("NullPointer Exception:: String cannot be null");
        }
        String realString = "";
        String alphabetSum = "";
        String firstPartString = "";
        String endPartString = "";

        for (int i = 0; i < startPosition - 1; i++) {
            char c = currentString.charAt(i);
            firstPartString = firstPartString + String.valueOf(c);
        }

        for (int i = startPosition - 1; i < endPosition; i++) {
            char c = currentString.charAt(i);
            String alphabet = "";
            if (c == '0') {
                alphabet = "Zero";
            } else if (c == '1') {
                alphabet = "One";
            } else if (c == '2') {
                alphabet = "Two";
            } else if (c == '3') {
                alphabet = "Three";
            } else if (c == '4') {
                alphabet = "Four";
            } else if (c == '5') {
                alphabet = "Five";
            } else if (c == '6') {
                alphabet = "Six";
            } else if (c == '7') {
                alphabet = "Seven";
            } else if (c == '8') {
                alphabet = "Eight";
            } else if (c == '9') {
                alphabet = "Nine";
            } else {
                alphabet = String.valueOf(c);
                System.out.print("character = " + alphabet);
            }
            alphabetSum = alphabetSum + alphabet;
        }


        for (int i = endPosition; i < currentString.length(); i++) {
            char c = currentString.charAt(i);
            endPartString = endPartString + String.valueOf(c);
        }

        realString = firstPartString + alphabetSum + endPartString;
        currentString = realString;

    }

}

