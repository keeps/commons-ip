/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip2.model.impl.IPConfig;

public abstract class SIP extends IP {
  private final List<SIPObserver> observers;

  public SIP() {
    super();
    observers = new ArrayList<>();
  }

  public SIP(String sipId) {
    super();
    setId(sipId);
    setType(IPType.SIP);
    observers = new ArrayList<>();
  }

  public SIP(String sipId, IPContentType contentType, IPContentInformationType contentInformationType) {
    super();
    setId(sipId);
    setType(IPType.SIP);
    setContentType(contentType);
    setContentInformationType(contentInformationType);
    observers = new ArrayList<>();
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

  public static SIP parse(Path source, IPConfig ipConfig) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public static SIP parse(Path source, Path destinationDirectory, IPConfig ipConfig) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}
