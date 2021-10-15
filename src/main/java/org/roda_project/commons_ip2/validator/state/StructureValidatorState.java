package org.roda_project.commons_ip2.validator.state;

import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;

import java.nio.file.Path;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructureValidatorState {
    private ZipManager zipManager;
    private FolderManager folderManager;
    private boolean isZipFileFlag;
    private Path ipPath;

    public StructureValidatorState(Path ipPath){
        this.zipManager = new ZipManager();
        this.folderManager = new FolderManager();
        this.isZipFileFlag = false;
        this.ipPath = ipPath;
    }

    public ZipManager getZipManager() {
        return zipManager;
    }

    public FolderManager getFolderManager() {
        return folderManager;
    }

    public boolean isZipFileFlag() {
        return isZipFileFlag;
    }

    public void setZipFileFlag(boolean zipFileFlag) {
        isZipFileFlag = zipFileFlag;
    }

    public Path getIpPath(){
        return ipPath;
    }

    public void setIpPath(Path ipPath){
        this.ipPath = ipPath;
    }
}
