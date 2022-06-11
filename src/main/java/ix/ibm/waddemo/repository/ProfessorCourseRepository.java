package ix.ibm.waddemo.repository;

import ix.ibm.waddemo.pojo.ProfessorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorCourseRepository extends JpaRepository<ProfessorCourse, Long> {
    List<ProfessorCourse> findAllByProfessorId(Long professorId);

    List<ProfessorCourse> findAllByCourseId(Long courseId);
}
