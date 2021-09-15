package com.syzygy.track.Repository;


import com.syzygy.track.Dto.UserDTO.UpdateUserDto;
import com.syzygy.track.model.User;
import com.syzygy.track.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Long> {
    UserData findByUser(User user);
}
