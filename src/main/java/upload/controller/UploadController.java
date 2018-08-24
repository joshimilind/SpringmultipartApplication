package upload.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import upload.domain.UploadEntity;
import upload.service.FileUploadService;

@Controller
public class UploadController {

  @Autowired
  FileUploadService fileUploadService;

  @RequestMapping(value = "/")
  public String homePage() {
    return "index";
  }

  @GetMapping("/uploadOneFile")
  public String uploadOneFileHandler(Model model) {
    UploadEntity uploadEntity = new UploadEntity();
    model.addAttribute("uploadEntity", uploadEntity);
    return "uploadOneFile";
  }

  @PostMapping("/uploadOneFile")
  public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model,
      @ModelAttribute("uploadEntity") UploadEntity uploadEntity) {
    return this.fileUploadService.doUpload(request, model, uploadEntity);
  }

  @GetMapping("/uploadMultiFile")
  public String uploadMultiFileHandler(Model model) {
    UploadEntity uploadEntity = new UploadEntity();
    model.addAttribute("uploadEntity", uploadEntity);
    return "uploadMultiFile";
  }

  @PostMapping("/uploadMultiFile")
  public String uploadMultiFileHandlerPOST(HttpServletRequest request,
      Model model, @ModelAttribute("uploadEntity") UploadEntity uploadEntity) {
    return this.fileUploadService.doUpload(request, model, uploadEntity);
  }
}