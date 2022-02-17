package com.kodilla.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DbManagerTest {

    @Test
    public void testUpdateVipLevels() throws SQLException {

        // Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdate = "UPDATE READERS SET VIP_LEVEL=\"Not set\"";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdate);
        String sqlCheckTable = "SELECT COUNT(*) AS HOW_MANY FROM READERS WHERE VIP_LEVEL=\"Not set\"";
        ResultSet rs = statement.executeQuery(sqlCheckTable);

        // When
        String sqlProcedureCall = "CALL UpdateVipLevels()";
        statement.execute(sqlProcedureCall);

        // Then
        int howMany = -1;
        if (rs.next()) {
            howMany = rs.getInt("HOW_MANY");
        }
        assertEquals(0, howMany);
        rs.close();
        statement.close();
    }

    @Test
    public void testUpdateBestseller() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();
        Statement statement = dbManager.getConnection().createStatement();
        String update = "UPDATE BOOKS SET BESTSELLER=0";
        statement.executeUpdate(update);
        String sqlCheckTable = "SELECT COUNT(*) AS HOW_MANY FROM BOOKS WHERE BESTSELLER=1";
        //When
        String proceduralCall = "CALL UpdateBestsellers()";
        statement.execute(proceduralCall);
        ResultSet resultSet = statement.executeQuery(sqlCheckTable);
        //Then
        if(resultSet.next())
            assertEquals(1, resultSet.getInt("HOW_MANY"));
        resultSet.close();
        statement.close();
    }
}