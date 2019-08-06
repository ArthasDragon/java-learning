import com.shiyanlou.RedisApplication;
import com.shiyanlou.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void setGetTest() {
        redisUtil.set("name", "shiyanlou");
        Assert.assertEquals("shiyanlou", redisUtil.get("name"));
        redisUtil.del("name");

    }

    @Test
    public void delKeyTest() {
        redisUtil.set("name", "shiyanlou");
        redisUtil.del("name");
        Assert.assertNull(redisUtil.get("name"));
    }

    @Test
    public void updateTest() {
        redisUtil.set("old", "old");
        Assert.assertEquals(redisUtil.getSet("old", "new"), "old");
        Assert.assertEquals(redisUtil.get("old"), "new");
        redisUtil.del("old");
    }

    @Test
    public void expireTest() {
        redisUtil.set("expireTest", "expireTest");
        redisUtil.expire("expireTest", 10000, TimeUnit.MILLISECONDS);
        Assert.assertTrue(redisUtil.ttl("expireTest", TimeUnit.MILLISECONDS) < 10000);
        redisUtil.del("expireTest");
    }

    @Test
    public void hgetAndHsetTest() {
        redisUtil.hset("shi", "yan", "lou");
        Assert.assertEquals(redisUtil.hget("shi", "yan"), "lou");
        redisUtil.del("shi");
    }
}
