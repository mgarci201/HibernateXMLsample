package hibernatestudent3;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;

public class HibernateStudent3 {
    
    static Configuration h2Config(Class[] dbClasses) {
        Configuration configuration =
                new Configuration()
                    .setProperty(Environment.DRIVER, "org.h2.Driver")
                    .setProperty(Environment.URL, "jdbc:h2:~/test")
                    .setProperty(Environment.USER, "sa")
                    .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
                    .setProperty(Environment.DIALECT, H2Dialect.class.getName())
                    .setProperty(Environment.SHOW_SQL, "false");
        for (Class clazz : dbClasses) {
            configuration.addClass(clazz);
        }
        return configuration;
    }
    
    private SessionFactory sessionFactory;       
    
    public HibernateStudent3() {
        Configuration configuration = h2Config(new Class[]{Student.class, Programme.class, Module.class});
        sessionFactory = configuration.buildSessionFactory();  
    }
    
    @SuppressWarnings({"unchecked"})

    public void addStudents() {

        System.out.println("Add Students");
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("\n***Session started...");
        
        System.out.println("\n***Creating student objects...");
        Student s1 = new Student("S12234", "Mario", "Garcia", "m.garcia@garcia.com", null);
        Student s2 = new Student("S12345", "Martin", "Gallacher", "m.gallacher@gallacher.com", null);
        
        System.out.println("\n***Student objects created...");

        System.out.println("\n***Programme objects retrieved");
        
        Programme p1 = (Programme) session.createQuery("from Programme where programmeCode = 'P01627'").uniqueResult();
        Programme p2 = (Programme) session.createQuery("from Programme where programmeCode = 'P00280'").uniqueResult();
        
//        Set<Programme> s1Programmes = new HashSet(Arrays.asList(p1));
//        Set<Programme> s2Programmes = new HashSet(Arrays.asList(p2));
        
        System.out.println("\n***Attach student to programmes");
        
        s1.setProgramme(p1);
        s2.setProgramme(p2);
        
        System.out.println("\n***Saving programme objects...");
        session.save(s1);
        session.save(s2);        
       
        System.out.println("\n***Programme objects saved...");

        session.getTransaction().commit();
        session.flush();
        session.close();
        System.out.println("\n***Session closed...");
        
    }
    
    public void listStudents() {
        System.out.println("List Students");
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();        
        System.out.println("\n***Session started...");     
      
        List studentList = session.createQuery("from Student").list();
        System.out.println(studentList.size() + " student(s) found:");
        for (Iterator iter = studentList.iterator(); iter.hasNext();) {
            Student s = (Student) iter.next();
            System.out.println(s);
        }
        session.getTransaction().commit();
        session.flush();
        session.close();
        System.out.println("\n***Session closed...");         
    }
    
    public void addProgrammes() {

        System.out.println("Add Programmes");
			
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("\n***Creating programme objects...");
        Programme p1 = new Programme("P01627", "Information Systems Development", "FT", null);
        Programme p2 = new Programme("P00280", "IT Management for Business", "FT", null);
        Programme p3 = new Programme("P09123", "Web Systems Development", "PT", null);
        
        System.out.println("\n***Programme objects created...");
        
        Module m1 = (Module) session.createQuery("from Module where moduleCode = 'M1'").uniqueResult();
        Module m2 = (Module) session.createQuery("from Module where moduleCode = 'M2'").uniqueResult();
        Module m3 = (Module) session.createQuery("from Module where moduleCode = 'M3'").uniqueResult();
        Module m4 = (Module) session.createQuery("from Module where moduleCode = 'M4'").uniqueResult();  
        Module m5 = (Module) session.createQuery("from Module where moduleCode = 'M5'").uniqueResult();
        Module m6 = (Module) session.createQuery("from Module where moduleCode = 'M6'").uniqueResult();
        Module m7 = (Module) session.createQuery("from Module where moduleCode = 'M7'").uniqueResult();
        
        Set<Module> p1Modules = new HashSet(Arrays.asList(m1, m2, m3, m4, m5, m6));
        Set<Module> p2Modules = new HashSet(Arrays.asList(m1, m2, m3, m4, m5, m7));
        Set<Module> p3Modules = new HashSet(Arrays.asList(m6, m3, m2, m5, m4));
        
        p1.setModules(p1Modules);
        p2.setModules(p2Modules);
        p3.setModules(p3Modules);
        
        session.save(p1);
        session.save(p2); 
        session.save(p3);

        session.getTransaction().commit();
        session.flush();
        session.close();
        System.out.println("\n***Session closed...");        
    }
    
    public void listProgrammes() {
        System.out.println("List Programmes");
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();        
        System.out.println("\n***Session started...");     
      
        List programmeList = session.createQuery("from Programme").list();
        System.out.println(programmeList.size() + " programme(s) found:");
        for (Iterator iter = programmeList.iterator(); iter.hasNext();) {
            Programme p = (Programme) iter.next();
            System.out.println(p);
        }
        session.getTransaction().commit();
        session.flush();
        session.close();
        System.out.println("\n***Session closed...");         
    }    
    
    public void addModules() {

        System.out.println("Add Modules");
			
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("\n***Creating module objects...");
        Module m1 = new Module("M1", "IT Project Management 2");
        Module m2 = new Module("M2", "Database Systems Development 2");
        Module m3 = new Module("M3", "Research Methods");
        Module m4 = new Module("M4", "Professional Issues");  
        Module m5 = new Module("M5", "Honours Project");
        Module m6 = new Module("M6", "Web Programming 2");
        Module m7 = new Module("M7", "International Business Strategy");        
        
        session.save(m1);
        session.save(m2);        
        session.save(m3);  
        session.save(m4);
        session.save(m5);
        session.save(m6);
        session.save(m7);       
        
        session.getTransaction().commit();
        session.flush();
        session.close();
        System.out.println("\n***Session closed...");        
    }
    
    public void listModules() {
        System.out.println("List Modules");
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();           
      
        List moduleList = session.createQuery("from Module").list();
        System.out.println(moduleList.size() + " module (s) found:");
        for (Iterator iter = moduleList.iterator(); iter.hasNext();) {
            Module m = (Module) iter.next();
            System.out.println(m);
        }
        session.getTransaction().commit();
        session.flush();
        session.close();
        System.out.println("\n***Session closed...");         
    }      

    public void studentAdmin() {
        
        // Add Modules
        addModules();
        
        // List Modules
        listModules();    
        
        // Add Programmes
        addProgrammes();        
        
        // List Programmes
        listProgrammes();        
        
        // Add Students
        addStudents();
        
        // List Students
        listStudents();         
    }
    
    
    public static void main(String[] args) {
        try {
            HibernateStudent3 run = new HibernateStudent3();
            run.studentAdmin();
        } catch (Exception e) { e.printStackTrace();
        }
    }

}
