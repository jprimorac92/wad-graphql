package ix.ibm.waddemo.controller;

import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> all() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @PostMapping()
    public Course create(@RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public Course update(@RequestBody Course course, @PathVariable Long id) {
        return courseService.update(course, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return courseService.delete(id);
    }
}
