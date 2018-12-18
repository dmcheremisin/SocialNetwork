package dao.impl;

import com.social.network.models.User;
import dao.BaseDaoTest;
import org.junit.Test;

import static com.social.network.constants.Role.ADMIN;
import static com.social.network.constants.Role.MEMBER;
import static com.social.network.utils.ServerUtils.isNotBlank;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserDaoTest extends BaseDaoTest {

    @Test
    public void testAdmin() {
        User user = userDao.get(1);
        assertEquals(1, (int) user.getRole());
        assertEquals("Tyrion", user.getFirstName());
        assertEquals("Lannister", user.getLastName());
        assertTrue(isNotBlank(user.getEmail()));
        assertTrue(isNotBlank(user.getPassword()));
        assertEquals(2, (int) user.getSex());
        assertTrue(isNotBlank(user.getDob()));
        assertFalse(user.getBlocked());
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setEmail("insert@test.ru");
        user.setPassword("123");
        User insert = userDao.insert(user);

        assertEquals("insert@test.ru", user.getEmail());
        assertEquals("123", user.getPassword());
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setEmail("update@test.ru");
        user.setPassword("123");
        User inserted = userDao.insert(user);

        inserted.setFirstName("update");
        inserted.setLastName("user");
        inserted.setSex(1);
        inserted.setDob("22.11.1985");
        inserted.setPhone("+7 921 555 22 33");
        inserted.setBlocked(false);
        userDao.update(inserted);

        User updated = userDao.get(inserted.getId());
        assertEquals("update", updated.getFirstName());
        assertEquals("user", updated.getLastName());
        assertEquals(1, (int) updated.getSex());
        assertTrue(updated.getDob().equals("22.11.1985"));
        assertTrue(updated.getPhone().equals("+7 921 555 22 33"));
        assertEquals("update@test.ru", updated.getEmail());
        assertEquals("123", updated.getPassword());
        assertFalse(updated.getBlocked());
    }

    @Test
    public void testGetByCreds(){
        User user = new User();
        user.setEmail("get@test.ru");
        user.setPassword("1234567890");
        User insert = userDao.insert(user);

        User userByCredentials = userDao.getUserByCredentials("get@test.ru", "1234567890");
        assertNotNull(userByCredentials);
        assertTrue(userByCredentials.getEmail().equals("get@test.ru"));

        User wrong = userDao.getUserByCredentials("get@test.ru", "01234567890");
        assertNull(wrong);
    }

    @Test
    public void testUpdateImage() {
        User user = new User();
        user.setEmail("image@test.ru");
        user.setPassword("123");
        User inserted = userDao.insert(user);

        inserted.setImage("image.jpg");
        User userWithImage = userDao.updateImage(inserted);

        assertNotNull(userWithImage.getImage());
        assertEquals(inserted.getImage(), userWithImage.getImage());
    }

    @Test
    public void testUpdatePassword() {
        User user = new User();
        user.setEmail("password@test.ru");
        user.setPassword("123");
        User inserted = userDao.insert(user);

        inserted.setPassword("456");
        User updated = userDao.updatePassword(inserted);

        assertNotNull(updated.getPassword());
        assertEquals(inserted.getPassword(), updated.getPassword());
    }

    @Test
    public void testBlockUnblock() {
        User user = new User();
        user.setEmail("blockUnblock@test.ru");
        user.setPassword("123");
        User inserted = userDao.insert(user);
        assertFalse(inserted.getBlocked());

        int userId = inserted.getId();
        userDao.blockUnblock(userId, true);
        User updated = userDao.get(userId);
        assertTrue(updated.getBlocked());

        userDao.blockUnblock(userId, false);
        updated = userDao.get(userId);
        assertFalse(updated.getBlocked());
    }

    @Test
    public void testSetPriveleges() {
        User user = new User();
        user.setEmail("privileges@test.ru");
        user.setPassword("123");
        User inserted = userDao.insert(user);
        assertEquals(MEMBER.getKey(), (int) inserted.getRole());

        int userId = inserted.getId();
        userDao.setPrivileges(userId, ADMIN);
        User updated = userDao.get(userId);
        assertEquals(ADMIN.getKey(), (int) updated.getRole());

        userDao.setPrivileges(userId, MEMBER);
        updated = userDao.get(userId);
        assertEquals(MEMBER.getKey(), (int) updated.getRole());
    }

}
