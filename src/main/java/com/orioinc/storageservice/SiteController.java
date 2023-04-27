package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SiteController {
    private final DataControlService dataControlService;

    public SiteController(DataControlService dataControlService) {
        this.dataControlService = dataControlService;
    }

    @GetMapping("/")
    public String getAllDocuments(Model model) {
        List<DataText> allData = dataControlService.getAllDocument();
        String link = "/" + allData;
        model.addAttribute("allData", allData);
        model.addAttribute("urls", "/key");
        model.addAttribute("linkText", "Text");
        return "databaseAdmin";
    }
}
