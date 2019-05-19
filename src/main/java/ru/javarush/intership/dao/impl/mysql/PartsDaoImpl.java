package ru.javarush.intership.dao.impl.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.intership.dao.PartsDao;
import ru.javarush.intership.model.Part;
import ru.javarush.intership.model.PartsFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PartsDaoImpl implements PartsDao {
    private static final Logger logger = LoggerFactory.getLogger(PartsDaoImpl.class);

    @Override
    public Map<Integer, Part> getAllParts() throws SQLException {
        Map<Integer, Part> parts = new HashMap<>(10);
        Part part;
        int id;
        String partName;
        boolean isNeed;
        int num;

        try (PreparedStatement statement = ConnectionFactory.getInstance()
                .getConnection().prepareStatement("SELECT PART_ID, PART_NAME, PART_IS_NEED, PART_NUM FROM PARTS")) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                id = set.getInt(1);
                partName = set.getString(2);
                isNeed = set.getBoolean(3);
                num = set.getInt(4);
                part = PartsFactory.newPart(
                        id,
                        partName,
                        isNeed,
                        num
                );
                parts.put(id, part);
            }
        } catch (SQLException e) {
            logger.error("Cannot get data from database", e);
            throw e;
        }
        return parts;
    }

    @Override
    public int add(Part part) throws SQLException {
        try (PreparedStatement statement = ConnectionFactory.getInstance()
                .getConnection().prepareStatement("INSERT INTO PARTS(PART_NAME, PART_IS_NEED, PART_NUM) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, part.getName());
            statement.setBoolean(2, part.isNeed());
            statement.setInt(3, part.getNum());
            statement.executeUpdate();
            int id = statement.getGeneratedKeys().getInt(1);
            logger.debug("{} has been inserted with id={}", part.toString(), id);
            return id;
        } catch (SQLException e) {
            logger.error("Couldn't add part", e);
            throw e;
        }
    }

    @Override
    public int edit(Part part) throws SQLException {
        try (PreparedStatement statement = ConnectionFactory.getInstance()
                .getConnection().prepareStatement(
                        "UPDATE PARTS SET PART_NAME = ?, PART_IS_NEED = ?, PART_NUM = ? WHERE PART_ID = ?"
                )
        ) {
            statement.setString(1, part.getName());
            statement.setBoolean(2, part.isNeed());
            statement.setInt(3, part.getNum());
            statement.setInt(4, part.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Couldn't update part", e);
            throw e;
        }
    }


    @Override
    public int delete(Part part) throws SQLException {
        return deletePartById(part.getId());
    }

    @Override
    public int deletePartById(int id) throws SQLException {
        try (PreparedStatement statement = ConnectionFactory.getInstance()
                .getConnection().prepareStatement("DELETE FROM PARTS WHERE PART_ID = ?")
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Couldn't delete part", e);
            throw e;
        }
    }

    @Override
    public Part getPartById(int id) {
        Part part = null;
        try (PreparedStatement statement = ConnectionFactory.getInstance()
                .getConnection().prepareStatement("SELECT PART_NAME, PART_IS_NEED, PART_NUM FROM PARTS WHERE PART_ID = ?")) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                part = PartsFactory.newPart(
                        id,
                        set.getString(1),
                        set.getBoolean(2),
                        set.getInt(3)
                );
            }
        } catch (SQLException e) {
            logger.error("Couldn't find part by id", e);
        }
        return part;
    }
}
