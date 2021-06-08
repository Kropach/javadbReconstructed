package KR.Controllers;

import KR.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

    private MainService mainService;
    @Autowired
    public MvcController(MainService mainService) {
        this.mainService = mainService;
    }
}