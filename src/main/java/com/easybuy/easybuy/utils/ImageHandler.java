package com.easybuy.easybuy.utils;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

public class ImageHandler {

    final static String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/portfolioap-102b7.appspot.com/o/%s?alt=media";

    private static String uploadFile(File file, String fileName) throws IOException {
        System.out.println("aca");
        BlobId blobId = BlobId.of("portfolioap-102b7.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./firebaseConfig.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private static File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String upload(MultipartFile multipartFile, String idName) throws Exception {

        String route = "productsImages/"+idName;

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = idName.concat(getExtension(fileName));
            File file = convertToFile(multipartFile, fileName);   // to convert multipartFile to File
            String pathToUpload = route.concat(getExtension(fileName));
            String url = uploadFile(file, pathToUpload);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }

}
