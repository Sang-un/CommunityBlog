package com.sanbon.backend.model.post.board;

import com.sanbon.backend.model.enumclass.BulletinStatus;
import com.sanbon.backend.model.enumclass.FirstCategory;
import com.sanbon.backend.model.enumclass.SecondCategory;
import com.sanbon.backend.model.post.Bulletin;
import com.sanbon.backend.model.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
//@ToString(exclude = {"commentList"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(chain = true)
@DiscriminatorValue("board")
public class Board extends Bulletin {

    @ColumnDefault("0")
    private Long likes;

    @Enumerated(EnumType.STRING)
    private SecondCategory category;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "board")
//    private List<Comment> commentList;

    public Board(Long id, String title, String writer, String content, BulletinStatus status, Long views,
                 FirstCategory firstCategory, SecondCategory secondCategory, User user, Long likes) {
        super(id, title, writer, content, status, views, firstCategory, secondCategory, user);
        this.likes = likes;
        this.category = secondCategory;

    }
}
