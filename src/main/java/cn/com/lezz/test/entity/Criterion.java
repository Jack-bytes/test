package cn.com.lezz.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 电价标准

 *
 * @author roger
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Criterion extends CoreEntity {

    private Integer cityId;

    private String groupId;

    private String criterionDate;

    private String section;

    private String criterionType;

    private BigDecimal tadPrice;

    private BigDecimal govFund;

    private BigDecimal categoryPeak;

    private BigDecimal categoryFlat;

    private BigDecimal categoryValley;

    private BigDecimal monthlyPrice;

    private BigDecimal lineLoss;

    private BigDecimal actPrice;

    /**
     * 浅克隆,仅对基本数据类型数据有效
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Criterion clone() throws CloneNotSupportedException {

        return (Criterion) new Criterion()
                .setCityId(this.cityId)
                .setGroupId(this.groupId)
                .setCriterionDate(this.criterionDate)
                .setSection(this.section)
                .setCriterionType(this.criterionType)
                .setTadPrice(this.tadPrice)
                .setGovFund(this.govFund)
                .setCategoryPeak(this.categoryPeak)
                .setCategoryFlat(this.categoryFlat)
                .setCategoryValley(this.categoryValley)
                .setMonthlyPrice(this.monthlyPrice)
                .setLineLoss(this.lineLoss)
                .setActPrice(this.actPrice)
                .setState(getState())
                .setRemark(getRemark());
    }

}
