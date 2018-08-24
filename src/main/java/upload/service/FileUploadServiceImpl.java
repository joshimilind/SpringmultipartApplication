package upload.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import upload.domain.UploadEntity;

@Service
public class FileUploadServiceImpl implements FileUploadService {

  @Override
  public String doUpload(HttpServletRequest request, Model model, UploadEntity uploadEntity) {
    String description = uploadEntity.getDescription();
    System.out.println("Description: " + description);
    String uploadRootPath = request.getServletContext().getRealPath("UploadedFiles");
    System.out.println("uploaded to = " + uploadRootPath);
    File uploadRootDir = new File(uploadRootPath);
    if (!uploadRootDir.exists()) {
      uploadRootDir.mkdirs();
    }
    MultipartFile[] fileDatas = uploadEntity.getFileDatas();
    List<File> uploadedFiles = new ArrayList<File>();
    List<String> failedFiles = new ArrayList<String>();

    for (MultipartFile fileData : fileDatas) {

      String name = fileData.getOriginalFilename();
      System.out.println("Client File Name = " + name);

      if (name != null && name.length() > 0) {
        try {
          File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

          BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
          stream.write(fileData.getBytes());
          stream.close();
          uploadedFiles.add(serverFile);
          System.out.println("Write file: " + serverFile);
        } catch (Exception e) {
          System.out.println("Error Write file: " + name);
          failedFiles.add(name);
        }
      }
    }
    model.addAttribute("description", description);
    model.addAttribute("uploadedFiles", uploadedFiles);
    model.addAttribute("failedFiles", failedFiles);
    model.addAttribute("size = "+uploadedFiles.size());
    return "uploadResult";
  }
}
