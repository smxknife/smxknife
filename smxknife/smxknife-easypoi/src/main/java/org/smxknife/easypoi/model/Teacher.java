package org.smxknife.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author smxknife
 * 2019/10/24
 */
@ExcelTarget("teacher")
@Getter
@Setter
public class Teacher implements Serializable {
	private String id;

	@Excel(name = "语文老师_eng,数学老师_math", orderNum = "1", needMerge = true, isImportField = "true_eng,true_math")
	private String name;
}
