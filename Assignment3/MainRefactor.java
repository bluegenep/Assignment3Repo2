package edu.gatech.seclass.replace;

//Created By Prakash

import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class MainRefactor {

    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private Charset charset = StandardCharsets.UTF_8;
    //ArrayList<Map.Entry<String, String>> fromToString = new ArrayList<Map.Entry<String, String>>();

    public static void main(String[] args) {
        // write your code here
        String replacedContent = "";
        MainRefactor newMain = new MainRefactor();

        boolean firstReplace = false;
        boolean lastReplace = false;
        boolean allReplace = false;
        boolean allReplaceCaseSensitive = false;
        boolean backUp = false;
        boolean firstReplaceFromAll = false;
        int noOfFile = 0;
        int posFileStart = 0;
        String fromString = "";
        String toString = "";
        int noOfDoubleDash = 0;
        int realDelimeterPos = -1;
        int noOfReplaceOptions = 0;
        int trueReplaceOptions = 0;


        List<String> replaceOptionsParse = new ArrayList<>(Arrays.asList());
        List<String> filesList = new ArrayList<>(Arrays.asList());
        List<File> filesTest = new ArrayList<File>(Arrays.asList());
        List<String> replaceOptReal = new ArrayList<>(Arrays.asList());
        List<String> fromReplaceString = new ArrayList<>(Arrays.asList());
        List<String> toReplaceString = new ArrayList<>(Arrays.asList());
        List<Integer> positionOfDashDash = new ArrayList<>(Arrays.asList());
        //Pair<Integer, String> simplePair = new Pair<>();
        ArrayList<Map.Entry<String, String>> fromToString = new ArrayList<Map.Entry<String, String>>();

        // List<Pair<String, Integer>> myPairss = new ArrayList<Pair<String, Integer>>();

        //Get number of arguments before --

        //If the arguments are less than 4 then we are going to throw the error since it does not match the parameters
        if (args.length < 4) {
            newMain.usage();
        } else {
            int numofDDash = 0;
            int realDashPosition = 0;
            int firstDash = 0;
            int realDelimiter =0;

            for (int i = 0; i <args.length; i ++) {

                if (args[i].equals("--")){
                    positionOfDashDash.add(i);
                }

            }

            numofDDash= positionOfDashDash.size();
            if(positionOfDashDash.size() == 1){
            /*for (int a : positionOfDashDash){
                System.out.println("File " +  a);
                firstDash =a;
                realDelimiter =a;
            }*/

                firstDash = positionOfDashDash.get(0);
                realDelimiter = positionOfDashDash.get(0);
            }
            else if (positionOfDashDash.size() == 2){
                firstDash = positionOfDashDash.get(0);
                realDelimiter = positionOfDashDash.get(1);
            }
            else if (positionOfDashDash.size() == 3){
                firstDash = positionOfDashDash.get(0);
                realDelimiter = positionOfDashDash.get(2);
            }


            if(numofDDash == 0){
                System.out.println ("No operation");
            }
            else if(numofDDash == 1 ) {
                int positionToCheck =0;
                for (int i = realDelimiter+1; i < args.length; i++) {
                    filesList.add(args[i]);


                }
                //get Replace options from String

                /*if (realDelimiter -2 <=0){
                    newMain.usage();
                }*/

                for(int i = 0 ; i <(realDelimiter-2); i++){

                    if ((args[i].equals("-b") || args[i].equals("-l") || args[i].equals("-f") || args[i].equals("-i"))
                            && (!replaceOptReal.contains(args[i])))
                            {

                        //&& (replaceOptReal.contains
                        //replaceOptReal = replaceOptReal.stream().distinct().collect(Collectors.toList());
                        positionToCheck = i;
                        replaceOptReal.add(args[i]);

                    }
                    //else if ((args[i].equals("")))

                }



                int testSize = replaceOptReal.size();
                for(int i = 0; i <testSize; i ++){
                    System.out.println(replaceOptReal.get(i));
                }
                //get From and To string




                    for (int i = realDelimiter - 1; i > positionToCheck; i -= 2) {
                        //for(int i = positionToCheck+1 ; i < realDelimiter; i+=2) {
                        toReplaceString.add(args[i]);
                        fromReplaceString.add(args[i - 1]);
                        //fromToString.add(new AbstractMap.SimpleEntry(args[i-1],args[i]));
                        // This is to only use the first replace if the fromString are same and to string differ (TC:46)
                        fromToString.add(new AbstractMap.SimpleEntry(args[i - 1], args[i]));
                        for (Map.Entry<String, String> test : fromToString) {
                            if (test.getKey().equals("")) {
                                test.setValue("");
                                newMain.usage();
                            } else if (test.getKey().equals(args[i - 1])) {
                                test.setValue(args[i]);
                            }


                        }
                        //fromToString.add(new AbstractMap.SimpleEntry(args[i-1],args[i]));

                    }

                //getFromToOptionsList(fromToString);

                //fromReplaceString.add(args[realDelimiter -2]);
                //toReplaceString.add(args[realDelimiter-1]);
            }
            else if(numofDDash == 2 || numofDDash == 3){
                for(int i = realDelimiter+1; i <args.length; i ++){
                    filesList.add(args[i]);

                }

                for(int i = 0 ; i <(firstDash); i++) {
                    if (args[i].equals("-b") || args[i].equals("-l") || args[i].equals("-f") || args[i].equals("-i")) {
                        replaceOptReal.add(args[i]);
                    }

                }

                for(int i = firstDash+1 ; i <(realDelimiter); i+=2) {
                    fromReplaceString.add(args[i]);
                    toReplaceString.add(args[i+1]);
                    fromToString.add(new AbstractMap.SimpleEntry(args[i],args[i+1]));
                    //fromString = fromToString.g
                }

            }
                        //check for useless
                        //Checking for backup
                        if (replaceOptReal.contains("-b")) {
                            backUp = true;
                        }

                        //looping to see how manyreplace options we have and perform actions accordingly
                        if (replaceOptReal.size() == 1) {
                            if (replaceOptReal.contains("-f")) {
                                firstReplace = true;
                            } else if (replaceOptReal.contains("-l")) {
                                lastReplace = true;
                            } else if (replaceOptReal.contains("-i")) {
                                allReplace = true;
                            } else if (replaceOptReal.contains("-b")) {
                                backUp = true;
                                allReplaceCaseSensitive = true;
                            } else {
                                allReplaceCaseSensitive = true;
                            }

                        } else if (replaceOptReal.size() == 0) {
                            allReplaceCaseSensitive = true;
                        } else if (replaceOptReal.size() == 2) {
                            if (replaceOptReal.contains("-f") && (replaceOptReal.contains("-l"))) {
                                firstReplace = true;
                                lastReplace = true;
                            } else if (replaceOptReal.contains("-l") && (replaceOptReal.contains("-i"))) {
                                //lastCaseSensitive = true;
                            } else if (replaceOptReal.contains("-i") && (replaceOptReal.contains("-f"))) {
                                firstReplaceFromAll = true;
                            } else if (replaceOptReal.contains("-b") && (replaceOptReal.contains("-f"))) {
                                firstReplace = true;
                            } else if (replaceOptReal.contains("-b") && (replaceOptReal.contains("-l"))) {
                                lastReplace = true;
                            } else if (replaceOptReal.contains("-b") && (replaceOptReal.contains("-i"))) {
                                allReplace = true;
                            }

                        } else if (replaceOptReal.size() == 3) {
                            if (replaceOptReal.contains("-f") && (replaceOptReal.contains("-l")) && (replaceOptReal.contains("-i"))) {
                                allReplace = true;

                            } else if (replaceOptReal.contains("-b") && (replaceOptReal.contains("-f")) && (replaceOptReal.contains("-l"))) {
                                firstReplace = true;
                                lastReplace = true;
                            } else if (replaceOptReal.contains("-b") && (replaceOptReal.contains("-l")) && (replaceOptReal.contains("-i"))) {
                                lastReplace = true;
                            } else if (replaceOptReal.contains("-b") && (replaceOptReal.contains("-i")) && (replaceOptReal.contains("-f"))) {
                                firstReplaceFromAll = true;
                            }

                        } else if (replaceOptReal.size() == 4) {
                            if (replaceOptReal.contains("-f") && (replaceOptReal.contains("-l")) && (replaceOptReal.contains("-l")) && (replaceOptReal.contains("-i"))) {
                                firstReplaceFromAll = true;
                            }

                        }
                    }

                    //PRB check for this option
                    /*else {
                        System.err.println("File "+ testFile.getName() + " not found");
                    }*/
                //}


                //If no delimeter throw null exception
            /*if (noOfDoubleDash == 0) {
                throw new NullPointerException("");
            }
            File[] files = new File[noOfFile];*/

                //Loop for checking the replaces


        //check or replaceOptReal.size +1. It was not that size
                for (int k = 0; k < filesList.size(); k++) {
                    //Testing now for replaces

                    //Testing if the file exists
                    File oldFile = new File(filesList.get(k));

                    File backupFile = new File(filesList.get(k)+".bck");

                    if(!oldFile.exists()){
                        System.err.println("File "+ oldFile.getName() + " not found");
                    }
                    else if((backupFile.exists() && (replaceOptReal.contains("-b")))){
                        System.err.println("Not performing replace for " + oldFile.getName() + ": Backup file already exists");
                    }
                    else {
                        for(Map.Entry<String, String> entry :fromToString) {


                            if (backUp == true) {
                                //File oldFile = new File(filesList.get(k));
                                //for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.backUpOption(oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //}
                            }
                            if (firstReplace == true) {
                                //File oldFile = new File(filesList.get(k));
                                //for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.firstReplceDashF(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //}
                            }

                            if (lastReplace == true) {
                                //File oldFile = new File(filesList.get(k));
                                //for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.lastReplceDashL(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //}
                            }

                            if (allReplace == true) {
                                //File oldFile = new File(filesList.get(k));
                                //for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();

                                try {
                                    newMain.allReplceDashI(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //}


                            }

                            if (allReplaceCaseSensitive == true) {
                                //File oldFile = new File(filesList.get(k));
                                //for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.allReplceCaseSensitive(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //}

                            }
                            if (firstReplaceFromAll == true) {
                                //File oldFile = new File(filesList.get(k));
                                //for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.firstReplaceForAll(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    //fromToString
                                }
                                //}

                            }
                        }


/*

                        if (backUp == true) {
                            //File oldFile = new File(filesList.get(k));
                            for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.backUpOption(oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (firstReplace == true) {
                            //File oldFile = new File(filesList.get(k));
                            for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.firstReplceDashF(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (lastReplace == true) {
                            //File oldFile = new File(filesList.get(k));
                            for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.lastReplceDashL(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (allReplace == true) {
                            //File oldFile = new File(filesList.get(k));
                            for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();

                                try {
                                    newMain.allReplceDashI(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }


                        }

                        if (allReplaceCaseSensitive == true) {
                            //File oldFile = new File(filesList.get(k));
                            for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.allReplceCaseSensitive(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        if (firstReplaceFromAll == true) {
                            //File oldFile = new File(filesList.get(k));
                            for (Map.Entry<String, String> entry : fromToString) {
                                fromString = entry.getKey();
                                toString = entry.getValue();
                                try {
                                    newMain.firstReplaceForAll(fromString, toString, oldFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    //fromToString
                                }
                            }

                        }*/
                    }

                }


            }




    private static void usage() {

        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*");

        //return "Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" ;
    }



    private void getLoopsForReplacingStrings(ArrayList fromToString){

    }

    //Operations for the replace option

    private File createTmpFile() throws IOException {
        temporaryFolder.create();
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private String getFileContent(String filename) {
        String content = "";
        try {

            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    //Operations

    public void parserForInputs(String[] args) {
        List<String> replaceOptionsParse = new ArrayList<>(Arrays.asList());
        List<String> filesList = new ArrayList<>(Arrays.asList());
        List<String> replaceOptReal = new ArrayList<>(Arrays.asList());
        List<String> fromReplaceString = new ArrayList<>(Arrays.asList());
        List<String> toReplaceString = new ArrayList<>(Arrays.asList());

        int numofDDash = 0;
        int realDashPosition = 0;
        int firstDash = 0;

        for (int i = 0; i <args.length; i ++){
            if (args[i].equals("-b") || args[i].equals("-l") || args[i].equals("-f") || args[i].equals("-i") ){
            replaceOptionsParse.add(args[i]);
            }
            else if (args[i].equals("--")){
                firstDash = i;
                for(int j = i; j < args.length; j ++){
                    if (args[j].equals("--")){

                        numofDDash ++;
                        realDashPosition = j;
                        break;
                    }
                }
            }

        }

        if(numofDDash == 0){
            System.out.println ("No operation");
        }
        else if(numofDDash == 1 ) {
            for (int i = realDashPosition; i < args.length; i++) {
                filesList.add(args[i]);

            }
            for(int i = 0 ; i <(realDashPosition-2); i++){
                if (args[i].equals("-b") || args[i].equals("-l") || args[i].equals("-f") || args[i].equals("-i") ){
                    replaceOptReal.add(args[i]);
                }
            }
            fromReplaceString.add(args[realDashPosition -2]);
            toReplaceString.add(args[realDashPosition-1]);

            //

        }
        else if(numofDDash == 2 ){
                for(int i = realDashPosition; i <args.length; i ++){
                    filesList.add(args[i]);

                }

                for(int i = 0 ; i <(firstDash); i++) {
                    if (args[i].equals("-b") || args[i].equals("-l") || args[i].equals("-f") || args[i].equals("-i")) {
                        replaceOptReal.add(args[i]);
                    }

                }

                for(int i = firstDash+1 ; i <(realDashPosition); i+=2) {
                    fromReplaceString.add(args[i]);
                    toReplaceString.add(args[i+1]);
                }

            }



    //return {a,b};
    }

    //Operation for BackUp

    public File backUpOption(File sourceFile) throws IOException, AssertionError {
        String targetFileName = sourceFile.getName() + ".bck";
        File targetFile = new File(targetFileName);
        String sourcepathDir = sourceFile.toPath().toString() + ".bck";
        Path pathtest = Paths.get(sourcepathDir);
        Files.copy(sourceFile.toPath(), pathtest, REPLACE_EXISTING);
        return targetFile;


    }

    //Operations for All Replace without case sensitive
    public void allReplceDashI(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        Pattern patternWord = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE);
        Matcher matchingWord = patternWord.matcher(fileContent);
        String replacedContent = matchingWord.replaceAll(toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();

    }

    //Operations for firstReplace case insensitive of the file content
    public void firstReplaceForAll(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        fromString = "(?i)" + fromString;
        String replacedContentTest = fileContent.replaceFirst(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContentTest);
        newFile.close();
    }

    //Operations for all replace case sensitive
    public void allReplceCaseSensitive(String fromString, String toString, File fileToConvert) throws IOException {

        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContent = fileContent.replaceAll(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();

    }

    //Operations for first Replace
    public void firstReplceDashF(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContentTest = fileContent.replaceFirst(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContentTest);
        newFile.close();

    }

    //Operation for last replace
    public void lastReplceDashL(String fromString, String toString, File fileToConvert) throws IOException {
        String replaceFromString = fromString;
        String replaceToString = toString;
        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContent = fileContent;
        int lastIndex = fileContent.lastIndexOf(replaceFromString);
        if (lastIndex >= 0) {
            replacedContent = fileContent.substring(0, lastIndex) + replaceToString + fileContent.substring(lastIndex + replaceFromString.length());
        }
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();
    }

}