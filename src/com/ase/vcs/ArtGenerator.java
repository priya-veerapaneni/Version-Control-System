package com.ase.vcs;

import java.io.*;

/**This program creates artifact ID by calculating checksum and appending it with the size and extension of the file.
 * The resulting name is the "Artifact name".
 * Created by Sankaran Shanmugavelayudam(sankaran22.5@gmail.com)
 * @version 1.0
 */
class ArtGenerator {
    private static long [] checksum_weights = {1, 7, 11, 17};	//Weights for calculating weighted checksum
    
    /**
     * Generate the artifact ID
     * @param file : The file whose artifact id is to be created
     * @return id
     */
    private static long calcArtID(File file) {
        long id = 0;
        int current;
        int checksumLength = checksum_weights.length;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            for (int i=0; (current = bufferedReader.read()) > 0; i++) {
                id = id + current * checksum_weights[i % checksumLength];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }
    
    /**
     * Append Artifact ID with size and extension
     * @param file : The file whose artifact name is to be returned
     * @return Artifact name
     */
    static String getArtName(File file) {
        long id = calcArtID(file);
        String fileExt = getFile_Ext(file.getName());

        return String.valueOf(id) + "-L" +
                file.length() + "." +
                fileExt;
    }
    
    /**
     * Returns file extension
     * @param fileName
     * @return extension: Extension of the file
     */
    private static String getFile_Ext(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }
}
