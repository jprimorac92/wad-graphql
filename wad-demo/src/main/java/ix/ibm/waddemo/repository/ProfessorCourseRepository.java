package ix.ibm.waddemo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ix.ibm.waddemo.pojo.ProfessorCourse;

@Repository
public interface ProfessorCourseRepository extends JpaRepository<ProfessorCourse,Long>
{
    List<ProfessorCourse> findAllByProfessorId(Long professorId);
    List<ProfessorCourse> findAllByCourseId(Long courseId);
}
