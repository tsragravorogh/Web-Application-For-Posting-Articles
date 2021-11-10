package com.dataart.coreservice.service;

import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.exception.IncorrectFileContentException;
import com.dataart.coreservice.exception.IncorrectZipFileContentException;
import com.dataart.coreservice.exception.TypeFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Service
public class ZipFileService {

    Logger log = LoggerFactory.getLogger(ZipFileService.class);

    @Value("${upload.path}")
    private String fileBaseName;

    public void isCorrect (ZipFile zipFile) {
        try{
            int countFiles = 0;
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                countFiles++;
                if(countFiles > 1) {
                    log.error("ZipFile must contain only one file");
                    throw new IncorrectZipFileContentException("ZipFile must contain only one file");
                }

                final ZipEntry zipEntry = entries.nextElement();
                InputStream input = zipFile.getInputStream(zipEntry);

                BufferedReader bf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

                int countLines = 0;
                while (bf.readLine()!=null) {
                    countLines++;
                }
                if(countLines < 3) {
                    log.error("Content in file must be more than 2 line. First line - title. Second line - topic. Next lines - body");
                    throw new IncorrectFileContentException("Content in file must be more than 2 line. First line - title. Second line - topic. Next lines - body");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isZip(String file) {
        String type = file.substring(file.length() - 4);
        if(!type.equals(".zip")) {
            log.error("The type of the file must be .zip");
            throw  new TypeFileException("The type of the file must be .zip");
        }
        log.trace("The file has correct format");
        return true;
    }

    public Article getArticleFromMultipartFile(MultipartFile file) {

        isZip(file.getOriginalFilename());
        Path path = Paths.get(fileBaseName, file.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(path)){
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ZipFile zipFile = new ZipFile(fileBaseName+"article.zip");
            Article article = getArticleFromZip(zipFile);
            zipFile.close();
            File fileToDelete = new File(String.valueOf(path));
            fileToDelete.delete();
            return article;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Article getArticleFromZip(ZipFile zipFile) {
        isCorrect(zipFile);
        try{
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            StringBuilder title = new StringBuilder();
            StringBuilder topic = new StringBuilder();
            StringBuilder body = new StringBuilder();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = entries.nextElement();
                InputStream input = zipFile.getInputStream(zipEntry);

                BufferedReader bf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

                title.append(bf.readLine());
                topic.append(bf.readLine());
                String line;
                while ((line = bf.readLine()) != null) {
                    body.append(line);
                }
            }
            return new Article(title.toString(), body.toString(), topic.toString());

        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
