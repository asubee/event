package event.ggp.local.event.repositories;

import java.sql.Timestamp;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import lombok.Data;

@Table(name = CommonMapper.TABLE_NAME_USERID)
@Entity
@Data
public class UserID {
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

    public UserID(int id, String userid, String familyname, String lastname, String mailaddress, String password,
            boolean enabled, Timestamp update_date, Timestamp create_date, Timestamp delete_date) {
        this.id = id;
        this.userid = userid;
        this.familyname = familyname;
        this.lastname = lastname;
        this.mailaddress = mailaddress;
        this.password = password;
        this.enabled = enabled;
        this.update_date = update_date;
        this.create_date = create_date;
        this.delete_date = delete_date;
    }

    public UserID(){
        
    }
    
}   