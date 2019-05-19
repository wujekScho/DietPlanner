package pl.wujekscho.dietplanner.dbtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.entity.ProductType;
import pl.wujekscho.dietplanner.repository.ProductRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@Component
public class DBFeeder {
    private ProductRepository productRepository;

    @Autowired
    public DBFeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void importProdutcts() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("imports/products.txt").toURI());
            Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                    .map(s -> s.split(";"))
                    .forEach(s -> {
                        Product product = new Product();
                        product.setName(s[0]);
                        Optional<ProductType> productType = Arrays.stream(ProductType.values())
                                .filter(p -> p.getText().equalsIgnoreCase(s[1].trim()))
                                .findFirst();
                        product.setProductType((productType.isPresent()) ? productType.get() : ProductType.INNE);
                        product.setCalories(Double.valueOf(s[2].replace(",", ".")).intValue());
                        product.setProtein(Double.valueOf(s[3].replace(",", ".")));
                        product.setFat(Double.valueOf(s[4].replace(",", ".")));
                        product.setCarbohydrates(Double.valueOf(s[5].replace(",", ".")));
                        if (s.length == 9) {
                            product.setHomeMeasureType(s[6]);
                            product.setHomeMeasureWeightRadio(Double.valueOf(s[7].replace(",", ".")));
                            product.setHomeMeasureStep(Double.valueOf(s[8].replace(",", ".")));
                        }
                        productRepository.save(product);
                    });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
