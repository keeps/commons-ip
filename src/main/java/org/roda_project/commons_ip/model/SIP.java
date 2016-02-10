/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.utils.EARKEnums.ContentType;
import org.roda_project.commons_ip.utils.EARKEnums.Type;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;

public abstract class SIP extends IP {
  private final List<SIPObserver> observers;

  public SIP() {
    super();
    observers = new ArrayList<SIPObserver>();
  }

  public SIP(String sipName) {
    super();
    setId(sipName);
    setType(Type.SIP);

    observers = new ArrayList<SIPObserver>();
  }

  public SIP(String sipName, ContentType contentType, String creator) {
    super();
    setId(sipName);
    setType(Type.SIP);
    setContentType(contentType);
    IPAgent creatorAgent = new IPAgent(creator, "CREATOR", null, CreatorType.OTHER, "SOFTWARE");
    getAgents().add(creatorAgent);

    observers = new ArrayList<SIPObserver>();
  }

  public void addObserver(SIPObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(SIPObserver observer) {
    observers.remove(observer);
  }

  public void notifySipBuildStarted(int totalNumberOfFiles) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildStarted(totalNumberOfFiles);
    }
  }

  public void notifySipBuildUpdated(int numberOfFilesAlreadyProcessed) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildCurrentStatus(numberOfFilesAlreadyProcessed);
    }
  }

  public void notifySipBuildEnded() {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildEnded();
    }
  }

}
