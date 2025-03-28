package com.metacoding.storev2.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private StoreRepository storeRepository;

    public List<Store> 상품목록() {
        List<Store> storeList = storeRepository.findAll();
        return storeList;
    }

    public void 상품등록(StoreRequest.ProductDTO productDTO) {
        storeRepository.save(productDTO.getName(), productDTO.getStock(), productDTO.getPrice());
    }

    public Store 상세보기(int id) {
        Store store = storeRepository.findById(id);
        return store;
    }

    public void 상품삭제(int id) {
        storeRepository.deleteById(id);

    }

    public void 상품수정(int id, StoreRequest.ProductDTO productDTO) {
        storeRepository.updateById(id, productDTO);
    }
}

