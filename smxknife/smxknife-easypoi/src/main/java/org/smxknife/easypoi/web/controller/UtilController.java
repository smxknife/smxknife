package org.smxknife.easypoi.web.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.smxknife.easypoi.model.Course;
import org.smxknife.easypoi.model.Student;
import org.smxknife.easypoi.model.Teacher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2019/10/24
 */
@RestController
@RequestMapping("/util")
public class UtilController {

	@RequestMapping("export")
	public String export() {

		String msg = "";

		List<Student> students = IntStream.iterate(0, i -> i + 1).limit(20)
				.mapToObj(studIdx -> {
					Student student = new Student();
					student.setId(studIdx + "");
					student.setName("学生-" + studIdx);
					return student;
				}).collect(Collectors.toList());

		List<Course> courses = IntStream.iterate(0, i -> i + 1).limit(10)
				.mapToObj(idx -> {
					Course course = new Course();
					course.setId(idx + "");
					course.setName("课程-" + idx);

					Teacher chinese = new Teacher();
					chinese.setId("chinese");
					chinese.setName("语文老师");
					course.setChineseTeacher(chinese);

					Teacher math = new Teacher();
					math.setId("math");
					math.setName("数学老师");
					course.setMathTeacher(math);

					course.setStudents(students);

					return course;
				}).collect(Collectors.toList());

		Workbook workbook = ExcelExportUtil.exportExcel(
				new ExportParams("标题", "副标题", "sheet名字"),
				Course.class,
				courses);

		try(FileOutputStream fos = new FileOutputStream("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-easypoi/target/测试导出.xls")) {
			workbook.write(fos);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			msg = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}

		return "导出完毕" + msg;
	}
}
