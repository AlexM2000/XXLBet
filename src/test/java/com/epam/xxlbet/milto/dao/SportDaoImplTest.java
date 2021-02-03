package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.connection.jdbc.JdbcConnectionPool;
import com.epam.xxlbet.milto.dao.impl.SportDaoImpl;
import com.epam.xxlbet.milto.domain.Sport;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SportDaoImplTest {
    private static final String TEST_PROPERTIES = "test.properties";
    private static final String URL = "xxl.bet.milto.jdbc.url.test";
    private static final String USERNAME = "xxl.bet.milto.jdbc.user.test";
    private static final String PASSWORD = "xxl.bet.milto.jdbc.password.test";

    @InjectMocks
    private SportDaoImpl sportDaoImpl = SportDaoImpl.getInstance();

    @Mock
    private JdbcConnectionPool connectionPool;

    private Connection connection;
    private PropertyLoader loader;

    @Before
    public void setupClass() throws IOException, SQLException, InterruptedException {
        this.loader = PropertyLoader.getInstance();

        loader.init(TEST_PROPERTIES);

        this.connection = DriverManager.getConnection(
                loader.getStringProperty(TEST_PROPERTIES, URL).get(),
                loader.getStringProperty(TEST_PROPERTIES, USERNAME).get(),
                loader.getStringProperty(TEST_PROPERTIES, PASSWORD).get()
        );

        // when
        when(connectionPool.getConnection()).thenReturn(connection);

        connection.createStatement().execute("insert into sports values (0, 'sport1')");
        connection.createStatement().execute("insert into sports values (1, 'sport2')");
        connection.createStatement().execute("insert into sports values (2, 'sport3')");
    }

    @After
    public void cleanUp() throws SQLException {
        this.connection = DriverManager.getConnection(
                loader.getStringProperty(TEST_PROPERTIES, URL).get(),
                loader.getStringProperty(TEST_PROPERTIES, USERNAME).get(),
                loader.getStringProperty(TEST_PROPERTIES, PASSWORD).get()
        );

        connection.createStatement().execute("delete from sports");
        connection.close();
    }

    @Test
    public void testGetAllSports_ShouldReturnCorrectResult() {
        Sport sport1 = new Sport();
        Sport sport2 = new Sport();
        Sport sport3 = new Sport();

        sport1.setId(0L);
        sport1.setName("sport1");
        sport2.setId(1L);
        sport2.setName("sport2");
        sport3.setId(2L);
        sport3.setName("sport3");

        List<Sport> expected = asList(sport1, sport2, sport3);

        List<Sport> actual = sportDaoImpl.getAllSports();

        assertEquals(expected, actual);
    }
}
