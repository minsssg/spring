package com.study.spring.dao;

import com.study.spring.domain.User;

import java.sql.*;

/**
 * 1. 초간단 DAO == 초난감 DAO
 * 실무에서 이런식으로 작성하면 쫓겨 난다고 한다.....
 * 2. getConnection() DB연결 관심사 분리
 * 3. abstract class 로 만들기
 */
public abstract class UserDao {

    /** DB Connection 중복코드 분리
     * DB의 url 이나 로그인 유저가 달라져도
     * getConnection 매서드만 수정하면 된다.
     */
    protected abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO users(id, name, password) value (?, ?, ?)"
        );

        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM users WHERE id = ?"
        );

        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
