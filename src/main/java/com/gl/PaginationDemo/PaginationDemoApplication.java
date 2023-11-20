package com.gl.PaginationDemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PaginationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaginationDemoApplication.class, args);
    }

}

@RestController
class EmployeeController {
    private final EmployeeRepo repo;

    public EmployeeController(EmployeeRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/employees")
    public List<Employee> create(@RequestBody List<Employee> employees) {
        return repo.saveAll(employees);
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getOne(@PathVariable Long id) {
        return repo.findById(id);
    }

    @GetMapping("/employeesByIds")
    public List<Employee> employeesByIds(@RequestParam List<Long> ids) {
        return repo.findAllById(ids);
    }

    @GetMapping("/employeesByPagination")
    public Page<Employee> employeesByPagination(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return repo.findAll(PageRequest.of(pageNo, pageSize));
    }

    @GetMapping("/employeesByPaginationWithSorting")
    public Page<Employee> employeesByPaginationWithSorting(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderBy) {

        Sort sort = null;
        if ("DESC".equalsIgnoreCase(orderBy)) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        System.out.println("pageRequest = " + pageRequest);

        return repo.findAll(pageRequest);
    }
}

interface EmployeeRepo extends JpaRepository<Employee, Long> {
}


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int age;
    private long salary;
}

@Configuration
class LoadData {
    @Bean
    CommandLineRunner loadInitData(EmployeeRepo repo) {
        return args -> {
            List<Employee> employees = new ArrayList<>();
            employees.add(Employee.builder().name("Smith").age(39).salary(67000).build());
            employees.add(Employee.builder().name("John").age(76).salary(17000).build());
            employees.add(Employee.builder().name("Marry").age(46).salary(68000).build());
            employees.add(Employee.builder().name("Kenneth").age(45).salary(64000).build());
            employees.add(Employee.builder().name("Adam").age(32).salary(23000).build());
            employees.add(Employee.builder().name("Bennet").age(64).salary(66000).build());
            employees.add(Employee.builder().name("Corry").age(36).salary(33000).build());
            employees.add(Employee.builder().name("Paula").age(24).salary(22000).build());
            employees.add(Employee.builder().name("Poul").age(41).salary(55000).build());
            employees.add(Employee.builder().name("James").age(44).salary(11000).build());
            repo.saveAll(employees);
            System.out.println("Data loaded. employees = " + employees);
        };
    }
}
