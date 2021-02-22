package ex.shop.service;

import ex.shop.model.service.ItemServiceModel;
import ex.shop.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    void addItem(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel findById(String id);

    void delete(String id);
}
