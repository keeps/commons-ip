/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

public interface SIPObserver {

  public void sipBuildStarted(int totalNumberOfFiles);

  public void sipBuildCurrentStatus(int numberOfFilesAlreadyProcessed);

  public void sipBuildEnded();

}
