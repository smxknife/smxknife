package org.smxknife.easypoi.view;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author smxknife
 * 2019/10/25
 */
@Getter
@Setter
public class ExportView {

	private ExportParams exportParams;
	private List<?> dataList;
	private Class<?> cls;

	public ExportView(Builder builder) {
		this.exportParams = builder.exportParams;
		this.dataList = builder.dataList;
		this.cls = builder.cls;
	}

	public static class Builder {

		public ExportParams exportParams;
		public List<?> dataList;
		public Class<?> cls;

		public Builder exportParams(ExportParams exportParams) {
			this.exportParams = exportParams;
			return this;
		}

		public Builder dataList(List<?> dataList) {
			this.dataList = dataList;
			return this;
		}

		public Builder cls(Class<?> cls) {
			this.cls = cls;
			return this;
		}

		public ExportView create() {
			return new ExportView(this);
		}
	}
}
