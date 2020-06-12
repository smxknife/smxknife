package org.smxknife.easypoi.util;

import org.smxknife.easypoi.model.Course;
import org.smxknife.easypoi.model.Student;
import org.smxknife.easypoi.model.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2019/10/25
 */
public class MockDataUtil {

	private static Map<String, String> teacherMap = new HashMap<>();

	static {
		teacherMap.put("chinese", "中文老师");
		teacherMap.put("math", "数学老师");
		teacherMap.put("english", "英语老师");
	}

	public static List<Student> mockStudents() {
		 return IntStream.iterate(0, i -> i + 1).limit(10)
				.mapToObj(studIdx -> {
					Student student = new Student();
					student.setId(studIdx + "");
					student.setName("学生-" + studIdx);
					return student;
				}).collect(Collectors.toList());
	}

	public static List<Teacher> mockTeachers() {
		return teacherMap.entrySet().stream()
				.map(entry -> {
					Teacher teacher = new Teacher();
					teacher.setId(entry.getKey());
					teacher.setName(entry.getValue());
					return teacher;
				}).collect(Collectors.toList());
	}

	public static List<Course> mockCourses() {
		List<Teacher> teachers = mockTeachers();
		return IntStream.iterate(0, i -> i + 1).limit(5)
				.mapToObj(idx -> {
					Course course = new Course();
					course.setMathTeacher(teachers.get(0));
					course.setChineseTeacher(teachers.get(1));
					course.setEnglishTeacher(teachers.get(2));
					course.setStudents(mockStudents());
					course.setName("课程" + idx);
					course.setId(idx+"");
					return course;
				}).collect(Collectors.toList());
	}
}
