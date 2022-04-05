package com.smxknife.springboot._01_exception;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author smxknife
 * 2020/6/6
 */
@Component
//@ControllerAdvice
public class _01ControllerAdvice {

	@ExceptionHandler(NotFoundPageException.class)
	public String error(Throwable throwable) {
		System.out.println("----------" + throwable.getClass().getCanonicalName());
		return "error";
	}

	@ExceptionHandler(RestfulNotFoundException.class)
	@ResponseBody
	public String restfulError(Throwable throwable) {
		System.out.println("++++++++++" + throwable.getClass().getCanonicalName());
		return "restful" + throwable.getClass().getCanonicalName();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public String def(Throwable throwable) {
		System.out.println("++++++++++" + throwable.getClass().getCanonicalName());
		return "def" + throwable.getClass().getCanonicalName();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new DateTimeFormatter());
	}

	public static class DateTimeFormatter implements Formatter<DateTime> {
		@Override
		public DateTime parse(String s, Locale locale) throws ParseException {
			//Out.out(locale.getDisplayName(), s);
			DateTime dateTime = new DateTime();
			dateTime.setDateTime(s);
			return dateTime;
		}

		@Override
		public String print(DateTime dateTime, Locale locale) {
			return dateTime.getDateTime();
		}
	}
}
