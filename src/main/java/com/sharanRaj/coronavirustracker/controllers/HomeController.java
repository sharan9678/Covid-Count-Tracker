package com.sharanRaj.coronavirustracker.controllers;

import com.sharanRaj.coronavirustracker.models.LocationStatistics;
import com.sharanRaj.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStatistics> allStats = coronaVirusDataService.getAllStats();
        long totalReportedCases = allStats.stream().mapToInt(LocationStatistics::getLatestTotalCases).sum();
        long totalNewCases = allStats.stream().mapToInt(LocationStatistics::getDiffFromPrevDay).sum();
        model.addAttribute("locationStatistics", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
