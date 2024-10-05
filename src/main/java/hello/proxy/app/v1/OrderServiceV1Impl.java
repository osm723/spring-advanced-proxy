package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1{

    private final OrderRepositoryV1 repository;

    public OrderServiceV1Impl(OrderRepositoryV1 repositoryV1) {
        this.repository = repositoryV1;
    }

    @Override
    public void orderItem(String itemId) {
        repository.save(itemId);
    }
}
