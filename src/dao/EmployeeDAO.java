package dao;
import myjava.Employee;
import java.util.List;

public interface EmployeeDAO {
	
	Employee getEmployee(int id);
	List<Employee> getEmployees();
	boolean addEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	int getEmployeeID(Employee employee);
}