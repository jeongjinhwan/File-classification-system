import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws Exception {
        String dir = "C:\\Users\\solaa\\.gradle\\caches\\modules-2\\files-2.1\\org.xmlunit\\xmlunit-parent\\2.9.1\\5f2bf29d274cb3f7f3db0e807058520ef03a27c9";

		Matcher matcher = Pattern.compile("([a-zA-Z_0-9.-]+)$").matcher(dir);
        // System.out.println(matcher.group());
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
    }
}
