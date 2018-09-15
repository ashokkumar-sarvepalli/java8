package learning.java8.empoyee;

import java.util.HashSet;
import java.util.Set;

public class Manager {
	
	private String mgrId;
	private String name;
	private double salary;
	
	private Set<Employee> empList = new HashSet<Employee>();

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(Set<Employee> empList) {
		this.empList = empList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mgrId == null) ? 0 : mgrId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		if (mgrId == null) {
			if (other.mgrId != null)
				return false;
		} else if (!mgrId.equals(other.mgrId))
			return false;
		return true;
	}
	
	

}
