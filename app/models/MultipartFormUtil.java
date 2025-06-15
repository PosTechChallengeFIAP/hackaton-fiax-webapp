package models;

import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class MultipartFormUtil {

    public static HttpEntity buildMultipartBody(File file, String fieldName)  {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        builder.addBinaryBody(fieldName, file, ContentType.DEFAULT_BINARY, file.getName());

        return builder.build();
    }

    public static byte[] getBytes(HttpEntity entity) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        entity.writeTo(baos);

        return baos.toByteArray();
    }

    public static String getContentType(HttpEntity entity) {
        return entity.getContentType();
    }
}