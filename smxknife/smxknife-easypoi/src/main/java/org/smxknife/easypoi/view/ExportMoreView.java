package org.smxknife.easypoi.view;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author smxknife
 * 2019/10/25
 */
public class ExportMoreView {
	private List<ExportView> moreViewList = Lists.newArrayList();

	public List<ExportView> getMoreViewList() {
		return moreViewList;
	}

	public void setMoreViewList(List<ExportView> moreViewList) {
		this.moreViewList = moreViewList;
	}
}
