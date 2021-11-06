package com.dataart.coreservice.service;

import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.exception.IncorrectFileContentException;
import com.dataart.coreservice.exception.IncorrectZipFileContentException;
import com.dataart.coreservice.exception.TypeFileException;
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

@Service
public class ZipFileService {

    final private String fileBaseName = "src/main/resources/zip/";

    public void isCorrect (ZipFile zipFile) {
        try{
            int countFiles = 0;
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                countFiles++;
                if(countFiles > 1) {
                    throw new IncorrectZipFileContentException("ZipFile must contain only one file");
                }

                final ZipEntry zipEntry = entries.nextElement();
                InputStream input = zipFile.getInputStream(zipEntry);

                BufferedReader bf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

                int countLines = 0;
                while (bf.readLine()!=null) {
                    countLines++;
                }
                //TODO вынести Большие типы
                if(countLines < 2) {
                    throw new IncorrectFileContentException("Content in file must be more than 1 line");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isZip(String file) {
        String type = file.substring(file.length() - 4);
        if(!type.equals(".zip")) {
            throw  new TypeFileException("The type of the file must be .zip");
        }
        return true;
    }

    public Article getArticleFromMultipartFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        isZip(fileName);
        Path path = Paths.get(fileBaseName + fileName);
        try{
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            e.printStackTrace();
        }
        try{
            ZipFile zipFile = new ZipFile(String.valueOf(path));
            Article a = getArticleFromZip(zipFile);
            zipFile.close();
            File fileToDelete = new File(String.valueOf(path));
            fileToDelete.delete();
            return a;
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
            StringBuilder body = new StringBuilder();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = entries.nextElement();
                InputStream input = zipFile.getInputStream(zipEntry);

                BufferedReader bf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

                title.append(bf.readLine());
                String line;
                while ((line = bf.readLine()) != null) {
                    body.append(line);
                }
            }
            return new Article(body.toString(), title.toString());

        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
