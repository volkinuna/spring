package com.example.springbasic2.repository;

import com.example.springbasic2.constant.ItemSellStatus;
import com.example.springbasic2.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

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

    //데이터 10개 저장
    public void createTestList() {
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());

            Item saverItem = itemRepository.save(item); //insert
        }
    } 
    
    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNmTest() {
        //데이터 10개 insert
        //createTestList();

        //select * from item where item_nm = '테스트 상품3' 조회
        //find + (엔티티이름(생략가능)) + By + 필드이름(where절에서 검색할 컬럼명)
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");

        //select * from item
        //List<Item> itemList = itemRepository.findAll();

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-1")
    public void quiz1_1Test() {
        //select * from item where item_nm = ? and item_sell_status = ?
        List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-2")
    public void quiz1_2Test() {
        //select * from item where price between ? and ?
        List<Item> itemList = itemRepository.findByPriceBetween(10004, 10008);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-3")
    public void quiz1_3Test() {
        //select * from item where reg_Time > ?
        LocalDateTime regTime = LocalDateTime.of(2023, 1, 1, 12, 12, 44);
        List<Item> itemList = itemRepository.findByRegTimeAfter(regTime);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-4")
    public void quiz1_4Test() {
        //select * from item where item_sell_status is not null
        List<Item> itemList = itemRepository.findByItemSellStatusNotNull();

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-5")
    public void quiz1_5Test() {
        //select * from item where item_detail like '%?'
        List<Item> itemList = itemRepository.findByItemDetailLike("%설명1");

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-6")
    public void quiz1_6Test() {
        //select * from item where item_nm = ? or item_detail = ?
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈1-7")
    public void quiz1_7Test() {
        //select * from item where price < ? order by price desc
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("JPQL @Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세");

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈2-1")
    public void quiz2_1Test() {
        List<Item> itemList = itemRepository.getPrice(10005);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈2-2")
    public void quiz2_2Test() {
        List<Item> itemList = itemRepository.getItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("퀴즈2-3")
    public void quiz2_3Test() {
        List<Item> itemList = itemRepository.getIdSeven(7L);

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("native query")
    public void findByItemDetailByNativeTest() {
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세");

        for (Item item : itemList) {
            System.out.println(item);
        }
    }
}
