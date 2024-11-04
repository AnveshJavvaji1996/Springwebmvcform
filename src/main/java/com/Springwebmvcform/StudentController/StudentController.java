package com.Springwebmvcform.StudentController;


import com.Springwebmvcform.StudentEntity.Student;
import com.Springwebmvcform.StudentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {


    @Autowired
    private StudentService studentService;


    //method handler for home page(index.html)to display list of employees
    @GetMapping("/viewHomePage")
    public String viewHomePage(Model model){
        model.addAttribute("listStudent",studentService.getAllStudent());
        return "index";

    }

    @GetMapping("/")
    public String welcomePage(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "welcomepage";

    }


    //This, method is used to add new student
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model){
        //Here, I created model attribute to bind form data
       // Student student = new Student();
        model.addAttribute("student",new Student());
        return "new_student";

    }



    //This, method is used to save student data into database
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student,Model model){
        boolean saveStudent = studentService.saveStudent(student);
        if(saveStudent){
             model.addAttribute("msg", "Student data saved successfully..!!!");
        }
        return "redirect:/dataSavedSuccessfully";

    }


    @RequestMapping(value = "/dataSavedSuccessfully", method = RequestMethod.GET)
    public  String dataSavedSuccessfully(Model model){

        model.addAttribute("student", new Student());
        return "new_student";

    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){

        //get student from StudentService
        Student student = studentService.getStudentById(id);
        //set student as a model attribute to pre-populate the form
        model.addAttribute("student",student);
       //model.addAttribute("msg1", "Student data updated successfully");
        return "update_student";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value ="id") long id,Model model){
        this.studentService.deleteStudentById(id);
        return "redirect:/";

    }

}
