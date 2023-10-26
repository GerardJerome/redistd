import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RedisUtil {
    public static Jedis jedis;
    public static History history= new History();
    private final static String TAG = "JGER";
    public static void connect(){
        jedis = new Jedis("URL BDD");
        history.setMap(jedis.hgetAll(TAG));
    }
    public static void store(String word) {
        history.getMap().put("Element "+ history.getMap().size(),word);
        jedis.hset(TAG,history.getMap());
    }

    public static void getHistory(){
        history.setMap(jedis.hgetAll(TAG));
        for (var entry : history.getMap().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void clearHistory(){
        jedis.del(TAG);
    }
}
