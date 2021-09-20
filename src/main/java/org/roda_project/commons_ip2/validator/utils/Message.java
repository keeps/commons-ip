package org.roda_project.commons_ip2.validator.utils;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class Message {

  private Message() { /* do nothing */ };

  public static String createErrorMessage(String message,String path, boolean isRootMets){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(message);
    stringBuilder.append("(");
    if(isRootMets) {
      stringBuilder.append("Root METS.xml");
    }
    else{
      stringBuilder.append(path);
    }
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
}
