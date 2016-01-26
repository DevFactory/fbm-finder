package com.fbm.finder.domain;

public class FacebookMessage {

  private String user;
  private String metadata;
  private String message;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "FacebookMessage [user=" + user + ", metadata=" + metadata + ", message=" + message
        + "]";
  }

}
