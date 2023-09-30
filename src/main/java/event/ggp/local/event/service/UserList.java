package event.ggp.local.event.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import event.ggp.local.event.repositories.UserIDMapper;
import event.ggp.local.event.repositories.UserID;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserList {
    
    // UserIDテーブルにアクセスするためのDAOを定義
    private final UserIDMapper userIDMapper;

    public List<UserDTO> GetUserAll(){
        List<UserID> userList = userIDMapper.getUserALL();
        ModelMapper modelMapper = new ModelMapper();
        List<UserDTO> userList_repo = modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
        return userList_repo;
    }

    public UserDTO GetUser(String userid){
        UserID user = userIDMapper.getUser(userid);
        ModelMapper modelMapper = new ModelMapper();
        UserDTO user_repo = modelMapper.map(user, UserDTO.class);
        return user_repo;         
    }

}
