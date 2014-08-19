/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatestudent3;
import java.util.Set;
import java.util.HashSet;
/**
 *
 * @author admin
 */
public class Programme {
    
    private long id;
    private String programmeCode;
    private String title;
    private String type;  
    private Set<Module> modules;
    
    public Programme() {
        this.programmeCode = null;
        this.title = null;
        this.type = null;
    }
    
    public Programme(String programmeCode, String title, String type, Set<Module> modules) {
        this.programmeCode = programmeCode;
        this.title = title;
        this.type = type;
        this.modules = modules;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long sid) {
        this.id = sid;
    }
        
    public String getProgrammeCode() {
        return this.programmeCode;
    }
    
    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Set<Module> getModules() {
        return this.modules;
    }
    
    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
       
    @Override
    public int hashCode() {
        return programmeCode.hashCode() * 7 +  title.hashCode() * 7 + type.hashCode() * 7 + modules.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Programme) {
            Programme p = (Programme)o;
            return  p.getProgrammeCode().equals(getProgrammeCode()) &&
                    p.getTitle().equals(getTitle()) && 
                    p.getType().equals(getType()) &&
                    p.getModules().equals(getModules());
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return  "id: " + getId() + ", " +
                "programme code: " + getProgrammeCode() + ", " +
                "title: " + getTitle() + ", " +
                "type: " + getType() + ", " +
                "modules:" + getModules();
    }   

}
