
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Customer {
    
    @Id
    @GeneratedValue 
    private int custNo;
    private String name;
    private String address;
    
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer")
    private List <Project> projects;

    public List getProjects() {
        return projects;
    }

    public void setProjects(List projects) {
        this.projects = projects;
    }

    public int getCustNo() {
        return custNo;
    }

    public void setCustNo(int custNo) {
        this.custNo = custNo;
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
     
}
