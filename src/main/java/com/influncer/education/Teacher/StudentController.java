package com.influncer.education.Teacher;
import com.influncer.education.Teacher.dto.TeacherDTO;
import com.influncer.education.Teacher.dto.UniversityDTO;
import com.influncer.education.Teacher.entity.Teacher;
import com.influncer.education.Teacher.repo.MyDataTeacherRepo;
import com.influncer.education.Teacher.repo.TeacherRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/teachers")
public class StudentController {

    private final MyDataTeacherRepo dataRepo;

    private final TeacherRepo teacherRepo;

    public StudentController(final MyDataTeacherRepo dataRepo,  TeacherRepo teacherRepo1) {
        this.dataRepo = dataRepo;

        this.teacherRepo = teacherRepo1;
    }

    @GetMapping
    public List<TeacherDTO> getList(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String surname ,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false, name="university_id") Integer universityId ,
                                    @RequestParam(required = false) Integer age) {
        List<Teacher> list= teacherRepo.getList(name,surname,email,universityId,age);
        List<TeacherDTO> result = new ArrayList<>();
        for (Teacher teacher : list) {
            result.add(new TeacherDTO()
                     .setId(teacher.getId())
                    .setName(teacher.getName())
                    .setSurname(teacher.getSurname())
                    .setEmail(teacher.getEmail())
                    .setId(teacher.getId())
                    .setUniversity(
                            new UniversityDTO()
                                    .setId(teacher.getUniversity().getId())
                                     .setName(teacher.getUniversity().getName())
                    )
            );
        }
        return result;
    }
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public void save(@RequestBody Teacher t) {
       dataRepo.save(t);
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
    teacherRepo.delete(id);
    }

    @PutMapping
    public void update(@RequestBody Teacher t) {
        dataRepo.save(t);
    }

}

