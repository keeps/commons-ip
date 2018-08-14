/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

public interface SIPObserver {

  public void sipBuildRepresentationsProcessingStarted(int totalNumberOfRepresentations);

  public void sipBuildRepresentationProcessingStarted(int totalNumberOfFiles);

  public void sipBuildRepresentationProcessingCurrentStatus(int numberOfFilesAlreadyProcessed);

  public void sipBuildRepresentationProcessingEnded();

  public void sipBuildRepresentationsProcessingEnded();

  public void sipBuildPackagingStarted(int totalNumberOfFiles);

  public void sipBuildPackagingCurrentStatus(int numberOfFilesAlreadyProcessed);

  public void sipBuildPackagingEnded();

}
