package upload.service;

import javax.servlet.http.HttpServletRequest;
import upload.domain.UploadEntity;
import org.springframework.ui.Model;

public interface FileUploadService {

  public String doUpload(HttpServletRequest request, Model model, UploadEntity uploadEntity);
}
