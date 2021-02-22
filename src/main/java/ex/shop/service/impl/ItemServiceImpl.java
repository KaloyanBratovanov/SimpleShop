package ex.shop.service.impl;

import ex.shop.model.entity.Item;
import ex.shop.model.service.ItemServiceModel;
import ex.shop.model.view.ItemViewModel;
import ex.shop.repository.ItemRepository;
import ex.shop.service.CategoryService;
import ex.shop.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {


        Item item = this.modelMapper.map(itemServiceModel, Item.class);

        item.setCategory(this.categoryService
                .find(itemServiceModel.getCategory().getCategoryName()));

        this.itemRepository.saveAndFlush(item);
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return this.itemRepository.findAll()
                .stream()
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper
                            .map(item, ItemViewModel.class);

                    itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender(),
                            item.getCategory().getCategoryName().name()));
                    return itemViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findById(String id) {

        return this.itemRepository.findById(id)
                .map(
                        item -> {
                            ItemViewModel itemViewModel = this.modelMapper
                                    .map(item, ItemViewModel.class);

                            itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender(),
                                    item.getCategory().getCategoryName().name()));
                            return itemViewModel;
                        }
                ).orElse(null);

    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }
}
