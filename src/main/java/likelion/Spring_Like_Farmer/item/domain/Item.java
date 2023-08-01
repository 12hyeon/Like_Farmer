package likelion.Spring_Like_Farmer.item.domain;

import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.user.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Item")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @ColumnDefault("0")
    private int gram;

    // builder
    @Builder
    public Item(ItemDto.SaveItem saveItem, User user) {
        this.title = saveItem.getTitle();
        this.gram = saveItem.getGram();
        this.user = user;
    }

}
