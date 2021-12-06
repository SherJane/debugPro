package jwc.debug_pro.tomcatregex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TomcatRegexTest {
	private static final Pattern PATH_PATTERN = Pattern.compile("(\".*?\")|(([^,])*)");

	public static void main(String[] args) {
		String value="D:\\apache-tomcat-7.0.57/lib,D:\\apache-tomcat-7.0.57/lib/*.jar,D:\\apache-tomcat-7.0.57/lib,D:\\apache-tomcat-7.0.57/lib/*.jar";
		String[] results = getPaths(value);
		for(String s : results) {
			System.out.println(s);
		}
	}
	
	protected static String[] getPaths(String value) {

        List<String> result = new ArrayList<>();
        Matcher matcher = PATH_PATTERN.matcher(value);

        while (matcher.find()) {
            String path = value.substring(matcher.start(), matcher.end());

            path = path.trim();
            if (path.length() == 0) {
                continue;
            }

            char first = path.charAt(0);
            char last = path.charAt(path.length() - 1);

            if (first == '"' && last == '"' && path.length() > 1) {
                path = path.substring(1, path.length() - 1);
                path = path.trim();
                if (path.length() == 0) {
                    continue;
                }
            } else if (path.contains("\"")) {
                // Unbalanced quotes
                // Too early to use standard i18n support. The class path hasn't
                // been configured.
                throw new IllegalArgumentException(
                        "The double quote [\"] character only be used to quote paths. It must " +
                        "not appear in a path. This loader path is not valid: [" + value + "]");
            } else {
                // Not quoted - NO-OP
            }

            result.add(path);
        }
        return result.toArray(new String[result.size()]);
    }

}
