package ix.ibm.waddemo.controller;

import ix.ibm.waddemo.pojo.Professor;
import ix.ibm.waddemo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> all() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public Professor findById(@PathVariable Long id) {
        return professorService.findById(id);
    }

    @PostMapping()
    public Professor create(@RequestBody Professor professor) {
        return professorService.create(professor);
    }

    @PutMapping("/{id}")
    public Professor update(@RequestBody Professor professor, @PathVariable Long id) {
        return professorService.update(professor, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return professorService.delete(id);
    }
}
