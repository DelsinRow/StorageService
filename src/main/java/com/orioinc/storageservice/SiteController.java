package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SiteController {
    private final MainController mainController;

    public SiteController(MainController mainController) {
        this.mainController = mainController;
    }

    @GetMapping("/getAll")
    public String getAllDocuments(Model model) {
        List<DataText> allData = mainController.getAllDocument();
        model.addAttribute("allData", allData);
        return "databaseAdmin";
    }

}
