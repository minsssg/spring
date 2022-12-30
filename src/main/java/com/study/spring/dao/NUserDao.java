package com.study.spring.dao;

import com.study.spring.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {
    /** 템플릿 메서드 패턴: 추상 메서드 오버라이드 가능한 메서드를 필요에 맞게 구현하는 방법. */
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "aa12345^^");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new NUserDao();

        User user = userDao.get("minsssg");
        System.out.println("user = " + user);
        System.out.println("조회 성공");
    }
}
