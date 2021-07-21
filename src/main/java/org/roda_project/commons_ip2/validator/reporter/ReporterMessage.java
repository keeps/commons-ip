package org.roda_project.commons_ip2.validator.reporter;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ReporterMessage {
    private String message;
    private boolean valid;

    public ReporterMessage() {
        this.message = "";
        this.valid = true;
    }

    public ReporterMessage(String message, boolean valid) {
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
