
import java.util.HashMap;
import java.util.Random;

public class URLShortener {
    // Map to store short and long URLs
    private HashMap<String, String> shortToLongMap = new HashMap<>();
    private HashMap<String, String> longToShortMap = new HashMap<>();
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private static final String BASE_URL = "http://short.url/";

    // Method to generate a random short URL
    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            shortURL.append(CHARACTERS.charAt(index));
        }
        return shortURL.toString();
    }

    // Method to shorten a URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return BASE_URL + longToShortMap.get(longURL);
        }

        String shortURL;
        do {
            shortURL = generateShortURL();
        } while (shortToLongMap.containsKey(shortURL));

        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);
        return BASE_URL + shortURL;
    }

    // Method to get the original URL from the short URL
    public String expandURL(String shortURL) {
        String key = shortURL.replace(BASE_URL, "");
        return shortToLongMap.getOrDefault(key, "URL not found");
    }

    // Main method for testing
    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        
        String originalURL = "https://www.example.com/very/long/url";
        System.out.println("Original URL: " + originalURL);
        
        // Shorten URL
        String shortURL = urlShortener.shortenURL(originalURL);
        System.out.println("Shortened URL: " + shortURL);

        // Expand URL
        String expandedURL = urlShortener.expandURL(shortURL);
        System.out.println("Expanded URL: " + expandedURL);
    }
}
