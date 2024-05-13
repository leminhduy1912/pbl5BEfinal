package com.pbl5.helpers;

import com.pbl5.configs.EnvConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;


public class SaveFile {
    private static final String UPLOAD_DIRECTORY = "public";
    private static final Logger logger = Logger.getLogger("SaveFile");
    public static String save(HttpServletRequest request, String folder) {

        Part filePart = null;

        try {
            System.out.println("saving file");
            filePart = request.getPart("image");

            String fileName = filePart.getSubmittedFileName();

            String id = IDGeneration.generate();
            String[] fileNameSplits = fileName.split("\\.");
            int extensionIndex = fileNameSplits.length - 1;
            InputStream inputStream = filePart.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String path = "/" + id + "." + fileNameSplits[extensionIndex];
            File file = new File(EnvConfig.load().get("IMAGE_FOLDER") + "\\", id + "." + fileNameSplits[extensionIndex]);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
            logger.info("saved image");
            return path;

        } catch (IOException | ServletException e) {
            logger.info("saved image failed");
            throw new RuntimeException(e);

        }
    }
    public static boolean delete(String filename){
        String imagePath = EnvConfig.load().get("IMAGE_FOLDER") + "/" + filename; // imageName là tên của ảnh cần xóa
        File imageFile = new File(imagePath);
        System.out.println("delete file");

        if (imageFile.exists()) {
            if (imageFile.delete()) {
                logger.info("deleted image ");
                return true;
            } else {
                logger.info("deleted image failed");
                return false;
            }
        } else {
            logger.info("image not exist");
            return false;
        }
    }
}
