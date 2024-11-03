package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Interface.demoInterface;
import com.example.demo.models.DemoModel;

@Controller
public class demoController {

    @Autowired
    demoInterface di;
    @GetMapping("/")
	public String addInfo()
	{		
		return "Form";
	}	
    @GetMapping("/add")
    public String InsertInfo(DemoModel Dm) {
        try {
            di.save(Dm);
            return "redirect:/display";
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; 
        }
    }

    @GetMapping("/display")
    public String displayInfo(Model model) {	
        List<DemoModel> dis = (List<DemoModel>) di.findAll();
        model.addAttribute("display", dis);
        return "display"; 
    }

    @GetMapping("/Delete/{id}")
    public String deletepublic (@PathVariable Integer id)
	{	
		di.deleteById(id);
		return "redirect:/display";
		
	}
    @GetMapping("/edit/{id}")
    public String editInfo(@PathVariable Integer id, Model model) {
        DemoModel demoData = di.findById(id).orElse(null);
        if (demoData == null) {
            model.addAttribute("error", "No record found with ID: " + id);
            return "error"; 
        }
        model.addAttribute("form_info", demoData);
        return "edit"; 
    }

    @PostMapping("/edit")
    public String updateInfo(DemoModel Dm) {
        try {
            DemoModel existingRecord = di.findById(Dm.getId()).orElse(null);
            if (existingRecord != null) {
                existingRecord.setName(Dm.getName());
                existingRecord.setEmail(Dm.getEmail());
                existingRecord.setMSD(Dm.getMSD());
                di.save(existingRecord);
            }
            return "redirect:/display"; 
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; 
        }
    }
}
