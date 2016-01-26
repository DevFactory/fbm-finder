package com.fbm.finder.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fbm.finder.domain.FacebookMessage;

public class Util {

  public static Set<String> allUsers(List<FacebookMessage> messages) {
    Set<String> users = new HashSet<String>();
    for (FacebookMessage message : messages) {
      users.add(message.getUser());
    }
    return users;
  }

}
