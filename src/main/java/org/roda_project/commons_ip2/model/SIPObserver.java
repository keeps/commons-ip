/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

public interface SIPObserver {

  void sipBuildRepresentationsProcessingStarted(int totalNumberOfRepresentations);

  void sipBuildRepresentationProcessingStarted(int totalNumberOfFiles);

  void sipBuildRepresentationProcessingCurrentStatus(int numberOfFilesAlreadyProcessed);

  void sipBuildRepresentationProcessingEnded();

  void sipBuildRepresentationsProcessingEnded();

  void sipBuildPackagingStarted(int totalNumberOfFiles);

  void sipBuildPackagingCurrentStatus(int numberOfFilesAlreadyProcessed);

  void sipBuildPackagingEnded();

}
