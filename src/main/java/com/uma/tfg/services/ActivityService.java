package com.uma.tfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.tfg.entities.Activity;
import com.uma.tfg.repositories.ActivityRepository;

@Service
@Transactional
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }
    
    public List<Activity> getActivitiesByUserId(String userId){
    	return activityRepository.getActivitiesByUserId(userId);
    }
}
