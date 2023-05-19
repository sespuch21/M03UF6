package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.persistence.daos.contracts.ColorDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

public class ColorDAOJDBCImpl implements ColorDAO {
    @Override
    public Color getColorById(long id) throws DAOException {
        Color color = null;

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection
                        .prepareStatement("SELECT id, name, red, green, blue FROM colors WHERE id = ?");) {

            sentSQL.setLong(1, id);
            try (ResultSet reader = sentSQL.executeQuery()) {
                if (reader.next()) {
                    color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                            reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return color;
    }

    @Override
    public List<Color> getColors() throws DAOException {
        List<Color> colors = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection
                        .prepareStatement("SELECT id, name, red, green, blue FROM colors");
                ResultSet reader = sentSQL.executeQuery()) {

            while (reader.next()) {
                Color color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                        reader.getInt("blue"));
                color.setId(reader.getLong("id"));
                colors.add(color);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        return colors;
    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("No se admite todavía.");
    }

    @Override
    public List<Color> getColors(String searchTerm) throws DAOException {
        throw new UnsupportedOperationException("No se admite todavía.");
    }

    @Override
    public List<Color> getColors(String searchTerm, int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("No se admite todavía.");
    }
}
