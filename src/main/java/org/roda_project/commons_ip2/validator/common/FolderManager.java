package org.roda_project.commons_ip2.validator.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class FolderManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FolderManager.class);

    private File folder = null;

    public boolean checkIfExistsRootMetsFile(Path path){
        boolean found = false;
        folder = path.toFile();
        for(File f: folder.listFiles()){
            if(f.getName().equals("METS.xml")){
                found = true;
            }
        }
        return found;
    }

    public InputStream getMetsRootInputStream(Path path) throws FileNotFoundException {
        folder = path.toFile();
        String metsPath = null;
        for(File f: folder.listFiles()){
            if(f.getName().equals("METS.xml")){
                metsPath = f.getPath();
            }
        }
        if(metsPath == null){
            LOGGER.debug("METS.xml not Found");
            throw new FileNotFoundException("METS.xml not Found");
        }
        return new FileInputStream(metsPath);
    }

    public String getSipRootFolderName(Path path){
        String[] tmp = path.toString().split("/");
        return tmp[tmp.length-1];
    }

    public boolean checkPathExists(Path path, Path filePath) throws IOException{
        String regex = path.toString() + "/";
        return Files.walk(path).anyMatch(p ->{
            String[] tmp = p.toString().split(regex);
            String relativePath = tmp[tmp.length-1];
            return relativePath.equals(filePath.toString());
        });
    }
}
