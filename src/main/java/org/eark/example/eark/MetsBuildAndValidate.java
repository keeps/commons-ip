package org.eark.example.eark;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.eark.model.SIP;
import org.eark.model.SIPAgent;
import org.eark.model.SIPDescriptiveMetadata;
import org.eark.model.SIPMetadata;
import org.eark.model.SIPRepresentation;
import org.eark.model.impl.eark.EARKSIP_OLD;
import org.eark.model.impl.eark.EARKSIP;
import org.eark.utils.METSEnums.CreatorType;
import org.eark.utils.METSEnums.MetadataType;
import org.eark.validation.Validator;
import org.eark.validation.impl.eark.EARKValidator;
import org.eark.validation.model.ValidationIssue;
import org.eark.validation.model.ValidationReport;

public class MetsBuildAndValidate {
  public static void main(String[] args) {
    try {
      SIP sip = new EARKSIP("objectID", "profile", "type");

      SIPMetadata logMetadata = new SIPMetadata(Paths.get("data/earkweb.log"), null);
      sip.addMetadata(logMetadata);

      SIPDescriptiveMetadata dcMetadata = new SIPDescriptiveMetadata(Paths.get("data/dc.xml"), Paths.get("data/dc.xsd"),
        MetadataType.DC);
      SIPDescriptiveMetadata textMetadata = new SIPDescriptiveMetadata(Paths.get("data/descriptive.txt"), null,
        MetadataType.TEXTMD);
      sip.addDescriptiveMetadata(textMetadata);
      sip.addDescriptiveMetadata(dcMetadata);

      SIPAgent agent1 = new SIPAgent("AgentName", "ROLE", CreatorType.INDIVIDUAL, "OTHER ROLE", "OTHER TYPE");
      SIPAgent agent2 = new SIPAgent("AgentName2", "ROLE2", CreatorType.INDIVIDUAL, "OTHER ROLE2", "OTHER TYPE2");
      sip.addAgent(agent1);
      sip.addAgent(agent2);

      SIPRepresentation representation1 = new SIPRepresentation("rep1", "repId1", "profile", "repType");
      sip.addRepresentation(representation1);
      sip.addDataToRepresentation("rep1", Paths.get("data/bike.gif"));
      sip.addDataToRepresentation("rep1", Paths.get("data/data.txt"));
      SIPRepresentation representation2 = new SIPRepresentation("rep2", "repId2", "profile", "repType");
      representation2.setData(Arrays.asList(Paths.get("data/bike.gif"), Paths.get("data/data.txt")));
      sip.addRepresentation(representation2);

      SIPMetadata premis = new SIPMetadata(Paths.get("data/premis.xml"), Paths.get("data/premis-v2-2.xsd"));
      sip.addPreservationToRepresentation("rep1", premis);
      SIPAgent agent = new SIPAgent("AgentName2", "ROLE2", CreatorType.INDIVIDUAL, "OTHER ROLE2", "OTHER TYPE2");
      sip.addAgentToRepresentation("rep1", agent);
      Path zip = sip.createZip();

      Validator validator = new EARKValidator();
      ValidationReport report = validator.isSIPValid(zip);
      System.out.println("Valid: " + report.isValid());
      if (report.getIssues() != null && report.getIssues().size() > 0) {
        for (ValidationIssue issue : report.getIssues()) {
          System.out.println(issue.getLevel().toString() + " - " + issue.getMessage());
        }
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
/*
    try {
      Validator validator = new EARKValidator();
      ValidationReport report = validator.isSIPValid(Paths.get("examples/SIP1"));
      System.out.println("Valid: " + report.isValid());
      if (report.getIssues() != null && report.getIssues().size() > 0) {
        for (ValidationIssue issue : report.getIssues()) {
          System.out.println("------------------");
          System.out.println(issue.getLevel().toString() + " - " + issue.getMessage());
          System.out.println("Description");
          System.out.println(issue.getDescription());
          System.out.println("------------------");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
*/
  }
}
