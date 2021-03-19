package ming.data.transmission.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    int id;
    String name;
    double price;
    int category;
}
