package com.school.core.repository;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class ScheduleRepository extends BaseRepository {
    public void keepAlive() {
        try {
            String sql = "SELECT 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
