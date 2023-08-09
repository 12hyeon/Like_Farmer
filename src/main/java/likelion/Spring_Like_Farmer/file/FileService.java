package likelion.Spring_Like_Farmer.file;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class FileService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    private static final String downPath_front = "https://firebasestorage.googleapis.com/v0/b/applepie-f030c.appspot.com/o/";
    private static final String downPath_back = "?alt=media";

    private static final Integer STATUS = 1;


    public String uploadFiles(MultipartFile file, String name) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = file.getInputStream(); // 수정
        Blob blob = bucket.create(name, content, file.getContentType());
        return blob.getMediaLink();
    }

    public String saveFile(Long id, MultipartFile multipartFile, String fileName) {
        try {
            String name = FilenameUtils.getBaseName(multipartFile.getOriginalFilename()) + "-" + fileName + id
                    + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()); // 수정
            String link = uploadFiles(multipartFile, name);
            return downPath_front + name + downPath_back;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
