 package cn.sf.qrcode.user.domain;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
public class UserDTO {

    private String openid;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 手机号
     */
    private String phone;
    
    
    
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserDTO(String openid) {
        super();
        this.openid = openid;
    }

    public UserDTO() {
        super();
    }
    
    
    
}
