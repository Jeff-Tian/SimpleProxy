public class FileNameExpert {
    public static String getFileFullNameFromUrl(String url) throws Exception {
        Url parsed = Url.parse(url);
        String mirrored = "/mirror/" + parsed.host + parsed.pathname;

        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            return convertToWindowsPath(mirrored);
        }

        return mirrored;
    }

    public static String convertToWindowsPath(String path) {
        return "C:" + path.replace('/', '\\');
    }
}