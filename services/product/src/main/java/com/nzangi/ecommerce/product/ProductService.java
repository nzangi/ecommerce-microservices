package com.nzangi.ecommerce.product;

import com.nzangi.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    // dependency injection
    private final ProductRepository repository;

    private final ProductMapper mapper;
    // create product
    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();

    }

    // purchase product
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = repository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException(
                    "One or More products doesn't exists!"
            );
        }

        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient products with quantity for product id "+productRequest.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchaseProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));

        }

        return purchaseProducts;
    }

    // find product by Id
    public ProductResponse findProductById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product Not Found with id"+productId));
    }

    // Find all the products
    public List<ProductResponse> findAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
