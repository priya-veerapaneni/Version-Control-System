package com.ase.vcs;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Arrays;
import java.util.Comparator;

/**This program uses java.nio to walk a folder tree. It extends SimpleFileVisitor for basic behavior and overrides all necessary methods.
 * Created by Tejas Tundulwar(ttejas@rocketmail.com)
 * @version 1.0
 */

public class PTreeWalker extends SimpleFileVisitor<Path> {
    private String source;
    private String destination;
    private Stack<Path> destinationPathStack;
    private List<LoggingRegister> fileLogList;

    PTreeWalker(String source, String destinationPath) {
        this.source = source;
        this.destination = destinationPath;
        destinationPathStack = new Stack<>();
        fileLogList = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path sourcePath, BasicFileAttributes basicFileAttributes) {

        if (basicFileAttributes.isRegularFile()) {
            Path sourceFilePath= sourcePath.getFileName();
            String sourceFileName = sourceFilePath.toString();
        	String artifactName = ArtGenerator.getArtName(sourcePath.toFile());
     
            Path stackTopPath = destinationPathStack.peek();
            Path destinationPath = stackTopPath.resolve(sourceFileName);
            File targetFile = destinationPath.toFile();
            targetFile.mkdirs();
            destinationPath = destinationPath.resolve(artifactName);

            try {
                if (!destinationPath.toFile().exists()) {
                	fileLogList.add(new LoggingRegister(artifactName, sourceFileName, destinationPath.toString()));
                    Files.copy(sourcePath, destinationPath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        String folderName = dir.toString();
        folderName = folderName.replace(source, destination);
        destinationPathStack.push(Paths.get(folderName));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException ioException) {
        destinationPathStack.pop();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException ioException) {
        ioException.printStackTrace();
        return FileVisitResult.CONTINUE;
    }

    public List<LoggingRegister> getLogfileList() {
        return fileLogList;
    }
}