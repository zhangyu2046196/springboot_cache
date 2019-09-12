package com.youyuan.repository;

import com.youyuan.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangyu
 * @version 1.0
 * @description
 * @date 2019/6/26 22:47
 */
@CacheConfig(cacheNames = {"userCache"})
public interface UserRepository extends JpaRepository<User,Integer> {

    @Cacheable
    @Query("from User where id=:id")
    //@Query(value = "select * from tbl_user where id=?",nativeQuery = true)
    public User getUserById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update User set lastName=:lastName where id=:id")
    public void update(@Param("id") Integer id,@Param("lastName") String lastName);

}
