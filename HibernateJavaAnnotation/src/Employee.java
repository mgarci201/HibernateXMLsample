
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author supermario
 */
@Entity
public class Employee {
    
    @Id
    @GeneratedValue  
    private int empNo;
    private String name;
    private String address;
    private String role;
    
    @ManyToMany(mappedBy= "employees", fetch=FetchType.EAGER )
    private List<Project> projects;

    public List getProjects() {
        return projects;
    }

    public void setProjects(List projects) {
        this.projects = projects;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
