
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
public class Project {
    
    @Id
    @GeneratedValue
    private int projId;
    private String title;
    private int days;
    
    @ManyToOne( cascade = {CascadeType.ALL} )
    @JoinColumn(name="cId")
    private Customer customer;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_employee", joinColumns = {@JoinColumn(name = "projId")},inverseJoinColumns = {@JoinColumn(name = "empId")})
    private List <Employee> employees;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List getEmployees() {
        return employees;
    }

    public void setEmployees(List employees) {
        this.employees = employees;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

}
