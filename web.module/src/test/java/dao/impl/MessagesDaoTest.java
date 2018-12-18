package dao.impl;

import com.social.network.models.Message;
import com.social.network.models.User;
import dao.BaseDaoTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MessagesDaoTest extends BaseDaoTest {
    private static User one;
    private static User two;

    @BeforeClass
    public static void setUpUsers() {
        one = new User();
        one.setEmail("one@test.ru");
        one.setPassword("123");
        one = userDao.insert(one);

        two = new User();
        two.setEmail("two@test.ru");
        two.setPassword("123");
        two = userDao.insert(two);
    }

    @Test
    public void testAddMessage() {
        messagesDao.addMessage(one.getId(), two.getId(), "One");
        messagesDao.addMessage(two.getId(), one.getId(), "Two");
        messagesDao.addMessage(one.getId(), two.getId(), "Three");

        List<Message> recentMessages = messagesDao.getRecentMessages(one.getId());
        assertNotNull(recentMessages);
        assertTrue(recentMessages.size() > 0);

        Message message = recentMessages.get(0);
        assertNotNull(message);
        assertEquals("Three", message.getMessage());
        assertEquals(one.getId(), message.getSender().getId());
        assertEquals(two.getId(), message.getReceiver().getId());
    }

    @Test
    public void testBothMessages() {
        User three = new User();
        three.setEmail("three@test.ru");
        three.setPassword("123");
        three = userDao.insert(three);

        messagesDao.addMessage(one.getId(), three.getId(), "one-three");
        messagesDao.addMessage(three.getId(), one.getId(), "three-one");

        messagesDao.addMessage(two.getId(), three.getId(), "two-three");
        messagesDao.addMessage(three.getId(), two.getId(), "three-two");
        messagesDao.addMessage(two.getId(), three.getId(), "two-three");

        List<Message> oneThree = messagesDao.getBothMessages(one.getId(), three.getId());
        assertNotNull(oneThree);
        assertTrue(oneThree.size() > 0);
        assertEquals(2, oneThree.size());

        List<Message> twoThree = messagesDao.getBothMessages(two.getId(), three.getId());
        assertNotNull(twoThree);
        assertTrue(twoThree.size() > 0);
        assertEquals(3, twoThree.size());
    }
}
