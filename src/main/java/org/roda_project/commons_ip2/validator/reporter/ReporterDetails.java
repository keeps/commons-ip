package org.roda_project.commons_ip2.validator.reporter;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ReporterDetails {
    private String message;
    private boolean valid;

    public ReporterDetails() {
        this.message = "";
        this.valid = true;
    }

    public ReporterDetails(String message, boolean valid) {
        this.message = message;
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
