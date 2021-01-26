package com.katrasolutions.feedbackservice.util;

import java.util.Base64;

public class ImageUtils {

    public static String convertImageToString(byte[] image) {
        if (image != null) {
            return Base64.getEncoder().encodeToString(image);
        }
        return null;
    }

    public static byte[] convertStringToImage(String image) {
        if (image != null) {
            return Base64.getDecoder().decode(image);
        }
        return null;
    }
}
