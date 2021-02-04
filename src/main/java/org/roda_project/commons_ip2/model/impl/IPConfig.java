/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl;

public class IPConfig {
  // 20201013 hsilva: by default, for backward compatibility, strict mode is off
  private boolean strictMode = false;
  private boolean schematronValidation = false;
  private boolean encodeDecodeHref = true;
  private boolean reportInfo = true;
  private boolean reportWarn = true;

  public IPConfig() {
    super();
  }

  public boolean isStrictMode() {
    return strictMode;
  }

  public IPConfig enableStrictMode() {
    strictMode = true;
    return this;
  }

  public boolean isSchematronValidation() {
    return schematronValidation;
  }

  public IPConfig enableSchematronValidation() {
    schematronValidation = true;
    return this;
  }

  public boolean isEncodeDecodeHref() {
    return encodeDecodeHref;
  }

  public IPConfig disableEncodeDecodeHref() {
    this.encodeDecodeHref = false;
    return this;
  }

  public boolean isReportInfo() {
    return reportInfo;
  }

  public IPConfig disableReportInfo() {
    this.reportInfo = false;
    return this;
  }

  public boolean isReportWarn() {
    return reportWarn;
  }

  public IPConfig disableReportWarn() {
    this.reportWarn = false;
    return this;
  }

}
