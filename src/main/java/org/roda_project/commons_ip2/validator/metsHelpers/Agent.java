package org.roda_project.commons_ip2.validator.metsHelpers;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class Agent {
    private String role = null;
    private String otherRole = null;
    private String type = null;
    private String otherType = null;
    private String noteType = null;
    private String name = null;

    public Agent() {
    }

    public String getRole() {
        return role;
    }

    public String getOtherRole() {
        return otherRole;
    }

    public String getType() {
        return type;
    }

    public String getOtherType() {
        return otherType;
    }

    public String getNoteType() {
        return noteType;
    }

    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setOtherRole(String otherRole) {
        this.otherRole = otherRole;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "role='" + role + '\'' +
                ", otherRole='" + otherRole + '\'' +
                ", type='" + type + '\'' +
                ", otherType='" + otherType + '\'' +
                ", noteType='" + noteType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
