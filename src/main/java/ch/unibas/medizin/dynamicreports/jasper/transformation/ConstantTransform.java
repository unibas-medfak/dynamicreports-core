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
package ch.unibas.medizin.dynamicreports.jasper.transformation;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.lowagie.text.pdf.PdfWriter;

import ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime;
import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.jasper.constant.BorderCollapse;
import ch.unibas.medizin.dynamicreports.jasper.constant.PdfPermission;
import ch.unibas.medizin.dynamicreports.jasper.constant.PdfVersion;
import ch.unibas.medizin.dynamicreports.jasper.constant.SizeUnit;
import ch.unibas.medizin.dynamicreports.jasper.exception.JasperDesignException;
import ch.unibas.medizin.dynamicreports.report.constant.AxisPosition;
import ch.unibas.medizin.dynamicreports.report.constant.BreakType;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabTotalPosition;
import ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HyperLinkTarget;
import ch.unibas.medizin.dynamicreports.report.constant.HyperLinkType;
import ch.unibas.medizin.dynamicreports.report.constant.ImageAnchorType;
import ch.unibas.medizin.dynamicreports.report.constant.ImageScale;
import ch.unibas.medizin.dynamicreports.report.constant.LineDirection;
import ch.unibas.medizin.dynamicreports.report.constant.LineSpacing;
import ch.unibas.medizin.dynamicreports.report.constant.LineStyle;
import ch.unibas.medizin.dynamicreports.report.constant.Markup;
import ch.unibas.medizin.dynamicreports.report.constant.MeterShape;
import ch.unibas.medizin.dynamicreports.report.constant.OrderType;
import ch.unibas.medizin.dynamicreports.report.constant.Orientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PdfPrintScaling;
import ch.unibas.medizin.dynamicreports.report.constant.PdfaConformance;
import ch.unibas.medizin.dynamicreports.report.constant.Position;
import ch.unibas.medizin.dynamicreports.report.constant.RectangleAnchor;
import ch.unibas.medizin.dynamicreports.report.constant.Rotation;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.constant.ScaleType;
import ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation;
import ch.unibas.medizin.dynamicreports.report.constant.SplitType;
import ch.unibas.medizin.dynamicreports.report.constant.StretchType;
import ch.unibas.medizin.dynamicreports.report.constant.TabStopAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.TableOrder;
import ch.unibas.medizin.dynamicreports.report.constant.TextAdjust;
import ch.unibas.medizin.dynamicreports.report.constant.TimePeriod;
import ch.unibas.medizin.dynamicreports.report.constant.ValueLocation;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import net.sf.jasperreports.charts.type.AxisPositionEnum;
import net.sf.jasperreports.charts.type.ChartTypeEnum;
import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.charts.type.MeterShapeEnum;
import net.sf.jasperreports.charts.type.PlotOrientationEnum;
import net.sf.jasperreports.charts.type.ScaleTypeEnum;
import net.sf.jasperreports.charts.type.TimePeriodEnum;
import net.sf.jasperreports.charts.type.ValueLocationEnum;
import net.sf.jasperreports.components.spiderchart.type.SpiderRotationEnum;
import net.sf.jasperreports.components.spiderchart.type.TableOrderEnum;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.analytics.dataset.BucketOrder;
import net.sf.jasperreports.engine.export.type.ImageAnchorTypeEnum;
import net.sf.jasperreports.engine.type.BreakTypeEnum;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.DatasetResetTypeEnum;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.FooterPositionEnum;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.LineDirectionEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;
import net.sf.jasperreports.engine.type.TextAdjustEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum;
import net.sf.jasperreports.export.type.HtmlBorderCollapseEnum;
import net.sf.jasperreports.export.type.HtmlSizeUnitEnum;
import net.sf.jasperreports.pdf.type.PdfPrintScalingEnum;
import net.sf.jasperreports.pdf.type.PdfVersionEnum;
import net.sf.jasperreports.pdf.type.PdfaConformanceEnum;

/**
 * <p>ConstantTransform class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class ConstantTransform {

    /**
     * <p>lineStyle.</p>
     *
     * @param lineStyle a {@link ch.unibas.medizin.dynamicreports.report.constant.LineStyle} object.
     * @return a {@link net.sf.jasperreports.engine.type.LineStyleEnum} object.
     */
    protected static LineStyleEnum lineStyle(final LineStyle lineStyle) {
        if (lineStyle == null) {
            return null;
        }

        return switch (lineStyle) {
            case SOLID -> LineStyleEnum.SOLID;
            case DASHED -> LineStyleEnum.DASHED;
            case DOTTED -> LineStyleEnum.DOTTED;
            case DOUBLE -> LineStyleEnum.DOUBLE;
        };
    }

    /**
     * <p>imageScale.</p>
     *
     * @param imageScale a {@link ch.unibas.medizin.dynamicreports.report.constant.ImageScale} object.
     * @return a {@link net.sf.jasperreports.engine.type.ScaleImageEnum} object.
     */
    protected static ScaleImageEnum imageScale(final ImageScale imageScale) {
        if (imageScale == null) {
            return null;
        }

        return switch (imageScale) {
            case CLIP, NO_RESIZE -> ScaleImageEnum.CLIP;
            case FILL_FRAME, FILL -> ScaleImageEnum.FILL_FRAME;
            case RETAIN_SHAPE, FILL_PROPORTIONALLY -> ScaleImageEnum.RETAIN_SHAPE;
            case REAL_HEIGHT -> ScaleImageEnum.REAL_HEIGHT;
            case REAL_SIZE -> ScaleImageEnum.REAL_SIZE;
        };
    }

    /**
     * <p>imageAnchorType.</p>
     *
     * @param imageAnchorType a {@link ch.unibas.medizin.dynamicreports.report.constant.ImageAnchorType} object.
     * @return a {@link net.sf.jasperreports.engine.export.type.ImageAnchorTypeEnum} object.
     */
    protected static ImageAnchorTypeEnum imageAnchorType(final ImageAnchorType imageAnchorType) {
        if (imageAnchorType == null) {
            return null;
        }

        return switch (imageAnchorType) {
            case MOVE_SIZE -> ImageAnchorTypeEnum.MOVE_SIZE;
            case MOVE_NO_SIZE -> ImageAnchorTypeEnum.MOVE_NO_SIZE;
            case NO_MOVE_NO_SIZE -> ImageAnchorTypeEnum.NO_MOVE_NO_SIZE;
        };
    }

    /**
     * <p>horizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a {@link net.sf.jasperreports.engine.type.HorizontalTextAlignEnum} object.
     */
    protected static HorizontalTextAlignEnum horizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment) {
        if (horizontalTextAlignment == null) {
            return null;
        }

        return switch (horizontalTextAlignment) {
            case LEFT -> HorizontalTextAlignEnum.LEFT;
            case CENTER -> HorizontalTextAlignEnum.CENTER;
            case RIGHT -> HorizontalTextAlignEnum.RIGHT;
            case JUSTIFIED -> HorizontalTextAlignEnum.JUSTIFIED;
        };
    }

    /**
     * <p>verticalTextAlignment.</p>
     *
     * @param verticalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.VerticalTextAlignment} object.
     * @return a {@link net.sf.jasperreports.engine.type.VerticalTextAlignEnum} object.
     */
    protected static VerticalTextAlignEnum verticalTextAlignment(final VerticalTextAlignment verticalTextAlignment) {
        if (verticalTextAlignment == null) {
            return null;
        }

        return switch (verticalTextAlignment) {
            case TOP -> VerticalTextAlignEnum.TOP;
            case MIDDLE -> VerticalTextAlignEnum.MIDDLE;
            case BOTTOM -> VerticalTextAlignEnum.BOTTOM;
            case JUSTIFIED -> VerticalTextAlignEnum.JUSTIFIED;
        };
    }

    /**
     * <p>horizontalImageAlignment.</p>
     *
     * @param horizontalImageAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment} object.
     * @return a {@link net.sf.jasperreports.engine.type.HorizontalImageAlignEnum} object.
     */
    protected static HorizontalImageAlignEnum horizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment) {
        if (horizontalImageAlignment == null) {
            return null;
        }

        return switch (horizontalImageAlignment) {
            case LEFT -> HorizontalImageAlignEnum.LEFT;
            case CENTER -> HorizontalImageAlignEnum.CENTER;
            case RIGHT -> HorizontalImageAlignEnum.RIGHT;
        };
    }

    /**
     * <p>verticalImageAlignment.</p>
     *
     * @param verticalImageAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.VerticalImageAlignment} object.
     * @return a {@link net.sf.jasperreports.engine.type.VerticalImageAlignEnum} object.
     */
    protected static VerticalImageAlignEnum verticalImageAlignment(final VerticalImageAlignment verticalImageAlignment) {
        if (verticalImageAlignment == null) {
            return null;
        }

        return switch (verticalImageAlignment) {
            case TOP -> VerticalImageAlignEnum.TOP;
            case MIDDLE -> VerticalImageAlignEnum.MIDDLE;
            case BOTTOM -> VerticalImageAlignEnum.BOTTOM;
        };
    }

    /**
     * <p>rotation.</p>
     *
     * @param rotation a {@link ch.unibas.medizin.dynamicreports.report.constant.Rotation} object.
     * @return a {@link net.sf.jasperreports.engine.type.RotationEnum} object.
     */
    protected static RotationEnum rotation(final Rotation rotation) {
        if (rotation == null) {
            return null;
        }

        return switch (rotation) {
            case NONE -> RotationEnum.NONE;
            case LEFT -> RotationEnum.LEFT;
            case RIGHT -> RotationEnum.RIGHT;
            case UPSIDE_DOWN -> RotationEnum.UPSIDE_DOWN;
        };
    }

    /**
     * <p>chartType.</p>
     *
     * @param chartType a {@link ch.unibas.medizin.dynamicreports.report.constant.ChartType} object.
     * @return a {@link java.lang.Byte} object.
     */
    protected static net.sf.jasperreports.charts.type.ChartTypeEnum chartType(final ChartType chartType) {
        return switch (chartType) {
            case AREA -> ChartTypeEnum.AREA;
            case STACKEDAREA -> ChartTypeEnum.STACKEDAREA;
            case BAR, LAYEREDBAR, WATERFALLBAR -> ChartTypeEnum.BAR;
            case BAR3D -> ChartTypeEnum.BAR3D;
            case STACKEDBAR, GROUPEDSTACKEDBAR -> ChartTypeEnum.STACKEDBAR;
            case STACKEDBAR3D -> ChartTypeEnum.STACKEDBAR3D;
            case LINE -> ChartTypeEnum.LINE;
            case PIE -> ChartTypeEnum.PIE;
            case PIE3D -> ChartTypeEnum.PIE3D;
            case TIMESERIES, DIFFERENCE -> ChartTypeEnum.TIMESERIES;
            case XYAREA -> ChartTypeEnum.XYAREA;
            case XYBAR -> ChartTypeEnum.XYBAR;
            case XYLINE, XYSTEP -> ChartTypeEnum.XYLINE;
            case SCATTER -> ChartTypeEnum.SCATTER;
            case MULTI_AXIS -> ChartTypeEnum.MULTI_AXIS;
            case XYBLOCK, BUBBLE -> ChartTypeEnum.BUBBLE;
            case CANDLESTICK -> ChartTypeEnum.CANDLESTICK;
            case HIGHLOW -> ChartTypeEnum.HIGHLOW;
            case METER -> ChartTypeEnum.METER;
            case THERMOMETER -> ChartTypeEnum.THERMOMETER;
            case GANTT -> ChartTypeEnum.GANTT;
            default -> throw new JasperDesignException("Chart " + chartType.name() + " not supported");
        };
    }

    /**
     * <p>whenNoDataType.</p>
     *
     * @param whenNoDataType a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType} object.
     * @return a {@link net.sf.jasperreports.engine.type.WhenNoDataTypeEnum} object.
     */
    protected static WhenNoDataTypeEnum whenNoDataType(final WhenNoDataType whenNoDataType) {
        return switch (whenNoDataType) {
            case NO_PAGES -> WhenNoDataTypeEnum.NO_PAGES;
            case BLANK_PAGE -> WhenNoDataTypeEnum.BLANK_PAGE;
            case ALL_SECTIONS_NO_DETAIL -> WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL;
            case NO_DATA_SECTION -> WhenNoDataTypeEnum.NO_DATA_SECTION;
        };
    }

    /**
     * <p>whenNoDataType.</p>
     *
     * @param whenNoDataType a {@link net.sf.jasperreports.engine.type.WhenNoDataTypeEnum} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType} object.
     */
    public static WhenNoDataType whenNoDataType(final WhenNoDataTypeEnum whenNoDataType) {
        if (whenNoDataType == null) {
            return null;
        }
        return switch (whenNoDataType) {
            case NO_PAGES -> WhenNoDataType.NO_PAGES;
            case BLANK_PAGE -> WhenNoDataType.BLANK_PAGE;
            case ALL_SECTIONS_NO_DETAIL -> WhenNoDataType.ALL_SECTIONS_NO_DETAIL;
            case NO_DATA_SECTION -> WhenNoDataType.NO_DATA_SECTION;
        };
    }

    /**
     * <p>whenResourceMissingType.</p>
     *
     * @param whenResourceMissingType a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType} object.
     * @return a {@link net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum} object.
     */
    protected static WhenResourceMissingTypeEnum whenResourceMissingType(final WhenResourceMissingType whenResourceMissingType) {
        return switch (whenResourceMissingType) {
            case NULL -> WhenResourceMissingTypeEnum.NULL;
            case EMPTY -> WhenResourceMissingTypeEnum.EMPTY;
            case KEY -> WhenResourceMissingTypeEnum.KEY;
            case ERROR -> WhenResourceMissingTypeEnum.ERROR;
        };
    }

    /**
     * <p>whenResourceMissingType.</p>
     *
     * @param whenResourceMissingType a {@link net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType} object.
     */
    public static WhenResourceMissingType whenResourceMissingType(final WhenResourceMissingTypeEnum whenResourceMissingType) {
        return switch (whenResourceMissingType) {
            case NULL -> WhenResourceMissingType.NULL;
            case EMPTY -> WhenResourceMissingType.EMPTY;
            case KEY -> WhenResourceMissingType.KEY;
            case ERROR -> WhenResourceMissingType.ERROR;
        };
    }

    /**
     * <p>pageOrientation.</p>
     *
     * @param orientation a {@link ch.unibas.medizin.dynamicreports.report.constant.PageOrientation} object.
     * @return a {@link net.sf.jasperreports.engine.type.OrientationEnum} object.
     */
    protected static OrientationEnum pageOrientation(final PageOrientation orientation) {
        return switch (orientation) {
            case PORTRAIT -> OrientationEnum.PORTRAIT;
            case LANDSCAPE -> OrientationEnum.LANDSCAPE;
        };
    }

    /**
     * <p>pageOrientation.</p>
     *
     * @param orientation a {@link net.sf.jasperreports.engine.type.OrientationEnum} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PageOrientation} object.
     */
    public static PageOrientation pageOrientation(final OrientationEnum orientation) {
        return switch (orientation) {
            case PORTRAIT -> PageOrientation.PORTRAIT;
            case LANDSCAPE -> PageOrientation.LANDSCAPE;
        };
    }

    /**
     * <p>variableResetType.</p>
     *
     * @param resetType a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @return a {@link net.sf.jasperreports.engine.type.ResetTypeEnum} object.
     */
    public static ResetTypeEnum variableResetType(final ResetType resetType) {
        if (resetType == null) {
            return ResetTypeEnum.NONE;
        }

        return switch (resetType) {
            case NONE -> ResetTypeEnum.NONE;
            case REPORT -> ResetTypeEnum.REPORT;
            case PAGE -> ResetTypeEnum.PAGE;
            case COLUMN -> ResetTypeEnum.COLUMN;
            case GROUP -> ResetTypeEnum.GROUP;
        };
    }

    public static DatasetResetTypeEnum variableDatasetResetType(final ResetType resetType) {
      if (resetType == null) {
          return DatasetResetTypeEnum.NONE;
      }

        return switch (resetType) {
            case NONE -> DatasetResetTypeEnum.NONE;
            case REPORT -> DatasetResetTypeEnum.REPORT;
            case PAGE -> DatasetResetTypeEnum.PAGE;
            case COLUMN -> DatasetResetTypeEnum.COLUMN;
            case GROUP -> DatasetResetTypeEnum.GROUP;
        };
  }

    /**
     * <p>evaluationTime.</p>
     *
     * @param evaluationTime a {@link ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime} object.
     * @return a {@link net.sf.jasperreports.engine.type.EvaluationTimeEnum} object.
     */
    public static EvaluationTimeEnum evaluationTime(final EvaluationTime evaluationTime) {
        if (evaluationTime == null) {
            return EvaluationTimeEnum.NOW;
        }

        return switch (evaluationTime) {
            case NOW -> EvaluationTimeEnum.NOW;
            case REPORT -> EvaluationTimeEnum.REPORT;
            case PAGE -> EvaluationTimeEnum.PAGE;
            case COLUMN -> EvaluationTimeEnum.COLUMN;
            case GROUP -> EvaluationTimeEnum.GROUP;
            case BAND -> EvaluationTimeEnum.BAND;
            case AUTO -> EvaluationTimeEnum.AUTO;
        };
    }

    /**
     * <p>splitType.</p>
     *
     * @param splitType a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     * @return a {@link net.sf.jasperreports.engine.type.SplitTypeEnum} object.
     */
    protected static SplitTypeEnum splitType(final SplitType splitType) {
        if (splitType == null) {
            return null;
        }
        return switch (splitType) {
            case IMMEDIATE -> SplitTypeEnum.IMMEDIATE;
            case PREVENT -> SplitTypeEnum.PREVENT;
            case STRETCH -> SplitTypeEnum.STRETCH;
        };
    }

    /**
     * <p>timePeriodType.</p>
     *
     * @param timePeriodType a {@link ch.unibas.medizin.dynamicreports.report.constant.TimePeriod} object.
     * @return a {@link java.lang.Class} object.
     */
    protected static TimePeriodEnum timePeriodType(final TimePeriod timePeriodType) {
        return switch (timePeriodType) {
            case YEAR -> TimePeriodEnum.YEAR;
            case QUARTER -> TimePeriodEnum.QUARTER;
            case MONTH -> TimePeriodEnum.MONTH;
            case WEEK -> TimePeriodEnum.WEEK;
            case DAY -> TimePeriodEnum.DAY;
            case HOUR -> TimePeriodEnum.HOUR;
            case MINUTE -> TimePeriodEnum.MINUTE;
            case SECOND -> TimePeriodEnum.SECOND;
            case MILLISECOND -> TimePeriodEnum.MILLISECOND;
        };
    }

    /**
     * <p>chartPlotOrientation.</p>
     *
     * @param orientation a {@link ch.unibas.medizin.dynamicreports.report.constant.Orientation} object.
     * @return a {@link net.sf.jasperreports.charts.type.PlotOrientationEnum} object.
     */
    protected static PlotOrientationEnum chartPlotOrientation(final Orientation orientation) {
        return switch (orientation) {
            case HORIZONTAL -> PlotOrientationEnum.HORIZONTAL;
            case VERTICAL -> PlotOrientationEnum.VERTICAL;
        };
    }

    /**
     * <p>chartPosition.</p>
     *
     * @param position a {@link ch.unibas.medizin.dynamicreports.report.constant.Position} object.
     * @return a {@link net.sf.jasperreports.charts.type.EdgeEnum} object.
     */
    protected static EdgeEnum chartPosition(final Position position) {
        if (position == null) {
            return null;
        }

        return switch (position) {
            case TOP -> EdgeEnum.TOP;
            case BOTTOM -> EdgeEnum.BOTTOM;
            case LEFT -> EdgeEnum.LEFT;
            case RIGHT -> EdgeEnum.RIGHT;
        };
    }

    /**
     * <p>chartAxisPosition.</p>
     *
     * @param axisPosition a {@link ch.unibas.medizin.dynamicreports.report.constant.AxisPosition} object.
     * @return a {@link net.sf.jasperreports.charts.type.AxisPositionEnum} object.
     */
    protected static AxisPositionEnum chartAxisPosition(final AxisPosition axisPosition) {
        if (axisPosition == null) {
            return null;
        }

        return switch (axisPosition) {
            case LEFT_OR_TOP -> AxisPositionEnum.LEFT_OR_TOP;
            case RIGHT_OR_BOTTOM -> AxisPositionEnum.RIGHT_OR_BOTTOM;
        };
    }

    /**
     * <p>meterShape.</p>
     *
     * @param meterShape a {@link ch.unibas.medizin.dynamicreports.report.constant.MeterShape} object.
     * @return a {@link net.sf.jasperreports.charts.type.MeterShapeEnum} object.
     */
    protected static MeterShapeEnum meterShape(final MeterShape meterShape) {
        if (meterShape == null) {
            return null;
        }

        return switch (meterShape) {
            case CHORD -> MeterShapeEnum.CHORD;
            case CIRCLE -> MeterShapeEnum.CIRCLE;
            case PIE -> MeterShapeEnum.PIE;
            case DIAL -> MeterShapeEnum.DIAL;
        };
    }

    /**
     * <p>valueLocation.</p>
     *
     * @param valueLocation a {@link ch.unibas.medizin.dynamicreports.report.constant.ValueLocation} object.
     * @return a {@link net.sf.jasperreports.charts.type.ValueLocationEnum} object.
     */
    protected static ValueLocationEnum valueLocation(final ValueLocation valueLocation) {
        if (valueLocation == null) {
            return null;
        }

        return switch (valueLocation) {
            case NONE -> ValueLocationEnum.NONE;
            case LEFT -> ValueLocationEnum.LEFT;
            case RIGHT -> ValueLocationEnum.RIGHT;
            case BULB -> ValueLocationEnum.BULB;
        };
    }

    /**
     * <p>calculation.</p>
     *
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     * @return a {@link net.sf.jasperreports.engine.type.CalculationEnum} object.
     */
    protected static CalculationEnum calculation(final Calculation calculation) {
        return switch (calculation) {
            case NOTHING -> CalculationEnum.NOTHING;
            case COUNT -> CalculationEnum.COUNT;
            case SUM -> CalculationEnum.SUM;
            case AVERAGE -> CalculationEnum.AVERAGE;
            case LOWEST -> CalculationEnum.LOWEST;
            case HIGHEST -> CalculationEnum.HIGHEST;
            case STANDARD_DEVIATION -> CalculationEnum.STANDARD_DEVIATION;
            case VARIANCE -> CalculationEnum.VARIANCE;
            case FIRST -> CalculationEnum.FIRST;
            case DISTINCT_COUNT -> CalculationEnum.DISTINCT_COUNT;
        };
    }

    /**
     * <p>sizeUnit.</p>
     *
     * @param sizeUnit a {@link ch.unibas.medizin.dynamicreports.jasper.constant.SizeUnit} object.
     * @return a {@link net.sf.jasperreports.export.type.HtmlSizeUnitEnum} object.
     */
    protected static HtmlSizeUnitEnum sizeUnit(final SizeUnit sizeUnit) {
        return switch (sizeUnit) {
            case PIXEL -> HtmlSizeUnitEnum.PIXEL;
            case POINT -> HtmlSizeUnitEnum.POINT;
        };
    }

    protected static HtmlBorderCollapseEnum borderCollapse(final BorderCollapse borderCollapse) {
        return switch (borderCollapse) {
            case SEPARATE -> HtmlBorderCollapseEnum.SEPARATE;
            case COLLAPSE -> HtmlBorderCollapseEnum.COLLAPSE;
            case INITIAL -> HtmlBorderCollapseEnum.INITIAL;
            case INHERIT -> HtmlBorderCollapseEnum.INHERIT;
        };
    }

    /**
     * <p>pdfVersion.</p>
     *
     * @param pdfVersion a {@link ch.unibas.medizin.dynamicreports.jasper.constant.PdfVersion} object.
     * @return a {@link net.sf.jasperreports.pdf.type.PdfVersionEnum} object.
     */
    protected static PdfVersionEnum pdfVersion(final PdfVersion pdfVersion) {
        return switch (pdfVersion) {
            case VERION_1_2 -> PdfVersionEnum.VERSION_1_2;
            case VERION_1_3 -> PdfVersionEnum.VERSION_1_3;
            case VERION_1_4 -> PdfVersionEnum.VERSION_1_4;
            case VERION_1_5 -> PdfVersionEnum.VERSION_1_5;
            case VERION_1_6 -> PdfVersionEnum.VERSION_1_6;
            case VERION_1_7 -> PdfVersionEnum.VERSION_1_7;
        };
    }

    /**
     * <p>pdfPermission.</p>
     *
     * @param permissions a {@link java.util.List} object.
     * @return a {@link java.lang.Integer} object.
     */
    protected static Integer pdfPermission(final List<PdfPermission> permissions) {
        final int permission = 0;
        for (final PdfPermission pdfPermission : permissions) {
            return switch (pdfPermission) {
                case PRINTING -> permission | PdfWriter.ALLOW_PRINTING;
                case MODIFY_CONTENTS -> permission | PdfWriter.ALLOW_MODIFY_CONTENTS;
                case COPY -> permission | PdfWriter.ALLOW_COPY;
                case MODIFY_ANNOTATIONS -> permission | PdfWriter.ALLOW_MODIFY_ANNOTATIONS;
                case FILL_IN -> permission | PdfWriter.ALLOW_FILL_IN;
                case SCREEN_READERS -> permission | PdfWriter.ALLOW_SCREENREADERS;
                case ASSEMBLY -> permission | PdfWriter.ALLOW_ASSEMBLY;
                case DEGRADED_PRINTING -> permission | PdfWriter.ALLOW_DEGRADED_PRINTING;
            };
        }
        return permission;
    }

    /**
     * <p>pdfPrintScaling.</p>
     *
     * @param pdfPrintScaling a {@link ch.unibas.medizin.dynamicreports.report.constant.PdfPrintScaling} object.
     * @return a {@link net.sf.jasperreports.pdf.type.PdfPrintScalingEnum} object.
     */
    protected static PdfPrintScalingEnum pdfPrintScaling(final PdfPrintScaling pdfPrintScaling) {
        return switch (pdfPrintScaling) {
            case NONE -> PdfPrintScalingEnum.NONE;
            case DEFAULT -> PdfPrintScalingEnum.DEFAULT;
        };
    }

    /**
     * <p>pdfaConformance.</p>
     *
     * @param pdfaConformance a {@link ch.unibas.medizin.dynamicreports.report.constant.PdfaConformance} object.
     * @return a {@link net.sf.jasperreports.pdf.type.PdfaConformanceEnum} object.
     */
    protected static PdfaConformanceEnum pdfaConformance(final PdfaConformance pdfaConformance) {
        return switch (pdfaConformance) {
            case NONE -> PdfaConformanceEnum.NONE;
            case PDFA_1A -> PdfaConformanceEnum.PDFA_1A;
            case PDFA_1B -> PdfaConformanceEnum.PDFA_1B;
        };
    }

    /**
     * <p>lineDirection.</p>
     *
     * @param lineDirection a {@link ch.unibas.medizin.dynamicreports.report.constant.LineDirection} object.
     * @return a {@link net.sf.jasperreports.engine.type.LineDirectionEnum} object.
     */
    public static LineDirectionEnum lineDirection(final LineDirection lineDirection) {
        if (lineDirection == null) {
            return null;
        }

        return switch (lineDirection) {
            case TOP_DOWN -> LineDirectionEnum.TOP_DOWN;
            case BOTTOM_UP -> LineDirectionEnum.BOTTOM_UP;
        };
    }

    /**
     * <p>markup.</p>
     *
     * @param markup a {@link ch.unibas.medizin.dynamicreports.report.constant.Markup} object.
     * @return a {@link java.lang.String} object.
     */
    public static String markup(final Markup markup) {
        if (markup == null) {
            return null;
        }

        return switch (markup) {
            case NONE -> "none";
            case STYLED -> "styled";
            case RTF -> "rtf";
            case HTML -> "html";
        };
    }

    /**
     * <p>lineSpacing.</p>
     *
     * @param lineSpacing a {@link ch.unibas.medizin.dynamicreports.report.constant.LineSpacing} object.
     * @return a {@link net.sf.jasperreports.engine.type.LineSpacingEnum} object.
     */
    public static LineSpacingEnum lineSpacing(final LineSpacing lineSpacing) {
        if (lineSpacing == null) {
            return null;
        }

        return switch (lineSpacing) {
            case SINGLE -> LineSpacingEnum.SINGLE;
            case ONE_AND_HALF -> LineSpacingEnum.ONE_AND_HALF;
            case DOUBLE -> LineSpacingEnum.DOUBLE;
            case AT_LEAST -> LineSpacingEnum.AT_LEAST;
            case FIXED -> LineSpacingEnum.FIXED;
            case PROPORTIONAL -> LineSpacingEnum.PROPORTIONAL;
        };
    }

    /**
     * <p>breakType.</p>
     *
     * @param breakType a {@link ch.unibas.medizin.dynamicreports.report.constant.BreakType} object.
     * @return a {@link net.sf.jasperreports.engine.type.BreakTypeEnum} object.
     */
    public static BreakTypeEnum breakType(final BreakType breakType) {
        if (breakType == null) {
            return null;
        }

        return switch (breakType) {
            case PAGE -> BreakTypeEnum.PAGE;
            case COLUMN -> BreakTypeEnum.COLUMN;
        };
    }

    /**
     * <p>runDirection.</p>
     *
     * @param runDirection a {@link ch.unibas.medizin.dynamicreports.report.constant.RunDirection} object.
     * @return a {@link net.sf.jasperreports.engine.type.RunDirectionEnum} object.
     */
    public static RunDirectionEnum runDirection(final RunDirection runDirection) {
        if (runDirection == null) {
            return null;
        }

        return switch (runDirection) {
            case LEFT_TO_RIGHT -> RunDirectionEnum.LTR;
            case RIGHT_TO_LEFT -> RunDirectionEnum.RTL;
        };
    }

    /**
     * <p>crosstabTotalPosition.</p>
     *
     * @param totalPosition a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabTotalPosition} object.
     * @return a {@link net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum} object.
     */
    public static CrosstabTotalPositionEnum crosstabTotalPosition(final CrosstabTotalPosition totalPosition) {
        if (totalPosition == null) {
            return CrosstabTotalPositionEnum.NONE;
        }

        return switch (totalPosition) {
            case START -> CrosstabTotalPositionEnum.START;
            case END -> CrosstabTotalPositionEnum.END;
        };
    }

    /**
     * <p>crosstabPercentageType.</p>
     *
     * @param percentageType a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType} object.
     * @return a {@link net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum} object.
     */
    public static CrosstabPercentageEnum crosstabPercentageType(final CrosstabPercentageType percentageType) {
        if (percentageType == null) {
            return null;
        }

        return switch (percentageType) {
            case NONE -> CrosstabPercentageEnum.NONE;
            case GRAND_TOTAL -> CrosstabPercentageEnum.GRAND_TOTAL;
        };
    }

    /**
     * <p>orderType.</p>
     *
     * @param orderType a {@link ch.unibas.medizin.dynamicreports.report.constant.OrderType} object.
     * @return a {@link net.sf.jasperreports.engine.type.SortOrderEnum} object.
     */
    public static SortOrderEnum orderType(final OrderType orderType) {
        if (orderType == null) {
            return null;
        }

        return switch (orderType) {
            case ASCENDING -> SortOrderEnum.ASCENDING;
            case DESCENDING -> SortOrderEnum.DESCENDING;
        };
    }

    /**
     * <p>bucketOrderType.</p>
     *
     * @param orderType a {@link ch.unibas.medizin.dynamicreports.report.constant.OrderType} object.
     * @return a {@link net.sf.jasperreports.engine.analytics.dataset.BucketOrder} object.
     */
    public static BucketOrder bucketOrderType(final OrderType orderType) {
        if (orderType == null) {
            return null;
        }

        return switch (orderType) {
            case ASCENDING -> BucketOrder.ASCENDING;
            case DESCENDING -> BucketOrder.DESCENDING;
        };
    }

    /**
     * <p>componentPositionType.</p>
     *
     * @param componentPositionType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType} object.
     * @return a {@link net.sf.jasperreports.engine.type.PositionTypeEnum} object.
     */
    public static PositionTypeEnum componentPositionType(final ComponentPositionType componentPositionType) {
        return switch (componentPositionType) {
            case FLOAT -> PositionTypeEnum.FLOAT;
            case FIX_RELATIVE_TO_TOP -> PositionTypeEnum.FIX_RELATIVE_TO_TOP;
            case FIX_RELATIVE_TO_BOTTOM -> PositionTypeEnum.FIX_RELATIVE_TO_BOTTOM;
        };
    }

    /**
     * <p>stretchType.</p>
     *
     * @param stretchType a {@link ch.unibas.medizin.dynamicreports.report.constant.StretchType} object.
     * @return a {@link net.sf.jasperreports.engine.type.StretchTypeEnum} object.
     */
    public static StretchTypeEnum stretchType(final StretchType stretchType) {
        if (stretchType == null) {
            return StretchTypeEnum.NO_STRETCH;
        }

        return switch (stretchType) {
            case NO_STRETCH -> StretchTypeEnum.NO_STRETCH;
            case RELATIVE_TO_BAND_HEIGHT, CONTAINER_HEIGHT -> StretchTypeEnum.CONTAINER_HEIGHT;
            case RELATIVE_TO_TALLEST_OBJECT, ELEMENT_GROUP_HEIGHT -> StretchTypeEnum.ELEMENT_GROUP_HEIGHT;
            case ELEMENT_GROUP_BOTTOM -> StretchTypeEnum.ELEMENT_GROUP_BOTTOM;
            case CONTAINER_BOTTOM -> StretchTypeEnum.CONTAINER_BOTTOM;
        };
    }

    /**
     * <p>hyperLinkType.</p>
     *
     * @param hyperLinkType a {@link java.lang.String} object.
     * @return a {@link net.sf.jasperreports.engine.type.HyperlinkTypeEnum} object.
     */
    public static HyperlinkTypeEnum hyperLinkType(final String hyperLinkType) {
        if (hyperLinkType == null || !EnumUtils.isValidEnum(HyperLinkType.class, hyperLinkType)) {
            return null;
        }

        final HyperLinkType type = HyperLinkType.valueOf(hyperLinkType);
        return switch (type) {
            case NONE -> HyperlinkTypeEnum.NONE;
            case REFERENCE -> HyperlinkTypeEnum.REFERENCE;
            case LOCAL_ANCHOR -> HyperlinkTypeEnum.LOCAL_ANCHOR;
            case LOCAL_PAGE -> HyperlinkTypeEnum.LOCAL_PAGE;
            case REMOTE_ANCHOR -> HyperlinkTypeEnum.REMOTE_ANCHOR;
            case REMOTE_PAGE -> HyperlinkTypeEnum.REMOTE_PAGE;
        };
    }

    /**
     * <p>hyperLinkTarget.</p>
     *
     * @param hyperLinkTarget a {@link java.lang.String} object.
     * @return a {@link net.sf.jasperreports.engine.type.HyperlinkTargetEnum} object.
     */
    public static HyperlinkTargetEnum hyperLinkTarget(final String hyperLinkTarget) {
        if (hyperLinkTarget == null || !EnumUtils.isValidEnum(HyperLinkTarget.class, hyperLinkTarget)) {
            return null;
        }

        final HyperLinkTarget target = HyperLinkTarget.valueOf(hyperLinkTarget);
        return switch (target) {
            case NONE -> HyperlinkTargetEnum.NONE;
            case SELF -> HyperlinkTargetEnum.SELF;
            case BLANK -> HyperlinkTargetEnum.BLANK;
            case PARENT -> HyperlinkTargetEnum.PARENT;
            case TOP -> HyperlinkTargetEnum.TOP;
        };
    }

    /**
     * <p>groupFooterPosition.</p>
     *
     * @param footerPosition a {@link ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition} object.
     * @return a {@link net.sf.jasperreports.engine.type.FooterPositionEnum} object.
     */
    public static FooterPositionEnum groupFooterPosition(final GroupFooterPosition footerPosition) {
        return switch (footerPosition) {
            case NORMAL -> FooterPositionEnum.NORMAL;
            case COLLATE_AT_BOTTOM -> FooterPositionEnum.COLLATE_AT_BOTTOM;
            case FORCE_AT_BOTTOM -> FooterPositionEnum.FORCE_AT_BOTTOM;
            case STACK_AT_BOTTOM -> FooterPositionEnum.STACK_AT_BOTTOM;
        };
    }

    /**
     * <p>spiderRotation.</p>
     *
     * @param rotation a {@link ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation} object.
     * @return a {@link net.sf.jasperreports.components.spiderchart.type.SpiderRotationEnum} object.
     */
    public static SpiderRotationEnum spiderRotation(final SpiderRotation rotation) {
        if (rotation == null) {
            return null;
        }

        return switch (rotation) {
            case CLOCKWISE -> SpiderRotationEnum.CLOCKWISE;
            case ANTICLOCKWISE -> SpiderRotationEnum.ANTICLOCKWISE;
        };
    }

    /**
     * <p>tableOrder.</p>
     *
     * @param tableOrder a {@link ch.unibas.medizin.dynamicreports.report.constant.TableOrder} object.
     * @return a {@link net.sf.jasperreports.components.spiderchart.type.TableOrderEnum} object.
     */
    public static TableOrderEnum tableOrder(final TableOrder tableOrder) {
        if (tableOrder == null) {
            return null;
        }

        return switch (tableOrder) {
            case BY_ROW -> TableOrderEnum.BY_ROW;
            case BY_COLUMN -> TableOrderEnum.BY_COLUMN;
        };
    }

    /**
     * <p>tabStopAlignment.</p>
     *
     * @param alignment a {@link ch.unibas.medizin.dynamicreports.report.constant.TabStopAlignment} object.
     * @return a {@link net.sf.jasperreports.engine.type.TabStopAlignEnum} object.
     */
    public static TabStopAlignEnum tabStopAlignment(final TabStopAlignment alignment) {
        return switch (alignment) {
            case LEFT -> TabStopAlignEnum.LEFT;
            case CENTER -> TabStopAlignEnum.CENTER;
            case RIGHT -> TabStopAlignEnum.RIGHT;
        };
    }

    /**
     * <p>printOrder.</p>
     *
     * @param printOrder a {@link ch.unibas.medizin.dynamicreports.report.constant.Orientation} object.
     * @return a {@link net.sf.jasperreports.engine.type.PrintOrderEnum} object.
     */
    protected static PrintOrderEnum printOrder(final Orientation printOrder) {
        return switch (printOrder) {
            case HORIZONTAL -> PrintOrderEnum.HORIZONTAL;
            case VERTICAL -> PrintOrderEnum.VERTICAL;
        };
    }

    /**
     * <p>scaleType.</p>
     *
     * @param scaleType a {@link ch.unibas.medizin.dynamicreports.report.constant.ScaleType} object.
     * @return a {@link net.sf.jasperreports.charts.type.ScaleTypeEnum} object.
     */
    public static ScaleTypeEnum scaleType(final ScaleType scaleType) {
        return switch (scaleType) {
            case ON_BOTH_AXES -> ScaleTypeEnum.ON_BOTH_AXES;
            case ON_DOMAIN_AXIS -> ScaleTypeEnum.ON_DOMAIN_AXIS;
            case ON_RANGE_AXIS -> ScaleTypeEnum.ON_RANGE_AXIS;
        };
    }

    /**
     * <p>rectangleAnchor.</p>
     *
     * @param rectangleAnchor a {@link ch.unibas.medizin.dynamicreports.report.constant.RectangleAnchor} object.
     * @return a {@link org.jfree.chart.ui.RectangleAnchor} object.
     */
    public static org.jfree.chart.ui.RectangleAnchor rectangleAnchor(final RectangleAnchor rectangleAnchor) {
        return switch (rectangleAnchor) {
            case CENTER -> org.jfree.chart.ui.RectangleAnchor.CENTER;
            case TOP -> org.jfree.chart.ui.RectangleAnchor.TOP;
            case TOP_LEFT -> org.jfree.chart.ui.RectangleAnchor.TOP_LEFT;
            case TOP_RIGHT -> org.jfree.chart.ui.RectangleAnchor.TOP_RIGHT;
            case BOTTOM -> org.jfree.chart.ui.RectangleAnchor.BOTTOM;
            case BOTTOM_LEFT -> org.jfree.chart.ui.RectangleAnchor.BOTTOM_LEFT;
            case BOTTOM_RIGHT -> org.jfree.chart.ui.RectangleAnchor.BOTTOM_RIGHT;
            case LEFT -> org.jfree.chart.ui.RectangleAnchor.LEFT;
            case RIGHT -> org.jfree.chart.ui.RectangleAnchor.RIGHT;
        };
    }

    /**
     * <p>textAdjust.</p>
     *
     * @param textAdjust a {@link ch.unibas.medizin.dynamicreports.report.constant.TextAdjust} object.
     * @return a {@link net.sf.jasperreports.engine.type.TextAdjustEnum} object.
     */
    public static TextAdjustEnum textAdjust(final TextAdjust textAdjust) {
        return switch (textAdjust) {
            case CUT_TEXT -> TextAdjustEnum.CUT_TEXT;
            case SCALE_FONT -> TextAdjustEnum.SCALE_FONT;
            case STRETCH_HEIGHT -> TextAdjustEnum.STRETCH_HEIGHT;
        };
    }
}
