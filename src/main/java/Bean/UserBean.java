package Bean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import Entity.DgutUser;
import Entity.LocalUser;

public class UserBean {

    private String username;
    private String password1;
    private String password2;
    private String email;
    private String name;
    private String grouptype;
    private String head;

    public UserBean() {
        username=null;
        password1=null;
        password2=null;
        email=null;
        name=null;
        grouptype=null;
        head="unset.jpg";
    }

    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username= username;
    }

    public String getpassword1() {
        return password1;
    }
    public void setpassword1(String password1) {
        this.password1= password1;
    }

    public String getpassword2() {
        return password2;
    }
    public void setpassword2(String password2) {
        this.password2= password2;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email= email;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name= name;
    }

    public String getgrouptype() { return grouptype; }
    public void setgrouptype(String grouptype) { this.grouptype= grouptype; }

    public String gethead() { return head; }
    public void sethead(String head) { this.head= head; }

    private static final EntityManagerFactory entityManagerFactory;
    private static final String PERSISTENCE_UNIT_NAME = "MyJPADemoPU";

    static { entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    EntityManager em = getEntityManager();

    EntityTransaction tx = em.getTransaction();

    LocalUser localUser;
    DgutUser dgutUser;


    //登录验证
    public boolean check_login(){
        boolean isValid = false;
        localUser= em.find(LocalUser.class,username);
        if(localUser!=null)
            if(localUser.getpassword().equals(password1)) {
                password1=localUser.getpassword();
                email=localUser.getemail();
                name=localUser.getname();
                grouptype=localUser.getgrouptype();
                head=localUser.gethead();
                isValid = true;
            }
        return isValid;
    }

    //登陆输入空验证
    public boolean isNull_log(){
        boolean isValid = false;
        if(username==null||"".equals(username)||password1==null||"".equals(password1)){
            isValid=true;
        }
        return isValid;
    }

    //工号登记验证
    public boolean check_register_dgut(){
        boolean isValid = false;
        dgutUser= em.find(DgutUser.class,username);
        if(dgutUser!=null)
            isValid=true;
        return isValid;
    }

    //注册验证
    public boolean check_register(){
        boolean isValid = false;
        localUser= em.find(LocalUser.class,username);
        if(localUser!=null)
            isValid=true;
        return isValid;
    }

    //登记工号
    public void add_dgut(){
        dgutUser=new DgutUser(username,head);
        tx.begin();
        em.persist(dgutUser);
        tx.commit();
    }

    //添加注册用户
    public void add(){
        localUser=new LocalUser(username,password1,email,name,grouptype,head);
        tx.begin();
        em.persist(localUser);
        tx.commit();
    }

    //注册输入空验证
    public boolean isNull_reg(){
        boolean isValid = false;
        if(username==null||"".equals(username)||password1==null||"".equals(password1)|| password2==null ||"".equals(password2)||email==null|| "".equals(email)|| name==null||"".equals(name)||email==grouptype|| "".equals(grouptype)){
            isValid=true;
        }
        return isValid;
    }
    //两次输入密码验证
    public boolean isEqual(){
        boolean isValid = false;
        if(password1.equals(password2)){
            isValid=true;
        }
        return isValid;
    }

    //获取关联的莞工账号
    public String getrelate(){
        localUser= em.find(LocalUser.class,username);
        if(localUser.getdgutUser()!=null) {
            dgutUser = localUser.getdgutUser();
            return dgutUser.getusername();
        }
        else
            return null;
    }
}
