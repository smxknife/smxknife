package org.smxknife.easypoi.web.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.smxknife.easypoi.model.Course;
import org.smxknife.easypoi.model.Student;
import org.smxknife.easypoi.model.Teacher;
import org.smxknife.easypoi.util.MockDataUtil;
import org.smxknife.easypoi.view.ExportMoreView;
import org.smxknife.easypoi.view.ExportView;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author smxknife
 * 2019/10/25
 */
@RestController
@RequestMapping("/multi")
public class MultiSheetController {

	@RequestMapping("export")
	public String export(ModelMap modelMap) {

		List<Map<String, Object>> exportParamList = Lists.newArrayList();
		ExportMoreView exportMoreView = new ExportMoreView();

		// region MockData
		List<ExportView> viewList = new ArrayList<>();

		// 课程
		ExportView.Builder courseBuilder = new ExportView.Builder();
		courseBuilder.exportParams(new ExportParams("", "课程表"))
				.cls(Course.class);

		courseBuilder.dataList(MockDataUtil.mockCourses());
		ExportView courseExportView = courseBuilder.create();
		viewList.add(courseExportView);

		// 老师
		ExportView.Builder teacherBuilder = new ExportView.Builder();
		teacherBuilder.exportParams(new ExportParams("", "老师"))
				.cls(Teacher.class);
		teacherBuilder.dataList(MockDataUtil.mockTeachers());
		ExportView teacherExportView = teacherBuilder.create();
		viewList.add(teacherExportView);

		// 学生
		ExportView.Builder studentBuilder = new ExportView.Builder();
		studentBuilder.exportParams(new ExportParams("", "学生"))
				.cls(Student.class);
		studentBuilder.dataList(MockDataUtil.mockStudents());
		ExportView studentExportView = studentBuilder.create();
		viewList.add(studentExportView);

		exportMoreView.setMoreViewList(viewList);

		// endregion

		for (ExportView view : exportMoreView.getMoreViewList()) {
			Map<String, Object> valMap = new HashMap<>();
			valMap.put(NormalExcelConstants.PARAMS, view.getExportParams());
			valMap.put(NormalExcelConstants.DATA_LIST, view.getDataList());
			valMap.put(NormalExcelConstants.CLASS, view.getCls());
			exportParamList.add(valMap);
		}

//		modelMap.put(NormalExcelConstants.FILE_NAME, "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-easypoi/target/" + LocalDate.now().toString());
//		modelMap.put(NormalExcelConstants.MAP_LIST, exportParamList);
//
		Workbook workbook = exportExcel(exportParamList, ExcelType.HSSF);

		try(FileOutputStream fos = new FileOutputStream("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-easypoi/target/test.xls")) {
			workbook.write(fos);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
	}

	private Workbook exportExcel(List<Map<String, Object>> list, ExcelType type) {
		Workbook workbook = getWorkbook(type, 0);
		for (Map<String, Object> map : list) {
			ExcelExportService service = new ExcelExportService();
			service.createSheet(workbook, (ExportParams) map.get("params"), (Class<?>) map.get("entity"), (Collection<?>) map.get("data"));
		}
		return workbook;
	}

	private Workbook getWorkbook(ExcelType type, int size) {
		if (ExcelType.HSSF.equals(type)) {
			return new HSSFWorkbook();
		} else if (size < 100000) {
			return new XSSFWorkbook();
		} else {
			return new SXSSFWorkbook();
		}
	}
}
