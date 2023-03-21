package org.roda_project.commons_ip2.validator.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.roda_project.commons_ip2.validator.constants.Constants;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class Message {

  private Message() {
    // do nothing
  }

  /**
   * Generates an error message.
   * 
   * @param message
   *          The message.
   * @param path
   *          The path to add to the message
   * @param isRootMets
   *          Flag if is root mets or not.
   * @return {@link String}.
   */
  public static String createErrorMessage(final String message, final String path, final boolean isRootMets) {
    return String.format(message, isRootMets ? "Root METS.xml" : path);
  }

  /**
   * Creates message with number of files missing in some validation methods.
   *
   * @param missedMetadataFiles
   *          the {@link Map} with the missing files.
   * @param initialMessage
   *          the {@link String} with the initial message
   * @param isZip
   *          flag if IP is in zip format or is a folder.
   * @param split
   *          the {@link String} with the path we don't want in the message.
   * @return a message {@link String}.
   */
  public static String createMissingFilesMessage(final Map<String, Boolean> missedMetadataFiles,
    final String initialMessage, final boolean isZip, final String split) {
    final StringBuilder message = new StringBuilder();
    message.append(initialMessage);
    final int size = missedMetadataFiles.size();
    final int limit = Constants.LIMIT_MISSING_FILES;
    if (size < limit) {
      for (Map.Entry<String, Boolean> entry : missedMetadataFiles.entrySet()) {
        message.append(calculateInnerMessage(isZip, entry.getKey(), split));
      }
    } else {
      int i = 0;
      for (Map.Entry<String, Boolean> entry : missedMetadataFiles.entrySet()) {
        if (i != limit) {
          message.append(calculateInnerMessage(isZip, entry.getKey(), split));
        } else {
          break;
        }
        i++;
      }
    }
    message.append(" and ").append(size - limit).append(" more ").append("in %1$s");
    return message.toString();
  }

  private static String calculateInnerMessage(final boolean isZip, final String key, final String split) {
    final StringBuilder message = new StringBuilder();
    Path keyPath = Paths.get(key);
    Path splitPath = Paths.get(split);
    String subtracted = splitPath.relativize(keyPath).toString();

    if (isZip) {
      message.append(subtracted.startsWith(Constants.SEPARATOR) ? subtracted
        : Constants.SEPARATOR + subtracted).append(", ");
    } else {
      message.append(subtracted);
    }
    return message.toString();
  }
}
