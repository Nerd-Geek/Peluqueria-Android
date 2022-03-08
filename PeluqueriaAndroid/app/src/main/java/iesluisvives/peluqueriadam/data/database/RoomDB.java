package iesluisvives.peluqueriadam.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import iesluisvives.peluqueriadam.data.daos.UserDao;
import iesluisvives.peluqueriadam.data.entity.CreateUserEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;
import iesluisvives.peluqueriadam.utils.UriConverters;

@Database(entities = {UserEntity.class},version = 1)
@TypeConverters(UriConverters.class)
public abstract class RoomDB extends RoomDatabase {

    private static String DATABASE_NAME = "peluqueria_android";
    private static volatile RoomDB INSTANCE;

    public abstract UserDao userDao();

    public  synchronized static RoomDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        //retorna la base de datos creada
        return INSTANCE;
    }

}
