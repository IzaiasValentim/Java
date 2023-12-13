package com.izaias.valentim.taskms.models.modelsToRequests;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Usernames implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> usernames;
    public Usernames() {
    }
    public Usernames(List<String> usernames) {
        this.usernames = usernames;
    }
    public List<String> getUsernames() {
        return usernames;
    }
    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
