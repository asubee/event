package event.ggp.local.event.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

import lombok.Data;

@Data
public class UserDTO implements UserDetails{
    private int id;
    private String userid;
    private String familyname;
    private String lastname;
    private String mailaddress;
    private String password;
    private boolean enabled;
    private Timestamp update_date;
    private Timestamp create_date;
    private Timestamp delete_date;
    private Collection<GrantedAuthority> authorities;

    public UserDTO() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(delete_date != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(delete_date != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean isEnabled() {
        if(delete_date != null){
            return false;
        }else{
            return true;
        }
    }

    public UserDTO(String userid, String password, Collection<GrantedAuthority> authorities) {
        this.userid = userid;
        this.password = password;
        this.authorities = authorities;
    }
    
    public UserDTO(String userid, String password) {
        this.userid = userid;
        this.password = password;
        this.familyname = "";
        this.lastname = "";
        this.mailaddress = "";
        this.enabled = true;
        this.create_date = new Timestamp(System.currentTimeMillis());
        this.update_date = new Timestamp(System.currentTimeMillis());
    }

}
