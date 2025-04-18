package ahmad.githubUserTracker;

import com.google.gson.annotations.SerializedName;

public class GithubEvents {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("created_at")
    private String createdAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
