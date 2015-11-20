package team.ideart.shiguang.server.persist.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */

@Entity
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;  //用户

    private String label;    //内容

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "post_label", joinColumns = {
            @JoinColumn(name = "label_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    })
    private List<Post> postList;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
