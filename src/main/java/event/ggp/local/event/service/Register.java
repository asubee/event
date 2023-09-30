package event.ggp.local.event.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import event.ggp.local.event.repositories.UserIDMapper;
import event.ggp.local.event.repositories.UserID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class Register {

    // UserIDテーブルにアクセスするためのDAOを定義
    private final UserIDMapper userIDMapper;

    public void registUser(UserDTO user) throws Exception {

        if(! userIDMapper.existUser(user.getUserid())){
            ModelMapper modelMapper = new ModelMapper();
            event.ggp.local.event.repositories.UserID userID = modelMapper.map(user, UserID.class);           
            userIDMapper.registUser(userID);
            log.info("[call]ユーザを登録する");
        }else{
            throw new Exception("同じユーザIDが既に登録されています。別のユーザIDを指定してください。");
        }
    }

    public void modifyUser(UserDTO user) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        event.ggp.local.event.repositories.UserID userID = modelMapper.map(user, UserID.class);           
        userIDMapper.modifyUser(userID);
        log.info("[call]ユーザを編集する");
    }

    public void deleteUser(UserDTO user) throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        userIDMapper.deleteUser(modelMapper.map(user, UserID.class));
        log.info("[call]ユーザを削除する");
    }
}
