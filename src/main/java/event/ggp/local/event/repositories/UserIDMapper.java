package event.ggp.local.event.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserIDMapper {

    private final JdbcTemplate JdbcTemplate;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserID> getUserALL(){
        String sql = "select * from " + CommonMapper.TABLE_NAME_USERID + " where enabled = true order by id asc ;";
        List<UserID> result = new ArrayList<UserID>();

        try{
            result = JdbcTemplate.query(sql,new DataClassRowMapper<>(UserID.class));          
        } catch (DataAccessException e){
            log.error(e.getMessage());
        }
        
        return result;
    }

    public UserID getUser(String name){
        String sql = "select * from " + CommonMapper.TABLE_NAME_USERID + " WHERE userid = ? AND enabled = true;";
        log.info("[call]UserIDMapper.getUser:" + name);
        UserID result = new UserID();
        result = JdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(UserID.class), name);
        return result;
    }

    public boolean existUser(String name) throws Exception{
        String sql = "select * from " + CommonMapper.TABLE_NAME_USERID + " WHERE userid = ?;";
 
        try{
            UserID result = JdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(UserID.class), name);
            if(result != null){
                return true;
            }else{
                return false;
            }           
        }catch(EmptyResultDataAccessException e){
            log.info("検索結果が1件もありません。userid：" + name);
            return false;
        }catch(DataAccessException e){
            log.error("[call existUser]:" + e.getMessage());
            throw new Exception("データアクセスが失敗しました。管理者に連絡してください。");
        }catch(Exception e){
            log.error("[call existUser]:" + e.getMessage());
            throw new Exception("想定外のエラーが発生しました。管理者に連絡してください。");
        }

    }

    public void registUser(UserID user) throws Exception{
        String sql = "insert into " + CommonMapper.TABLE_NAME_USERID  
        + "( userid , familyname, lastname, mailaddress, password, enabled, update_date, create_date )" 
        + " values ( ?, ? , ? , ?, ? , true, current_timestamp, current_timestamp);";
        log.info("user password:" + user.getPassword());

    try{
        JdbcTemplate.update(sql, 
            user.getUserid(), 
            user.getFamilyname(), 
            user.getLastname(), 
            user.getMailaddress(), 
            passwordEncoder.encode(user.getPassword()));
            
    }catch(DataAccessException e){
        log.error("[call addUser]:" + e.getMessage());
        throw new Exception("データアクセスが失敗しました。管理者に連絡してください。");

    }catch(Exception e){
        log.error("[call addUser]:" + e.getMessage());
        throw new Exception("想定外のエラーが発生しました。管理者に連絡してください。");
    }    

    }

    public void modifyUser(UserID user) throws Exception{
        String sql = "update " + CommonMapper.TABLE_NAME_USERID  
        + " set familyname = ?, lastname = ?, mailaddress = ?, update_date = current_timestamp where userid = ?;";
        log.info("[modifyUser]" + sql + " userID: " + user.getUserid());
        try{
        JdbcTemplate.update(sql, 
            user.getFamilyname(), 
            user.getLastname(), 
            user.getMailaddress(),
            user.getUserid()
            );
            
    }catch(DataAccessException e){
        log.error("[call modifyUser]:" + e.getMessage());
        throw new Exception("データアクセスが失敗しました。管理者に連絡してください。");

    }catch(Exception e){
        log.error("[call modifyUser]:" + e.getMessage());
        throw new Exception("想定外のエラーが発生しました。管理者に連絡してください。");
    }    
    
    }

    public void deleteUser(UserID user) throws Exception{
        String sql = "update " + CommonMapper.TABLE_NAME_USERID
        + " set delete_date = current_timestamp, enabled = false where userid = ?;";
        log.info("[deleteUser]" + sql + " userID: " + user.getUserid());
        try{
            JdbcTemplate.update(sql, user.getUserid());
            
        }catch(DataAccessException e){
            log.error("[call deleteUser]:" + e.getMessage());
            throw new Exception("データアクセスが失敗しました。管理者に連絡してください。");

        }catch(Exception e){
            log.error("[call deleteUser]:" + e.getMessage());
            throw new Exception("想定外のエラーが発生しました。管理者に連絡してください。");
        }       
    
    }

}
