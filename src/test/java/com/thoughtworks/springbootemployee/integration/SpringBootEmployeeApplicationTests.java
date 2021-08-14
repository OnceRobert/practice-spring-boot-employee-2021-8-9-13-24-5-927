package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

@SpringBootTest
class SpringBootEmployeeApplicationTests {

    @Mock
    private EmployeesRepo employeesRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void should_return_all_employess_when_getallemployees_given_all_employess() {
        //given
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(1, "Angelo", 23, "male", 1000));
        employeeList.add(new Employee(2, "Angela", 26, "female", 900));

        //when
        given(employeesRepo.findAll()).willReturn(employeeList);
        List<Employee> actualEmployess = employeeService.getEmployeesList();

        assertEquals(employeeList.size(), actualEmployess.size());
        assertEquals(employeeList, actualEmployess);
    }


}
