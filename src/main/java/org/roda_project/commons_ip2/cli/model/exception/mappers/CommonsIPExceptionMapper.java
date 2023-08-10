package org.roda_project.commons_ip2.cli.model.exception.mappers;

import org.roda_project.commons_ip2.cli.model.exception.CLIException;
import org.roda_project.commons_ip2.cli.model.exception.InvalidPathException;
import org.roda_project.commons_ip2.cli.model.exception.SIPBuilderException;
import org.roda_project.commons_ip2.cli.model.ExitCodes;
import org.roda_project.commons_ip2.cli.model.exception.ValidationException;
import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class CommonsIPExceptionMapper implements CommandLine.IExitCodeExceptionMapper {
  @Override
  public int getExitCode(Throwable t) {
    if (t instanceof InvalidPathException) {
      return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
    } else if (t instanceof CLIException) {
      return ExitCodes.EXIT_CODE_CREATE_MISSING_ARGS;
    } else if (t instanceof SIPBuilderException) {
      return ExitCodes.EXIT_CODE_CREATE_CANNOT_SIP;
    } else if (t instanceof ValidationException) {
      return ExitCodes.EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT;
    }

    return 1;
  }
}
