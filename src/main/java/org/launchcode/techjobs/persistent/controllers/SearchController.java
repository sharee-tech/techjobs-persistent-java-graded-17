package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.JobData;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.techjobs.persistent.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private JobRepository jobRepository;

//    @Autowired
//    private SkillRepository skillRepository;
//
//    @Autowired
//    private EmployerRepository employerRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);

//        Iterable<Skill> skills = skillRepository.findAll();
//        Iterable<Employer> employers = employerRepository.findAll();
//
//        model.addAttribute("skills", skills);
//        model.addAttribute("employers", employers);

        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = false) String searchTerm){
        Iterable<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            jobs = jobRepository.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobs", jobs);


        return "search";
    }
}
