package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class SpringBootEmployeeApplicationTests {

    @Mock
    private EmployeesRepo employeesRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void shoul_return_all_employess_when_getallemployees_given_all_employess() {
        //given
        List<Employees> employeesList = new ArrayList<>();

        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000));
        employeesList.add(new Employees(2, "Angela", 26, "female", 900));

        //when
        given(employeesRepo.getAllEmployees()).willReturn(employeesList);
        List<Employees> actualEmployess = employeeService.geteEmployeesList();

        assertEquals(employeesList.size(), actualEmployess.size());
        assertEquals(employeesList, actualEmployess);

    }


}
