public class Url {
    public String protocol;
    public String host;
    public String pathname;
    public Boolean ssl;

    private static String token;
    private static char c;
    private static String state;
    private static int index;

    private Url() {

    }

    private static String getStates() {
        return "token = " + token + "; c = " + c + "; index = " + index + "; state = " + state + ".";
    }

    public static Url parse(String url) throws Exception {
        index = 0;
        token = "";
        state = "protocol";

        Url parsed = new Url();

        Url.parseProtocol(parsed, url);
        Url.parseHost(parsed, url);
        Url.parsePathname(parsed, url);

        return parsed;
    }

    private static void gotoHostState(Url parsed) {
        parsed.protocol = token;
        token = "";

        state = "host";
    }

    private static void gotoPathState(Url parsed) {
        parsed.host = token;
        token = "";

        state = "path";
    }

    private static void gotoEndState(Url parsed) {
        parsed.pathname = token;
        token = "";
        state = "end";
    }

    private static void eatToken() {
        token += c;
        index++;
    }

    private static void parseProtocol(Url parsed, String url) throws Exception {
        while (state == "protocol") {
            c = url.charAt(index);

            if (index == 0 && c == 'h') {
                eatToken();

                continue;
            }

            if ((index == 1 || index == 2) && c == 't') {
                eatToken();

                continue;
            }

            if (index == 3 && c == 'p') {
                eatToken();

                continue;
            }

            if (index == 4 && c == ':') {
                eatToken();

                parsed.ssl = false;

                continue;
            }

            if (index == 4 && c == 's') {
                eatToken();

                parsed.ssl = true;

                continue;
            }

            if (index == 5 && c == '/' && !parsed.ssl) {
                eatToken();

                continue;
            }

            if (index == 6 && c == '/' && !parsed.ssl) {
                eatToken();
                gotoHostState(parsed);

                continue;
            }

            if (index == 5 && c == ':' && parsed.ssl) {
                eatToken();

                continue;
            }

            if (index == 6 && c == '/' && parsed.ssl) {
                eatToken();

                continue;
            }

            if (index == 7 && c == '/' && parsed.ssl) {
                eatToken();
                gotoHostState(parsed);

                continue;
            }

            throw new Exception("parseProtocol 时出错！" + Url.getStates());
        }
    }

    private static void parseHost(Url parsed, String url) {
        c = url.charAt(index);

        while (index < url.length() && state == "host") {
            eatToken();

            if (url.charAt(index) != '/') {
                c = url.charAt(index);
            } else {
                gotoPathState(parsed);
            }
        }
    }

    private static void parsePathname(Url parsed, String url) {
        while (index < url.length() && state == "path") {
            c = url.charAt(index);
            eatToken();
        }

        gotoEndState(parsed);
    }
}