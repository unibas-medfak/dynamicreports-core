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
package ch.unibas.medizin.dynamicreports.design.transformation;

import ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime;
import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.exception.DRDesignReportException;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalCellComponentAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.DRIGroup;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
class ConstantTransform {

    /**
     * <p>variableResetType.</p>
     *
     * @param resetType  a {@link ch.unibas.medizin.dynamicreports.report.constant.Evaluation} object.
     * @param resetGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     * @param accessor   a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     */
    public static ResetType variableResetType(Evaluation resetType, DRIGroup resetGroup, DesignTransformAccessor accessor) {
        if (resetType == null) {
            return ResetType.REPORT;
        }

        return switch (resetType) {
            case NONE -> ResetType.NONE;
            case REPORT -> ResetType.REPORT;
            case PAGE -> ResetType.PAGE;
            case COLUMN -> ResetType.COLUMN;
            case FIRST_GROUP -> {
                if (accessor.getGroupTransform().getFirstGroup() == null) {
                    yield ResetType.REPORT;
                }
                yield ResetType.GROUP;
            }
            case BEFORE_GROUP -> {
                if (accessor.getGroupTransform().getBeforeGroup(resetGroup) == null) {
                    yield ResetType.REPORT;
                }
                yield ResetType.GROUP;
            }
            case LAST_GROUP -> {
                if (accessor.getGroupTransform().getLastGroup() == null) {
                    yield ResetType.REPORT;
                }
                yield ResetType.GROUP;
            }
            case GROUP -> ResetType.GROUP;
            default -> throw new DRDesignReportException("Reset type " + resetType.name() + " not supported");
        };
    }

    /**
     * <p>variableResetGroup.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param resetType  a {@link ch.unibas.medizin.dynamicreports.report.constant.Evaluation} object.
     * @param resetGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     * @param accessor   a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public static DRIGroup variableResetGroup(String name, Evaluation resetType, DRIGroup resetGroup, DesignTransformAccessor accessor) throws DRException {
        if (resetType == null) {
            return null;
        }

        return switch (resetType) {
            case NONE, REPORT, PAGE, COLUMN -> {
                if (resetGroup != null) {
                    throw new DRException("Reset group for variable " + name + " is required only for reset type BEFORE_GROUP or GROUP");
                }
                yield null;
            }
            case FIRST_GROUP -> {
                if (resetGroup != null) {
                    throw new DRException("Reset group for variable " + name + " is required only for reset type BEFORE_GROUP or GROUP");
                }
                yield accessor.getGroupTransform().getFirstGroup();
            }
            case BEFORE_GROUP -> {
                if (resetGroup == null) {
                    throw new DRException("Reset group missing for variable " + name);
                }
                yield accessor.getGroupTransform().getBeforeGroup(resetGroup);
            }
            case LAST_GROUP -> {
                if (resetGroup != null) {
                    throw new DRException("Reset group for variable " + name + " is required only for reset type BEFORE_GROUP or GROUP");
                }
                yield accessor.getGroupTransform().getLastGroup();
            }
            case GROUP -> {
                if (resetGroup == null) {
                    throw new DRException("Reset group missing for variable " + name);
                }
                yield resetGroup;
            }
            default -> throw new DRDesignReportException("Reset group " + resetType.name() + " not supported");
        };
    }

    /**
     * <p>textFieldEvaluationTime.</p>
     *
     * @param evaluationTime  a {@link ch.unibas.medizin.dynamicreports.report.constant.Evaluation} object.
     * @param evaluationGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     * @param accessor        a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime} object.
     */
    public static EvaluationTime textFieldEvaluationTime(Evaluation evaluationTime, DRIGroup evaluationGroup, DesignTransformAccessor accessor) {
        if (evaluationTime == null) {
            return EvaluationTime.NOW;
        }

        return switch (evaluationTime) {
            case NONE -> EvaluationTime.NOW;
            case REPORT -> EvaluationTime.REPORT;
            case PAGE -> EvaluationTime.PAGE;
            case COLUMN -> EvaluationTime.COLUMN;
            case FIRST_GROUP -> {
                if (accessor.getGroupTransform().getFirstGroup() == null) {
                    yield EvaluationTime.REPORT;
                }
                yield EvaluationTime.GROUP;
            }
            case BEFORE_GROUP -> {
                if (accessor.getGroupTransform().getBeforeGroup(evaluationGroup) == null) {
                    yield EvaluationTime.REPORT;
                }
                yield EvaluationTime.GROUP;
            }
            case LAST_GROUP -> {
                if (accessor.getGroupTransform().getLastGroup() == null) {
                    yield EvaluationTime.REPORT;
                }
                yield EvaluationTime.GROUP;
            }
            case GROUP -> EvaluationTime.GROUP;
            default -> throw new DRDesignReportException("Evaluation time " + evaluationTime.name() + " not supported");
        };
    }

    /**
     * <p>textFieldEvaluationGroup.</p>
     *
     * @param evaluationTime  a {@link ch.unibas.medizin.dynamicreports.report.constant.Evaluation} object.
     * @param evaluationGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     * @param accessor        a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public static DRIGroup textFieldEvaluationGroup(Evaluation evaluationTime, DRIGroup evaluationGroup, DesignTransformAccessor accessor) throws DRException {
        if (evaluationTime == null) {
            return null;
        }

        return switch (evaluationTime) {
            case NONE, REPORT, PAGE, COLUMN -> {
                if (evaluationGroup != null) {
                    throw new DRException("Evaluation group for textField is required only for evaluation time BEFORE_GROUP or GROUP");
                }
                yield null;
            }
            case FIRST_GROUP -> {
                if (evaluationGroup != null) {
                    throw new DRException("Evaluation group for textField is required only for evaluation time BEFORE_GROUP or GROUP");
                }
                yield accessor.getGroupTransform().getFirstGroup();
            }
            case BEFORE_GROUP -> {
                if (evaluationGroup == null) {
                    throw new DRException("Evaluation group missing for textField");
                }
                yield accessor.getGroupTransform().getBeforeGroup(evaluationGroup);
            }
            case LAST_GROUP -> {
                if (evaluationGroup != null) {
                    throw new DRException("Evaluation group for textField is required only for evaluation time BEFORE_GROUP or GROUP");
                }
                yield accessor.getGroupTransform().getLastGroup();
            }
            case GROUP -> {
                if (evaluationGroup == null) {
                    throw new DRException("Evaluation group missing for textField");
                }
                yield evaluationGroup;
            }
            default ->
                    throw new DRDesignReportException("Evaluation group " + evaluationTime.name() + " not supported");
        };
    }

    /**
     * <p>toHorizontalCellComponentAlignment.</p>
     *
     * @param widthType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
     */
    public static HorizontalCellComponentAlignment toHorizontalCellComponentAlignment(ComponentDimensionType widthType) {
        if (widthType == null) {
            return null;
        }

        return switch (widthType) {
            case FIXED -> HorizontalCellComponentAlignment.LEFT;
            case FLOAT -> HorizontalCellComponentAlignment.FLOAT;
            case EXPAND -> HorizontalCellComponentAlignment.EXPAND;
            default ->
                    throw new DRDesignReportException("Component dimension type " + widthType.name() + " not supported");
        };
    }

    /**
     * <p>toVerticalCellComponentAlignment.</p>
     *
     * @param heightType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
     */
    public static VerticalCellComponentAlignment toVerticalCellComponentAlignment(ComponentDimensionType heightType) {
        if (heightType == null) {
            return null;
        }

        return switch (heightType) {
            case FIXED -> VerticalCellComponentAlignment.TOP;
            case FLOAT, EXPAND -> VerticalCellComponentAlignment.EXPAND;
            default ->
                    throw new DRDesignReportException("Component dimension type " + heightType.name() + " not supported");
        };
    }
}
