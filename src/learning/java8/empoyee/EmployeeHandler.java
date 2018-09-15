package learning.java8.empoyee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeHandler {

	private Map<String, Employee> employeeMap = new HashMap<String, Employee>();
	private Map<String, Manager> managerMap = new HashMap<String, Manager>();

	public void createEmployee(Employee employee) {
		employeeMap.put(employee.getEmpId(), employee);
		Manager manager = managerMap.get(employee.getMgrId());
		// add the employee to the manager list
		manager.getEmpList().add(employee);

	}

	public void removeEmployee(String empId) {
		Employee employee = employeeMap.get(empId);
		Manager manager = managerMap.get(employee.getMgrId());
		// remove from manager
		manager.getEmpList().remove(employee);
		// remove from employee
		employeeMap.remove(empId);
	}

	public void createManager(Manager manager) {
		managerMap.put(manager.getMgrId(), manager);
	}

	public void changeManager(String srcMgrId, String destMgrId) {
		// first update the manager id of all employees.
		Set<Employee> employeeSet = managerMap.get(srcMgrId).getEmpList();
		employeeSet.stream().forEach(e -> e.setMgrId(destMgrId));
		// add all employees to the new manager
		Manager newManager = managerMap.get(destMgrId);
		newManager.getEmpList().addAll(employeeSet);
		// remove from existing manager
		Manager oldManager = managerMap.get(srcMgrId);
		oldManager.getEmpList().clear();
	}

	public Employee searchEmployee(String empId) {
		return employeeMap.get(empId);
	}

	public Set<Employee> displayAllEmployeesByManagerId(String managerId) {
		return managerMap.get(managerId).getEmpList();
	}

	public Manager searchManager(String mgrId) {
		return managerMap.get(mgrId);
	}

	public Employee displayEmployeeWithHighestSalaryUnderGivenManager(String mgrId) {
		return managerMap.get(mgrId).getEmpList().stream().max(Comparator.comparing(Employee::getSalary)).get();
	}

	public Set<Employee> sortEmployeesWithSalaryForGivenManager(String mgrId) {
		return managerMap.get(mgrId).getEmpList().stream().sorted(Comparator.comparing(Employee::getSalary))
				.collect(Collectors.toSet());
	}

	public Map<String, Integer> countOfEmployeesUnderEachManager() {
		Map<String, Integer> employeeUnderEachManagerCount = new HashMap<String, Integer>();
		managerMap.forEach((id, manager) -> employeeUnderEachManagerCount.put(id, manager.getEmpList().size()));
		return employeeUnderEachManagerCount;
	}

	public Map<String, Long> countOfEmployeesUnderEachManagerGreaterThanSpecificSalary(double salary) {
		Map<String, Long> employeeUnderEachManagerCount = new HashMap<String, Long>();
		managerMap.forEach((id, manager) -> employeeUnderEachManagerCount.put(id,
				manager.getEmpList().stream().filter(emp -> emp.getSalary() > salary).count()));
		return employeeUnderEachManagerCount;
	}

	public Long countOfEmployeesWithSalaryGreaterThanGivenValue(double salary) {
		return employeeMap.values().stream().filter(emp -> emp.getSalary() > salary).count();
	}

}
