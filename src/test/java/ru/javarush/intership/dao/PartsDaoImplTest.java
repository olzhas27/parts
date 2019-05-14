package ru.javarush.intership.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.javarush.intership.dao.impl.mysql.ConnectionFactory;
import ru.javarush.intership.dao.impl.mysql.PartsDaoImpl;
import ru.javarush.intership.model.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class PartsDaoImplTest {
    private PartsDao partsDao = new PartsDaoImpl();

    @Test
    public void testGetAllParts() {
        Map<Integer, Part> parts = null;
        try {
            parts = partsDao.getAllParts();
        } catch (SQLException e) {
            fail();
        }
        int count = 0;
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM PARTS") ) {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    count = set.getInt(1);
                }
            }

        } catch (SQLException e) {
            fail();
        }
        Assert.assertEquals(count, parts.size());

    }

}
