package com.syzygy.track.repository;

import com.syzygy.track.model.User;
import com.syzygy.track.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct,Long> {
    List<UserProduct> findAllByUser(User user);
}
