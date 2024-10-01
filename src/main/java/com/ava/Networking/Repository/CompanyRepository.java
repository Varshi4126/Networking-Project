package com.ava.Networking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ava.Networking.Model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByEmail(String email);
    
    @Query("SELECT c FROM Company c WHERE LOWER(CAST(c.name AS string)) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(CAST(c.about AS string)) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Company> searchCompanies(@Param("query") String query);

	Company findByName(String username);
}