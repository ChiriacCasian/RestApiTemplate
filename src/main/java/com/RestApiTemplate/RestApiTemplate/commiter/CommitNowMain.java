package com.RestApiTemplate.RestApiTemplate.commiter;

import java.io.*;
import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CommitNowMain { ///123sdaf12312312
    private static String remoteBranchName ;
    private static String localBranchName ;
    public static void main(String[] args) throws IOException {///12311K11
        /// + test if they have https or ssh protocol
        /// if they have ssh return 1 go through
        /// if they have http use the application.properties to get pat token, and set the remote url accordingly
        getProperties() ;
        String freshestCommit = getFreshestCommit() ;
        String oldestCommit = getOldestUnpushedCommit();
        System.out.println(freshestCommit + " " + oldestCommit);
        if(freshestCommit == null || oldestCommit == null){
            System.out.println("Nothing to push, branch up to date");
            return ;
        }
        setHeadToSha1(oldestCommit);
        System.out.println(freshestCommit + " " + oldestCommit);
        push() ;
        setHeadToSha1(freshestCommit);
    }
    private static void runCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command.split(" "));
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void getProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("src/main/resources/application.properties");
        properties.load(in);
        in.close();
        remoteBranchName = properties.getProperty("remote_branch_name");
        localBranchName = properties.getProperty("local_branch_name");
    }
    private static void push() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "push", remoteBranchName, localBranchName) ;
        processBuilder.command(command).start() ;
    }
    private static String getOldestUnpushedCommit() throws IOException {
        //git log origin/main..main --pretty=format:"%H %s"
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "log", remoteBranchName + "/" + localBranchName + ".." +
                localBranchName, "--pretty=format:%H");
        processBuilder.command(command) ;
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "" ;
        String rez = null ;
        while((line = reader.readLine()) != null){
            rez = line ;
        }
        return rez ;
    }
    private static String getFreshestCommit() throws IOException {
        //git log origin/main..main --pretty=format:"%H %s"
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "log", remoteBranchName + "/" + localBranchName + ".." +
                localBranchName, "--pretty=format:%H");
        processBuilder.command(command) ;
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine() ;
    }
    private static void setHeadToSha1(String sha1) throws IOException {
        runCommand("git rev-parse HEAD");
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = Arrays.asList("git", "reset", "--soft", sha1) ;
        processBuilder.command(command).start() ;
        runCommand("git rev-parse HEAD");
        System.out.println("HEAD supposed to be set to " + sha1);
    }
}