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
import org.springframework.http.MediaType;
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

    @Test
    void should_return_correct_pagination_when_call_get_employee_given_index_and_size() throws Exception
    {
        //given
        final Employee employee = new Employee(1,"Momo", 24, "female",9999);
        final Employee secondemployee = new Employee(2,"Mina", 24, "female",9999);
        final Employee thirdemployee = new Employee(3,"Sana", 24, "female",9999);
        employeesRepo.save(employee);
        employeesRepo.save(secondemployee);
        employeesRepo.save(thirdemployee);

        //when
        //then
        int index = 1 ,size = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?index={index}&size={size}",index,size))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Momo"))
                .andExpect(jsonPath("$[0].age").value(24))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].name").value("Mina"))
                .andExpect(jsonPath("$[1].age").value(24))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[1].salary").value(9999))
                .andExpect(jsonPath("$[2].salary").doesNotExist());
    }

    @Test
    void should_return_all_male_employees_when_call_get_employees_given_gender_equals_male() throws Exception
    {
        //given
        final Employee employee = new Employee(1,"JYP Oppar", 24, "male",9999);
        final Employee secondemployee = new Employee(2,"Ralston", 24, "male",9999);
        final Employee thirdemployee = new Employee(3,"Sana", 24, "female",9999);
        employeesRepo.save(employee);
        employeesRepo.save(secondemployee);
        employeesRepo.save(thirdemployee);

        //when
        //then
        String gender = "male";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender={gender}",gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[1].gender").value("male"))
                .andExpect(jsonPath("$[2].salary").doesNotExist());
    }

    @Test
    void should_create_employee_when_call_create_employee_api() throws Exception
    {
        //given
        String employee = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Park Jinyoung\",\n" +
                "        \"age\": 49,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 2011233,\n" +
                "        \"companyId\": 1\n" +
                "    }";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated());

    }

    @Test
    void should_update_employee_when_call_update_employee_api() throws Exception
    {
        final Employee newEmployee = new Employee(1,"JYP Oppar", 24, "male",9999);
        Employee savedEmployee = employeesRepo.save(newEmployee);
        //given
        String employee = "{\n" +
                "        \"name\": \"Park Jinyoung\",\n" +
                "        \"age\": 49,\n" +
                "        \"gender\": \"male\"\n" +
                "    }";
        //when
        //then

        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Park Jinyoung"))
                .andExpect(jsonPath("$.age").value(49))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    void should_return_no_employee_when_call_delete_employees_api() throws Exception {
        //given
        final Employee employee = new Employee(1,"Momo", 24, "female",9999);
        Employee savedEmployee = employeesRepo.save(employee);

        //when
        //then
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}",id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
