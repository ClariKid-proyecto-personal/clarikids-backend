package com.clarikids.clarikids_backend.utils;

import java.text.Normalizer;

public class TextUtils {
    public static String normalize(String text) {
        return Normalizer
                .normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }
}