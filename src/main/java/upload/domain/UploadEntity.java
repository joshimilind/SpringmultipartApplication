package upload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadEntity {

  private String description;
  private MultipartFile[] fileDatas;
}