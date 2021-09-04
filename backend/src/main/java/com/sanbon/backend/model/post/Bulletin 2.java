package com.sanbon.backend.model.post;

import com.sanbon.backend.model.BaseTimeEntity;
import com.sanbon.backend.model.enumclass.BulletinStatus;
import com.sanbon.backend.model.enumclass.FirstCategory;
import com.sanbon.backend.model.enumclass.SecondCategory;
import com.sanbon.backend.model.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"user"})
@Entity
public class Bulletin extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String writer;

    private String content;

    @Enumerated(EnumType.STRING)
    private BulletinStatus status;

    @ColumnDefault("0")
    private Long views;

    @Enumerated(EnumType.STRING)
    private FirstCategory firstCategory;

    @Enumerated(EnumType.STRING)
    private SecondCategory secondCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // user id


}
