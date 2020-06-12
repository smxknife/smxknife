package org.smxknife.easypoi.web.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

/**
 * @author smxknife
 * 2019/10/28
 */
@RestController
@RequestMapping("/temp")
@Slf4j
public class TemplateController {

	@RequestMapping
	public String temp() {

		ImportParams importParams = new ImportParams();
		importParams.setKeyMark("{{");
		importParams.setDictHandler(new MapDictHandler());
		importParams.setDataHandler(new MapImportHandler());
		ExcelImportUtil.importExcel(new File("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-easypoi/target/个人任务跟踪.xlsx"), Map.class, importParams);

		TemplateExportParams params = new TemplateExportParams("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-easypoi/target/个人任务跟踪.xlsx", true);
		IExcelDataHandler dataHandler = params.getDataHandler();
		Arrays.asList(dataHandler.getNeedHandlerFields()).forEach(System.out::println);
		return params.getTemplateUrl();
	}
}
