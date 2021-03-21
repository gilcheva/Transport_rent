package com.endava.commands.listing;

import static com.endava.commands.Constants.JOIN_DELIMITER;

import com.endava.commands.contracts.Command;
import com.endava.core.contracts.VehiclesRepository;
import com.endava.models.contracts.Course;
import java.util.ArrayList;
import java.util.List;

public class ListCoursesCommand implements Command {
  private final List<Course> courses;

  public ListCoursesCommand(VehiclesRepository repository) {
    courses = repository.getCourses();
  }

  @Override
  public String execute(List<String> parameters) {
    if (courses.size() == 0) {
      return "There are no registered courses.";
    }

    List<String> listCourses = coursesToString();

    return JOIN_DELIMITER+
        System.lineSeparator()+
        String.join(JOIN_DELIMITER + System.lineSeparator(), listCourses).trim();

  }

  private List<String> coursesToString() {
    List<String> result = new ArrayList<>();
    for (Course course : courses) {
      result.add("CourseID:" + courses.indexOf(course)+ course.toString());
    }
    return result;
  }
}
