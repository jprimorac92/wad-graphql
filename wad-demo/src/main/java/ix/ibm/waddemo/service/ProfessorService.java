package ix.ibm.waddemo.service;

import ix.ibm.waddemo.pojo.Professor;
import ix.ibm.waddemo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
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
