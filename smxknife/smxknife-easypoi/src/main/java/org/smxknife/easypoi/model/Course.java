package org.smxknife.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author smxknife
 * 2019/10/24
 */
@ExcelTarget("course")
@Getter
@Setter
public class Course implements Serializable {

	private String id;

	@Excel(name = "课程名字", orderNum = "1", width = 25, needMerge = true)
	private String name;

	@ExcelEntity(id = "chinese")
	private Teacher chineseTeacher;

	@ExcelEntity(id = "math")
	private Teacher mathTeacher;

	@ExcelEntity(id = "english")
	private Teacher englishTeacher;

	@ExcelCollection(name = "选课学生", orderNum = "4")
	private List<Student> students;

}
