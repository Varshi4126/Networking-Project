package com.ava.Networking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ava.Networking.Model.Company;
import com.ava.Networking.Model.Job;
import com.ava.Networking.Repository.CompanyRepository;
import com.ava.Networking.Service.CompanyService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company-profile")
    public String companyProfile(HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("user");
        if (company == null) {
            return "redirect:/login1";
        }
        model.addAttribute("company", company);
        return "company-profile";
    }

    @PostMapping("/company-profile")
    public String updateCompanyProfile(@ModelAttribute Company updatedCompany, HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("user");
        if (company == null) {
            return "redirect:/login1";
        }
        // Update company properties
        company.setName(updatedCompany.getName());
        company.setAbout(updatedCompany.getAbout());

        Company updatedCompanyProfile = companyService.updateCompanyProfile(company);
        session.setAttribute("user", updatedCompanyProfile);
        model.addAttribute("company", updatedCompanyProfile);
        model.addAttribute("message", "Profile updated successfully!");
        return "company-profile";
    }
    
}
