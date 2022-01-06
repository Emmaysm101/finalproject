package org.perscholas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perscholas.model.Items;
import org.perscholas.repository.ItemsRepository;
import org.perscholas.service.ItemsServiceImplement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ItemsServiceTest {
    @Mock
    ItemsRepository itemsRepository;

    @InjectMocks
    private ItemsServiceImplement itemsServiceImplement;

    @BeforeEach
    void setUp() {
        Items items = Items.builder()
                .itemId(999L)
                .itemName("hello")
                .itemPrice(65.0)
                .image("").build();

        Mockito.when(itemsRepository.findById(999L)).thenReturn(Optional.of(items));
    }

    @Test
    @DisplayName("Get Data Based on Item ID")
    public void testGetItemById() {
        Long itemId = 999L;
        Items found = itemsServiceImplement.getItemById(itemId);

        assertEquals(itemId, found.getItemId());

    }
}
