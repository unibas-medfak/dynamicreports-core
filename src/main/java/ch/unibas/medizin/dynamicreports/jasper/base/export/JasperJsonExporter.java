/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package ch.unibas.medizin.dynamicreports.jasper.base.export;

import ch.unibas.medizin.dynamicreports.jasper.definition.export.JasperIJsonExporter;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

public class JasperJsonExporter extends AbstractJasperExporter implements JasperIJsonExporter {
  @Serial
  private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

  private Boolean ignoreHyperlink;
  private Boolean flushOutput;
  private Boolean reportComponentsExportOnly;

  public void setIgnoreHyperlink(Boolean ignoreHyperlink) {
    this.ignoreHyperlink = ignoreHyperlink;
  }

  @Override
  public Boolean isIgnoreHyperlink() {
    return ignoreHyperlink;
  }

  public void setFlushOutput(Boolean flushOutput) {
    this.flushOutput = flushOutput;
  }

  @Override
  public Boolean isFlushOutput() {
    return flushOutput;
  }

  public void setReportComponentsExportOnly(Boolean reportComponentsExportOnly) {
    this.reportComponentsExportOnly = reportComponentsExportOnly;
  }

  @Override
  public Boolean isReportComponentsExportOnly() {
    return reportComponentsExportOnly;
  }

}
