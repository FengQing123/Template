package com.example.template.jetpack.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.template.jetpack.room.entity.Address;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
@Dao
public interface AddressDao {

    @Insert
    void insert(Address... addresses);

    @Query("select * from address")
    List<Address> findAllAddress();
}
