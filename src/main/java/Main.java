import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;

import java.io.IOException;

public class Main {

    private StateEnum state;
    public static void main(String[] args) throws IOException {
        /*Jedis jedis = new Jedis("redis://groupeTP:Legroupe01!@redis-18974.c304.europe-west1-2.gce.cloud.redislabs.com:18974");
        Connection connection = jedis.getConnection();
        jedis.set("test","test");
        System.out.println(jedis.get("test"));*/
        RedisUtil.connect();
        ConsoleUtil.state = StateEnum.MENU;
        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter("describe", "create"))
                .build();
        ConsoleUtil.generateOutput();

        while (true) {
            String line = reader.readLine("> ");
            if (line == null || line.equalsIgnoreCase("exit")) {
                break;
            }
            if (ConsoleUtil.state == StateEnum.MENU) {
                if (line.contains("1")) {
                    ConsoleUtil.state = StateEnum.SEARCH;
                    ConsoleUtil.generateOutput();
                } else if (line.contains("2")) {
                    ConsoleUtil.state = StateEnum.HISTORY;
                    ConsoleUtil.generateOutput();
                } else if (line.contains("3")) {
                    ConsoleUtil.clearHistory();
                }
            } else if (ConsoleUtil.state == StateEnum.SEARCH) {
                if (line.contains("0")) {
                    ConsoleUtil.state = StateEnum.MENU;
                    ConsoleUtil.generateOutput();
                } else {
                    System.out.println(SearchUtil.makeSearch(line.replace("> ", "")));
                }
            }
        }
    }


}
