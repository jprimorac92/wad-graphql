package ix.ibm.waddemo.service;

import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.ProfessorCourse;
import ix.ibm.waddemo.pojo.StudentCourse;
import ix.ibm.waddemo.pojo.UpdateCourseInput;
import ix.ibm.waddemo.repository.CourseRepository;
import ix.ibm.waddemo.repository.ProfessorCourseRepository;
import ix.ibm.waddemo.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ProfessorCourseRepository professorCourseRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         ProfessorCourseRepository professorCourseRepository,
                         StudentCourseRepository studentCourseRepository) {
        this.courseRepository = courseRepository;
        this.professorCourseRepository = professorCourseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course, Long id) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setTitle(course.getTitle());
                    existingCourse.setDescription(course.getDescription());
                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public Course update(UpdateCourseInput courseInput) {
        return courseRepository.findById(courseInput.getId())
                .map(existingCourse -> {
                    existingCourse.setTitle(courseInput.getTitle());
                    existingCourse.setDescription(courseInput.getDescription());
                    updateProfessorCourses(courseInput);
                    updateStudentCourses(courseInput);
                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean delete(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void updateProfessorCourses(UpdateCourseInput courseInput) {
        if (courseInput.getProfessors() == null) {
            return;
        }
        professorCourseRepository.deleteAll(professorCourseRepository.findAllByCourseId(courseInput.getId()));

        courseInput.getProfessors().forEach(professor -> {
            createProfessorCourse(courseInput.getId(), professor);
        });
    }

    private void updateStudentCourses(UpdateCourseInput courseInput) {
        if (courseInput.getStudents() == null) {
            return;
        }
        studentCourseRepository.deleteAll(studentCourseRepository.findAllByCourseId(courseInput.getId()));

        courseInput.getStudents().forEach(student -> {
            createStudentCourse(courseInput.getId(), student);
        });
    }

    private void createProfessorCourse(Long courseId, Long professorId) {
        ProfessorCourse professorCourse = new ProfessorCourse();
        professorCourse.setCourseId(courseId);
        professorCourse.setProfessorId(professorId);
        professorCourseRepository.save(professorCourse);
    }

    private void createStudentCourse(Long courseId, Long studentId) {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(studentId);
        studentCourseRepository.save(studentCourse);
    }
}
