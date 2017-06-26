package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller used with statistics page
 * Created by Aleksander on 2017-06-24.
 */
@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private static final String STATISTICS_PAGE_NAME = "statistics";

    @GetMapping
    public String showCarStatisticsPage(@RequestParam(name = "id") String id,
                                        Model model){
        model.addAttribute("cost",135);
        return STATISTICS_PAGE_NAME;
    }
}
