package com.artamonov.restaurantmenuvoting.repository;

import com.artamonov.restaurantmenuvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.restaurant.id=:restaurantId AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId, @Param("userId") int userId);

}
