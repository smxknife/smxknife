package com.smxknife.annotation.example;

import com.smxknife.annotation.processor.DataPointAdapter;
import lombok.Data;

/**
 * @author smxknife
 * 2019-03-28
 */
@Data
@DataPointAdapter
public class DataPoint {
	Long id;
	String name;
}
