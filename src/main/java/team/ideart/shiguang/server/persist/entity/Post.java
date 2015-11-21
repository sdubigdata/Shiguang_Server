package team.ideart.shiguang.server.persist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;  //用户

    private String content;    //内容

    @Temporal(TemporalType.DATE)
    private Date date;    //时间

    private String weather;      //天气

    private int color;    //颜色

    private int contPosX;

    private int contPoxY;

    private String path;

    @JsonIgnore
    @ManyToMany(mappedBy = "postList")
    private List<Label> labelList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getContPosX() {
        return contPosX;
    }

    public void setContPosX(int contPosX) {
        this.contPosX = contPosX;
    }

    public int getContPoxY() {
        return contPoxY;
    }

    public void setContPoxY(int contPoxY) {
        this.contPoxY = contPoxY;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }
}
