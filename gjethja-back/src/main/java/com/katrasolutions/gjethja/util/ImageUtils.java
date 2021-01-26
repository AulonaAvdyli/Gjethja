package com.katrasolutions.gjethja.util;

import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiUnsupportedMediaFileException;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class ImageUtils {

    private static List<String> imageContent = Collections.unmodifiableList(Arrays.asList("image/png", "image/jpg", "image/jpeg"));

    public static void validateImageContent(String contentType) {
        if (!imageContent.contains(contentType)) {
            throw new RestApiUnsupportedMediaFileException(ExceptionMessage.FORMAT_NOT_SUPPORTED);
        }
    }

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
