package com.uma.tfg.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.uma.tfg.entities.Activity;
import com.uma.tfg.services.ActivityService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ActivityController {

    private final ActivityService activityService;
    
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    
    @GetMapping("/activities/{userId}")
    public List<Activity> getActivitiesByUserId(@PathVariable String userId) throws Exception {
    	System.out.println(userId);
        return activityService.getActivitiesByUserId(userId);
    }

}
