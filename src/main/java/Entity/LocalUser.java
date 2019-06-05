/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.*;

@Entity
public class LocalUser {

    @Id
    private String username;

    private String password;
    private String email;
    private String name;
    private String grouptype;
    private String head;

    @OneToOne
    private DgutUser dgutUser;

    public DgutUser getdgutUser() {
        return dgutUser;
    }

    public void setdgutUser(DgutUser dgutUser) {
        this.dgutUser = dgutUser;
    }

    public LocalUser() {
    }

    public LocalUser(String username, String password, String email, String name, String grouptype, String head) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.grouptype=grouptype;
        this.head=head;
    }

    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username= username;
    }

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password= password;
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

}
