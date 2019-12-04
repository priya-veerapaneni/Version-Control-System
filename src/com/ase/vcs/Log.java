package com.ase.vcs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

import com.ase.vcs.Main;


/**
 * This creates the manifest file and log's every command entered.
 * @author Sankaran Shanmugavelayudam(sankaran22.5@gmail.com)
 * @version 1.0
 */

public class Log {
    private Log () {
    }

    public static Log getInstance() {
        return new Log();
    }

	/**
	 * A new Manifest file is created with date/time attached. This file contains all the files that were affected by the command passed.
	 * @param cmd - The command to be logged.
	 * @param srcPath - Source path of directory
	 * @param repPath - The destination path
	 * @param LoggingRegister - An ArrayList of all the classes that were copied/affected by the command
	 * @throws IOException If an I/O error occurs or the parent directory does not exist
	 */
	public void log(String cmd, String srcPath, String repPath, List<LoggingRegister> LoggingRegister) throws IOException{
		
		Scanner scLabel,scLabelInput;
		
		int labelDecision;
		String Label;
		
		scLabel = Main.scLabel;
		@SuppressWarnings("unused")
		//String fileName=null;
		ArrayList<String> entry = new ArrayList<String>();
		ArrayList<String> logList = new ArrayList<String>();
		
		LocalDateTime Date_Time = LocalDateTime.now();
		String currentTime = String.valueOf(Date_Time.getHour() + "." + Date_Time.getMinute() + "." + Date_Time.getSecond()) + " Hrs";
		String fileName = "Manifest "+ Date_Time.toLocalDate() + " - " +currentTime+ ".txt";
		FileSystem manifest =  FileSystems.getDefault();
		
		if(cmd.equals("Create")  || cmd.equals("Checkin")) {
	        
            System.out.println("Do you want to Enter Label: \n 1. Yes \n 2. No ");
            scLabel=new Scanner(System.in);
            labelDecision=scLabel.nextInt();
            
        if(labelDecision==1){
            
            System.out.println(" Enter Label ");
            scLabelInput=new Scanner(System.in);
            Label=scLabelInput.nextLine();
            fileName = "Manifest " + Date_Time.toLocalDate() + " - " +currentTime+ " - " + Label + ".txt";
        }
		}

		Path manifestFilePath = manifest.getPath(repPath + FileSystems.getDefault().getSeparator() + "Manifest" 
				+ FileSystems.getDefault().getSeparator() + fileName);
		
		
		
		
		
		
		Path logPath = FileSystems.getDefault().getPath(destination + FileSystems.getDefault().getSeparator() + "Activity" 
                + FileSystems.getDefault().getSeparator() + "Parent_Name.Log");
		
		
		
		
		
		Charset charset = StandardCharsets.US_ASCII;

		CreateManifestDir(repPath);
		Files.createFile(manifestFilePath);
		entry.add("Timestamp : "+ Date_Time.toLocalDate() +" - " +currentTime);
		entry.add("Source : " + srcPath);
		entry.add("Destination/Repo-Path : " + repPath);
		entry.add("command: " + (cmd + " " +srcPath + " " +repPath));
		entry.add("Files created/copied: ");
		
		for (LoggingRegister reg : LoggingRegister) {
			String line = reg.getArtName() + "\t" + reg.getfile_Name() + "\t" + reg.getfile_Path();
			entry.add(line);
			
			
			
			
			
			
			
			
			
			if(command.equals("Create")) {
                String logLine = help.getArtifactId() + ":" + help.getArtifactId() + ":" + help.getFileName();
                logList.add(logLine);
                
                if(!Files.exists(logPath)) {
                Files.createFile(logPath);
                }
            }
            
            else if (command.equals("Checkin")) {
                
                String []fileData;
                File logFile = new File(destination + File.separatorChar + help.getFileName());
                File[] fileList = logFile.listFiles();
                File parentFile = new File(destination + File.separatorChar + "Activity" + File.separatorChar + "Parent_Name.Log");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(parentFile));
                Arrays.sort(fileList, new Comparator<File>(){
                    public int compare(File f1, File f2)
                    {
                        //return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                        if(Long.valueOf(f1.lastModified()) - Long.valueOf(f2.lastModified()) > 0)
                            return -1;
                        if(Long.valueOf(f1.lastModified()) - Long.valueOf(f2.lastModified()) < 0)
                            return 1;
                        else 
                            return 0;
                    } });
                try{
                    
                    while (bufferedReader.ready()) {
                        fileData = bufferedReader.readLine().split(":");
                        if(help.getFileName().equals(fileData[2])) {
                            String logLine;
                            if(fileList.length > 1) {
                                logLine = help.getArtifactId() + ":" + fileList[1].getName() + ":" + help.getFileName();
                            }
                            else {
                                logLine = help.getArtifactId() + ":" + fileList[0].getName() + ":" + help.getFileName();
                            }
                        logList.add(logLine);
                        }
                }
                    }catch (Exception e) {
                    // TODO: handle exception
                }
                
            }
 
			
		
		
		
		
		
		
		
		
		
		
		
		}

		Files.write(manifestFilePath, entry, charset, StandardOpenOption.APPEND);
		
		
		
		
		
		
		
		
		if(command.equals("Create") || command.equals("Checkin")) {
	        Files.write(logPath, logList, chset, StandardOpenOption.APPEND);
	        }
		
		
		
		
		
		
		
		
	}
	
	/**
	 * Creates an "Activity" directory if it is not present at the path that is specified.
	 * @param pathToRepo - The path to verify/create the activity directory at.
	 * @throws IOException  If an I/O error occurs or the parent directory does not exist.
	 */
	public void CreateManifestDir(String pathToRepo) throws IOException{
		FileSystem manifestfs = FileSystems.getDefault();
		Path path = manifestfs.getPath(pathToRepo + FileSystems.getDefault().getSeparator() + "Manifest");
		
		if(!Files.exists(path)){
			Files.createDirectory(path);
		}
	}
}