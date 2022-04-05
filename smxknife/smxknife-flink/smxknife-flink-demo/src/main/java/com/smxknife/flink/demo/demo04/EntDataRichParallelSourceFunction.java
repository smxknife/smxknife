package com.smxknife.flink.demo.demo04;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/8/17
 */
public class EntDataRichParallelSourceFunction extends RichParallelSourceFunction<JSONObject> {

	private static String[] deviceIds = new String[] {"111111", "222222", "333333", "444444"};
	private static String[] entCodes = new String[] {"0111111", "0222222", "0333333", "0444444"};
	private static String[] dataCodes = new String[] {
			"00-00-0000-023300-21",
			"00-00-0000-023200-21",
			"00-00-0000-023100-21",
			"00-00-0000-023000-21",
			"00-00-0000-022300-21",
			"00-00-0000-021300-21"};
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private volatile int state = 0;
	@Override
	public void run(SourceContext<JSONObject> sourceContext) throws Exception {
		while (state >= 0) {
			int index = ThreadLocalRandom.current().nextInt(deviceIds.length);
			JSONObject entData = new JSONObject();
			entData.put("deviceId", deviceIds[index]);
			entData.put("entCode", entCodes[index]);

			int itemCount = ThreadLocalRandom.current().nextInt(dataCodes.length);
			JSONArray jsonArray = new JSONArray();
			entData.put("data", jsonArray);

			for (int i = 0; i < itemCount; i++) {
				JSONObject data = new JSONObject();
				data.put("dataCode", dataCodes[i]);
				data.put("dataValue", 100);
				data.put("statType", 1);
				data.put("statDate", LocalDateTime.now().format(FORMATTER));
				jsonArray.add(data);
			}


			sourceContext.collect(entData);
			TimeUnit.SECONDS.sleep(5);
		}
	}

	@Override
	public void cancel() {
		state = -1;
	}
}
