package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootEmployeeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeesRepo employeesRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @AfterEach
    void tearDown(){ employeesRepo.deleteAll();}

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee(1,"Momo", 24, "female",9999);
        final Employee secondemployee = new Employee(2,"Mina", 24, "female",9999);
        employeesRepo.save(employee);
        employeesRepo.save(secondemployee);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Momo"))
                .andExpect(jsonPath("$[0].age").value(24))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[1].name").value("Mina"));
    }

    @Test
    void should_return_specific_employee_when_call_get_employee_api_given_employee_id() throws Exception {
        final Employee employee = new Employee(1,"Momo", 24, "female",9999);
        final Employee savedEmployee = employeesRepo.save(employee);

        //when
        //then
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Momo"))
                .andExpect(jsonPath("$.age").value(24))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(9999));
    }


}
