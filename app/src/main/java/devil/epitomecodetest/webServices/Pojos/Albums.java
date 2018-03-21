package devil.epitomecodetest.webServices.Pojos;

/**
 * Created by devil on 3/21/18.
 */

public class Albums {

    private Integer userId;
    private Integer id;
    private String title="";

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
