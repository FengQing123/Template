package com.example.template.jetpack.room.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.template.jetpack.room.dao.AddressDao;
import com.example.template.jetpack.room.dao.StudentDao;
import com.example.template.jetpack.room.entity.Address;
import com.example.template.jetpack.room.entity.Student;

/**
 * 功能描述：数据库
 * Created by gfq on 2020/4/1.
 */
@Database(entities = {Student.class, Address.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {

    public abstract StudentDao studentDao();

    public abstract AddressDao addressDao();

    private static AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context mContext) {
        if (instance == null) {
            instance = Room.databaseBuilder(mContext.getApplicationContext(), AppDataBase.class, "AppDataBase")
                    //可以强制在主线程运行数据库操作
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return instance;
    }

    /**
     * 从版本1 升级到 版本2
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS 'Address' (" +
                    "addressId INTEGER PRIMARY KEY NOT NULL," +
                    "addressName TEXT)");
        }
    };

    /**
     * 从版本2 升级到 版本3
     */
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table Student add column age integer not null default 0");
        }
    };

}
