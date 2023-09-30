package event.ggp.local.event.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Repository
@RequiredArgsConstructor
@Slf4j
public class CommonMapper {

    /* テーブル名定義 */
    public static final String TABLE_NAME_USERID = "userid";

    /*  JDBCドライバ読み込み */
    private final JdbcTemplate JdbcTemplate;

    public boolean IsTableExists(String table_name){
        String sql =  "SELECT * FROM information_schema.tables WHERE table_name = ?";
        List<Map<String, Object>> result = JdbcTemplate.queryForList(sql,table_name);

        if(result.size() == 0){
            log.info("[IsTableExists]" + table_name + " テーブルは存在しません。");
            return false;
        }else{
            log.info("[IsTableExists]" + table_name + " テーブルは既にあります。");
            return true;
        }
    }

}



