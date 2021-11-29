package com.school.core.repository;

import com.school.core.config.DBConnection;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public abstract class BaseRepository {
    protected Connection connection = DBConnection.getInstance().getConnection();
}
