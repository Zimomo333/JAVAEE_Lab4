/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.*;

@Entity
public class DgutUser {

    @Id
    private String username;

    private String head;

    @OneToOne
    private LocalUser localUser;

    public LocalUser getlocalUser() {
        return localUser;
    }

    public void setlocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }

    public DgutUser() {
    }

    public DgutUser(String username,String head) {
        this.username=username;
        this.head=head;
    }

    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username= username;
    }

    public String gethead() { return head; }
    public void sethead(String head) { this.head= head; }

}
