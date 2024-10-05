package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {

    private final OrderRepositoryV3 repository;

    public OrderServiceV3(OrderRepositoryV3 repositoryV2) {
        this.repository = repositoryV2;
    }

    public void orderItem(String itemId) {
        repository.save(itemId);
    }
}
