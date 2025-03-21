package com.rest.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsResponse {
  private Long id;
  private String name;
  private Long userId;

  // Builder
  public static DocumentsResponseBuilder builder() {
    return new DocumentsResponseBuilder();
  }

  public static class DocumentsResponseBuilder {
    private Long id;
    private String name;

    public DocumentsResponseBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public DocumentsResponseBuilder name(String name) {
      this.name = name;
      return this;
    }

    public DocumentsResponseBuilder userId(Long userId) {
      this.userId = userId;
      return this;
    }

    public DocumentsResponse build() {
      DocumentsResponse response = new DocumentsResponse();
      response.setId(this.id);
      response.setName(this.name);
      response.setUserId(this.userId);
      return response;
    }
  }
}
