
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import static org.hibernate.type.TypeFactory.set;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author supermario
 */
public class HibernateCoursework {
        
    static Configuration h2Config(Class[] dbClasses) {
        AnnotationConfiguration configuration =
               new AnnotationConfiguration()
                       .setProperty(Environment.DRIVER, "org.h2.Driver")
                       .setProperty(Environment.URL, "jdbc:h2:~/test")
                       .setProperty(Environment.USER, "sa")
                       .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
                       .setProperty(Environment.DIALECT, H2Dialect.class.getName())
                       .setProperty(Environment.SHOW_SQL, "false");
                 for (Class clazz : dbClasses) {
                 configuration.addAnnotatedClass(clazz);
        }
        return configuration;
    }
    
    private SessionFactory sessionFactory;  
    
    public HibernateCoursework() {
        Configuration configuration = h2Config(new Class[]{Customer.class, Project.class, Employee.class});
        sessionFactory = configuration.buildSessionFactory();  
    }
    
    public static void main(String[] args) {
        try {
            HibernateCoursework run = new HibernateCoursework();
            
            //Create employees (10 in total)
            Employee e1 = run.createEmployee("Mario", "123 Circus Dr", "Manager");
            Employee e2 = run.createEmployee("Andy", "129 Circus Dr", "Vice"); 
            Employee e3 = run.createEmployee("Joshua", "1 Bailiff County", "Assistant Manager");  
            Employee e4 = run.createEmployee("Marko", "60 West George St", "Supervisor");
            Employee e5 = run.createEmployee("Ashton", "99 Avenue Place", "Floor Manager");     
            Employee e6 = run.createEmployee("Marshall", "21 Jump St", "Team Leader"); 
            Employee e7 = run.createEmployee("Noel", "87 Pent Dr", "Instructor"); 
            Employee e8 = run.createEmployee("Brian", "25 Summerdrive Rd", "Assistant Instructor");
            Employee e9 = run.createEmployee("Costanza", "99 West George St", "Associate Manager");
            Employee e10 = run.createEmployee("Jerry", "86 East Avenue", "Executive");             
            
            //Save the created employees
            run.saveEmployee(e1);
            run.saveEmployee(e2);
            run.saveEmployee(e3);
            run.saveEmployee(e4);
            run.saveEmployee(e5);
            run.saveEmployee(e6);
            run.saveEmployee(e7);
            run.saveEmployee(e8);
            run.saveEmployee(e9);
            run.saveEmployee(e10);            
            
            //Lists employees
            List employees = new ArrayList();
            employees.add(e1);
            employees.add(e2);
            employees.add(e3);
            employees.add(e4);
            employees.add(e5);
            employees.add(e6);
            employees.add(e7);
            employees.add(e8);
            employees.add(e9);
            employees.add(e10);
           
            //Create projects
            Project p1 = run.createProject("The project", 7, employees, null);
            Project p2 = run.createProject("Great Project", 12, employees, null);
            Project p3 = run.createProject("An Excellent One", 24, employees, null);
            Project p4 = run.createProject("The Phenomenal Work", 48, employees, null);
            Project p5 = run.createProject("Absolute Masterpiece", 6, employees, null);
            
            System.out.println("\n***Employee objects retrieved");
            
            run.saveProject(p1);
            run.saveProject(p2);
            run.saveProject(p3);
            run.saveProject(p4);
            run.saveProject(p5);
            
            //List projects
            List projects = new ArrayList();
            projects.add(p1);
            projects.add(p2);
            projects.add(p3);
            projects.add(p4);
            projects.add(p5);
                  
            //Create customers
            Customer c1 = run.createCustomer("Geo", "56 Glen Dr", projects);
            Customer c2 = run.createCustomer("Pedro", "119 Buford St", projects);
            Customer c3 = run.createCustomer("Juan", "97 Linsdale Dr", projects); 
         
//            p1.setCustomer(c1);
//            p1.setCustomer(c2);
   
            run.saveCustomer(c1);
            run.saveCustomer(c2);
            run.saveCustomer(c3);
            
//            p1.setCustomer(c1);

            run.listEmployees();
            run.listCustomers();
            run.listProjects();
            run.findProj(p1.getProjId());
            
            //Update specified project; change days
            Employee e11 = run.createEmployee("John", "124 Your Dr", "President");
            run.saveEmployee(e11);
            run.updateProj(p1, 10, e2, e11);
            
            //Delete employee
            //run.deleteEmployee(e2);
            

            //run.showProjDetailsForCustomer(c1);
            
            //Retrieve E1 with list of related projects
            e5 = run.findEmployeeNumber(e5.getEmpNo());
            run.showProjDetailsEmployee(e5);
            
            //Show project details for specified customer (customer 1 = Geo)            
            run.showProjDetailsCustomer(c1);
            
            run.showCustomerEmployee(c1, e5);

        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public Employee createEmployee ( String name, String address, String role ) {
        System.out.println("Create Employee");       
        Employee e = new Employee();
            e.setName(name);
//            e1.setEmpNo(empNo);
            e.setAddress(address);
            e.setRole(role);
 
            return e;
    }
    
    public void saveEmployee (Employee e) {
        System.out.println("Save Employee");
			
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();
        System.out.println("****************");
        
        hibernate.save(e);
       
        System.out.println("***Employee objects saved***"); 
        
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed****");  
   }
    
    public Customer createCustomer ( String name, String address, List projects ) {
           
        Customer c = new Customer();
            c.setName(name);
//            c.setCustNo(custNo);
            c.setAddress(address);
            c.setProjects(projects);
            return c;
    }
    
    public void saveCustomer (Customer c) {
        
        System.out.println("Save Customer");
			
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();

        System.out.println("****************");
        
        hibernate.save(c);
       
        System.out.println("***Customer objects saved***"); 
        
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");  
        
    }
    
    public Project createProject ( String title, int days, List employees, Customer customer ) {
           
        Project p = new Project();
//            p.setProjId(projId);
            p.setTitle(title);
            p.setCustomer(customer);
            p.setDays(days);
            p.setEmployees(employees);
            return p;
    }
    
    public void saveProject (Project p) {
        System.out.println("Save Project");
			
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();

        System.out.println("****************");
        
        hibernate.save(p);
       
        System.out.println("***Project objects saved***"); 
        
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");   
    }
    
        public void listEmployees() {
        System.out.println("List Employees");
        
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();        
        System.out.println("***************");     
      
        List employeeList = hibernate.createQuery("from Employee").list();
        System.out.println(employeeList.size() + " employee(s) found:");
        for (Iterator iter = employeeList.iterator(); iter.hasNext();) {
           //assign employee
            Employee e = (Employee) iter.next();
            System.out.println(e.getName());
        }
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");         
    }
    
    public void listCustomers() {
        System.out.println("List Customers");
        
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();        
        System.out.println("****************");     
      
        List customerList = hibernate.createQuery("from Customer").list();
        System.out.println(customerList.size() + " customer(s) found:");
        for (Iterator iter = customerList.iterator(); iter.hasNext();) {
           //assign employee
            Customer e = (Customer) iter.next();
            System.out.println(e.getName());
        }
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");         
    }
    
       public void listProjects() {
        System.out.println("List Projects");
        
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();        
        System.out.println("****************");     
      
        List projectList = hibernate.createQuery("from Project").list();
        System.out.println(projectList.size() + " project(s) found:");
        for (Iterator iter = projectList.iterator(); iter.hasNext();) {
           //assign employee
            Project p = (Project) iter.next();
            System.out.println(p.getTitle());
        }
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");         
    }
    
    public void findProj(int projId){
        System.out.println("Specified Project");
        
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();        
        System.out.println("*****************");     
      
        List projectList = hibernate.createQuery("from Project p where p.projId =  "+ projId).list();
        System.out.println(projectList.size() + " project(s) found:");
        for (Iterator iter = projectList.iterator(); iter.hasNext();) {
           //assign project
            Project p = (Project) iter.next();
            System.out.println("ProjectID = "+ p.getProjId());
            System.out.println("Project Title = "+ p.getTitle());
            System.out.println("Project Days = "+ p.getDays());
        }
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");    
    }
    
    public void updateProj (Project project, int days, Employee employeeToBeRemoved, Employee employeeToBeAdded ){

        System.out.println("Update Project");		
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();

        System.out.println("*************");
        
        project.setDays(days);
        
        List tmpList = project.getEmployees();
        tmpList.remove(employeeToBeRemoved);
        tmpList.add(employeeToBeAdded);
        
        project.setEmployees(tmpList);
        
        hibernate.update(project);
        
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");   
           
    }
    
    public void deleteEmployee ( Employee deleteEmployee ){
        
        System.out.println("Delete Employee");
			
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();
        System.out.println("***Session started***");

        System.out.println("***Delete employee***");
        
        hibernate.delete(deleteEmployee);
        
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");     
        
    }
    
//    public void showProjDetailsForCustomer (Customer customer){
//
//        List tmp = customer.getProjects();
//        for (Iterator iter = tmp.iterator(); iter.hasNext();) {
//           //assign project
//            Project p = (Project) iter.next();
//            System.out.println("ProjectID = "+ p.getProjId());
//            System.out.println("Project Title = "+ p.getTitle());
//            System.out.println("Project Days = "+ p.getDays());
//            
//            System.out.println("****************** ");  
//            p.setCustomer(customer);   
//        }   
//    }
    
    public Employee findEmployeeNumber (int empNumber) {
        Session hibernate = sessionFactory.openSession();
        hibernate.beginTransaction();
        System.out.println("***Session started***");
        
        List tmpList = hibernate.createQuery("from Employee e where e.empNo =" + empNumber).list();
        Employee emp = null;
        
        for (Iterator iter = tmpList.iterator(); iter.hasNext();) {
            emp = (Employee) iter.next();
            System.out.println("Employee found" + emp.getName().toUpperCase()+"("+emp.getEmpNo()+")");
            
        hibernate.getTransaction().commit();
        hibernate.flush();
        hibernate.close();
        System.out.println("***Session closed***");                        
        }   
        return emp;
    }
    
    public void showProjDetailsEmployee (Employee emp){
        System.out.println("********************");
        List tmpProjectList = emp.getProjects();
        System.out.println(emp.getName()+" is related to = "+ emp.getProjects().size()+" project(s) ");
        for (Iterator iter = tmpProjectList.iterator(); iter.hasNext();) {
            //assign project
            Project p = (Project) iter.next();
            System.out.println("ProjectID = "+ p.getProjId());
            System.out.println("Project Title = "+ p.getTitle());
            System.out.println("Project Days = "+ p.getDays());
        }
        System.out.println("********************");        
    }
    
    public void showProjDetailsCustomer (Customer cust){
        System.out.println("********************");
        List tmpProjectList = cust.getProjects();
        System.out.println(cust.getName()+" is related to = "+ cust.getProjects().size()+ " project(s)");
        for (Iterator iter = tmpProjectList.iterator(); iter.hasNext();) {
            //will display the project assigned to cust
            Project p = (Project) iter.next();
            System.out.println("ProjectID = "+ p.getProjId());
            System.out.println("Project Title = "+ p.getTitle());
            System.out.println("Project Days = "+ p.getDays());
        }
        System.out.println("********************");            
    }    
    
    public void showCustomerEmployee (Customer cust, Employee emp){
        System.out.println("********************");
        System.out.println(cust.getName()+" is working alongside = "+ emp.getName());
        
        
    }
}
