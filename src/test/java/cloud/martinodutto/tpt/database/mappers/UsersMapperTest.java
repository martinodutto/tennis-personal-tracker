package cloud.martinodutto.tpt.database.mappers;

import cloud.martinodutto.tpt.database.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UsersMapperTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void selectAllWorks() {
        final List<User> users = usersMapper.selectAll();
        assertNotNull(users);
        assertEquals(3, users.size());

        // to test the mapping of the fields
        final Optional<User> opMdutto = users.stream().filter(u -> "mdutto".equals(u.getUsername())).findFirst();
        assertTrue(opMdutto.isPresent());
        final User mdutto = opMdutto.get();
        assertEquals(0, mdutto.getUserId());
        assertEquals("password", mdutto.getPassword());
        assertTrue(mdutto.isEnabled());
        assertEquals(1, (long) mdutto.getRoleId());
    }

    @Test
    public void selectByPkWorks() {
        final User user = usersMapper.selectByPk(1);
        assertNotNull(user);
        assertEquals("pcometto", user.getUsername());
        assertEquals("thisismykey", user.getPassword());
        assertFalse(user.isEnabled());
        assertEquals(2, (long) user.getRoleId());
    }

    @Test
    public void selectingAnInexistentPkReturnsNull() {
        assertNull(usersMapper.selectByPk(8));
    }

    @Test
    public void selectByUsernameWorks() {
        final User user = usersMapper.selectByUsername("mdutto");
        assertNotNull(user);
        assertEquals(0, user.getUserId());
        assertEquals("mdutto", user.getUsername());
        assertEquals("password", user.getPassword());
        assertTrue(user.isEnabled());
        assertEquals(1, (long) user.getRoleId());
    }

    @Test
    public void anInvalidUsernameReturnsNull() {
        final User user = usersMapper.selectByUsername("MDUTTO");
        assertNull(user);
    }

    @Test
    public void insertWorks() {
        User user = new User();
        user.setUsername("gponzano");
        user.setPassword("XXXZZZYYY");
        user.setEnabled(true);
        user.setRoleId(1);

        assertEquals(1, usersMapper.insert(user));
        final User insertedUser = usersMapper.selectByPk(user.getUserId());
        assertNotNull(insertedUser); // this also lets us verify that the id is attached correctly to the entity
        // we verify that all the inserted fields were correctly persisted
        assertEquals(user.getUsername(), insertedUser.getUsername());
        assertEquals(user.getPassword(), insertedUser.getPassword());
        assertEquals(user.isEnabled(), insertedUser.isEnabled());
        assertEquals((long) user.getRoleId(), (long) insertedUser.getRoleId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertingAnEmptyUsernameProducesAnError() {
        User user = new User();
        // the username is left null
        user.setPassword("XXXZZZYYY");
        user.setEnabled(true);
        user.setRoleId(1);

        usersMapper.insert(user);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertingAnEmptyPasswordProducesAnError() {
        User user = new User();
        user.setUsername("gponzano");
        // the password is left null
        user.setEnabled(true);
        user.setRoleId(1);

        usersMapper.insert(user);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void aDuplicateUsernameProducesAnError() {
        User user = new User();
        user.setUsername("mdutto");
        user.setPassword("ciaociao");
        user.setEnabled(true);
        user.setRoleId(1);

        usersMapper.insert(user);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void aViolatedRoleFkProducesAnError() {
        User user = new User();
        user.setUsername("gponzano");
        user.setPassword("ciaociao");
        user.setEnabled(true);
        user.setRoleId(33); // inexistent

        usersMapper.insert(user);
    }

    @Test
    public void updateWorks() {
        final User user = usersMapper.selectByPk(2);
        assertNotNull(user);
        user.setUsername("betterthanezra");
        user.setPassword("newpasswd");
        user.setEnabled(false);
        user.setRoleId(2);

        assertEquals(1, usersMapper.update(user));

        final User updatedUser = usersMapper.selectByPk(user.getUserId());
        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.isEnabled(), updatedUser.isEnabled());
        assertEquals(user.getRoleId(), updatedUser.getRoleId());
    }

    @Test
    public void deleteWorks() {
        final User user = usersMapper.selectByPk(2);
        assertNotNull(user);
        assertEquals(1, usersMapper.delete(user));
        assertEquals(2, usersMapper.selectAll().size());
        assertNull(usersMapper.selectByPk(user.getUserId()));
    }
}