package com.izaias.valentim.taskms.feignclients;

import com.izaias.valentim.taskms.models.feignEntities.UsernameResponseFeign;
import com.izaias.valentim.taskms.models.modelsToRequests.Usernames;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Component
@FeignClient(name = "user-ms", path = "/users/")
public interface UserFeignClient {

    @PostMapping(value = "verify/")
    ResponseEntity<Set<UsernameResponseFeign>> verifyListOfUsernames(@RequestBody Usernames usernames);

}
