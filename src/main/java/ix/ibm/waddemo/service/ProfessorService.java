package ix.ibm.waddemo.service;

import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.Professor;
import ix.ibm.waddemo.pojo.ProfessorCourse;
import ix.ibm.waddemo.repository.ProfessorCourseRepository;
import ix.ibm.waddemo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorCourseRepository professorCourseRepository;


    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, ProfessorCourseRepository professorCourseRepository) {
        this.professorRepository = professorRepository;
        this.professorCourseRepository = professorCourseRepository;
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> findAllProfessorsForCourse(Course course) {
        final List<ProfessorCourse> professorCourses = professorCourseRepository.findAllByCourseId(course.getId());

        return professorCourses.stream()
                .map(e -> professorRepository.findById(e.getProfessorId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Professor update(Professor professor, Long id) {
        return professorRepository.findById(id)
                .map(existingProfessor -> {
                    existingProfessor.setName(professor.getName());
                    return professorRepository.save(existingProfessor);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean delete(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
