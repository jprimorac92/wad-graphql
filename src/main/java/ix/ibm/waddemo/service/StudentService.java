package ix.ibm.waddemo.service;

import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.Student;
import ix.ibm.waddemo.pojo.StudentCourse;
import ix.ibm.waddemo.repository.StudentCourseRepository;
import ix.ibm.waddemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Student updateStudent(Long id)
    {
        final Student student = findById(id);
        student.setLevel((int)Math.floor(Math.random()*10));

        return student;
    }

    public List<Student> findAllStudentsForCourse(Course course) {
        final List<StudentCourse> studentCourses = studentCourseRepository.findAllByCourseId(course.getId());

        return studentCourses.stream()
                .map(e -> studentRepository.findById(e.getStudentId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student, Long id) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(student.getName());
                    existingStudent.setLevel(student.getLevel());
                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
