package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        return "add";
    }

//    @GetMapping("add")
//    public String displayAddJobForm(@RequestParam Integer employerId, Model model) {
//        Optional<Job> result = jobRepository.findById(employerId);
//        Job job = result.get();
//        model.addAttribute("title", "Add Job");
//        model.addAttribute("employers", employerRepository.findAll());
//        model.addAttribute(new Job());
//        return "add";
//    }

//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                       Errors errors, Model model,
//                                    @RequestParam Integer employerId) {
//
//        if (errors.hasErrors()) {
//	    model.addAttribute("title", "Add Job");
//            return "add";
//        }
//        return "redirect:";
//    }

//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model,
//                                    @RequestParam Integer employerId) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        }
//        Employer employer = newJob.getEmployer();
//        newJob.setEmployer(employer);
////        newJob.add(employerId);
//        jobRepository.save(newJob);
//        return "redirect:";
//    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model, @RequestParam Integer employerId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        // Retrieve the selected employer from the employerRepository
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);

        if (optionalEmployer.isPresent()) {
            Employer selectedEmployer = optionalEmployer.get();

            // Set the selected employer in the newJob
            newJob.setEmployer(selectedEmployer);

            // Save the job to the database
            jobRepository.save(newJob);

            return "redirect:/";
        } else {
            // Handle the case where the selected employer doesn't exist
            model.addAttribute("title", "Add Job");
            model.addAttribute("errorMessage", "Selected employer does not exist.");
            return "add";
        }
    }



    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
