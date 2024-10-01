package com.ava.Networking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ava.Networking.Model.Company;
import com.ava.Networking.Model.Student;
import com.ava.Networking.Model.User;
import com.ava.Networking.Repository.CompanyRepository;
import com.ava.Networking.Repository.UserRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public Company getCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }
    

    public Company findCompanyByName(String name) {
        return companyRepository.findByName(name);
    }

    public Company updateCompanyProfile(Company company) {
        // First, update the User entity
        User user = userRepository.findByEmail(company.getEmail());
        if (user != null) {
            user.setEmail(company.getEmail());
            userRepository.save(user);
        }
        Company existingCompany = companyRepository.findById(company.getUserId()).orElse(null);
        if (existingCompany != null) {
            existingCompany.setName(company.getName());
            existingCompany.setAbout(company.getAbout());
            return companyRepository.save(existingCompany);
        }
        // Then, update the Company entity
        return companyRepository.save(company);
    }

    public Company createCompany(User user) {
        Company company = new Company();
        company.setUser(user);
        company.setEmail(user.getEmail());
        return companyRepository.save(company);
    }
    
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public List<Company> searchCompanies(String query) {
        return companyRepository.searchCompanies(query);
    }
}