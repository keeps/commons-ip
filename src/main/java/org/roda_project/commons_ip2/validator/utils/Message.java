package org.roda_project.commons_ip2.validator.utils;

import java.util.Map;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public final class Message {

  private Message() {
    /* do nothing */ }

  public static String createErrorMessage(final String message, final String path, final boolean isRootMets) {
    return String.format(message, isRootMets ? "Root METS.xml" : path);
  }

  public static String createMissingFilesMessage(final Map<String, Boolean> missedMetadataFiles,
    final String initialMessage) {
    final StringBuilder message = new StringBuilder();
    message.append(initialMessage);
    final int size = missedMetadataFiles.size();
    final int limit = Constants.LIMIT_MISSING_FILES;
    if (size < limit) {
      for (Map.Entry<String, Boolean> entry : missedMetadataFiles.entrySet()) {
        message.append(entry.getKey()).append(", ");
      }
    } else {
      int i = 0;
      for (Map.Entry<String, Boolean> entry : missedMetadataFiles.entrySet()) {
        if (i != limit) {
          message.append(entry.getKey()).append(", ");
        } else {
          break;
        }
        i++;
      }
    }
    message.append("and ").append(size - limit).append(" more ").append("in %1$s");
    return message.toString();
  }
}
