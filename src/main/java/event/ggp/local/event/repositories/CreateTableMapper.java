package event.ggp.local.event.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CreateTableMapper {
    private final JdbcTemplate JdbcTemplate;

    public void UserId(){

        String sql = "CREATE TABLE IF NOT EXISTS userid ("
        +"      id          SERIAL          NOT NULL,"
        +"      userid      VARCHAR(100)    NOT NULL UNIQUE,"
        +"      familyname  VARCHAR(100)    ,"
        +"      lastname    VARCHAR(100)    ,"
        +"      password    VARCHAR(300)    NOT NULL,"
        +"      mailaddress VARCHAR(300)    ,"
        +"      enabled     BOOLEAN         NOT NULL,"
        +"      create_date TIMESTAMP       NOT NULL,"
        +"      update_date TIMESTAMP       NOT NULL,"
        +"      delete_date TIMESTAMP,"
        +"      PRIMARY KEY (id)"
        +");";

        try{
            JdbcTemplate.execute(sql);
        } catch (DataAccessException e){
            log.error(e.getMessage());
        }
        
        return;
    }
}