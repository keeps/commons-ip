package org.roda_project.commons_ip2.model.impl.eark;

public class METSGeneratorFactory {

    public METSGeneratorFactory() {
        // empty constructor
    }

    public EARKMETSCreator getGenerator(String version) {
        if (version.equals("2.0.4")) {
            return new EARKMETSCreator204();
        }
        return new EARKMETSCreator210();
    }
}
