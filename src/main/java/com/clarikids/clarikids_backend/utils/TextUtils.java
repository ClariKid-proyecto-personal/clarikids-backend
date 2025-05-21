package com.clarikids.clarikids_backend.utils;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class TextUtils {
    public static String normalize(String input) {
        if (input == null) return "";
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{M}", ""); // quitar tildes
        normalized = normalized.replaceAll("[^a-zA-Z0-9\\s]", ""); // quitar símbolos raros
        return normalized.toLowerCase();
    }

    public static boolean isFuzzyMatch(String a, String b) {
        LevenshteinDistance distance = new LevenshteinDistance();
        int threshold = 3; // permite hasta 3 errores
        return distance.apply(normalize(a), normalize(b)) <= threshold;
    }
}