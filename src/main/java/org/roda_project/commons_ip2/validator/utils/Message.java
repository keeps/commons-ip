package org.roda_project.commons_ip2.validator.utils;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class Message {

  private Message() { /* do nothing */ };

  public static String createErrorMessage(String message,String path, boolean isRootMets){
    return String.format(message, isRootMets ? "Root METS.xml" : path);
  }
}
