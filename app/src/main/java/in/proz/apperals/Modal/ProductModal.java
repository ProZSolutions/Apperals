package in.proz.apperals.Modal;

public class ProductModal {

    private int imageResource;
    private String name;
    private String colors;
    private String price;
    private String originalPrice;
    private String discount;

    public ProductModal(int imageResource, String name, String colors, String price, String originalPrice, String discount) {
        this.imageResource = imageResource;
        this.name = name;
        this.colors = colors;
        this.price = price;
        this.originalPrice = originalPrice;
        this.discount = discount;
    }
    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getColors() {
        return colors;
    }

    public String getPrice() {
        return price;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getDiscount() {
        return discount;
    }
}
