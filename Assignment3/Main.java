package edu.gatech.seclass.replace;


import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String replacedContent = "";
        Main newMain = new Main();
        System.out.println("Arguments !!!! " + args.length);

        //{"-b", "!!!", "!@#$", "--", inputFile1.getPath()}

        /*for(int i = 0; i <args.length; i ++){
            if (args[i].toString().equals("-f")){
                File oldFile = new File(args[4]);
                newMain.firstReplceDashF("!!", "OOOO",oldFile);

                //replacedCc= newMain.firstReplceDashF(args[4]).toString();
                *//*String fileContent =newMain.getFileContent(args[4]);
                File oldFile = new File(args[4]);
                replacedContent = newMain.firstReplceDashF("!!", "OOOO",oldFile);
                System.out.println("YOOooooo replaceing " + replacedContent.toString());
                //newMain. getFileContentAfterReplace();

                FileWriter newFile = new FileWriter(args[4], false);
                newFile.write(replacedContent);
                newFile.close();*//*

            }*/
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
        boolean firstReplacePrelimTest = false;
        int noOfReplaceOptions = 0;
        int trueReplaceOptions = 0;

        //Get number of arguments before --
        for (int i = 0; i < args.length; i++) {
            if (args[i].toString().equals("--")) {
                noOfDoubleDash++;
                realDelimeterPos = i;
                trueReplaceOptions = i;


                for (int j = i; j < args.length; j++) {
                    if (args[j].toString().equals("--")) {
                        realDelimeterPos = j;
                    }

                }
                break;
                //noOfFile = args.length - realDelimeterPos;
                //posFileStart = realDelimeterPos + 1;
                //fromString = args[realDelimeterPos-2];
                //toString = args[realDelimeterPos-1];
                //noOfReplaceOptions = args.length -1 - (realDelimeterPos + 2);

                /*if ((realDelimeterPos-2 ==0) || (realDelimeterPos -1 ==1)){
                    allReplaceCaseSensitive = true;
                }*/
            }
        }

        noOfFile = args.length - realDelimeterPos;
        posFileStart = realDelimeterPos + 1;
        /*if (realDelimeterPos -2 < 0){
            throw new usage();
        }*/

            fromString = args[realDelimeterPos - 2];
            toString = args[realDelimeterPos - 1];



        noOfReplaceOptions = trueReplaceOptions;

            if ((realDelimeterPos-2 ==0) || (realDelimeterPos -1 ==1)){
                //allReplaceCaseSensitive = true;
                newMain.usage();
                //System.exit(0);

            }



        /*//For replace options after getting replace arguments
        String []replaceOptions = new String[noOfReplaceOptions];
        for (int j =0; j <noOfReplaceOptions; j++){
            replaceOptions[j] = args[j];

        }
        List<String> replaceOptionList = new ArrayList<>(Arrays.asList(replaceOptions));
*/

        List<String> replaceOptionList = new ArrayList<>(Arrays.asList());
        //For replace options after getting replace arguments

        /*if(noOfReplaceOptions < 0){
            throw new NegativeArraySizeException;
        }*/
        try {
            String[] replaceOptions = new String[noOfReplaceOptions];
        } catch (NegativeArraySizeException e){
            e.printStackTrace();
        }
        for (int j =0; j <noOfReplaceOptions; j++){
            if(args[j] == null){
                throw new NullPointerException();
            }
            /*else if(args[j] == "--"){
                newMain.usage();
            }*/
            if ((args[j].equals("-i") || args[j].equals("-l") || args[j].equals("-f")
                    || args[j].equals("-b") ) && (!replaceOptionList.contains(args[j]))){
                replaceOptionList.add(args[j]);
            }
        }


        if (replaceOptionList.contains("-b")){
            backUp = true;
        }


        if(replaceOptionList.size()==1) {
            if (replaceOptionList.contains("-f")) {
                firstReplace = true;
            } else if (replaceOptionList.contains("-l")) {
                lastReplace = true;
            } else if (replaceOptionList.contains("-i")) {
                allReplace = true;
            } else if (replaceOptionList.contains("-b")) {
                backUp = true;
                allReplaceCaseSensitive = true;
            } else {
                allReplaceCaseSensitive = true;
                //newMain.usage();
            }

        }
        else if(replaceOptionList.size() ==0){
            allReplaceCaseSensitive = true;
            //newMain.usage();
        }

        else if(replaceOptionList.size() ==2){
            if (replaceOptionList.contains("-f") && (replaceOptionList.contains("-l"))){
                firstReplace = true;
                lastReplace = true;
            }
            else if (replaceOptionList.contains("-l") && (replaceOptionList.contains("-i"))){
                //lastCaseSensitive = true;
            }
            else if (replaceOptionList.contains("-i") && (replaceOptionList.contains("-f"))){
                firstReplaceFromAll = true;
            }
            else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-f"))){
                firstReplace = true;
            }
            else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-l"))){
                lastReplace = true;
            }
            else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-i"))){
                allReplace = true;
            }
            /*else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("--"))){
                allReplaceCaseSensitive = true;
            } else{
                for (int i =0 ; i <replaceOptionList.size(); i++){
                    if(replaceOptionList.get(i).equals("-f")){
                        firstReplace = true;
                    }
                    else if(replaceOptionList.get(i).equals("-l")){
                        lastReplace = true;
                    }
                    else if(replaceOptionList.get(i).equals("-i")){
                        allReplace = true;
                    }
                    *//*else if(replaceOptionList.get(i).contains("--")){
                        allReplace = true;
                    }*//*
                    *//*else {
                        allReplace =true;
                    }*//*
                }
            }*/

        }

        else if(replaceOptionList.size() ==3){
            if (replaceOptionList.contains("-f") && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-i"))){
                allReplace = true;
            }
            else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-f")) && (replaceOptionList.contains("-l"))){
                firstReplace = true;
                lastReplace = true;
            }
            else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-i"))){
                lastReplace = true;
            }
            else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-i")) && (replaceOptionList.contains("-f"))){
                firstReplaceFromAll = true;
            }
        }

        else if(replaceOptionList.size() ==4){
            if (replaceOptionList.contains("-f") && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-i"))){
                firstReplaceFromAll = true;
            }

        }

        /*
        This works
        if (replaceOptionList.contains("-b")){
            backUp = true;
        }

        if(replaceOptionList.contains("-f")){
            firstReplace = true;
            if (replaceOptionList.contains("-l")){
                firstReplace = true;
                lastReplace = false;
            }
            else if (replaceOptionList.contains("-i")){
                allReplace =false;
                firstReplace = false;
                firstReplaceFromAll = true;
            }
        }

        if(replaceOptionList.contains("-l")){
            lastReplace = true;
            if (replaceOptionList.contains("-f")){
                lastReplace = false;
                firstReplace = true;
            if (replaceOptionList.contains("-i")){
                lastReplace = true;
                firstReplace = false;
            }
            }
        }

        if(replaceOptionList.contains("-i")){
            allReplace = true;
            if (replaceOptionList.contains("-f")){
                allReplace = false;
                firstReplace = true;
                if (replaceOptionList.contains("-l")){
                    lastReplace = true;
                    allReplace = false;
                }
            }

        }



*/

/*        for(int i = 0; i <args.length; i ++){

            if (args[i].toString().equals("-f")){
                firstReplace =true;
                //firstReplacePrelimTest = true;
                //if(firstReplacePrelimTest = true) &&
            }
            else if (args[i].toString().equals("-b")){
                backUp = true;
            }
            else if (args[i].toString().equals("-l")){
                //lastReplace = true;
                if (firstReplace = true){

                }
            }
            else if (args[i].toString().equals("-i")){
                allReplace = true;
            }
            *//*else if (args[i].toString().equals("--")){
                noOfFile = args.length - i;
                posFileStart = i + 1;
                fromString = args[i-2];
                toString = args[i-1];
                if ((i-2 ==0) && (i -1 ==1)){
                    allReplace = true;
                }

            }*//*
            else if (args[i].toString().equals("--")) {
                noOfDoubleDash++;
                realDelimeterPos = i;


                for(int j = i; j<args.length; j++){
                    if (args[j].toString().equals("--")){
                        realDelimeterPos = j;
                    }
                }
                noOfFile = args.length - realDelimeterPos;
                posFileStart = realDelimeterPos + 1;
                fromString = args[realDelimeterPos-2];
                toString = args[realDelimeterPos-1];

                if ((realDelimeterPos-2 ==0) || (realDelimeterPos -1 ==1)){
                    allReplaceCaseSensitive = true;
                }
            }
            else if ((i == args.length -1) && (firstReplace == false) && (lastReplace == false) && (allReplace == false)){
                allReplaceCaseSensitive = true;
            }

        }*/

        if (noOfDoubleDash == 0){
            //newMain.usage();
            throw new NullPointerException("");
        }
        File [] files = new File[noOfFile];
        for (int k = posFileStart; k < args.length; k ++){
            //Testing now for replaces

            if (backUp == true){
                File oldFile = new File(args[k]);
                try {
                    newMain.backUpOption(oldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (firstReplace == true){
                File oldFile = new File(args[k]);
                try {
                    newMain.firstReplceDashF(fromString, toString,oldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (lastReplace == true){
                File oldFile = new File(args[k]);
                try {
                    newMain.lastReplceDashL(fromString, toString,oldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (allReplace == true){
                File oldFile = new File(args[k]);
                try {
                    newMain.allReplceDashI(fromString, toString,oldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



            if (allReplaceCaseSensitive == true){
                File oldFile = new File(args[k]);
                try {
                    newMain.allReplceCaseSensitive(fromString, toString,oldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (firstReplaceFromAll == true){
                File oldFile = new File(args[k]);
                try {
                    newMain.firstReplaceForAll(fromString, toString,oldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }



        //return replacedContent;
    }

    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    /*private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }*/
    private static String usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
        return "Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" ;
    }

    private Charset charset = StandardCharsets.UTF_8;


    private File createTmpFile() throws IOException {
        temporaryFolder.create();
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private File createInputFile1() throws Exception {
        File testFile =  createTmpFile();
        FileWriter fileWriter = new FileWriter(testFile);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return testFile;
    }

    private int commitChangesToFile() throws Exception{
        return 0;
    }
    public String getFileContentAfterReplace() throws Exception {

        String value = "IMPLEMENTATION YET TO BE COMPLETED IN D2";
        //Back up tested
        //backUpOption(inputFile1);


        return value;

    }


    /*public String getFileContentAfterReplace() throws Exception {


        //File filename = new File("example.txt");
        File inputFile1 = createInputFile1();
        String actual = getFileContent(inputFile1.getPath());

        String value = "IMPLEMENTATION YET TO BE COMPLETED IN D2";
        //Back up tested
        //backUpOption(inputFile1);


        String testingContent = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure File it has at least a few lines\n" +
                "so that we can create some Files interesting test cases...\n" +
                "And let's files say  is say \"howdy bill\" again!";

        //Both tested successfully
        //firstReplceDashF(testingContent);
        //allReplceDashI(testingContent);
        lastReplceDashF(testingContent);

        return value;
    }*/

    public File backUpOption(File sourceFile) throws IOException , AssertionError{
        //temporaryFolder.create();
        String targetFileName = sourceFile.getName() + ".bck";
        File targetFile = new File(targetFileName);
        String sourcepathDir = sourceFile.toPath().toString()+".bck";
        Path pathtest = Paths.get(sourcepathDir);

        Path sourcePathagain = sourceFile.toPath();
        Path toFilePath = targetFile.toPath();
        //Files.copy( sourceFile.toPath(), pathtest);
        Files.copy(sourceFile.toPath(),pathtest,REPLACE_EXISTING);
        //Files.copy( sourceFile.toPath(), targetFile.toPath());
        /*String targetFileName = sourceFile.getName() + ".bck";
        File targetFile = new File(targetFileName);
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(sourceFile).getChannel();
            destChannel = new FileOutputStream(targetFile).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }*/
        return targetFile;


    }

    /*public File backUpOption(File sourceFile) throws IOException {
        //temporaryFolder.create();
        String targetFileName = sourceFile.getName() + "_backUp";
        File targetFile = new File(targetFileName);
        Files.copy( sourceFile.toPath(), targetFile.toPath());

        System.out.println("Copied file name = " +targetFile.getName());
        String fileContent = getFileContent(targetFileName);
        System.out.println("File contents == " + fileContent);
        System.out.println("File path == " + targetFile.getAbsolutePath());
        return targetFile;
    }*/

    /*public String allReplceDashI(String originalContent){
        System.out.print("\n All replace \n");

        System.out.print("File content For Dash Changes " + originalContent);
        String replacedContent = originalContent.replaceAll("a", "Drive");
        System.out.print("File content after replace " + replacedContent);

        return  replacedContent;
    }*/

    public void allReplceDashI(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        Pattern patternWord = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE);
        Matcher matchingWord = patternWord.matcher(fileContent);
        String replacedContent = matchingWord.replaceAll(toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();

    }


    public void firstReplaceForAll(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        //String[] arrayFileContent = fileContent.split("(?!^)");
        //String[] arrayFromString = fileContent.split("(?!^)");
        /*for(int i=0; i<arrayFromString.length; i++){
            for(int j =0; j <arrayFileContent.length; j++){
                if( arrayFromString[i].toLowerCase().equals(arrayFileContent))
            }
        }*/

        fromString ="(?i)" + fromString;
        String replacedContentTest = fileContent.replaceFirst(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContentTest);
        newFile.close();







        /*Pattern patternWord = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE);
        Matcher matchingWord = patternWord.matcher(fileContent);
        String replacedContent = matchingWord.replaceAll(toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();*/

    }

    public void allReplceCaseSensitive(String fromString, String toString, File fileToConvert) throws IOException {
        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContent = fileContent.replaceAll(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();
    }



    public void firstReplceDashF(String fromString, String toString, File fileToConvert) throws IOException {

        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContentTest = fileContent.replaceFirst(fromString, toString);
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContentTest);
        newFile.close();

    }
    /*public String firstReplceDashF(String originalContent){
        System.out.print("\n First replace \n");
        System.out.print("File content For Dash Changes " + originalContent);

        String replacedContent = originalContent.replaceFirst("a", "Drive");
        System.out.print("File content after replace " + replacedContent);


        return replacedContent;
    }*/

   /* public String lastReplceDashF(String originalContent){
        //Using StringUtils library from commons jar
        System.out.print("\n Last replace \n");
        System.out.print("File content For Dash Changes " + originalContent);

        String replaceString="say";
        int lastIndex =originalContent.lastIndexOf(replaceString);
        String beforeReverse = StringUtils.reverse(originalContent).replaceFirst(StringUtils.reverse("say"), StringUtils.reverse("Drive"));
        String replacedContent = StringUtils.reverse(beforeReverse);
        //String replacedContent = originalContent.replaceFirst("say", "Drive");
        System.out.print("File content after replace " + replacedContent);


        return replacedContent;
    }*/

    public void lastReplceDashL(String fromString, String toString, File fileToConvert) throws IOException {
        String replaceFromString=fromString;
        String replaceToString = toString;
        String fileContent = getFileContent(fileToConvert.toString());
        String replacedContent = fileContent;
        int lastIndex =fileContent.lastIndexOf(replaceFromString);
        if (lastIndex >=0){
            replacedContent = fileContent.substring(0,lastIndex) + replaceToString + fileContent.substring(lastIndex + replaceFromString.length());
        }
        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();
    }

    /*public String lastReplceDashL(String originalContent){
        System.out.print("\n Last replace \n");
        System.out.print("File content For Dash Changes " + originalContent);

        String replaceFromString="say";
        String replaceToString = "Drive";
        String replacedContent = originalContent;
        int lastIndex =originalContent.lastIndexOf(replaceFromString);
        if (lastIndex >=0){
            replacedContent = originalContent.substring(0,lastIndex) + replaceToString + originalContent.substring(lastIndex + replaceFromString.length());
        }

        System.out.print("File content after replace " + replacedContent);


        return replacedContent;
    }*/
    private void testFileContent(){

    }
}
