/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.model.impl.eark.EARKEnums.IPContentType;
import org.roda_project.commons_ip.model.impl.eark.EARKEnums.Type;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;

public abstract class SIP extends IP {
  private final List<SIPObserver> observers;

  public SIP() {
    super();
    observers = new ArrayList<SIPObserver>();
  }

  public SIP(String sipId) {
    super();
    setId(sipId);
    setType(Type.SIP);

    observers = new ArrayList<SIPObserver>();
  }

  public SIP(String sipId, IPContentType contentType, String creator) {
    super();
    setId(sipId);
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

  public void notifySipBuildRepresentationsProcessingStarted(int totalNumberOfRepresentations) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildRepresentationsProcessingStarted(totalNumberOfRepresentations);
    }
  }

  public void notifySipBuildRepresentationProcessingStarted(int totalNumberOfFiles) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildRepresentationProcessingStarted(totalNumberOfFiles);
    }
  }

  public void notifySipBuildRepresentationProcessingCurrentStatus(int numberOfFilesAlreadyProcessed) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildRepresentationProcessingCurrentStatus(numberOfFilesAlreadyProcessed);
    }
  }

  public void notifySipBuildRepresentationProcessingEnded() {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildRepresentationProcessingEnded();
    }
  }

  public void notifySipBuildRepresentationsProcessingEnded() {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildRepresentationsProcessingEnded();
    }
  }

  public void notifySipBuildPackagingStarted(int totalNumberOfFiles) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildPackagingStarted(totalNumberOfFiles);
    }
  }

  public void notifySipBuildPackagingCurrentStatus(int numberOfFilesAlreadyProcessed) {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildPackagingCurrentStatus(numberOfFilesAlreadyProcessed);
    }
  }

  public void notifySipBuildPackagingEnded() {
    for (SIPObserver sipObserver : observers) {
      sipObserver.sipBuildPackagingEnded();
    }
  }

  public static SIP parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public static SIP parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}
