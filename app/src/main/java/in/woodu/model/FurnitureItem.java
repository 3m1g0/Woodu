package in.woodu.model;

/**
 * Created by KT on 7/28/15.
 */
public class FurnitureItem {

    private String id;

    private String name;

    private String image;

    private String description;

    private String price;

    public FurnitureItem() {
    }

    public FurnitureItem(String id, String name, String image, String description, String price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + ", " + price;
    }
}
