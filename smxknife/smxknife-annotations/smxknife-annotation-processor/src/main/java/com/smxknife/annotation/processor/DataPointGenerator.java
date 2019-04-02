package com.smxknife.annotation.processor;

import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;

/**
 * @author smxknife
 * 2019-03-28
 */
public interface DataPointGenerator {

	void generate(Elements elements, Filer filer);
}
