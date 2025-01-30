package service;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.PropertiesManager;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = PropertiesManager.get("image.base.url");

    public static ImageService getInstance(){return INSTANCE;}

    @SneakyThrows
    public void upload(String imagePath, InputStream content){
        Path imageFullPath = Path.of(basePath, imagePath);
        try(content){
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath,content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<InputStream> getImage(String imagePath){
        Path fullPath = Path.of(basePath, imagePath);

        return Files.exists(fullPath)
                ? Optional.of(Files.newInputStream(fullPath))
                : Optional.empty();
    }

}
