package helper;

import java.text.Normalizer;
import java.util.UUID;

public class GenerateSlug {
    public static String toSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String noDiacritics = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        noDiacritics = noDiacritics.replaceAll("Đ", "D").replace("đ", "d");

        noDiacritics = noDiacritics.replaceAll("[^a-zA-Z0-9\\s-]", "");

        noDiacritics = noDiacritics.trim();
        noDiacritics = noDiacritics.toLowerCase();
        noDiacritics = noDiacritics.replaceAll("\\s+", "-");

        String baseSlug = noDiacritics.replaceAll("-+", "-").replaceAll("^-|-$", "");
        return baseSlug + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
