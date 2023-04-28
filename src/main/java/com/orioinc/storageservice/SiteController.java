package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SiteController {
    private final DataControlService dataControlService;

    public SiteController(DataControlService dataControlService) {
        this.dataControlService = dataControlService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<DataText> allData = dataControlService.getAllDocument();
        model.addAttribute("allData", allData);
        return "index";
    }

    @GetMapping("/document/{key}")
    public String getDocument(@PathVariable String key, Model model) {
        String text = dataControlService.getText(key);
        model.addAttribute("document", text);
        return "document";
    }

}
