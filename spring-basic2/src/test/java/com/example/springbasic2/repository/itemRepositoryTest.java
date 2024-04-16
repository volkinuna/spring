package com.example.springbasic2.repository;

import com.example.springbasic2.constant.ItemSellStatus;
import com.example.springbasic2.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest //테스트용 클래스임을 나타내는 어노테이션
@TestPropertySource(locations = "classpath:application-test.properties")
public class itemRepositoryTest {

    //ItemRepository itemRepository = new ItemRepositoryImpl();
    @Autowired
    ItemRepository itemRepository;

    //item 테이블에 insert
    @Test //테스트용 junit 메소드로 지정
    @DisplayName("상품 저장 테스트") //테스트 코드 실행시 테스트명을 지정해준다.
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        //save는 insert한 엔티티 객체를 그대로 return 해 준다.
        Item saverItem = itemRepository.save(item); //insert
        System.out.println("insert한 엔티티 객체 : " + saverItem);
    }
}
