package cn.sf.qrcode.document.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sf.qrcode.common.domain.entity.AbstractSecureObject;

/**
 * 
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/22
 */
@Entity
@Table(name = "document")
public class Document extends AbstractSecureObject {

    private String pathName;
    
    private String name;
    
    private String suffix;
    
    private String minPath;
    
    private Long objectId;

    public Document() {
        super();
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
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


    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "Document [pathName=" + pathName + ", name=" + name + ", suffix=" + suffix
            + ", minPath=" + minPath + ", objectId=" + objectId + "]";
    }
    
}
