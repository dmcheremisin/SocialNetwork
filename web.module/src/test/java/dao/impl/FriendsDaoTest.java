package dao.impl;

import com.social.network.models.User;
import com.social.network.models.UserFriend;
import dao.BaseDaoTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FriendsDaoTest extends BaseDaoTest {
    private User one;
    private User two;

    @Before
    public void createUsers() {
        int random = (int) (Math.random() * 1000);
        one = new User();
        one.setEmail(random + "@test.ru");
        one.setPassword("123");
        one = userDao.insert(one);

        random = (int) (Math.random() * 1000);
        two = new User();
        two.setEmail(random + "@test.ru");
        two.setPassword("123");
        two = userDao.insert(two);
    }

    @Test
    public void testAddFriendship() {
        friendsDao.addToFriends(one.getId(), two.getId());
        List<UserFriend> friendsRequests = friendsDao.getFriendsRequests(one.getId());
        assertNotNull(friendsRequests);
        assertTrue(friendsRequests.size() > 0);
        UserFriend userFriend = friendsRequests.get(0);
        assertTrue(userFriend.getUserSender().getId() == one.getId());
        assertTrue(userFriend.getUserReceiver().getId() == two.getId());

        boolean haveFriendship = friendsDao.checkUsersHaveFriendship(one.getId(), two.getId());
        assertTrue(haveFriendship);
    }

    @Test
    public void testAcceptFriendship() {
        friendsDao.addToFriends(one.getId(), two.getId());
        friendsDao.acceptFriendRequest(one.getId(), two.getId());
        List<UserFriend> friends = friendsDao.getFriends(one.getId());
        assertNotNull(friends);
        assertTrue(friends.size() > 0);
        UserFriend friend = friends.get(0);
        assertTrue(friend.getUserSender().getId() == one.getId());
        assertTrue(friend.getUserReceiver().getId() == two.getId());
    }

    @Test
    public void testDeclineFriendship() {
        friendsDao.addToFriends(one.getId(), two.getId());
        List<UserFriend> friendsRequests = friendsDao.getFriendsRequests(one.getId());
        assertNotNull(friendsRequests);
        assertTrue(friendsRequests.size() > 0);
        boolean haveFriendship = friendsDao.checkUsersHaveFriendship(one.getId(), two.getId());
        assertTrue(haveFriendship);

        friendsDao.declineFriendRequest(one.getId(), two.getId());
        friendsRequests = friendsDao.getFriendsRequests(one.getId());
        assertNotNull(friendsRequests);
        assertTrue(friendsRequests.size() == 0);
        haveFriendship = friendsDao.checkUsersHaveFriendship(one.getId(), two.getId());
        assertFalse(haveFriendship);
    }
}
