package ix.ibm.waddemo.repository;

import ix.ibm.waddemo.pojo.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findAllByStudentId(Long studentId);

    List<StudentCourse> findAllByCourseId(Long courseId);
}
