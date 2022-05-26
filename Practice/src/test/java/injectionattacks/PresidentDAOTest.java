package injectionattacks;

import InjectionAttacks.data.President;
import InjectionAttacks.data.PresidentDAO;
import InjectionAttacks.util.DatabaseUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PresidentDAOTest {

    @BeforeAll
    public static void setUp() throws Exception {
        DatabaseUtil.loadFile("sql/schema.sql");
        DatabaseUtil.loadFile("sql/data.sql");
    }

    @Test
    public void getByLastName() {
        PresidentDAO dao = new PresidentDAO();
        List<President> results = dao.getByLastName("Washington");
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("George", results.get(0).getFirstName());
    }

    @Test
    public void getByLastName_Injections(){
        PresidentDAO dao = new PresidentDAO();
        List<President> results = dao.getByLastName("Trump' or '1' = '1");
        assertNotNull(results);
        assertEquals(0, results.size());
    }
}