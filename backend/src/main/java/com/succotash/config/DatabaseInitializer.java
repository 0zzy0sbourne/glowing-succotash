package com.succotash.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DatabaseInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            if (checkDatabaseConnection()) {
                createUsersTable();
                // Add more initialization steps here if needed
            }
        };
    }

    private boolean checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("Successfully connected to the database.");
            return true;
        } catch (SQLException e) {
            logger.error("Failed to connect to the database.", e);
            return false;
        }
    }

    private void createUsersTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                username VARCHAR(255) NOT NULL UNIQUE,
                email VARCHAR(255) NOT NULL UNIQUE,
                password_hash VARCHAR(255) NOT NULL,
                created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
            )
        """;

        try {
            jdbcTemplate.execute(createTableSQL);
            logger.info("Users table created or already exists.");
        } catch (Exception e) {
            logger.error("Error creating users table.", e);
        }
    }
}