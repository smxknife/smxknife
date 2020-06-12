package org.smxknife.easypoi.web.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2019/10/28
 */
@RestController
@RequestMapping("/dynamic")
public class ExcelExportEntityDynamicController {

	@RequestMapping("export")
	public String export() {
		List<ExcelExportEntity> list = new ArrayList<>();

		//构造对象等同于@Excel
		ExcelExportEntity nameEntity = new ExcelExportEntity("姓名", "name");
		nameEntity.setNeedMerge(true);
		list.add(nameEntity);

		list.add(new ExcelExportEntity("性别", "sex"));
		nameEntity = new ExcelExportEntity(null, "student");

		//构造List等同于@ExcelCollection
		List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
		temp.add(new ExcelExportEntity("姓名", "name"));
		temp.add(new ExcelExportEntity("性别", "sex"));
		nameEntity.setList(temp);

		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
//把我们构造好的bean对象放到params就可以了
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试title", "测试sheet"), list,
				list1);

		try(FileOutputStream fos = new FileOutputStream("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-easypoi/target/tt.xls")) {
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "nn";
	}
}
