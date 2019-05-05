package cn.sf.qrcode.document.domain;

/**
 * 
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/22
 */
public class DocumentVO {

    private Long id;
    
    private String path;
    
    private String minPath;
    
    private String name;
    
    private Long objectId;

    public DocumentVO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMinPath() {
        return minPath;
    }

    public void setMinPath(String minPath) {
        this.minPath = minPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "DocumentVO [id=" + id + ", path=" + path + ", minPath=" + minPath + ", name=" + name
            + ", objectId=" + objectId + "]";
    }

    
}
