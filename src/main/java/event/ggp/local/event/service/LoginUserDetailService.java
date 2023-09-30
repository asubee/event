package event.ggp.local.event.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;

import event.ggp.local.event.repositories.UserIDMapper;
import event.ggp.local.event.repositories.UserID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginUserDetailService implements UserDetailsService  {

    private final UserIDMapper mapper;

    public LoginUserDetailService(UserIDMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
            log.info("[call]loadUserByUsername: " + userid);
            try{
                UserID searchuser = mapper.getUser(userid);
                if(searchuser == null ){
                    throw new UsernameNotFoundException(userid, null);
                }
                
                ModelMapper modelMapper = new ModelMapper();
                UserDTO user = modelMapper.map(searchuser, UserDTO.class);
                return user;
            }catch( UsernameNotFoundException e){
                throw new UsernameNotFoundException(userid, null);
            }catch( DataAccessException e){
                throw new UsernameNotFoundException("データベースアクセスエラーです。", e);
            }

    }


    
}
