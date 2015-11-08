package models;

/**
 * Created by Alec Klein on 11/7/2015.
 */
public class UserDAO {

    long userid;
    int state;
    String email;
    String fname;
    String lname;
    String password;

    public UserDAO(long userid, int state, String email, String fname, String lname, String password) {
        this.userid = userid;
        this.state = state;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }

    public UserDAO(long userid, String email) {
        this.userid = userid;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
