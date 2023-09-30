package event.ggp.local.event.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event.ggp.local.event.repositories.CommonMapper;
import event.ggp.local.event.repositories.CreateTableMapper;
import event.ggp.local.event.repositories.UserID;
import event.ggp.local.event.repositories.UserIDMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class IntializeService {
    private final CreateTableMapper createTableMapper;
    private final UserIDMapper userIDMapper;
    private final CommonMapper commonMapper;

    public ArrayList<String> createTableUserId(){
        ArrayList<String> initialize_msg = new ArrayList<String>();

        try{
            // UserIDテーブルを作成する
            if(!commonMapper.IsTableExists(CommonMapper.TABLE_NAME_USERID)){
                createTableMapper.UserId();
                log.info("[create table]" + CommonMapper.TABLE_NAME_USERID + " テーブルを作成しました。");
                initialize_msg.add(new String("[Create] UserIDテーブルを作成しました。"));
            }
        
            // 初期ユーザを登録する
            ModelMapper modelMapper = new ModelMapper();
            event.ggp.local.event.repositories.UserID user = modelMapper.map(new UserDTO("user", "password"), UserID.class);
            if(! userIDMapper.existUser(user.getUserid())){
                userIDMapper.registUser(user);
                log.info("[CreateUser] UserID:user password:password");
                initialize_msg.add(new String("[Insert] ユーザID：user パスワード:password 初期ユーザを登録しました。"));
            }

        }catch( Exception e){
            log.info("[CreateTableUserID]"+ e.getMessage());
        }
        return initialize_msg;
    }
}
