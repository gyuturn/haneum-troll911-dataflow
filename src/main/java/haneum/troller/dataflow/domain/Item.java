package haneum.troller.dataflow.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "item")
public class Item {
    @Id
    private Integer itemNumber;
    private String item;
    private String itemImg;


    @Builder
    public Item(Integer itemNumber, String item, String itemImg) {
        this.itemNumber = itemNumber;
        this.item = item;
        this.itemImg = itemImg;
    }
}
