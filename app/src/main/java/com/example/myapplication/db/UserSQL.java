package com.example.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Tour;
import com.example.myapplication.model.User;
import com.example.myapplication.model.Account;

import java.util.ArrayList;
import java.util.List;

public class UserSQL extends SQLiteOpenHelper {

    private static final String DB_NAME = "usertblnew";
    private static final int DB_VERSION = 2;

    public UserSQL(@Nullable Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "birthday TEXT," +
                "gender TEXT," +
                "image TEXT," +
                "role TEXT," +
                "accountID INTEGER," +
                "FOREIGN KEY (accountID) REFERENCES account(id))";
        sqLiteDatabase.execSQL(sql);
        String sql1 = "CREATE TABLE IF NOT EXISTS account(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)";
        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addUser(User user, int accountID){
        String sql = "INSERT INTO user (name, birthday, gender, image, role, accountID)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        String agrs[] = {
                user.getName(),
                user.getBirthday(),
                user.getGender(),
                user.getImage(),
                user.getRole(),
                String.valueOf(accountID)
        };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql, agrs);
    }
    public void updateUser(User user){
        String sql = "UPDATE user SET\n" +
                "name = ?,\n" +
                "birthday = ?," +
                "gender = ?," +
                "image = ?," +
                "role = ?" +
                "WHERE id = ?";
        String args[] = {
                user.getName(),
                user.getBirthday(),
                user.getGender(),
                user.getImage(),
                user.getRole(),
        };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql,args);
    }
    public void deleteUser(User user){
        String sql = "DELETE FROM user WHERE id = ?";
        String[] agrs = {String.valueOf(user.getId())};
        SQLiteDatabase state = getReadableDatabase();
        Log.d("NM", "delete");
        state.execSQL(sql, agrs);
    }
    public List<User> getAll(int accountID){
        List<User> list = new ArrayList<>();
        SQLiteDatabase state = getReadableDatabase();
        Cursor cursor = state.rawQuery("SELECT * FROM user WHERE user.accountID = " + accountID, null);
        while (cursor != null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String birthday = cursor.getString(2);
            String gender = cursor.getString(3);
            String image = cursor.getString(4);
            String role = cursor.getString(5);
            int accountId = cursor.getInt(7);
            User user = new User(id, name, birthday, gender, image,role);
            Log.d("TAGLOG", user.toString() + " account: "+ accountId);
            list.add(user);
        }
        return list;
    }
    public List<User> search(String txtSearch){
        List<User> listUser = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE name " +
                " LIKE '%"+txtSearch + "%'", null);
        while (cursor != null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String birthday = cursor.getString(2);
            String gender = cursor.getString(3);
            String image = cursor.getString(4);
            String role = cursor.getString(5);
            int accountId = cursor.getInt(6); //sao lai 7 nhi, phai 6 chu =)) lu' nhu con cu' .treét lai di ong.ocee
            User user = new User(id, name, birthday, gender, image, role);
            Log.d("TAGLOG", user.toString() + " account: "+ accountId);
            listUser.add(user);

        }
        return listUser;
    }
    public Account checkLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM account WHERE username = '" + username + "' AND password = '"+password+"'", null);
        while (cursor != null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String user = cursor.getString(1);
            String pass = cursor.getString(2);
            Account account = new Account(id, user, pass);
            return account;
        }
        return null;
    }

    public boolean checkUserName(String username){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM account WHERE username = '" + username + "'", null);
        while (cursor != null && cursor.moveToNext()){
            return false;
        }
        return true;
    }

    public void addAccount (Account account){
        String sql = "INSERT INTO account (username, password)" +
                "VALUES (?, ?)";
        String args[] = {
                account.getUsername(),
                account.getPassword()
        };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql,args);
    }

    public void updateAccount(Account account){
        String sql = "UPDATE account SET\n" +
                "username = ?,\n" +
                "password = ?\n"+
                "WHERE id = ?";
        String args[] = {
                account.getUsername(),
                account.getPassword(),
                String.valueOf(account.getId())
        };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql,args);

    }

    public List<Tour> searchTour(String txtSearch){
        List<Tour> listUser = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tour WHERE name " +
                " LIKE '%"+txtSearch + "%'", null);
        while (cursor != null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String startDate = cursor.getString(2);
            String trans = cursor.getString(3);
            String duration = cursor.getString(4);
            String image = cursor.getString(5);
            String total = cursor.getString(6);
            Tour tour = new Tour(id, name, startDate, trans, duration, image, total);
            Log.d("TAGLOG", tour.toString() );
            listUser.add(tour);

        }
        return listUser;
    }

    public void addTour(Tour tour){
        String sql = "INSERT INTO tour (name,  startDate,  trans,  duration, img,  total)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String agrs[] = {
                tour.getName(),
                tour.getStartDate(),
                tour.getTrans(),
                tour.getDuration(),
                tour.getImg(),
                tour.getTotal()
        };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql, agrs);
    }
    public String test(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id FROM account",null);
        while (cursor != null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String array = id + " ";
            return array;
        }
        return null;
    }
    public void deleteTour(Tour tour){
        String sql = "DELETE FROM tour WHERE id = ?";
        String[] agrs = {String.valueOf(tour.getId())};
        SQLiteDatabase state = getReadableDatabase();
        Log.d("NM", "delete");
        state.execSQL(sql, agrs);
    }
}
