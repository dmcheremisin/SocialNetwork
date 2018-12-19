package service;

import org.junit.Test;

import static com.social.network.utils.ServerUtils.getRequestedUrl;
import static com.social.network.utils.ServerUtils.isBlank;
import static com.social.network.utils.ServerUtils.isInteger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerUtilsTest {
    @Test
    public void testBlank(){
        assertTrue(isBlank(""));
        assertTrue(isBlank(" "));
        assertFalse(isBlank(" 1"));
        assertFalse(isBlank(" A"));
    }

    @Test
    public void testInteger() {
        assertTrue(isInteger("123"));
        assertFalse(isInteger(" 123"));
        assertFalse(isInteger("123 "));
        assertFalse(isInteger("A123"));
        assertFalse(isInteger("123A"));
    }

    @Test
    public void testGetRequestedUrl() {
        String requestedUrl = getRequestedUrl("http://www.social-network.com");
        assertEquals("", requestedUrl);

        String requestedUrl1 = getRequestedUrl("http://www.social-network.com/");
        assertEquals("", requestedUrl1);

        String requestedUrl2 = getRequestedUrl("http://www.social-network.com/profile");
        assertEquals("profile", requestedUrl2);

        String requestedUrl3 = getRequestedUrl("http://www.social-network.com/friends");
        assertEquals("friends", requestedUrl3);

        String requestedUrl4 = getRequestedUrl("http://www.social-network.com/friends?id=1");
        assertEquals("friends", requestedUrl4);

        String requestedUrl5 = getRequestedUrl("http://www.social-network.com/friends?id=1&f=dsfdsf");
        assertEquals("friends", requestedUrl5);

    }
}
