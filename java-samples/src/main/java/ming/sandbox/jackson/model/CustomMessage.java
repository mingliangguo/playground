package ming.sandbox.jackson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage {
  private String id;

  @JsonProperty("connection_id")
  private String connectionId;

  @JsonProperty("space_id")
  private String spaceId;

  @JsonProperty("identity_ext_id")
  private String identityExtId;

  @JsonProperty("identity_id")
  private String identityId;

  @JsonProperty("tags")
  private List<String> tags;

  private List<EmbeddedIdentity> members;

  @JsonProperty("created_at")
  private Long created;

  @JsonProperty("updated_at")
  private Long updated;

  @JsonProperty("created_by")
  private String createdBy;

  @JsonProperty("updated_by")
  private String updatedBy;

  @JsonProperty("version")
  private Long version;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class EmbeddedIdentity {
    @JsonProperty("identity_id")
    private String identityId;

    @JsonProperty("identity_ext_id")
    private String identityExtId;

    @JsonProperty("identity_name")
    private String identityName;

    @JsonProperty("identity_type")
    private String identityType;

    @JsonProperty("identity_email")
    private String identityEmail;

    @JsonProperty("identity_org")
    private String identityOrgId;

    @JsonProperty("identity_team_ids")
    private Set<String> identityTeamIds;

    @JsonProperty("identity_is_blocked")
    private boolean identityIsBlocked;

    @JsonProperty("identity_state")
    private String identityState;

    @JsonProperty("identity_photo_url")
    private String identityPhotoUrl;

    @JsonProperty("identity_created_at")
    private Long identityCreated;

    @JsonProperty("identity_updated_at")
    private Long identityUpdated;

    @JsonProperty("identity_created_by")
    private String identityCreatedBy;

    @JsonProperty("identity_updated_by")
    private String identityUpdatedBy;
  }
}

