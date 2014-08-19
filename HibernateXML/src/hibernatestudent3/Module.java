package hibernatestudent3;

/**
 *
 * @author admin
 */
public class Module {
    
    private long mId;
    private String moduleCode;
    private String moduleTitle;   
    
    public Module() {
        this.moduleCode = null;
        this.moduleTitle = null;
    }
    
    public Module(String moduleCode, String moduleTitle) {
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
    }
    
    public long getmId() {
        return this.mId;
    }
    
    public void setmId(long sid) {
        this.mId = sid;
    }
        
    public String getModuleCode() {
        return this.moduleCode;
    }
    
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    
    public String getModuleTitle() {
        return this.moduleTitle;
    }
    
    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }
       
    @Override
    public int hashCode() {
        return moduleCode.hashCode() * 37 +  moduleTitle.hashCode() * 37;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Module) {
            Module m = (Module)o;
            return  m.getModuleCode().equals(getModuleCode()) &&
                    m.getModuleTitle().equals(getModuleTitle());
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return  "id: " + getmId() + ", " +
                "module code: " + getModuleCode() + ", " +
                "module title: " + getModuleTitle();
    }   
}
